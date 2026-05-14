# Actividad Obligatoria 1 y 2 - Móvil

Aplicación Android de gestión de contactos desarrollada en Android Studio para la materia Programación de Aplicaciones Móviles I.

## Funcionalidades

- Login con usuario y contraseña hardcodeados
- Persistencia de sesión con SharedPreferences
- Cierre de sesión desde el menú
- Lista de contactos
- Búsqueda de contactos por nombre
- Alta de contactos
- Modificación de contactos
- Baja de contactos
- Llamada a un contacto desde la lista
- Almacenamiento persistente de contactos con SQLite

## Credenciales

- Usuario: admin
- Contraseña: 1234

## Tecnologías utilizadas

- Java
- XML
- Android Studio
- SharedPreferences
- SQLite

## Comportamiento de la aplicación

- Si el usuario inicia sesión correctamente, la aplicación guarda el estado de autenticación.
- Al volver a abrir la app, si la sesión sigue activa, se accede directamente a la lista de contactos.
- Los contactos se almacenan en una base de datos SQLite local.
- Los cambios realizados en los contactos se mantienen aunque la aplicación se cierre.





