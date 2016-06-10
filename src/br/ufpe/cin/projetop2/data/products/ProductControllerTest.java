package br.ufpe.cin.projetop2.data.products;

public class ProductControllerTest {
  public static void testSingleton() {
    // Should print constructor called message only once
    ProductController.getInstance();
    ProductController.getInstance();
  }

  public static void main(String[] args) {
    testSingleton();
  }
}
