package br.ufpe.cin.projetop2.aspects;

import br.ufpe.cin.projetop2.annotations.RequireLogin;
import br.ufpe.cin.projetop2.annotations.WrapLogin;
import br.ufpe.cin.projetop2.application.console.login.LoginHandler;
import br.ufpe.cin.projetop2.exceptions.UserNotLoggedInException;

public aspect LoginAspect {

  String currentUser = null;
  
  after(String username) returning(boolean loginStatus) : execution(boolean LoginHandler.login(String, ..)) && args(username, ..) {
    if (loginStatus) {
      currentUser = username;
    }
  }
  
  pointcut requireLogin() : call(@RequireLogin * *(*));
  
  declare soft: UserNotLoggedInException: requireLogin();

  Object around(): requireLogin() {
    try {
      return proceed();
    } catch(UserNotLoggedInException e) {
      System.err.println("You need to log in to perform this action");
      LoginHandler loginHandler = new LoginHandler();
      loginHandler.open();
      while(!loginHandler.login()) {
          System.out.println("Login failed. Please try again");
      }
      loginHandler.close();
      return proceed();
    }
  }
  
  before() throws UserNotLoggedInException: requireLogin() {
    if (currentUser == null) {
      throw new UserNotLoggedInException("User is not logged in");
    }
  }
  
  after() returning(FunctionWithUsername<Void> function) throws Exception : execution(@WrapLogin * *(..)) {
    System.out.println("Here at LoginAspect");
    System.out.println("Current user is " + currentUser);
    function.run(currentUser);
  }
}
