# Aplicación Web para Cálculo y Seguimiento del IMC

**Autor:** Hever González  
**Tecnologías:** Java EE (Jakarta EE 10), JSP, Servlets, JDBC, Bootstrap 5, PostgreSQL, GlassFish 7.  
**Arquitectura:** Modelo–Vista–Controlador (MVC).  
**Base de datos:** PostgreSQL con DataSource JNDI `jdbc/imctrackerDS`.

---

## 🚀 Descripción General

Es una aplicación web desarrollada en **Java EE** bajo el patrón **MVC**, que permite a los usuarios:

- Registrarse e iniciar sesión.  
- Calcular su **Índice de Masa Corporal (IMC)**.  
- Visualizar su **historial de mediciones** a lo largo del tiempo.  
- Consultar su progreso en una **tabla dinámica y gráfica (Chart.js)**.  
- Administrar su perfil personal y actualizar sus datos.

Toda la información se almacena en una base de datos **PostgreSQL** y se consulta a través de un **servicio REST**.

---

## 🧩 Arquitectura del Proyecto

### 🧠 Modelo (Model)
| Clase | Descripción |
|--------|--------------|
| `Usuario` | Representa a cada usuario registrado. |
| `Medicion` | Registra peso, IMC y fecha de cada medición. |
| `Sexo` | Enumeración (`MASCULINO`, `FEMENINO`, `NO_BINARIO`). |
| `Rol` | Enumeración (`USUARIO`, `ADMIN`). |
| `UsuarioDAO`, `MedicionDAO`, `CredencialesDAO` | Interfaces DAO. |
| `UsuarioDAOImpl`, `MedicionDAOImpl`, `CredencialesDAOImpl` | Implementaciones JDBC. |

### 🎨 Vista (View)
| Archivo | Descripción |
|----------|--------------|
| `registro.jsp` | Formulario de registro de usuario. |
| `login.jsp` | Inicio de sesión. |
| `dashboard.jsp` | Panel principal con tabla y gráfica. |
| `perfil.jsp` | Edición de datos del usuario. |
| `medicion_list.jsp` | Lista completa de mediciones. |
| `_layout/header.jspf` / `_layout/footer.jspf` | Plantillas comunes con Bootstrap. |

### ⚙️ Controlador (Controller)
| Servlet | Función |
|----------|----------|
| `RegistroServlet` | Registra usuarios nuevos. |
| `LoginServlet` | Autentica usuarios. |
| `DashboardServlet` | Muestra el panel principal. |
| `MedicionServlet` | Registra nuevas mediciones. |
| `PerfilServlet` | Muestra y actualiza el perfil. |
| `MedicionesApiServlet` | Servicio REST con historial en JSON. |
| `AuthFilter` | Protege rutas privadas y valida sesión. |

---

## 🗄️ Diseño de la Base de Datos

**Base de datos:** `imctrackerdb`  
**Esquema:** `imctracker`

### Tablas principales

#### 🧍 `usuarios`
| Campo | Tipo | Descripción |
|--------|------|-------------|
| id | SERIAL PK | Identificador |
| nombre_completo | VARCHAR(100) | Nombre completo |
| nombre_usuario | VARCHAR(50) | Único |
| contraseña_hash | VARCHAR(255) | Hash con BCrypt |
| edad | INT | Edad mínima: 15 |
| sexo | imctracker.sexo_enum | MASCULINO / FEMENINO / NO_BINARIO |
| estatura_m | NUMERIC(3,2) | Rango: 1.00–2.50 |
| rol | imctracker.rol_enum | USUARIO / ADMIN |
| creado_en | TIMESTAMP | Fecha de registro |

#### ⚖️ `mediciones`
| Campo | Tipo | Descripción |
|--------|------|-------------|
| id | SERIAL PK | Identificador |
| usuario_id | FK → usuarios.id | Usuario asociado |
| peso_kg | NUMERIC(5,2) | Peso |
| imc | NUMERIC(5,2) | IMC calculado |
| fecha_medicion | DATE | Fecha de registro |
| nota | TEXT | Observaciones opcionales |

#### Enums
```sql
CREATE TYPE imctracker.sexo_enum AS ENUM ('MASCULINO', 'FEMENINO', 'NO_BINARIO');
CREATE TYPE imctracker.rol_enum AS ENUM ('USUARIO', 'ADMIN');
