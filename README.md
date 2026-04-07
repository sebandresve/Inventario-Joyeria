# Sistema de Inventario - Joyería

## Requisitos

* Java 17 o superior
* PostgreSQL instalado

## Base de Datos

1. Crear base de datos:
   CREATE DATABASE joyeria;

2. Restaurar backup:
   psql -U postgres -d joyeria < backup.sql

## Ejecución

Ejecutar:
java -jar JoyeriaInventario.jar

## Notas

* Asegurarse de que PostgreSQL esté corriendo
* Configurar usuario y contraseña en archivo "ConexionDB" si es necesario:

URL = "jdbc:postgresql://localhost:5432/joyeria";
USER = "postgres";
PASSWORD = "sasa";
