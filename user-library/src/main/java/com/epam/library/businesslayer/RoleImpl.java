package com.epam.library.businesslayer;

import com.epam.library.businesslayer.interfaces.IRoleService;
import com.epam.library.dao.RoleAccesses;
import com.epam.library.dao.interfaces.ICommonOperations;
import com.epam.library.exception.DataException;
import com.epam.library.model.EnumRole;
import com.epam.library.model.Role;
import org.apache.log4j.Logger;

import static com.epam.library.exception.ExceptionsCods.ROLE_DOES_NOT_EXIST;

public class RoleImpl implements IRoleService
{

    private ICommonOperations<Role> roleTable = new RoleAccesses();
    private static final Logger logger = Logger.getLogger("BusinessLayerLogger");

    @Override
    public Role getRole(int roleId) throws DataException {
        Role role =  roleTable.getEntity(roleId);
        if(role == null) throw new DataException("Role with such id doesn't exist",ROLE_DOES_NOT_EXIST);
        logger.info("Role gotten");
        return role;
    }

    @Override
    public void addRole(EnumRole type) {
        logger.info("Role added");
        roleTable.addNewEntity(new Role(roleTable.getMaxIdPlusOne(),type));
    }

    @Override
    public void dropRole(int roleId) throws DataException {
       roleTable.deleteEntity(getRole(roleId));
        logger.info("Role deleted");
    }
}
