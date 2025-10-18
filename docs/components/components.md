## FHIR Provider Directory API

**Not ordered by company size**

1. United Health Care*
    1. Base URL → https://public.fhir.flex.optum.com/R4/
        1. No authentication needed since public
        2. Both Optum and UnitedHealthcare are subsidiaries of UnitedHealth Group (UHG)
        3. Hosted by Optum is intended to satisfy CMS’s Interoperability and Patient Access Final Rule for UnitedHealthcare’s Provider Directory requirements.
2. Elevance Health (Anthem)*
    1. Base URL (Subsidiaries) →
        1. Amerigroup
            1. https://totalview.healthos.elevancehealth.com/resources/unregistered/api/v1/fhir/cms_mandate/mcd
        2. Anthem Blue Cross
            1. https://totalview.healthos.elevancehealth.com/resources/unregistered/api/v1/fhir/cms_mandate/mcd
        3. Anthem Blue Cross Blue Shield
            1. https://totalview.healthos.elevancehealth.com/resources/unregistered/api/v1/fhir/cms_mandate/mcd
        4. Blue Medicare Advantage
            1. https://totalview.healthos.elevancehealth.com/resources/unregistered/api/v1/fhir/cms_mandate/mcd
        5. Clear Health Alliance
            1. https://totalview.healthos.elevancehealth.com/resources/unregistered/api/v1/fhir/cms_mandate/mcd
        6. Dell Children Health Plan
            1. https://totalview.healthos.elevancehealth.com/resources/unregistered/api/v1/fhir/cms_mandate/mcd
        7. Health Blue
            1. https://totalview.healthos.elevancehealth.com/resources/unregistered/api/v1/fhir/cms_mandate/mcd
        8. Healthy Blue Blue Choice
            1. https://totalview.healthos.elevancehealth.com/resources/unregistered/api/v1/fhir/cms_mandate/mcd
        9. Healthy Blue NC
            1. https://totalview.healthos.elevancehealth.com/resources/unregistered/api/v1/fhir/cms_mandate/mcd
        10. Simply HealthCare
            1. https://totalview.healthos.elevancehealth.com/resources/unregistered/api/v1/fhir/cms_mandate/mcd
        11. Summit
            1. https://totalview.healthos.elevancehealth.com/resources/unregistered/api/v1/fhir/cms_mandate/mcd
        12. Unicare
            1. https://totalview.healthos.elevancehealth.com/resources/unregistered/api/v1/fhir/cms_mandate/mcd
        13. Wellpoint
            1. https://totalview.healthos.elevancehealth.com/resources/unregistered/api/v1/fhir/cms_mandate/mcd
    2. https://www.anthem.com/content/dam/digital/developers-portal/Anthem-IOProviderDirectoryAndFormulary-API-Documentation.pdf
    3. https://www.anthem.com/developers
        1. “To establish connections, third party applications (TPA) need to complete the Registration process. This involves filling out the Provider Directory API Production Environment request form, available on the Developer Portal. Once the registration is successfully completed, TPA will receive the client credentials and the access token URL through a secure email.”
3. Cigna*
    1. Base URL → https://fhir.cigna.com/ProviderDirectory/v1/
    2. https://developer.cigna.com/docs/service-apis/provider-directory/docs
        1. According to Cigna documentation, you do not need to register and authenticate since it is public.
4. Kaiser Permanente*
    1. Base URL → https://kpx-service-bus.kp.org/service/hp/mhpo/healthplanproviderv1rc
    2. https://developer.kp.org/#/apis/60c008510278dd00123cef6e
        1. According to Kaiser Permanente documentation, you do not need to register and authenticate.
5. Health Care Service Corporation (HCSC)*
    1. Base URL → https://api.hcsc.net
    2. https://interoperability.hcsc.com/s/provider-directory-api
        1. According to Health Care Service Corporation documentation, you do not need to register and authenticate.
6. Molina Healthcare*
    1. Base URL → https://api.interop.molinahealthcare.com/providerdirectory/
    2. https://developer.interop.molinahealthcare.com/api-details#api=trizetto-provider-directory&operation=607f68d222af8c23888bf60f
        1. According to Molina Healthcare documentation, you do not need to register and authenticate.
7. Independence Blue Cross*
   1. Base URL -> https://eapics.ibx.com/provider/v1/fhir
   2. https://www.ibx.com/developer-resources
       1. According to Independence Blue Cross, you do not need to register and authenticate.

## NPI Registry API

Publicly available API used to query for practitioner data. This API belongs to the government and is free to use.

- https://npiregistry.cms.hhs.gov/api-page
- https://npiregistry.cms.hhs.gov/demo-api
    - This project uses **version 2.1** of the API

## Add Company
1. If necessary, registers the app with the company.
2. Update "HealthPlanOrganizationName.java" and add the target to the enum.
3. Update "application.yaml" and add the target configuration. (If oauth2 make sure to add the GitHub secret variable names under security)
4. Add the target config class to the config package. Reference configurations like "CignaConfig.java".
5. Update "PractitionerService.java" and add if statement to handle the new target with proper configuration.
6. Deploy via GitHub Actions workflow .github/workflows/deploy.yml. (Make sure to push tag, add deploy hook secret to the repository and make sure the workflow has correct permissions)
