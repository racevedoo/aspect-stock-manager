package br.ufpe.cin.projetop2.aspects;

import java.util.HashMap;
import java.util.Map;

import br.ufpe.cin.projetop2.annotations.Singleton;

public aspect SingletonsAspect {
  Map<Class<?>, Object> instances = new HashMap<>();

  Object around(): execution(* *.getInstance(..)) && within(@Singleton *) {
    Class<?> singletonType = thisJoinPoint.getSignature().getDeclaringType();
    Object instance = instances.get(singletonType);
    if (instance == null) {
      instance = proceed();
      instances.put(singletonType, instance);
    }
    return instance;
  }
}
