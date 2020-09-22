package com.epam.library.businesslayer.interfaces;

import com.epam.library.exception.DataException;
import com.epam.library.model.Log;
import com.epam.library.model.User;

import java.util.List;

public interface ILogService
{
    List<Log> getAllLogs() throws DataException;
    void addLog(User user, String message);
}
