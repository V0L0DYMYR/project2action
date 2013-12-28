package org.project2action.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class Authorization {

    @JsonProperty
    private GoogleAuthorization google;
    @NotNull
    @JsonProperty
    private String homePage;
    @NotNull
    @JsonProperty
    private String securityTokenName;
    @NotNull
    @JsonProperty
    private int securityTokenExpirationTime;
    @NotNull
    @JsonProperty
    private String signInPage;
    private String secureUrl;

    public GoogleAuthorization getGoogle() {
        return google;
    }

    public void setGoogle(GoogleAuthorization google) {
        this.google = google;
    }

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public String getSecurityTokenName() {
        return securityTokenName;
    }

    public void setSecurityTokenName(String securityTokenName) {
        this.securityTokenName = securityTokenName;
    }

    public int getSecurityTokenExpirationTime() {
        return securityTokenExpirationTime;
    }

    public void setSecurityTokenExpirationTime(int securityTokenExpirationTime) {
        this.securityTokenExpirationTime = securityTokenExpirationTime;
    }

    public String getSignInPage() {
        return signInPage;
    }

    public void setSignInPage(String signInPage) {
        this.signInPage = signInPage;
    }

    public String getSecureUrl() {
        return secureUrl;
    }

    public void setSecureUrl(String secureUrl) {
        this.secureUrl = secureUrl;
    }
}
