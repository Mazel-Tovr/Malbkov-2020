package com.tstu.library.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "log")
@XmlRootElement
public class Log extends Entity
{
    @XmlAttribute
    private long logId;
    @XmlAttribute
    private long userId;
    @XmlAttribute
    private String text;

    public Log(long logId, long userId, String text) {
        this.logId = logId;
        this.userId = userId;
        this.text = text;
    }

    public Log() {
    }

    public Log(long userId, String text) {
        this.logId = logId;
        this.userId = userId;
        this.text = text;
    }
    public long getLogId() {
        return logId;
    }

    public long getUserId() {
        return userId;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "Log{" +
                "logId=" + logId +
                ", userId=" + userId +
                ", text=" + text +
                " }";
    }
}
