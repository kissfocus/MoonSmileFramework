package com.apptech.base.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class DBEndPoint {

    @NotNull
    @JsonProperty
    private String jdbcUrl;

    @NotNull
    @JsonProperty
    private String username;

    @NotNull
    @JsonProperty
    private String password;

    @JsonProperty()
    private String poolName;

    @NotNull
    @JsonProperty
    private boolean readOnly;

    @JsonProperty
    private long maxLifetime;

    @JsonProperty
    private int maximumPoolSize;

    @JsonProperty
    private boolean cachePrepStmts;

    @JsonProperty
    private int prepStmtCacheSize;

    @JsonProperty
    private int prepStmtCacheSqlLimit;

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPoolName() {
        return poolName;
    }

    public void setPoolName(String poolName) {
        this.poolName = poolName;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public long getMaxLifetime() {
        return maxLifetime;
    }

    public void setMaxLifetime(long maxLifetime) {
        this.maxLifetime = maxLifetime;
    }

    public int getMaximumPoolSize() {
        return maximumPoolSize;
    }

    public void setMaximumPoolSize(int maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
    }

    public boolean isCachePrepStmts() {
        return cachePrepStmts;
    }

    public void setCachePrepStmts(boolean cachePrepStmts) {
        this.cachePrepStmts = cachePrepStmts;
    }

    public int getPrepStmtCacheSize() {
        return prepStmtCacheSize;
    }

    public void setPrepStmtCacheSize(int prepStmtCacheSize) {
        this.prepStmtCacheSize = prepStmtCacheSize;
    }

    public int getPrepStmtCacheSqlLimit() {
        return prepStmtCacheSqlLimit;
    }

    public void setPrepStmtCacheSqlLimit(int prepStmtCacheSqlLimit) {
        this.prepStmtCacheSqlLimit = prepStmtCacheSqlLimit;
    }
}
