package org.project2action.config;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GoogleAuthorization {

    @JsonProperty
    private String accessTokenName;
    @JsonProperty
    private String oauth2Url;
    @JsonProperty
    private String clientId;
    @JsonProperty
    private String clientSecret;
    @JsonProperty
    private String redirectUri;
    @JsonProperty
    private String grantType;
    @JsonProperty
    private String userInfoUrl;

    public String getAccessTokenName() {
        return accessTokenName;
    }

    public void setAccessTokenName(String accessTokenName) {
        this.accessTokenName = accessTokenName;
    }

    public void setOauth2Url(String oauth2Url) {
        this.oauth2Url = oauth2Url;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getOauth2Url() {
        return oauth2Url;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public String getGrantType() {
        return grantType;
    }

    public String getUserInfoUrl() {
        return userInfoUrl;
    }

    public void setUserInfoUrl(String userInfoUrl) {
        this.userInfoUrl = userInfoUrl;
    }
}
