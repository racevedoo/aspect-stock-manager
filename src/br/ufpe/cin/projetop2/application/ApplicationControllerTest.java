package br.ufpe.cin.projetop2.application;

import br.ufpe.cin.projetop2.application.console.login.UserNotLoggedInException;
import br.ufpe.cin.projetop2.data.products.Product;
import br.ufpe.cin.projetop2.data.products.ProductController;
import br.ufpe.cin.projetop2.exceptions.InvalidStateException;

public class ApplicationControllerTest {
  public static void main(String[] args) {    
    ApplicationController ap = new ApplicationController();
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
