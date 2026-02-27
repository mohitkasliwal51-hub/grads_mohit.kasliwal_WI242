---

# Jenkins CI Setup – Calculator Project

## 1️⃣ Initial Project Setup

![Build success and test running](image-1.png)

![Created package and ran the created JAR file](image-2.png)

![GitHub page showing initial project uploaded](image-3.png)

---

## 2️⃣ Jenkins Configuration

### 🔹 JDK Configuration

![JDK configuration in Jenkins](image-4.png)

### 🔹 Maven Configuration

![Maven configuration in Jenkins](image-5.png)

### 🔹 SCM Configuration (Calculator-CI)

![SCM configure for Calculator-CI](image-6.png)

### 🔹 Build Trigger Configuration

![Triggers configure for Calculator-CI](image-7.png)

### 🔹 Build Step Configuration

![Build step configure for Calculator-CI](image-8.png)

---

## 3️⃣ Successful Build

![Build success screen](image-9.png)

![Console output](image-10.png)

---

# 🚀 GitHub Webhook Integration

## 🔹 Changed Trigger to GitHub Hook

![Changed trigger to GitHub hook trigger for GITScm polling](image-11.png)

## 🔹 Configured ngrok

![Configured ngrok for webhook](image-12.png)

## 🔹 Added Webhook in GitHub

![Added webhook in GitHub](image-13.png)

![Webhook added successfully](image-14.png)

---

## ✅ Webhook Execution Verification

![GitHub page showing request was successful](image-15.png)

![ngrok terminal showing request was successful](image-16.png)

![Request visible in Jenkins GitHub log](image-17.png)

![Build was successful](image-18.png)

![Console output in Jenkins](image-19.png)

---
