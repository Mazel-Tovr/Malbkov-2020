package com.tstu.library.businesslayer.interfaces;

import com.tstu.library.exception.UserException;
import com.tstu.library.model.User;

public interface IUserFeatures {
    User login(String nickName, String passWord) throws UserException;

    void toRegister(String firstName, String lastName, String nickName, String password) throws UserException;

}
