package br.ufpe.cin.projetop2.aspects;


import br.ufpe.cin.projetop2.data.products.ProductController;

public aspect ProductControllerSingleton {
    
  before(): execution(ProductController.new(..)) {
    System.out.println("ProductController constructor called");
  }
  
  ProductController instance = null;
  
  ProductController around(): execution(ProductController ProductController.getInstance(..)) {
    if (instance == null) {
      instance = proceed();
    }
    return instance;
  }
}
