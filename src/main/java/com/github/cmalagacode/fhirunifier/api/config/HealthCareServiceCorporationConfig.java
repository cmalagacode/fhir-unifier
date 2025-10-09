package com.github.cmalagacode.fhirunifier.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "support.health-care-service-corporation")
public class HealthCareServiceCorporationConfig implements Config {
    private String baseURL;
    private String practitionerRolePath;
    private boolean practitionerRoleURLQueryViaNPI;
    private String practitionerRoleURLParameter;
    private boolean oauth2;
    private String registrationId;
    private boolean paginationSupported;

    public String getBaseURL() {
        return baseURL;
    }

    public void setBaseURL(String baseURL) {
        this.baseURL = baseURL;
    }

    public String getPractitionerRolePath() {
        return practitionerRolePath;
    }

    public void setPractitionerRolePath(String practitionerRolePath) {
        this.practitionerRolePath = practitionerRolePath;
    }

    public boolean isPractitionerRoleURLQueryViaNPI() {
        return practitionerRoleURLQueryViaNPI;
    }

    public void setPractitionerRoleURLQueryViaNPI(boolean practitionerRoleURLQueryViaNPI) {
        this.practitionerRoleURLQueryViaNPI = practitionerRoleURLQueryViaNPI;
    }

    public String getPractitionerRoleURLParameter() {
        return practitionerRoleURLParameter;
    }

    public void setPractitionerRoleURLParameter(String practitionerRoleURLParameter) {
        this.practitionerRoleURLParameter = practitionerRoleURLParameter;
    }

    public boolean getOauth2() {
        return oauth2;
    }

    public void setOauth2(boolean oauth2) {
        this.oauth2 = oauth2;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public boolean getPaginationSupported() {
        return paginationSupported;
    }

    public void setPaginationSupported(boolean paginationSupported) {
        this.paginationSupported = paginationSupported;
    }
}
