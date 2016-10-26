package com.apptech.setting.services;

import com.apptech.base.model.DBEndPoint;
import com.apptech.base.tools.DBExecutor;
import com.apptech.base.tools.DBPoolCenter;
import com.apptech.base.tools.GeneralFormator;
import com.google.common.collect.ImmutableMap;
import org.skife.jdbi.v2.DBI;

import java.util.Map;

public class DBRouterServiceImpl implements DBRouterService {

    @Override
    public DBEndPoint fetchEndPoint(String tenantCode, Integer readWrite) {
        DBI dbi = DBPoolCenter.getDBI("xx_tenant", DBPoolCenter.DBType.READ_WRITE);
        String sql = "SELECT * FROM tenant_db WHERE tenant_code = :tenant_code AND readwrite = :readwrite LIMIT 1;";
        final Map<String, Object> configInputParams = ImmutableMap.of("tenant_code", tenantCode, "readwrite", readWrite);
        Map<String, Object> result = DBExecutor.executeScalar(dbi, sql, configInputParams);
        DBEndPoint endPoint = null;
        if (result.size() > 0) {
            String config = (String) result.get("config");
            endPoint = GeneralFormator.fromJsonToType(config, DBEndPoint.class);
        }
        return endPoint;
    }
}
