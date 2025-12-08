# Aromas del Alma - Sistema de Gestión

![Build Status] (https://app.travis-ci.com/rortizse-star/aromas-del-alma.svg?token=7rq8B1MC14sXts9hssss)
![Coverage](https://img.shields.io/badge/coverage-65%25-green)
![Version](https://img.shields.io/badge/version-1.0.0-blue)
![License](https://img.shields.io/badge/license-MIT-green)

 Aromas del Alma – Sistema de Gestión de Ventas
Un sistema web para gestionar ventas, inventario y descripción de productos de aromaterapia, desarrollado para apoyar a un emprendimiento familiar.

 1. Resumen Ejecutivo
 Descripción del Proyecto
“Aromas del Alma” es una aplicación web desarrollada en Java con Spring Boot, que permite gestionar de forma integral el punto de venta de una tienda de aromaterapia. La aplicación incluye funciones para:

Registrar y editar productos (nombre, descripción, precio, stock, código de barras).
Escanear o buscar productos por código de barras.
Visualizar beneficios y uso de cada producto.
Calcular descuentos personalizados.
Realizar ventas con actualización automática de inventario.
Soporte para múltiples productos en una sola venta (carrito).

Problemática Actual
Antes del desarrollo de esta aplicación, la gestión de ventas se realizaba de forma manual y desorganizada:

No existía un inventario centralizado ni seguimiento del stock.
No se contaba con una descripción accesible y estandarizada de los beneficios de cada producto.
Los descuentos y cálculos se hacían a mano, con alto riesgo de error.
No había historial de ventas ni trazabilidad.
Este proyecto nace del deseo de ayudar a mi esposa a profesionalizar su negocio y ofrecer una experiencia más clara y confiable a sus clientes.

Solución Propuesta
Se desarrolló una aplicación web con las siguientes características:

Arquitectura: Monolítica, basada en capas (Controlador → Servicio → Repositorio → Modelo).
Tecnologías: Java 17, Spring Boot 3.4, Thymeleaf, MySQL, Maven.
Patrón MVC: Separación clara entre lógica de negocio, datos y presentación.
Interfaz amigable: Diseñada para usuarios no técnicos.
Objetivo
Automatizar la gestión de ventas e inventario, ofreciendo una herramienta simple, rápida y confiable para el día a día del negocio.

Fecha Límite
Primera semana de enero de 2025

✅ Avance del Proyecto
✅ Aplicación funcional y desplegada en la nube (AWS EC2 + RDS)
✅ Gestión completa de productos y ventas
✅ Soporte para descuentos y carrito de compras
✅ Interfaz en español con diseño intuitivo

🧰 2. Requerimientos Técnicos
Componente
Tecnología
Lenguaje
Java 17
Framework
Spring Boot 3.4
Plantillas
Thymeleaf
Base de Datos
MySQL 8.0 (en AWS RDS)
Servidor de Aplicación
Tomcat embebido (Spring Boot)
Servidor Web (Proxy)
Nginx
Gestor de Dependencias
Maven
Plataforma de Despliegue
AWS (EC2 + RDS)
Control de Versiones
Git + GitHub
 3. Instalación
 Requisitos previos
JDK 17
MySQL 8.0 (local o en la nube)
Maven 3.8+
Git
 Clonar el repositorio
bash
12
git clone https://github.com/tu-usuario/aromas-del-alma.gitcd aromas-del-alma

🔧 Configurar la base de datos
Crear una base de datos llamada aromas_del_alma.
Asegurar que el usuario tenga permisos de lectura/escritura.
⚙️ Configurar application.properties
properties
12345
# src/main/resources/application.propertiesspring.datasource.url=jdbc:mysql://localhost:3306/aromas_del_almaspring.datasource.username=tu_usuariospring.datasource.password=tu_contraseñaspring.jpa.hibernate.ddl-auto=update

Ejecutar la aplicación
bash
12
mvn clean package -DskipTestsjava -jar target/aromas-del-alma-*.jar

Acceder desde: http://localhost:8080

 Despliegue en la nube (AWS)
Instancia EC2: Ubuntu 24.04, tipo t3.micro (Free Tier).
Base de datos RDS: MySQL 8.0, tipo db.t3.micro.
Configuración:
Subir JAR al servidor.
Crear application-prod.properties con credenciales de RDS.
Usar Nginx como proxy inverso en el puerto 80.
IP pública: Accesible desde cualquier navegador.
 4. Configuración del Proyecto
Archivos esenciales de configuración
application.properties: Configuración local (desarrollo).
application-prod.properties: Configuración para producción (AWS).
Procfile (opcional): Para despliegue en plataformas PaaS.
system.properties: Define versión de Java (para Heroku/Render).


5. Manual de Usuario
Inicio
Accede a la URL de la aplicación. Verás una página de bienvenida con el logo “Aromas del Alma”.

Buscar un producto
Escanea o escribe el código de barras.
Haz clic en “Agregar al Carrito”.
El producto aparecerá en la lista con su descripción, precio y stock.
Aplicar descuento
Ingresa un porcentaje de descuento (0–100).
El total a pagar se actualiza automáticamente.
Realizar venta
Revisa los productos en el carrito.
Haz clic en “✅ Confirmar Venta Completa”.
El stock de todos los productos se reduce automáticamente.
Agregar nuevo producto
Haz clic en “➕ Agregar Producto” (arriba a la derecha).
Llena todos los campos (nombre, descripción detallada, precio, stock, código de barras).
Haz clic en “💾 Guardar Producto”.
Editar producto
Haz clic en el nombre del producto en el carrito.
Modifica los datos y guarda los cambios.
6. Contribución
Este es un proyecto de desarrollo interno. Si deseas contribuir:

Crea una rama con tu nombre y funcionalidad:
bash
1
git checkout -b nombre/nueva-funcionalidad

Haz commit con mensajes claros:
bash
1
git commit -m "feat: agrega carrito de compras"

Crea un Pull Request a la rama main.
Asegúrate de que todas las pruebas pasen (aunque actualmente no hay pruebas automatizadas).
📄 Consulta el archivo CONTRIBUTING.md para más detalles.

7. Roadmap
Etapa
Objetivo
Estado
MVP
Ventas, inventario, descripción de productos
Completado
🟡 V2
Historial de ventas con fecha, producto, cantidad y total
En desarrollo
🟢 V3
Reportes de stock bajo y productos más vendidos
Planeado
🟢 V4
Sistema de login para proteger edición de productos
Planeado
🟢 V5
Integración con escáner físico de código de barras
realizado
🟢 V6
Soporte para impresión de tickets de venta
Futuro
💚 Hecho con amor
Este proyecto fue creado para mi esposa, como un regalo de apoyo a su emprendimiento en aromaterapia.
