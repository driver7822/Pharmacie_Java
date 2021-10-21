package be.condorcet.victorlorfevreprojet2.services;

public interface InterfService<T>{
    public T create(T t) throws Exception;
    public T read(Integer id) throws Exception;
    public T update(T t) throws Exception;
    public void delete(T t) throws Exception;
}
