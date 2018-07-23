/**
 * 
 */
package com.jhonnymontoya.ImapUploader;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMessage;

import com.sun.mail.imap.IMAPFolder;

/**
 * @author Jhonny Montoya
 *
 */
public class Imap {
	
	/**
	 * IMAP folder
	 */
	private IMAPFolder imapFolder = null;
	
	/**
	 * Store
	 */
	private Store store;
	
	/**
	 * Session
	 */
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
	
	private List<MimeMessage> mensajes = new ArrayList<MimeMessage>();

	public Imap(String server, String user, String password) {
		super();
		this.setServer(server);
		this.setUser(user);
		this.setPassword(password);
	}
	
	public boolean open() throws NoSuchProviderException, MessagingException {
		Properties props = System.getProperties();
		props.setProperty("mail.store.protocol", "imaps");
		this.session = Session.getDefaultInstance(props, null);
		this.store = this.session.getStore("imaps");
		this.store.connect(this.getServer(), this.getUser(), this.getPassword());
		if(Config.getImapFolder() != null && Config.getImapFolder().length() > 0) {
			this.imapFolder = (IMAPFolder) store.getFolder(Config.getImapFolder());
		}
		else {
			this.imapFolder = (IMAPFolder) store.getFolder("Inbox");
			//this.imapFolder = (IMAPFolder) store.getDefaultFolder();
		}
		this.imapFolder.open(Folder.READ_WRITE);
		return true;
	}
	
	public void close() throws MessagingException {
		this.load();
		if(this.imapFolder != null && this.imapFolder.isOpen()) this.imapFolder.close(true);
		if(this.store != null) this.store.close();
		System.out.println("\n========================\nConnection is closed!");
	}
	
	private boolean isOpen() {
		if(this.imapFolder != null && this.imapFolder.isOpen()) return true;
		return false;
	}
	private void load() throws MessagingException {
		if(this.mensajes.size() == 0) return;
		MimeMessage[] mensajesParaSubir = new MimeMessage[this.mensajes.size()];
		mensajesParaSubir = this.mensajes.toArray(mensajesParaSubir);
		this.imapFolder.addMessages(mensajesParaSubir);
		this.mensajes = new ArrayList<MimeMessage>();
	}

	public boolean subirEml(InputStream mensaje) throws MessagingException {
		if(!this.isOpen()) {
			throw new MessagingException("Connection to IMAP Server is not open");
		}
		MimeMessage msj = new MimeMessage(this.session, mensaje);
		msj.setFlag(Flag.SEEN, true);
		this.mensajes.add(msj);
		if(this.mensajes.size() >= Config.getMaxMessagesPerUpload()) this.load();
		return true;
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
