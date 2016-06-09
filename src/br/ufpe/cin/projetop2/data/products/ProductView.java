package br.ufpe.cin.projetop2.data.products;

import br.ufpe.cin.projetop2.actors.Customer;
import br.ufpe.cin.projetop2.data.DataModel;
import br.ufpe.cin.projetop2.data.HashMapDataModel;


//TODO(limalucas): Insert exception handling (maybe using aspects)
public final class ProductView {
  DataModel<Product> productModel;

  private ProductView() {
    this.productModel = new HashMapDataModel<Product>();
  }

  private static ProductView singletonInstance = new ProductView();

  public ProductView getInstance() {
    return singletonInstance;
  }

  public void registerNewProduct(String name) {
    Product product = new Product(name);
    productModel.saveData(name, product);
  }

  public void sell(String name, int amount) {
    Product product = productModel.getData(name);
    product.increaseQuantity(amount);
    productModel.saveData(name, product);
  }

  public void sell(String name, int amount, Customer customer) {
    // TODO(limalucas): do something with customer =P
    sell(name, amount);
  }

  public String queryProduct(String name) {
    return productModel.getData(name).toString();
  }
}
