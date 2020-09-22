package com.epam.library.businesslayer.interfaces;

import com.epam.library.exception.DataException;
import com.epam.library.model.Author;

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
