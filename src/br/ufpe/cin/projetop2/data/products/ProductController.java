package br.ufpe.cin.projetop2.data.products;

import br.ufpe.cin.projetop2.annotations.Singleton;
import br.ufpe.cin.projetop2.data.DataModel;
import br.ufpe.cin.projetop2.data.HashMapDataModel;
import br.ufpe.cin.projetop2.exceptions.InvalidStateException;


//TODO(limalucas): Insert exception handling (maybe using aspects)
@Singleton
public final class ProductController {
  DataModel<Product> productModel;

  private ProductController() {
    this.productModel = new HashMapDataModel<Product>();
  }

  public static ProductController getInstance() {
    return new ProductController();
  }

  public void registerNewProduct(String name) {
    Product product = new Product(name);
    productModel.saveData(name, product);
  }

  public void sell(String name, int amount) throws InvalidStateException {
    Product product = productModel.getData(name);
    product.decreaseQuantity(amount);
    productModel.saveData(name, product);
  }

  public void supply(String name, int amount) throws InvalidStateException {
    Product product = productModel.getData(name);
    product.increaseQuantity(amount);
    productModel.saveData(name, product);
  }

  public Product queryProduct(String name) {
    return productModel.getData(name);
  }
}
