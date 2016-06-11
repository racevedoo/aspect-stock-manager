package br.ufpe.cin.projetop2.application.console;

import java.util.Scanner;

public class ConsoleHandler {
  private static Scanner in; 

  public void open() {
    in = new Scanner(System.in);
  }
  public String getConsoleString(String msg) {
    System.out.println(msg);
    String ret = in.nextLine();
    return ret;
  }
  
  public int getConsoleInt(String msg) {
    System.out.println(msg);
    int ret = in.nextInt();
    return ret;
  }

  public void close(){
    in.close();
  }
}
