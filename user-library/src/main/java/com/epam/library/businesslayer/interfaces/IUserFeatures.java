package com.epam.library.businesslayer.interfaces;

import com.epam.library.exception.UserException;
import com.epam.library.model.User;

public interface IUserFeatures {
    User login(String nickName, String passWord) throws UserException;

    void toRegister(String firstName, String lastName, String nickName, String password) throws UserException;

}
