/**
 * 
 */
package com.jhonnymontoya.ImapUploader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

/**
 * @author Jhonny Montoya
 *
 */
public class Loader {
	
	private Imap imap;
	
	private List<File> filesToDelete = new ArrayList<File>();
	
	public Loader() {
		imap = new Imap(Config.getServer(), Config.getUser(), Config.getPassword());
		try {
			imap.open();
		} catch (MessagingException e) {
			System.err.println("\nError: When opening connection with IMAP server");
			//e.printStackTrace();
			System.exit(-1);
		}
	}

	public void startLoading() {
		File basePath = new File(Config.getBasePath());
		File[] files = basePath.listFiles();
		this.load(files);
		try {
			imap.close();
		} catch (MessagingException e) {
			System.err.println("\nError: When closing connection with IMAP server");
			e.printStackTrace();
		}
	}

	private void load(File[] files) {
		int cont = 1;
		for(int i = 0; i < files.length; i++) {
			File file = files[i];
			if(file.isFile()) {
				if(file.getName().contains("ToDelete")) continue;
				String fileExt = file.getName().toLowerCase();
				fileExt= fileExt.substring(fileExt.length() - 3, fileExt.length());
				if(fileExt.compareTo("eml") == 0) {
					try {
						InputStream mensaje = new FileInputStream(file);
						this.imap.subirEml(mensaje);
						System.out.println("Uploading: file "+ (cont++) + file.getAbsolutePath());
						if(Config.isDeleteSources()) {
							this.filesToDelete.add(file);
							System.out.println("\tSource file market to delete: " + file.getAbsolutePath());
							if(this.filesToDelete.size() >= Config.getMaxMessagesPerUpload()) {
								this.deleteFiles();
							}							
						}
					} catch (FileNotFoundException e) {
						System.err.println("Error: " + e.getMessage());
						return;
					} catch (MessagingException e) {
						System.err.println("Error: " + e.getMessage());
						return;
					}
				}
			}
			else if(files[i].isDirectory()) {
				//Folder
			}
		}
		if(Config.isDeleteSources()) {
			if(this.filesToDelete.size() >= Config.getMaxMessagesPerUpload()) {
				this.deleteFiles();
			}							
		}
	}

	private void deleteFiles() {
		if(Config.isDeleteSources()) {
			for(File file : this.filesToDelete) {
				file.renameTo(new File(file.getParent() + File.separator + "ToDelete-" + file.getName()));
			}
		}
	}
}
