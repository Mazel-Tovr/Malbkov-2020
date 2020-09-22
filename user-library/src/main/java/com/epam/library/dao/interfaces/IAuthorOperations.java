package com.epam.library.dao.interfaces;

import com.epam.library.model.Author;

import java.util.List;

public interface IAuthorOperations extends ICommonOperations<Author>
{
    List<Author> getAll();
    void deleteAuthorAndAllHisBooks(int authorId);
    Author isAuthorExist(Author author);
    void editing(int authorId , Author author);

}
