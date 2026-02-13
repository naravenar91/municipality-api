# municipality-api

API REST desarrollada con **Quarkus** para la gestión de comunas, siguiendo los principios de la Arquitectura Hexagonal (Ports & Adapters).

## 🚀 Tecnologías Utilizadas
* **Java 17+**
* **Quarkus**: Framework Java nativo de la nube.
* **Hibernate ORM con Panache**: Para la persistencia de datos simplificada.
* **MapStruct**: Mapeo de objetos entre capas (Dominio, DTOs, Entidades).
* **SmallRye OpenAPI**: Documentación interactiva de la API (Swagger UI).
* **Lombok**: Reducción de código boilerplate.
* **Jakarta Bean Validation**: Validación de datos de entrada.

## 🏗️ Arquitectura
El proyecto está estructurado siguiendo el patrón de Arquitectura Hexagonal:
* **Domain**: Contiene la lógica de negocio pura (Models, Value Objects, Exception, Ports).
* **Application**: Orquesta los casos de uso (Services).
* **Infrastructure**: Implementación de adaptadores.
    * **In**: Controladores REST y DTOs de entrada/salida.
    * **Out**: Adaptadores de persistencia, entidades JPA y repositorios.

## 🛠️ Instalación y Configuración
<!-- 
1- Clonar el repositorio:
git clone https://github.com/tu-usuario/municipality-api.git
- cd municipality-api

2- Ejecutar en modo desarrollo:
```shell script
./mvnw quarkus:dev
```
-->

1.  **Clonar el repositorio:**
    ```bash
    git clone [https://github.com/tu-usuario/municipality-api.git](https://github.com/tu-usuario/municipality-api.git)
    cd municipality-api
    ```

2.  **Ejecutar en modo desarrollo:**
    ```bash
    ./mvnw quarkus:dev
    ```

## 📖 Documentación de la API
* **Swagger UI:** [http://localhost:8080/q/swagger-ui/](http://localhost:8080/q/swagger-ui/)
* **OpenAPI Spec (JSON):** [http://localhost:8080/q/openapi](http://localhost:8080/q/openapi)

## 🚦 Endpoints Principales
Método Endpoint Descripción
- GET  /api/municipality       Obtener todas las comunas.
- GET  /api/municipality/{id}  Obtener una comuna por ID.
- POST /api/municipality       Crear una nueva comuna.
- PUT  /api/municipality       Actualizar una comuna existente.

## 🛡️ Manejo de Errores
- La API utiliza un formato estandarizado para las respuestas de error a través de ErrorResponse:
```json
{
  "code": "DataNotFoundException",
  "message": "No se encontró la comuna con ID: 1",
  "timestamp": "2026-02-10T12:00:00",
  "path": "/api/municipality/1"
}
```

## 🐳 Levantar el proyecto con Docker Compose

El proyecto incluye backend (Quarkus), base de datos (PostgreSQL) y frontend (Angular) listos para ejecutar con Docker.

### Requisitos
* Docker y Docker Compose instalados

### Pasos

1. **Clonar el repositorio:**
    ```bash
    git clone https://github.com/naravenar91/municipality-api.git
    cd municipality-api
    git checkout disenio
    ```

2. **Levantar todos los servicios:**
    ```bash
    docker compose up -d --build
    ```

3. **Verificar que los contenedores estén corriendo:**
    ```bash
    docker compose ps
    ```

### Servicios disponibles

| Servicio | Puerto | URL |
|----------|--------|-----|
| Frontend Angular | 4200 | http://localhost:4200 |
| API Quarkus | 8080 | http://localhost:8080 |
| Swagger UI | 8080 | http://localhost:8080/q/swagger-ui/ |
| PostgreSQL | 5432 | localhost:5432 |

El frontend incluye un proxy reverso con Nginx que redirige las peticiones `/api/*` al backend, por lo que toda la aplicacion funciona desde `http://localhost:4200`.

### Detener los servicios
```bash
docker compose down
```

### Detener y eliminar datos persistentes
```bash
docker compose down -v
```

## 🖥️ Frontend (Angular)

El frontend se encuentra en la carpeta `municipality-frontend/` y esta construido con:

* **Angular 17+** (standalone components)
* **Angular Material** para la interfaz
* **Reactive Forms** con validacion

### Paginas

| Pagina | Ruta | Descripcion |
|--------|------|-------------|
| Dashboard | `/` | Resumen con conteo de entidades |
| Municipalidades | `/municipalities` | Tabla con CRUD (crear/editar) |
| Usuarios | `/users` | Tabla con creacion de usuarios |
| Servicios | `/services` | Asociacion usuario-municipalidad |