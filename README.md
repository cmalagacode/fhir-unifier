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

## 📚 Documentation:

- Path
  - docs/goals/* (Contains documentation on the goal of the project and its strategy to achieve these goals)
  - docs/components/* (contains documentation on fhir and other components)

