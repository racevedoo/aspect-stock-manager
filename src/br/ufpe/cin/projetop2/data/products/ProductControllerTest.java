package br.ufpe.cin.projetop2.data.products;

import br.ufpe.cin.projetop2.exceptions.InvalidStateException;

public class ProductControllerTest {
  public static void testSingleton() {
	assert ProductController.getInstance().hashCode() == ProductController.getInstance().hashCode();
  }

  public static void main(String[] args) throws InvalidStateException {
    testSingleton();

    ProductController controller = ProductController.getInstance();

    controller.registerNewProduct("prod");
    Product p = controller.queryProduct("prod");
    assert p.getQuantity() == 0;

    controller.supply("prod", 30);
    p = controller.queryProduct("prod");
    assert p.getQuantity() == 30;

    controller.sell("prod", 20);
    p = controller.queryProduct("prod");
    assert p.getQuantity() == 10;

    try {
      controller.sell("prod", 20);
      assert false;
    } catch(InvalidStateException e) {}

    p = controller.queryProduct("prod");
    assert p.getQuantity() == 10;
  }
}
