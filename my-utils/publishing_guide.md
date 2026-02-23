# Publishing `my-utils` to Maven Central

## Project Details

* **Group ID:** `io.github.mohitkasliwal51-hub`
* **Artifact ID:** `my-utils`
* **Version:** `1.0.0`
* **Java Version:** 17
* **License:** MIT
* **Repository:** [https://github.com/mohitkasliwal51-hub/grads_mohit.kasliwal_WI242](https://github.com/mohitkasliwal51-hub/grads_mohit.kasliwal_WI242)

---

# Step-by-Step Process Followed

## 1. Created Maven Project

Created a Maven project using:

```
mvn archetype:generate
```

Configured:

* `groupId`
* `artifactId`
* Java 17
* Packaging: `jar`

---

## 2. Prepared pom.xml for Maven Central

Added required metadata:

* `<name>`
* `<description>`
* `<url>`
* `<licenses>`
* `<developers>`
* `<scm>`

Added plugins:

* `maven-source-plugin`
* `maven-javadoc-plugin`
* `maven-gpg-plugin`
* `central-publishing-maven-plugin`

Added distribution management:

```xml
<distributionManagement>
    <repository>
        <id>central</id>
        <url>https://central.sonatype.com/api/v1/publisher</url>
    </repository>
</distributionManagement>
```

---

## 3. Created Sonatype Central Account

* Logged in using GitHub
* Namespace `io.github.mohitkasliwal51-hub` was automatically verified

---

## 4. Generated User Token

From Sonatype Central:

Profile → User Settings → Generate User Token

Added credentials to:

```
C:\Users\<username>\.m2\settings.xml
```

```xml
<settings>
  <servers>
    <server>
      <id>central</id>
      <username>GENERATED_USERNAME</username>
      <password>GENERATED_PASSWORD</password>
    </server>
  </servers>
</settings>
```


---

## 5. Installed and Configured GPG

Installed Gpg4win.

Generated key:

```
gpg --full-generate-key
```

Verified key:

```
gpg --list-secret-keys --keyid-format=long
```

Uploaded public key to key server:

```
gpg --keyserver keyserver.ubuntu.com --send-keys 9BD24BBE1DD6CFCE
```

Verified public key publication (this command is used to verify whether our key is published or not):

```
gpg --keyserver hkps://keyserver.ubuntu.com --recv-keys 9BD24BBE1DD6CFCE
```

Configured Maven GPG plugin for signing artifacts.

---

## 6. Built and Deployed

From project root:

```
mvn clean deploy
```

This performed:

* Compilation
* JAR creation
* Source JAR generation
* Javadoc JAR generation
* GPG signing
* Upload to Sonatype Central

---

## 7. Validation and Publishing

* Deployment appeared in Sonatype Central
* Validation successful
* Clicked **Publish**
* Artifact synced to Maven Central

---

# How to Use This Library

Add dependency:

```xml
<dependency>
    <groupId>io.github.mohitkasliwal51-hub</groupId>
    <artifactId>my-utils</artifactId>
    <version>1.0.0</version>
</dependency>
```

---

# Commands Summary

```
mvn clean deploy
gpg --full-generate-key
gpg --list-secret-keys --keyid-format=long
gpg --keyserver keyserver.ubuntu.com --send-keys <KEY_ID>
```

---

# Outcome

The library `my-utils` was successfully:

* Signed
* Validated
* Published
* Synced to Maven Central
