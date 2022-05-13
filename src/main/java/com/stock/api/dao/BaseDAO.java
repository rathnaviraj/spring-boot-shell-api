package com.stock.api.dao;

public interface BaseDAO {

    <T> T findById(long id, Class<T> tClass);

    <T> void save(T t);

    <T> void saveOrUpdate(T t);

    <T> void update(T t);

    <T> void delete(T t);
}
