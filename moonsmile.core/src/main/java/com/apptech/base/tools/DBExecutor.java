package com.apptech.base.tools;

import org.skife.jdbi.v2.DBI;

import java.util.List;
import java.util.Map;

public class DBExecutor {

    public static int executeNonQuery(DBI jdbi, String commandSql, Map<String, Object> params) {
        return jdbi.withHandle(handle -> handle.createStatement(commandSql).bindFromMap(params).execute());
    }

    public static Map<String, Object> executeScalar(DBI jdbi, String commandSql, Map<String, Object> params) {
        return jdbi.withHandle(handle -> handle.createQuery(commandSql).bindFromMap(params).first());
    }

    public static <T> T executeScalar(DBI jdbi, String commandSql, Map<String, Object> params, Class<T> resultType) {
        return jdbi.withHandle(handle -> handle.createQuery(commandSql).bindFromMap(params).map(resultType).first());
    }

    public static List<Map<String, Object>> executeQuery(DBI jdbi, String commandSql, Map<String, Object> params) {
        return jdbi.withHandle(handle -> handle.createQuery(commandSql).bindFromMap(params).list());
    }

    public static <T> List<T> executeQuery(DBI jdbi, String commandSql, Map<String, Object> params, Class<T> resultType) {
        return jdbi.withHandle(handle -> handle.createQuery(commandSql).bindFromMap(params).map(resultType).list());
    }

    public static <T> List<T> executeQuery(DBI jdbi, String commandSql, Map<String, Object> params, Class<T> resultType, int maxRows) {
        return jdbi.withHandle(handle -> handle.createQuery(commandSql).bindFromMap(params).map(resultType).list(maxRows));
    }
}
