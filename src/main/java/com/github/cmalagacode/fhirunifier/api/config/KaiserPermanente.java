package com.github.cmalagacode.fhirunifier.api.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "support.kaiser-permanente")
public class KaiserPermanente implements Config {
    private String baseURL;
    private String practitionerRolePath;
    private boolean practitionerRoleURLQueryViaNPI;
    private String practitionerRoleURLParameter;

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
}
