/**
 * 
 */
package com.jhonnymontoya.ImapUploader;

import java.io.File;

import javax.mail.MessagingException;

/**
 * @author Jhonny Montoya
 *
 */
public class Loader {
	
	private Imap imap;
	
	public Loader() {
		imap = new Imap(Config.getServer(), Config.getUser(), Config.getPassword());
		/*try {
			imap.open();
		} catch (MessagingException e) {
			System.out.println("\nError: When opening connection with IMAP server");
			e.printStackTrace();
		}*/
	}

	public void startLoading() {
		File basePath = new File(Config.getBasePath());
		File[] files = basePath.listFiles();
		this.load(files);
		try {
			imap.close();
		} catch (MessagingException e) {
			System.out.println("\nError: When closing connection with IMAP server");
			e.printStackTrace();
		}
	}

	private void load(File[] files) {
		for(int i = 0; i < files.length; i++) {
			if(files[i].isFile()) {
				//Archivo
			}
			else if(files[i].isDirectory()) {
				System.out.println(files[i].getName());
				this.load(files[i].listFiles());
			}
		}
	}
}
