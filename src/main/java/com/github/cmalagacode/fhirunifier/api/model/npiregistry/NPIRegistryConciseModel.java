package com.github.cmalagacode.fhirunifier.api.model.npiregistry;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NPIRegistryConciseModel {
    private final String npiRegistryEnumerationType;
    private final String npiRegistryCredential;
    private final String npiRegistrySex;
    private final String npiRegistryStatus;
    private final String npiRegistryFirstName;
    private final String npiRegistryLastName;
    private final String npiRegistryMiddleName;
    private final String npiRegistrySoleProprietor;
    private final String npiRegistryEnumerationDate;
    private final String npiRegistryLastUpdated;
    private final String npiRegistryCertificationDate;
    private final List<NPIRegistryTaxonomies> npiRegistryTaxonomies;
    private final List<NPIRegistryAddresses> npiRegistryAddresses;

    public NPIRegistryConciseModel(String npiRegistryEnumerationType, String npiRegistryCredential, String npiRegistrySex, String npiRegistryStatus, String npiRegistryFirstName, String npiRegistryLastName, String npiRegistryMiddleName, String npiRegistrySoleProprietor, String npiRegistryEnumerationDate, String npiRegistryLastUpdated, String npiRegistryCertificationDate, List<NPIRegistryTaxonomies> npiRegistryTaxonomies, List<NPIRegistryAddresses> npiRegistryAddresses) {
        this.npiRegistryEnumerationType = npiRegistryEnumerationType;
        this.npiRegistryCredential = npiRegistryCredential;
        this.npiRegistrySex = npiRegistrySex;
        this.npiRegistryStatus = npiRegistryStatus;
        this.npiRegistryFirstName = npiRegistryFirstName;
        this.npiRegistryLastName = npiRegistryLastName;
        this.npiRegistryMiddleName = npiRegistryMiddleName;
        this.npiRegistrySoleProprietor = npiRegistrySoleProprietor;
        this.npiRegistryEnumerationDate = npiRegistryEnumerationDate;
        this.npiRegistryLastUpdated = npiRegistryLastUpdated;
        this.npiRegistryCertificationDate = npiRegistryCertificationDate;
        this.npiRegistryTaxonomies = npiRegistryTaxonomies;
        this.npiRegistryAddresses = npiRegistryAddresses;
    }


    public List<NPIRegistryAddresses> getNpiRegistryAddresses() {
        return npiRegistryAddresses;
    }

    public List<NPIRegistryTaxonomies> getNpiRegistryTaxonomies() {
        return npiRegistryTaxonomies;
    }

    public String getNpiRegistryCertificationDate() {
        return npiRegistryCertificationDate;
    }

    public String getNpiRegistryLastUpdated() {
        return npiRegistryLastUpdated;
    }

    public String getNpiRegistryEnumerationDate() {
        return npiRegistryEnumerationDate;
    }

    public String getNpiRegistrySoleProprietor() {
        return npiRegistrySoleProprietor;
    }

    public String getNpiRegistryMiddleName() {
        return npiRegistryMiddleName;
    }

    public String getNpiRegistryLastName() {
        return npiRegistryLastName;
    }

    public String getNpiRegistryFirstName() {
        return npiRegistryFirstName;
    }

    public String getNpiRegistryStatus() {
        return npiRegistryStatus;
    }

    public String getNpiRegistrySex() {
        return npiRegistrySex;
    }

    public String getNpiRegistryEnumerationType() {
        return npiRegistryEnumerationType;
    }

    public String getNpiRegistryCredential() {
        return npiRegistryCredential;
    }
}
