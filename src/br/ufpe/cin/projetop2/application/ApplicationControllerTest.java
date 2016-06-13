package br.ufpe.cin.projetop2.application;

public class ApplicationControllerTest {
  public static void main(String[] args) {
    ApplicationController ap = ApplicationController.getInstance();

    ap.testPermission();

    ap.registerProduct("prod");
    System.out.println(ap.queryProduct("prod"));
    ap.supply("prod", 30);
    System.out.println(ap.queryProduct("prod"));
    ap.supply("prod", -40);
    System.out.println(ap.queryProduct("prod"));
    ap.sell("prod", -40);
    System.out.println(ap.queryProduct("prod"));

  }
}
