package br.ufpe.cin.projetop2.data.products;

public class ProductControllerTest {
  public static void testSingleton() {
	assert ProductController.getInstance().hashCode() == ProductController.getInstance().hashCode();
  }

  public static void main(String[] args) {
    testSingleton();
  }
}
