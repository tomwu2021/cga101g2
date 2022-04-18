package com.common.exception;

import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCException extends SQLException {

    public JDBCException(String reason) {
        super(reason);
            if (DriverManager.getLogWriter() != null) {
                printStackTrace(DriverManager.getLogWriter());
            }
    }
}
