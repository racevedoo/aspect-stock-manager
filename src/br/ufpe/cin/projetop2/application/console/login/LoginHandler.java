package br.ufpe.cin.projetop2.application.console.login;

import java.util.HashMap;

import br.ufpe.cin.projetop2.application.console.ConsoleHandler;

public class LoginHandler extends ConsoleHandler {
	
	private HashMap<String, String> passwords;
	public LoginHandler() {
		super();
		this.passwords = new HashMap<>();
		this.passwords.put("admin", "admin");
	}
	
	public boolean login() {
		String username = this.getUsername();
		String password = this.getPassword();
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
