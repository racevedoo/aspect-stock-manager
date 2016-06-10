package br.ufpe.cin.projetop2.aspects;


import br.ufpe.cin.projetop2.data.products.ProductController;

public aspect ProductControllerConstructorAspect {
    
  before(): execution(ProductController.new(..)) {
    System.out.println("ProductController constructor called");
  }
}
