package com.tstu.library.businesslayer.interfaces;

import com.tstu.library.exception.UserException;
import com.tstu.library.model.User;

public interface IAdminFeatures extends IUserFeatures {
    void toRegister(String firstName, String lastName, String nickName, String password, int roleId) throws UserException;

   // List<Log> getAllLogs() throws DataException;

    void blockUser(long userId) throws UserException;
    void blockUser(User user) throws UserException;
}
