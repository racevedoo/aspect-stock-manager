package br.ufpe.cin.projetop2.application.console;

import java.util.Scanner;

public class ConsoleHandler {
<<<<<<< HEAD
  private static Scanner in = null; 

  public void open() {
    if (in == null) {
      in = new Scanner(System.in);
    }
=======
  private static Scanner in; 

  public void open() {
    in = new Scanner(System.in);
>>>>>>> 9ef4ad4873afee73c4d3cb4bf1e45d587702ed19
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
