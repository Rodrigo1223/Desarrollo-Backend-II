# Minimarket Backend API

Proyecto final de desarrollo Backend II. Esta API proporciona la estructura necesaria para gestionar las operaciones de un minimarket, incluyendo la administración de productos, categorías, inventario, ventas y autenticación segura de usuarios.

## 🛠 Tecnologías utilizadas
- **Java 17+**
- **Spring Boot 3.x**
- **Spring Security (JWT)**: Implementación de seguridad basada en tokens para proteger los endpoints y gestionar la identidad.
- **H2 Database**: Base de datos relacional en memoria utilizada para el entorno de desarrollo y pruebas.
- **Maven**: Sistema de gestión y construcción de dependencias.

## 🚀 Cómo ejecutar el proyecto
Para levantar el proyecto en tu entorno local, sigue estos pasos:

1. Asegúrate de tener instalado **JDK 17 o superior**.
2. Clona este repositorio:
   git clone https://github.com/Rodrigo1223/Desarrollo-Backend-II.git
3. Entra en la carpeta del proyecto:
   cd minimarket
4. Ejecuta la aplicación utilizando el wrapper de Maven:
   En Windows: .\mvnw.cmd spring-boot:run
   En Linux/Mac: ./mvnw spring-boot:run
5. La API estará disponible y escuchando en: http://localhost:8080

## 🔒 Seguridad y Autenticación
El proyecto implementa un sistema de autenticación robusto basado en **JSON Web Tokens (JWT)**:
- Los recursos están protegidos y requieren un token válido para acceder.
- El sistema cuenta con filtros de seguridad configurados en `SecurityConfig.java`.
- Cualquier intento de acceso no autorizado es interceptado por el protocolo de seguridad de Spring, el cual garantiza que las rutas protegidas no sean expuestas sin la debida autenticación.

## 📸 Evidencias del sistema
- **Ejecución Exitosa:** El proyecto compila y levanta correctamente, gestionando sus dependencias y contextos de seguridad.
- **Endpoint de Autenticación:** Se ha verificado mediante pruebas en Postman que el endpoint de login (`/auth/login`) se encuentra operativo y responde correctamente a las peticiones del cliente.
- **Integración:** La estructura del proyecto sigue las buenas prácticas de arquitectura en capas (Controller, Service, Repository, Entity), facilitando la escalabilidad y el mantenimiento del código.

---
*Desarrollado para el módulo de Desarrollo Backend II - DuocUC (2026)*