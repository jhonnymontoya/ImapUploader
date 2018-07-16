/**
 * 
 */
package com.jhonnymontoya.ImapUploader;

import java.util.Properties;

import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

import com.sun.mail.imap.IMAPFolder;

/**
 * @author Jhonny Montoya
 *
 */
public class Imap {

	/**
	 * IMAP folder
	 */
	private IMAPFolder imapFolder;
	
	/**
	 * Store
	 */
	private Store store;
	
	/**
	 * Flag
	 */
	private Flag flag;
	
	private Session session;
	
	/**
	 * Imap Server
	 */
	private String server;
	
	/**
	 * User
	 */
	private String user;
	
	/**
	 * Password
	 */
	private String password;

	public Imap(String server, String user, String password) {
		super();
		this.setServer(server);
		this.setUser(user);
		this.setPassword(password);
	}
	
	public boolean open() throws NoSuchProviderException, MessagingException {
		Properties props = System.getProperties();
		props.setProperty("mail.store.protocol", "imaps");
		this.session = Session.getDefaultInstance(props);
		this.store = this.session.getStore("imaps");
		this.store.connect(this.getServer(), this.getUser(), this.getPassword());
		this.imapFolder = (IMAPFolder) store.getDefaultFolder();
		return true;
	}
	
	public void close() throws MessagingException {
		if(this.imapFolder != null && this.imapFolder.isOpen()) this.imapFolder.close(true);
		if(this.store != null) this.store.close();
	}
	
	public void createFolder(String folderName, String folderPath) {
		
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the server
	 */
	public String getServer() {
		return server;
	}

	/**
	 * @param server the server to set
	 */
	public void setServer(String server) {
		this.server = server;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}
}
