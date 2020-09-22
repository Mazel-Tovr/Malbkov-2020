package com.tstu.library.dao.interfaces;

import com.tstu.library.model.User;

public interface IUserOperation extends ICommonOperations<User> {
    boolean isUserExist(String nickName);

    User getUser(String nickName, String password);

    void blockUser(User user);

    void blockUser(long userId);

}
