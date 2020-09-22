package com.tstu.library.businesslayer.interfaces;

import com.tstu.library.exception.DataException;
import com.tstu.library.model.Log;
import com.tstu.library.model.User;

import java.util.List;

public interface ILogService
{
    List<Log> getAllLogs() throws DataException;
    void addLog(User user, String message);
}
