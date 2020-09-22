package com.epam.library.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "EnumRole")
@XmlRootElement
public enum EnumRole
{
    User,Admin
}
