package com.github.cmalagacode.fhirunifier.api.config;

public interface Config {
    String getBaseURL();
    String getPractitionerRolePath();
    String getPractitionerRoleURLParameter();
    boolean getOauth2();
    String getRegistrationId();
}
