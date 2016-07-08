package br.ufpe.cin.projetop2.aspects;

import br.ufpe.cin.projetop2.annotations.WrapLogin;
import br.ufpe.cin.projetop2.application.console.login.LoginHandler;
import br.ufpe.cin.projetop2.application.ApplicationController;
import br.ufpe.cin.projetop2.exceptions.PermissionDeniedException;
import br.ufpe.cin.projetop2.exceptions.UserNotLoggedInException;

public aspect LoginAspect {

  String currentUser = null;

  after(String username) returning(boolean loginStatus)
      : execution(boolean LoginHandler.login(String, ..)) && args(username, ..) {
    if (loginStatus) {
      currentUser = username;
    }
  }

  after() returning : execution(void LoginHandler.logout()) {
    currentUser = null;
  }

  pointcut requireLogin() :
      call(* ApplicationController.*(..))
      && !(call(void ApplicationController.login()) || call(* ApplicationController.getInstance()));

  declare soft: UserNotLoggedInException: requireLogin();

  Object around(): requireLogin() {
    try {
      return proceed();
    } catch (UserNotLoggedInException e) {
      System.err.println(e.getMessage());
      LoginHandler loginHandler = LoginHandler.getInstance();
      while (!loginHandler.login()) {
        System.out.println("Login failed. Please try again");
      }
      return proceed();
    }
  }

  before() throws UserNotLoggedInException: requireLogin() {
    if (currentUser == null) {
      throw new UserNotLoggedInException("You need to log in to perform this action");
    }
  }

  after() returning(FunctionWithUsername<Void> function) throws PermissionDeniedException : execution(@WrapLogin * *(..)) {
    function.run(currentUser);
  }
}
