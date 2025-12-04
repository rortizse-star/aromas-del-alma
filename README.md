# Aromas del Alma - Sistema de Gestión

![Build Status] (https://app.travis-ci.com/rortizse-star/aromas-del-alma.svg?token=7rq8B1MC14sXts9hssss)
![Coverage](https://img.shields.io/badge/coverage-65%25-green)
![Version](https://img.shields.io/badge/version-1.0.0-blue)
![License](https://img.shields.io/badge/license-MIT-green)

Sistema integral de gestión de inventario y ventas para el negocio de aromaterapia "Aromas del Alma".

---

##  Tabla de Contenidos

- [Características](#características)
- [Tecnologías](#tecnologías)
- [Arquitectura](#arquitectura)
- [Requisitos Previos](#requisitos-previos)
- [Instalación](#instalación)
- [Uso](#uso)
- [Testing](#testing)
- [Deployment](#deployment)
- [Contribuir](#contribuir)
- [Licencia](#licencia)

---

##  Características

### Backend (Java Spring Boot)
-  API REST completa con 15+ endpoints
-  Gestión de inventario en tiempo real
-  Sistema de registro de ventas
-  Actualización automática de stock
-  Cálculo de ganancias y descuentos


### Frontend Web
-  Panel de administración de productos (CRUD)
-  Punto de venta con escáner de códigos de barras
-  Dashboard de reportes con 4 gráficas interactivas
-  Búsqueda en tiempo real
-  Diseño responsive
-  Compatible con lectores USB de códigos


---

##  Tecnologías

### Backend
- **Java 11**
- **Spring Boot 2.7.14**
- **Spring Data JPA** (Hibernate)
- **Spring Security**
- **MySQL 8.0**
- **Maven**
- **Lombok**

### Frontend Web
- **HTML5 / CSS3 / JavaScript ES6+**
- **Chart.js 4.4.0** (Gráficas)
- **Fetch API** (AJAX)



### DevOps
- **Git / GitHub**
- **Travis CI**
- **JUnit 5 / Mockito**
- **Docker** (opcional)

---

## 🏗️ Arquitectura
```
┌─────────────────────────────────────────────────┐
│           CLIENTES (Web/Mobile)                 │
└────────────────┬────────────────────────────────┘
                 │ HTTP/REST
                 ▼
┌─────────────────────────────────────────────────┐
│        SPRING BOOT (Tomcat Embedded)            │
│  ┌───────────────────────────────────────────┐  │
│  │  Controllers (REST API)                   │  │
│  ├───────────────────────────────────────────┤  │
│  │  Services (Lógica de Negocio)             │  │
│  ├───────────────────────────────────────────┤  │
│  │  Repositories (Spring Data JPA)           │  │
│  └───────────────────────────────────────────┘  │
└────────────────┬────────────────────────────────┘
                 │ JDBC
                 ▼
┌─────────────────────────────────────────────────┐
│              MySQL Database                     │
│  • productos  • ventas  • usuarios              │
└─────────────────────────────────────────────────┘
```



---



---

##  Instalación

### 1. Clonar el repositorio

\`\`\`bash
git clone https://github.com/rortizse-star/aromas-del-alma-sistema.git
cd aromas-del-alma-sistema
\`\`\`

### 2. Configurar Base de Datos

\`\`\`bash
# Conectar a MySQL
mysql -u root -p

# Ejecutar script de creación
source backend/src/main/resources/sql/schema.sql
\`\`\`

### 3. Configurar Backend

\`\`\`bash
# Editar application.properties
cd backend/src/main/resources
nano application.properties

# Actualizar credenciales:
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password
\`\`\`

### 4. Instalar dependencias del Backend

\`\`\`bash
cd backend
mvn clean install
\`\`\`


---

##  Uso




 Backend disponible en: **http://localhost:8080**

### Acceder al Frontend Web

Abre tu navegador en:
-  **Inicio:** http://localhost:8080/index.html

- **Punto de Venta:** http://localhost:8080/ventas.html




---


---



### Build de producción

\`\`\`bash
# Backend
cd backend
mvn clean package -DskipTests

# El JAR estará en: target/aromas-backend-1.0.0.jar
\`\`\`

### Ejecutar en producción

\`\`\`bash
# Configurar perfil de producción
export SPRING_PROFILES_ACTIVE=prod

# Ejecutar
java -jar target/aromas-backend-1.0.0.jar
\`\`\`

Ver guía completa: [DEPLOYMENT.md](docs/DEPLOYMENT.md)


---



## 📝 Estructura del Proyecto


---

##  Licencia



