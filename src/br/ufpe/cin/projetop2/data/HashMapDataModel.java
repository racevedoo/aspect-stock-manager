package br.ufpe.cin.projetop2.data;

import java.util.HashMap;
import java.util.Map;

// Do NOT use this directly (please access it through the Controller object)
public final class HashMapDataModel <T> implements DataModel<T> {
  private Map<String, T> entries;

  public HashMapDataModel() {
    this.entries = new HashMap<String, T>();
  }

  @Override
  public void saveData(String key, T data) {
    entries.put(key, data);
  }

  @Override
  public T getData(String key) {
    return entries.get(key);
  }
}
