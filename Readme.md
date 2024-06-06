## Configuración de Variables de Entorno

Para configurar las variables de entorno necesarias para que el proyecto funcione correctamente, sigue estos pasos:

1. **Crear el archivo `.env`**:
    - En el directorio raíz del proyecto, crea un archivo llamado `.env`.

2. **Agregar las variables de entorno**:
    - Copia y pega las siguientes variables de entorno en el archivo `.env`:

    ```plaintext
    # Archivo .env
    # Variables de entorno necesarias para el proyecto

    # URI de MongoDB
    MONGODB_URI=mongodb+srv://usuario:contraseña@cluster.mongodb.net/test

    # Nombre de la base de datos
    MONGODB_DATABASE=Friendly-test

    # Secreto para JWT
    JWT_SECRET=tu_secreto_para_jwt
    ```

3. **Reemplazar los valores de ejemplo**:
    - Asegúrate de reemplazar `usuario`, `contraseña` y `tu_secreto_para_jwt` con los valores reales que utilizarás.

4. **Guardar el archivo**:
    - Guarda el archivo `.env` después de agregar y modificar las variables necesarias.

5. **No subir el archivo `.env` al repositorio**:
    - Asegúrate de que el archivo `.env` esté listado en el archivo `.gitignore` para evitar que se suba al repositorio y así mantener seguras las variables de entorno sensibles.

**Nota**: Es fundamental no compartir el archivo `.env` públicamente ni incluirlo en el control de versiones para proteger la información sensible.
