package com.tstu.library.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "role")
@XmlRootElement
public class Role extends Entity
{
    @XmlAttribute
    private int roleId;
    @XmlAttribute
    private EnumRole type;

    public Role(int roleId, EnumRole type) {
        this.roleId = roleId;
        this.type = type;
    }

    public Role() {
    }

    public Role(EnumRole type) {
        this.type = type;
    }

    public int getRoleId() {
        return roleId;
    }

    public EnumRole getType() {
        return type;
    }
}
