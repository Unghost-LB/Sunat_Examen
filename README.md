Sistema de Consulta de RUC - SUNAT (Examen G14)

Este proyecto es una API REST desarrollada con Spring Boot que permite consultar información de empresas mediante el RUC, integrándose con un proveedor externo vía OpenFeign y persistiendo los datos en PostgreSQL.

- Instalación

1. Clonar el repositorio.
2. Configurar las variables mencionadas en su IDE (IntelliJ) o terminal.
3. Ejecutar con `./mvnw spring-boot:run`.

- Configuración y Ejecución

Variables de Entorno
Por seguridad (cumpliendo con el requisito de "No Token Hardcoded"), el sistema requiere las siguientes variables de entorno:
- `DECOLECTA_TOKEN`: Token de autenticación para la API del proveedor.
- `DB_PASSWORD`: Contraseña de su base de datos local PostgreSQL.

En este caso realice los siguientes pasos para poder inicialzar `DECOLECTA_TOKEN` y `DB_PASSWORD` en el proyecto

1. Primero se va a  `Run` > `Edit Configurations`.
2. Se selecciono la clase principal `SunatConsultaApplication`.
3. En el campo **Environment variables**, se clic en el icono de edición y se agrego:
   `DECOLECTA_TOKEN=token_aqui`;
   `DB_PASSWORD=clave_bd_aqui`
4. Aplique los cambios y reinicie la aplicación.

- Manejo de Excepciones

Se han implementado manejadores globales para las siguientes excepciones:
1. InvalidRucException (400): Validación local cuando el RUC no tiene 11 dígitos.
2. ExternalServiceException (400/502): Error cuando el proveedor no reconoce el RUC o el servicio externo falla.
3. GeneralException (500): Red de seguridad para errores inesperados en el servidor.

- Ejemplos de Uso (CURL)

```bash
# Consulta Exitosa
curl -X GET "http://localhost:8080/api/sunat/ruc/20601030013"

# Consulta con Error
curl -X GET "http://localhost:8080/api/sunat/ruc/12345"

# Consulta de RUC inexistente
curl -X GET "http://localhost:8080/api/sunat/ruc/11111111111"

# Historial de Consultas
curl -X GET "http://localhost:8080/api/sunat/ruc/20601030013/consultas"
