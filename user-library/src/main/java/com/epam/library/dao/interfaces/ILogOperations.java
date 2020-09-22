package com.epam.library.dao.interfaces;

import com.epam.library.model.Log;

import java.util.List;

public interface ILogOperations extends ICommonOperations<Log>
{
    List<Log> getAllLogs();
}
