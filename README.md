# Ejecución del código

## Instalación y configuración de la base de datos


1. Abrimos nuestro proyecto en una terminal y ejecutamos el siguiente comando:
   ```docker-compose up -d```, De esta forma, descargamos la imagen de MySQL desde Docker Hub y la configuramos en nuestro equipo.
2. Una vez que nuestra base de datos esté activa, nos dirigimos a la carpeta del proyecto llamada data. En el archivo tables.sql, encontraremos las tablas utilizadas en este proyecto. Las copiamos y procedemos a ejecutar los scripts en nuestra base de datos.
3. Con nuestra base de datos y tablas creadas, procedemos a ejecutar el proyecto en nuestro editor de código preferido.


### Endpoint de nuestro servicio REST

###### CRUD para gestionar los productos
- Mostrar todos los productos - Método GET
http://localhost:8002/franchises/products/api

- Ver detalle de un producto en especifico - Método GET 
http://localhost:8002/franchises/products/api/7 

- Guardar un producto - Método POST
  http://localhost:8002/franchises/products/api

JSON que se enviá en el body
```dart
{
    "name": "Franchise 2"
}
```

- Actualizar el stock del producto - Método PUT
  http://localhost:8002/franchises/products/api/7

JSON que se enviá en el body
```dart
{
    "name": "Franchise update"
}
```

- Actualizar el nombre y el stock del producto - Método PUT
  http://localhost:8002/franchises/products/api/all/7

JSON que se enviá en el body
```dart
{
    "quantity":23,
    "name": "Product update"
}
```
- Eliminar un producto - Método Delete
  http://localhost:8002/franchises/products/api/7


###### CRUD para gestionar los las sucursales
- Mostrar todos las sucursales - Método GET
  http://localhost:8002/franchises/branches/api

- Ver detalle de un sucursal en especifico - Método GET
  http://localhost:8002/franchises/branches/api/3

- Guardar una sucursal - Método POST
  http://localhost:8002/franchises/branches/api

JSON que se enviá en el body
```dart
{
    "name": "Breanch 1"
}
```

- Crear un producto desde una sucursal en especifica - Método POST
  http://localhost:8002/franchises/branches/api/assign/1

JSON que se enviá en el body
```dart
{
    "name": "Product 3",
    "quantity": 4
}
```
- Actualizar una sucursal en especifica - Método PUT
http://localhost:8002/franchises/branches/api/1


- Eliminar un producto a las sucursal - Método Delete
  http://localhost:8002/franchises/branches/api/delete/1/22

###### CRUD para gestionar los las franquicias

- Mostrar todas las franquicias - Método GET
  http://localhost:8002/franchises/franchises/api

- Mostrar una franquicia - Método GET
  http://localhost:8002/franchises/franchises/api/7


- Crear una franquicia - Método POST
  http://localhost:8002/franchises/franchises/api

JSON que se enviá en el body
```dart
{
"name": "Franquicia",
}
```
- Modificar una franquicia - Método PUT
  http://localhost:8002/franchises/franchises/api/1

JSON que se enviá en el body
```dart
{
"name": "Franquicia Update",
}
```
- Eliminar un producto a las sucursal - Método Delete
-  http://localhost:8002/franchises/branches/api/17

- Crear una sucursal desde la franquicia - Método POST
  http://localhost:8002/franchises/franchises/api/assign/1

JSON que se enviá en el body
```dart
{
"name": "Branch update"
}
```

- Eliminar un franquicia - Método Delete
http://localhost:8002/franchises/franchises/api/1