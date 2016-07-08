package br.ufpe.cin.projetop2.application.console.login;

import java.util.HashMap;

import br.ufpe.cin.projetop2.annotations.Singleton;
import br.ufpe.cin.projetop2.application.console.ConsoleHandler;

@Singleton
public class LoginHandler extends ConsoleHandler {

	private HashMap<String, String> passwords;
	private LoginHandler() {
		super();
		this.passwords = new HashMap<>();
		this.passwords.put("admin", "admin");
	}

	public static LoginHandler getInstance() {
	  return new LoginHandler();
	}

	public boolean login() {
	    String username = this.getUsername();
	    String password = this.getPassword();
	    return login(username, password);
	}

	public void addUser(String username, String password) {
	    passwords.put(username, password);
	}

	public void logout() {

	}

	public boolean login(String username, String password) {
		String storedPassword = this.passwords.get(username);
		return storedPassword != null && storedPassword.equals(password);
	}

	private String getUsername() {
		return super.getConsoleString("Type in your username");
	}

	private String getPassword() {
		return super.getConsoleString("Type in your password:");
	}
}
