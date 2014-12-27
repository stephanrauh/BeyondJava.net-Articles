package de.beyondjava.di;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;

public class ClassPathScanner {
  private static Map<Class, Class> diMap = new HashMap<>();

  private static Map<Class, Object> applicationScope = new HashMap<>();

  static {
    Reflections reflections = new Reflections("");
    Set<Class<?>> types = reflections.getTypesAnnotatedWith(BeyondService.class);
    for (Class<?> implementationClass : types) {
      for (Class iface : implementationClass.getInterfaces()) {
        diMap.put(iface, implementationClass);
      }
    }
  }

  public Object getBean(Class interfaceClass) {
    Class implementationClass = diMap.get(interfaceClass);
    if (applicationScope.containsKey(interfaceClass)) {
      return applicationScope.get(implementationClass);
    }
    synchronized (applicationScope) {
      Object service;
      try {
        service = implementationClass.newInstance();
        applicationScope.put(implementationClass, service);
        return service;
      } catch (InstantiationException | IllegalAccessException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        return null;
      }
    }
  }
}
