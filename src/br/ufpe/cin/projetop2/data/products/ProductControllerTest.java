package br.ufpe.cin.projetop2.data.products;

import br.ufpe.cin.projetop2.exceptions.InvalidStateException;

public class ProductControllerTest {
  public static void testSingleton() {
	assert ProductController.getInstance().hashCode() == ProductController.getInstance().hashCode();
  }

  public static void main(String[] args) throws InvalidStateException {
    testSingleton();
    
    ProductController.getInstance().registerNewProduct("prod");
    Product p = ProductController.getInstance().queryProduct("prod");
    System.out.println(p.getQuantity());
    ProductController.getInstance().supply("prod", 30);
    p = ProductController.getInstance().queryProduct("prod");
    System.out.println(p.getQuantity());
    ProductController.getInstance().supply("prod", -40);
    p = ProductController.getInstance().queryProduct("prod");
    System.out.println(p.getQuantity());
  }
}
