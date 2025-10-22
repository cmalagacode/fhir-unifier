# 🚀 Fhir Unifier

## ⚠️ Forking This Project?

If you fork this repository or use it as a template, please make the following updates to avoid publishing conflicts and preserve project identity:

- **`groupId`**: Change from `com.github.myusername.fhirunifier` to your own namespace.
- **`url`**: Update to reflect your own repository URL.
- **Package name**: Rename `com.github.myusername.fhirunifier` to match your own domain or organization.

> 💡 _Note: The Maven artifact ID uses `fhirunifier` (without a dash) to avoid issues with naming conventions._


### ✅ Why this matters

Using your own namespace ensures that:

- Your builds and deployments don’t conflict with the original project.
- You maintain a clear identity for your fork or derivative work.
- Maven Central or other repositories won’t reject your artifacts due to duplicate coordinates.


## 📖 Summary:
Open source project aimed at harmonizing provider data across multiple insurers and the NPI registry. <br>
While this project ingests FHIR-based data, it transforms it into a simplified internal model for ease of use and performance.<br>

Think of this like a data unification layer that makes it easier to view the data across various APIs. <br>

## 🎯 Current Support:

1. United Health Care (target=UNITED_HEALTH_CARE)
2. Cigna (target=CIGNA)
3. Kaiser Permanente (target=KAISER_PERMANENTE)
4. Independence Blue Cross (target=INDEPENDENCE_BLUE_CROSS)
5. Molina Healthcare (target=MOLINA_HEALTHCARE)
6. Health Care Service Corporation (target=HEALTH_CARE_SERVICE_CORPORATION)
7. Elevance Health (target=ELEVANCE_HEALTH)

## 📚 Documentation:

- Path
  - docs/goals/* (Contains documentation on the goal of the project and its strategy to achieve these goals)
  - docs/components/* (contains documentation on fhir and other components)
- Swagger API Documentation
  - swagger-ui/index.html

## 📚 Add Company:
1. If necessary, registers the app with the company.
2. Update "HealthPlanOrganizationName.java" and add the target to the enum.
3. Update "application.yaml" and add the target configuration. (If oauth2 make sure to add the GitHub secret variable names under security)
4. Add the target config class to the config package. Reference configurations like "CignaConfig.java".
5. Update "PractitionerService.java" and add if statement to handle the new target with proper configuration.
6. Deploy via GitHub Actions workflow .github/workflows/deploy.yml. (Make sure to push tag v*, add deploy hook secret to the repository and make sure the workflow has correct permissions)

## 📚 Required GitHub Secrets:
- GITHUB_TOKEN (For deploying, GitHub automatically creates this secret)
- DEPLOY_HOOK (For deploying to PaaS -> Heroku, Render, etc)
- ELEVANCE_HEALTH_CLIENT_ID (For Elevance Health / Anthem)
- ELEVANCE_HEALTH_CLIENT_SECRET (For Elevance Health / Anthem)
- **Any other target that requires secrets because of OAuth2 like Elevance Health / Anthem**

