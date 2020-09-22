package com.tstu.library.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "user")
@XmlRootElement
public class User extends Entity
{
    @XmlAttribute
    private long userId;
    @XmlAttribute
    private String firstName;
    @XmlAttribute
    private String lastName;
    @XmlAttribute
    private String nickName;
    @XmlAttribute
    private String password;
    @XmlElement(type = Role.class)
    private Role role;
    @XmlAttribute
    private boolean isBlocked;

    public User(String firstName, String lastName, String nickName, String password, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.password = password;
        this.role = role;
    }

    public User(long userId, String firstName, String lastName, String nickName,
                String password, Role role, boolean isBlocked) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.password = password;
        this.role = role;
        this.isBlocked = isBlocked;
    }

    public User() {
    }

    public long getUserId() { return userId; }

    public String getFirstName() { return firstName; }

    public String getLastName() { return lastName; }

    public String getNickName() { return nickName; }

    public String getPassword() { return password; }

    public Role getRole() { return role; }

    public boolean isBlocked() { return isBlocked; }


}
