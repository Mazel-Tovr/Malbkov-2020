package com.tstu.library.dao.interfaces;

import com.tstu.library.model.Entity;

public interface ICommonOperations<T extends Entity>
{
    T getEntity(long id);
    void addNewEntity(T entity);
    void deleteEntity(T entity);
    void deleteEntity(long id);//TODO pb i should delete this method
    int getMaxIdPlusOne();
}
