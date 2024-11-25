# Library Management System <img src="library.svg" alt="" width="50" />

Este proyecto es un sistema de gestión de biblioteca desarrollado en **Spring Boot**, siguiendo un enfoque **modular monolítico** utilizando **Spring Modulith**. Permite gestionar libros y sus copias, y manejar préstamos entre usuarios, implementando eventos internos para sincronizar el estado de las entidades.

---
## Características principales

- **🛠 Arquitectura modular**: Uso de módulos de aplicación para dividir responsabilidades.
- **📚 Gestión de préstamos de libros**:
    - Los préstamos se registran y actualizan utilizando eventos internos de Spring Modulith.
    - Cada copia del libro puede marcarse como disponible o no disponible.
- **💾 Persistencia**: Utiliza **PostgreSQL** con integración de **Flyway** para el manejo de migraciones de base de datos.
- **🔔 Eventos**: Manejo de eventos entre módulos (`LendingEvent`, `LoanCreated`, `LoanClosed`).
- **✅ Tests**: Testeos automatizados con **Testcontainers** para garantizar integridad con la base de datos.

---
## Estructura del proyecto

```plaintext
src/main/java/com/library
├── catalog           # Módulo de catálogo (gestión de libros y copias)
├── core              # Componentes reutilizables (base del sistema)
└── lending           # Módulo de préstamos
```

### Módulo `catalog`

- **Responsabilidad**: Gestionar libros y sus copias.
- **Eventos manejados**:
    - `LoanCreated`: Marca una copia como no disponible.
    - `LoanClosed`: Marca una copia como disponible.
- **Puertos**:
    - `CopyQueryPersistencePort`: Consultar el estado de una copia.
    - `CopyCreatePersistencePort`: Actualizar el estado de una copia.

### Módulo `lending`

- **Responsabilidad**: Gestionar el flujo de préstamos.
- **Eventos generados**:
    - `LoanCreated`: Notifica que un préstamo ha sido creado.
    - `LoanClosed`: Notifica que un préstamo ha sido cerrado.
- **Casos de uso**:
    - `RentBookUseCase`: Gestiona la creación de préstamos.

---

## Requisitos previos

Antes de ejecutar la aplicación, asegúrate de tener instalado:

- **☕ Java 23** o superior.
- **🔨 Maven 3.9.x** o superior.
- **🐳 Docker** (opcional, para Testcontainers).

---

## Configuración del entorno

### Base de datos

1. Instalar y configurar PostgreSQL.
2. Crear una base de datos llamada `library` (o el nombre configurado en `application.properties`).
3. Flyway manejará automáticamente las migraciones al ejecutar la aplicación.

Si prefieres usar **Docker**, puedes utilizar el siguiente comando para iniciar una instancia de PostgreSQL:

```bash
docker run --name library-db -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=library -p 5432:5432 -d postgres
```

## Ejecución del proyecto

### Localmente

Este proyecto requiere **Docker** para ejecutar ciertos servicios (como la base de datos PostgreSQL) en tu entorno local.

1. **Clonar el repositorio**:

    ```bash
    git clone https://github.com/andresre1/library.git
    cd library
    ```

2. **Iniciar los contenedores de Docker** (por ejemplo, para la base de datos PostgreSQL):

   Asegúrate de tener Docker y Docker Compose instalados. Luego, ejecuta:

    ```bash
    docker-compose up -d
    ```

   Esto iniciará los contenedores necesarios para la base de datos y otros servicios requeridos.

3. **Compilar y ejecutar la aplicación**:

    ```bash
    mvn spring-boot:run
    ```

4. La aplicación estará disponible en `http://localhost:8080`.

---

## Tests

Ejecuta los tests utilizando Maven:

```bash
mvn test
```

## Contacto

| **Campo**                                                                                                                     | **Descripción**  |  
|-------------------------------------------------------------------------------------------------------------------------------|------------------|  
| 👤 **Autor**                                                                                                                  | Andrés Sánchez-Crespo Martínez|  
| 📧 **Email**                                                                                                                  | [andressaanchezz@gmail.com](mailto:andressaanchezz@gmail.com)|  
| <img src="https://upload.wikimedia.org/wikipedia/commons/9/91/Octicons-mark-github.svg" alt="GitHub" width="15" /> **GitHub** | [andresre1](https://github.com/andresre1)  |  

