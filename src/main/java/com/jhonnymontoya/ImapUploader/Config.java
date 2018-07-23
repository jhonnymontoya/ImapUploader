/**
 * 
 */
package com.jhonnymontoya.ImapUploader;

/**
 * @author Jhonny Montoya
 * Configuration class
 */
public final class Config {
	
	/**
	 * Imap Server
	 */
	private static String server;
	
	/**
	 * User
	 */
	private static String user;
	
	/**
	 * Password
	 */
	private static String password;
	
	/**
	 * Path of the local folders and eml files
	 */
	private static String basePath;
	
	/**
	 * Path and log file name
	 */
	private static String logFileName;
	
	/**
	 * Delete EML sources
	 */
	private static boolean deleteSources = false;
	
	/**
	 * Imap folder
	 */
	private static String imapFolder;
	
	/**
	 * Max messages per upload
	 */
	private static int maxMessagesPerUpload = 50;

	/**
	 * @return the server
	 */
	public static String getServer() {
		return server;
	}

	/**
	 * @param server the server to set
	 */
	public static void setServer(String server) {
		Config.server = server;
	}

	/**
	 * @return the user
	 */
	public static String getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public static void setUser(String user) {
		Config.user = user;
	}

	/**
	 * @return the password
	 */
	public static String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public static void setPassword(String password) {
		Config.password = password;
	}

	/**
	 * @return the basePath
	 */
	public static String getBasePath() {
		return basePath;
	}

	/**
	 * @param basePath the basePath to set
	 */
	public static void setBasePath(String basePath) {
		/*if(basePath.length() > 0) {
			basePath = basePath.charAt(basePath.length() - 1) == File.pathSeparatorChar ? basePath.substring(0,  basePath.length() - 1) : basePath;
		}*/
		Config.basePath = basePath;
	}

	/**
	 * @return the logFileName
	 */
	public static String getLogFileName() {
		return logFileName;
	}

	/**
	 * @param logFileName the logFileName to set
	 */
	public static void setLogFileName(String logFileName) {
		Config.logFileName = logFileName;
	}

	/**
	 * @return the deleteSources
	 */
	public static boolean isDeleteSources() {
		return deleteSources;
	}

	/**
	 * @param deleteSources the deleteSources to set
	 */
	public static void setDeleteSources(boolean deleteSources) {
		Config.deleteSources = deleteSources;
	}

	public static String getImapFolder() {
		return imapFolder;
	}

	public static void setImapFolder(String imapFolder) {
		Config.imapFolder = imapFolder;
	}

	/**
	 * @return the maxMessagesPerUpload
	 */
	public static int getMaxMessagesPerUpload() {
		return maxMessagesPerUpload;
	}

	/**
	 * @param maxMessagesPerUpload the maxMessagesPerUpload to set
	 */
	public static void setMaxMessagesPerUpload(int maxMessagesPerUpload) {
		Config.maxMessagesPerUpload = maxMessagesPerUpload;
	}
}
