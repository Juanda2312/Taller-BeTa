# ⚙ Taller BeTa — Sistema de Gestión de Órdenes de Mantenimiento

Aplicación de escritorio desarrollada en **JavaFX** para gestionar órdenes de mantenimiento vehicular. Permite a los clientes registrarse, iniciar sesión y administrar sus órdenes de servicio desde un panel de control moderno.

---

## 🖥 Tecnologías

| Tecnología | Versión |
|---|---|
| Java | 17 |
| JavaFX | 21.0.6 |
| Maven | 3.8.5 |
| Lombok | 1.18.46 |
| Jackson (XML) | 2.15.0 |
| JUnit Jupiter | 5.12.1 |

---

## 📁 Estructura del Proyecto

```
TallerBeTa/
├── src/
│   └── main/
│       ├── java/co/edu/uniquindio/tallerbeta/
│       │   ├── HelloApplication.java       # Punto de entrada JavaFX
│       │   ├── Launcher.java               # Main wrapper
│       │   ├── controller/                 # Controladores FXML
│       │   │   ├── ControladorPrincipal.java
│       │   │   ├── logincontroller.java
│       │   │   ├── ordenController.java
│       │   │   └── registrocontroller.java
│       │   ├── model/
│       │   │   ├── Enum/                   # Estado, Especializacion
│       │   │   └── entity/                 # Persona, Usuario, Cliente, Mecanico, Orden...
│       │   ├── repository/                 # Acceso a datos XML
│       │   ├── service/                    # Lógica de negocio
│       │   └── util/                       # Constantes, Persistencia, DatosDePrueba
│       └── resources/co/edu/uniquindio/tallerbeta/
│           ├── login.fxml
│           ├── orden.fxml
│           └── registro.fxml
├── data/                                   # Archivos XML generados en tiempo de ejecución
├── pom.xml
└── mvnw / mvnw.cmd
```

---

## 🚀 Ejecución

### Prerrequisitos

- Java 17 o superior instalado
- Maven 3.8+ (o usar el wrapper incluido `mvnw`)

### Compilar y ejecutar

```bash
# Con Maven Wrapper (recomendado)
./mvnw clean javafx:run

# En Windows
mvnw.cmd clean javafx:run
```

---

## 👥 Cuentas de Prueba

Al iniciar por primera vez, se cargan automáticamente los siguientes usuarios:

| Nombre | Correo | Contraseña |
|---|---|---|
| Juan Tapiero | juan@gmail.com | juan123 |
| Jose Bedoya | jose@gmail.com | jose123 |
| Maria Lopez | maria@gmail.com | maria123 |
| Carlos Reyes | carlos@gmail.com | carlos123 |
| Ana Gutierrez | ana@gmail.com | ana123 |
| Pedro Morales | pedro@gmail.com | pedro123 |
| Laura Castillo | laura@gmail.com | laura123 |
| Andres Herrera | andres@gmail.com | andres123 |
| Sofia Vargas | sofia@gmail.com | sofia123 |
| Diego Ramirez | diego@gmail.com | diego123 |

---

## ✅ Reglas de Negocio

- **RN-01:** El correo y la cédula de cada cliente deben ser únicos.
- **RN-02:** No se pueden registrar más de 10 órdenes activas simultáneamente.
- **RN-03:** Solo se asignan mecánicos que no tengan una orden en curso.
- **RN-04:** Solo se pueden editar o eliminar órdenes en estado `SINASIGNAR`.

---

## 📊 Estados de una Orden

| Estado | Descripción |
|---|---|
| `SINASIGNAR` | Orden creada, pendiente de mecánico |
| `ENPROCESO` | Mecánico asignado, trabajo en curso |
| `FINALIZADO` | Mantenimiento completado |

---

## 💾 Persistencia

Los datos se almacenan en archivos XML en la carpeta `data/` del directorio raíz:

- `data/cliente.xml`
- `data/mecanico.xml`
- `data/orden.xml`
- `data/administrador.xml`
- `data/recepcionista.xml`

Los archivos se crean automáticamente al arrancar la aplicación por primera vez.

---

## 🏗 Arquitectura

El proyecto sigue una arquitectura en capas:

```
Vista (FXML) → Controlador → Servicio → Repositorio → XML (Jackson)
```

- **Controladores:** Manejan eventos de la interfaz y delegan al servicio.
- **Servicios:** Contienen la lógica de negocio y validaciones.
- **Repositorios:** Gestionan la lectura y escritura de datos XML.
- **`ControladorPrincipal`:** Singleton que centraliza el acceso al servicio y al usuario en sesión.

---

## 📝 Licencia

Proyecto académico — Universidad del Quindío · `co.edu.uniquindio`
