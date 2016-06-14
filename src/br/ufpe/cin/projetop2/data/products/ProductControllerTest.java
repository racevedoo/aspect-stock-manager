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
    assert p.getQuantity() == 0;
    ProductController.getInstance().supply("prod", 30);
    p = ProductController.getInstance().queryProduct("prod");
    assert p.getQuantity() == 30;
    try {
      ProductController.getInstance().supply("prod", -40);
      assert false;
    } catch(InvalidStateException e) {
      //passed
    }
    
    p = ProductController.getInstance().queryProduct("prod");
    assert p.getQuantity() == -10;
  }
}
