package com.tstu.library.dao.interfaces;

import com.tstu.library.model.Log;

import java.util.List;

public interface ILogOperations extends ICommonOperations<Log>
{
    List<Log> getAllLogs();
}
