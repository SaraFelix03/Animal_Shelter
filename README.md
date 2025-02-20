# 🐾 Animal Shelter Management System

This **Animal Shelter Management System** is a **Java-based application** that helps manage a shelter for animals awaiting adoption.  
The system allows **adding animals, registering adoptions, tracking veterinary visits, and generating reports**.  

---

## 🚀 Features
- **Add Animal** → Register new animals in the shelter  
- **View Available Animals** → List all animals that are available for adoption  
- **Register Adoption** → Assign an animal to an adopter  
- **Record Veterinary Visit** → Log health check-ups for animals  
- **Generate Shelter Statistics** → View stats on adoptions and pending cases  
- **Search Animals** → Find animals based on different criteria  
- **View Pending Adoptions** → Show adoptions that are not yet finalized  

---

## ⚙️ Technologies Used
- **Java 17** 🟡
- **Serialization** 📦
- **JUnit** 🧪
- **JaCoCo** for code coverage analysis ✅
- **PMD** for static code analysis 🔍
- **Checkstyle** to ensure coding standards 🧹
- **Maven Site Plugin & Javadoc** for generating project documentation 📖
- **Doker & Dokerhub** for containerizing the application and managing image deployments 📦🚀

---
## ⚙️ Useful commands
- **Version management**:
use 'mvn release:prepare release:perform -Dgoals=install' to manage versioning 
and automate the release process

- **Documentation**:
use 'mvn site' to generate it

- **Coverage report**:
use 'mvn jacoco:report' to generate a report of the total code and brench coverage in html