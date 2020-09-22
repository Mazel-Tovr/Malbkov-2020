package com.tstu.library.businesslayer.interfaces;

import com.tstu.library.exception.DataException;
import com.tstu.library.model.EnumRole;
import com.tstu.library.model.Role;

public interface IRoleService
{
    Role getRole(int roleId) throws DataException;
    void addRole(EnumRole type);
    void dropRole(int roleId) throws DataException;

}
