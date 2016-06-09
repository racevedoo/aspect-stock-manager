package br.ufpe.cin.projetop2.data;

public interface DataModel<T> {
  public void saveData(String key, T data);

  public T getData(String key);
}
