package com.apptech.base.identity;

public class AuthToken {
    private String fromSource;
    private String apiKey;
    private String signature;

    public AuthToken(String fromSource,String apiKey,String signature){
        this.fromSource=fromSource;
        this.apiKey=apiKey;
        this.signature=signature;
    }

    public String getFromSource() {
        return fromSource;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getSignature() {
        return signature;
    }
}
