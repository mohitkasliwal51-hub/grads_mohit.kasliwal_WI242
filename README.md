# grad_mohit.kasliwal_WI242

Monorepo for internship deliverables.

## Project

**MaintenanceApp**

```
MaintenanceApp/
├─ src/  # Java source files
└─ db/   # SQL schema & seed files
```

### Structure
- `src/` → place your `.java` classes.
- `db/`  → place your `.sql` DDL/DML scripts.

### Quick Start (Local)
1. Add your Java sources under `MaintenanceApp/src/`.
2. Add your database scripts under `MaintenanceApp/db/`.
3. (Optional) Initialize as a Java project using your preferred build tool (Maven/Gradle).

### Suggested Build Options
- **Maven**: run `mvn -q archetype:generate` and move sources into `src/main/java`.
- **Gradle**: create `build.gradle` and use `src/main/java`.

> This repo starts minimal by design. You can layer tooling as needed.

## GitHub Bootstrap
```bash
# from the parent directory of this README
cd grad_mohit.kasliwal_WI242

git init

# if you create a GitHub repo named grad_mohit.kasliwal_WI242, then:
git remote add origin https://github.com/<your-username>/grad_mohit.kasliwal_WI242.git

# first commit
echo "# grad_mohit.kasliwal_WI242" > TEMP_README.md
rm TEMP_README.md  # optional, just an example

git add .
git commit -m "chore: scaffold repository with MaintenanceApp skeleton"

git branch -M main

git push -u origin main
```

---
_Authored by automation: repo scaffold generated._
