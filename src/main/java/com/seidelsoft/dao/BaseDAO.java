package com.seidelsoft.dao;

import java.util.List;

public abstract class BaseDAO<T> {
    public abstract T getById(Long id);
    public abstract List<T> getAll();
    public abstract T save(T obj);
    public abstract void delete(Long id);
}
