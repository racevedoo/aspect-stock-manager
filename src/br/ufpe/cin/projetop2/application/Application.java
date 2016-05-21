package br.ufpe.cin.projetop2.application;

import br.ufpe.cin.projetop2.application.console.login.LoginHandler;

public class Application {

	private static void login() {
		LoginHandler loginHandler = new LoginHandler();
		while(!loginHandler.login()) {
			System.out.println("Login failed. Please try again");
		}
		
		System.out.println("Welcome to our application");
		 
	}
	
	public static void main(String[] args) {
		login();
		 
	}
	
	

}
