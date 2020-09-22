package com.epam.library.businesslayer.interfaces;

import com.epam.library.exception.DataException;
import com.epam.library.model.EnumRole;
import com.epam.library.model.Role;

public interface IRoleService
{
    Role getRole(int roleId) throws DataException;
    void addRole(EnumRole type);
    void dropRole(int roleId) throws DataException;

}
