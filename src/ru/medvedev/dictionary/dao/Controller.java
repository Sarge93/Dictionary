package ru.medvedev.dictionary.dao;

import java.util.List;

/**
 * Created by Сергей on 02.05.2016.
 */
public interface Controller<E, K> {
    public abstract List<E> getAll();
    public abstract boolean update(K id, K field, K value);
    public abstract E getEntityByName(K id);
    public abstract boolean delete(K id);
    public abstract boolean create(E entity);
}
