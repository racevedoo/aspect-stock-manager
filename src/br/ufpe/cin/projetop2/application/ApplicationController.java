package br.ufpe.cin.projetop2.application;

import br.ufpe.cin.projetop2.actors.CustomerController;
import br.ufpe.cin.projetop2.actors.EmployeeController;
import br.ufpe.cin.projetop2.annotations.RequireFullPermission;
import br.ufpe.cin.projetop2.annotations.Singleton;
import br.ufpe.cin.projetop2.application.console.login.LoginHandler;
import br.ufpe.cin.projetop2.data.products.Product;
import br.ufpe.cin.projetop2.data.products.ProductController;
import br.ufpe.cin.projetop2.exceptions.InvalidStateException;
import br.ufpe.cin.projetop2.exceptions.PermissionDeniedException;
import br.ufpe.cin.projetop2.exceptions.UserNotLoggedInException;

@Singleton
public final class ApplicationController {

  private CustomerController customerController;
  private EmployeeController employeeController;
  private ProductController productController;

  private ApplicationController() {
    this.customerController = CustomerController.getInstance();
    this.employeeController = EmployeeController.getInstance();
    this.productController = ProductController.getInstance();
  }

  public static ApplicationController getInstance() {
    return new ApplicationController();
  }

  public void registerCustomer(String name, String cpf)
      throws UserNotLoggedInException, InvalidStateException {
    customerController.registerNewCustomer(name, cpf);
  }

  public void registerEmployee(
      String name, String cpf, String username, String password)
          throws UserNotLoggedInException, InvalidStateException {
    employeeController.registerNewEmployee(name, cpf, username, password);
    LoginHandler loginHandler = LoginHandler.getInstance();
    loginHandler.addUser(username, password);
  }

  @RequireFullPermission
  public void registerProduct(String name)
      throws PermissionDeniedException, UserNotLoggedInException, InvalidStateException {
    productController.registerNewProduct(name);
  }

  @RequireFullPermission
  public void sell(String productName, int amount)
      throws InvalidStateException, PermissionDeniedException, UserNotLoggedInException {
    if (productController.queryProduct(productName) == null) {
      System.out.println("Product not found");
      return;
    }

    productController.sell(productName, amount);
  }

  @RequireFullPermission
  public void sell(String productName, int amount, String customerCpf)
      throws InvalidStateException, PermissionDeniedException, UserNotLoggedInException {
    if (productController.queryProduct(productName) == null) {
      System.out.println("Product not found");
      return;
    }

    productController.sell(productName, amount);
    Product product = productController.queryProduct(productName);
    customerController.addPurchase(customerCpf, product);
  }

  @RequireFullPermission
  public void supply(String productName, int amount)
      throws InvalidStateException, PermissionDeniedException, UserNotLoggedInException {
    if (productController.queryProduct(productName) == null) {
      System.out.println("Product not found");
      return;
    }

    productController.supply(productName, amount);
  }

  public String queryProduct(String productName) throws UserNotLoggedInException {
    Product product = productController.queryProduct(productName);
    if (product == null) {
      return "Product not found";
    }
    return product.toString();
  }

  public void login() {
    LoginHandler loginHandler = LoginHandler.getInstance();
    if (loginHandler.login()) {
      System.out.println("Login successful");
    } else {
      System.out.println("Login failed");
    }
  }

  public void logout() throws UserNotLoggedInException {
    LoginHandler loginHandler = LoginHandler.getInstance();
    loginHandler.logout();
  }
}
