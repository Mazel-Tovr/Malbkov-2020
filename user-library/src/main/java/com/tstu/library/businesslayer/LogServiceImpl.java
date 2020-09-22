package com.tstu.library.businesslayer;

import com.tstu.library.businesslayer.interfaces.ILogService;
import com.tstu.library.dao.LogAccesses;
import com.tstu.library.dao.interfaces.ILogOperations;
import com.tstu.library.exception.DataException;
import com.tstu.library.model.Log;
import com.tstu.library.model.User;
import org.apache.log4j.Logger;

import java.util.List;

import static com.tstu.library.exception.ExceptionsCods.EMPTY_RESULT;

public class LogServiceImpl implements ILogService
{
    private ILogOperations logTable = new LogAccesses();
    private static final Logger logger = Logger.getLogger("BusinessLayerLogger");

    @Override
    public List<Log> getAllLogs() throws DataException {
        List<Log> logList = logTable.getAllLogs();
        if (logList.isEmpty()) throw new DataException("Logs are absent",EMPTY_RESULT);
        logger.info("Logs gotten");
        return logList;
    }

    @Override
    public void addLog(User user, String message) {
        logger.info("Log added");
        logTable.addNewEntity(new Log(logTable.getMaxIdPlusOne(),user.getUserId(),message));
    }
}
