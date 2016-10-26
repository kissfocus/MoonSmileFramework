package com.apptech.setting.services;

import com.apptech.base.model.DBEndPoint;
import com.google.inject.ImplementedBy;

@ImplementedBy(DBRouterServiceImpl.class)
public interface DBRouterService {
    DBEndPoint fetchEndPoint(String tenantCode, Integer readWrite);
}
