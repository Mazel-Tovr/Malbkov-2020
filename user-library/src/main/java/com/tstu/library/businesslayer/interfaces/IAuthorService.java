package com.tstu.library.businesslayer.interfaces;

import com.tstu.library.exception.DataException;
import com.tstu.library.model.Author;

import java.util.List;

public interface IAuthorService
{
    List<Author> getAll()throws DataException;
    Author getAuthor(int authorId) throws DataException;
    void addAuthor(String name, String secondName, String lastName, String dob) throws DataException;
    void dropAuthorAndAllHisBooks(int authorId) throws DataException;
    Author getByNameSecondNameLastNameDob(String name, String secondName, String lastName, String dob)throws DataException;
    void editing(int authorId , String name, String secondName, String lastName, String dob) throws DataException;
}
