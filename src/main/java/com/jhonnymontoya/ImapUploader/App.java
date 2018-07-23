package com.jhonnymontoya.ImapUploader;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Jhonny Montoya
 *
 */
public class App {
	
	
	/**
	 * Constructor
	 */
	public App() {
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		App app = new App();
		
		if(args.length == 0) {
			app.displayHelp();
			return;
		}
		
		List<String> params = Arrays.asList(args);
		if(params.contains("-h") || params.contains("-H")) {
			app.displayHelp();
			return;
		}
		app.setConfiguration(params);
		Loader loader = new Loader();
		loader.startLoading();
	}
	
	/**
	 * Sets the configuration
	 * @param params
	 */
	private void setConfiguration(List<String> params) {
		Iterator<String> stringIterator = params.iterator();
		while(stringIterator.hasNext()) {
			String param = stringIterator.next().toLowerCase();
			
			switch (param) {
				case "-s":
					try {
						String server = stringIterator.next();
						Config.setServer(server);
					}
					catch (NoSuchElementException e) {
						System.err.println("\nError: You haven't specified an IMAP server.\n");
						this.displayHelp();
						return;
					}
					break;
				case "-u":
					try {
						String user = stringIterator.next();
						Config.setUser(user);
					}
					catch (NoSuchElementException e) {
						System.err.println("\nError: You haven't specified an user.\n");
						this.displayHelp();
						return;
					}
					break;
				case "-p":
					try {
						String password = stringIterator.next();
						Config.setPassword(password);
					}
					catch (NoSuchElementException e) {
						System.err.println("\nError: You haven't specified a password.\n");
						this.displayHelp();
						return;
					}
					break;
				case "-b":
					try {
						String basePath = stringIterator.next();
						Config.setBasePath(basePath);
					}
					catch (NoSuchElementException e) {
						System.err.println("\nError: You haven't specified a base path.\n");
						this.displayHelp();
						return;
					}
					break;
				case "-f":
					try {
						String imapFolder = stringIterator.next();
						Config.setImapFolder(imapFolder);
					}
					catch (NoSuchElementException e) {
						System.err.println("\nError: You haven't specified an Imap folder path.\n");
						this.displayHelp();
						return;
					}
					break;
				case "-l":
					try {
						String logPath = stringIterator.next();
						Config.setLogFileName(logPath);
					}
					catch (NoSuchElementException e) {
						System.err.println("\nError: You haven't specified a log path.\n");
						this.displayHelp();
						return;
					}
					break;
				case "-d":
					Config.setDeleteSources(true);
					break;
				default:
					break;
			}
		}
		if(
				Config.getServer() == null ||
				Config.getUser() == null ||
				Config.getPassword() == null ||
				Config.getBasePath() == null
		) {
			this.displayHelp();
			return;
		}
	}
	
	/**
	 * Displays help
	 */
	private void displayHelp() {
		System.out.println("Uploads recursively folders and EML files to a IMAP server.");
		System.out.println("Author: Jhonny Montoya.");
		System.out.println("Mail: jhonnymontoya@outlook.com");
		System.out.println("Version: 0.0.1 2018-07-15.");
		System.out.println("\n\nUsage:");
		System.out.print("\t-s imapServer ");
		System.out.print("-u user ");
		System.out.print("-p password ");
		System.out.print("-b basePath ");
		System.out.print("[-f IMAP Folder] ");
		System.out.print("[-d] ");
		System.out.print("[-l outputLogFile.log] ");
		System.out.print("[-h]");
		
		System.out.println("\n\n");
		
		System.out.println("\t-s\t:Imap server.");
		System.out.println("\t-u\t:User name.");
		System.out.println("\t-p\t:Password of the user.");
		System.out.println("\t-b\t:Base path contanting folders and EML files.");
		System.out.println("\t-l\t:Path and log file name.");
		System.out.println("\t-f\t:IMAP Folder eg: Inbox/folder.");
		System.out.println("\t-d\t:Delete source EML files.");
		System.out.println("\t-h\t:Displays this help.");
	}

}
