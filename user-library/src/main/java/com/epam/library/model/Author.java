package com.epam.library.model;

import javax.xml.bind.annotation.*;
import java.util.Date;

@XmlType(name = "author")
@XmlRootElement
public class Author extends Entity
{
    @XmlAttribute
    private long authorId;
    @XmlAttribute
    private String name;
    @XmlAttribute
    private String secondName;
    @XmlAttribute
    private String lastName;
    @XmlAttribute
    private Date dob;

    public Author() {
    }

    public Author(long authorId, String name, String secondName, String lastName, Date dob) {
        this.authorId = authorId;
        this.name = name;
        this.secondName = secondName;
        this.lastName = lastName;
        this.dob = dob;
    }

    public Author(String name, String secondName, String lastName, Date dob) {
        this.name = name;
        this.secondName = secondName;
        this.lastName = lastName;
        this.dob = dob;
    }

    public long getAuthorId() {
        return authorId;
    }

    public String getName() {
        return name;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDob() {
        return dob;
    }
}
