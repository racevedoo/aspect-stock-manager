package br.ufpe.cin.projetop2.application.console;

import java.util.Scanner;

public class ConsoleHandler {
	private static final Scanner in = new Scanner(System.in);
	
	public String getConsoleString(String msg) {
		System.out.println(msg);
		String ret = in.nextLine();
		return ret;
	}
	
	public void closeScanner(){
		in.close();
	}
}
