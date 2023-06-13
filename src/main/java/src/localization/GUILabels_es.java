package src.localization;

import java.util.ListResourceBundle;

public class GUILabels_es extends ListResourceBundle {

    private static final Object[][] contents =
    {
            {"log_in", "INICIAR sesión"},
            {"login", "login"},
            {"password", "password"},
            {"sign_up", "REGISTRARSE"},
            {"sign_in", "tienes una CUENTA? ENTRAR"},
            {"auth", "AUTORIZACIÓN"},
            {"reg", "REGISTRO"},
            {"become", " SIN CUENTA? REGISTRARSE"},
            {"already", "ya existe"},
            {"invalid", "entrada Incorrecta"},
            {"add", "ADD"},
            {"exit", "EXIT"},
            {"clear", "CLEAR"},
            {"b_help", "AYUDA"},
            {"info", "INFO"},
            {"map", "MAPA"},
            {"count", "CONTAR por TIPO"},
            {"script", "EJECUTAR SCRIPT"},
            {"remove_type", "ELIMINAR por TIPO"},
            {"remove_id", "ELIMINAR por ID"},
            {"upd", "ACTUALIZAR por ID"},
            {"username", "nombre de USUARIO"},
            {"vehicle", "Vehículo"},
            {"name", "Nombre"},
            {"creator", "Creator"},
            {"coords", "Coordenadas"},
            {"creation_date", "fecha de creación"},
            {"type", "Type"},
            {"capacity", "Capacidad"},
            {"ep", "Power"},
            {"fc", "Consumo"},
            {"all", "ALL"},
            {"script_help", """
                            add {element}: añadir un nuevo elemento a la colección
                            execute_script file_name: Leer y ejecutar el script desde el archivo especificado. El script contiene comandos en la misma forma en que los introduce
                            clear: borrar colección
                            update id: actualizar el valor de un elemento de colección cuyo id es igual al especificado
                            remove_by_id id: eliminar un elemento de la colección por su id
                            remove_all_by_type type: elimina de la colección todos los elementos cuyo valor de campo type es equivalente al valor especificado
                            """},
            {"help", """
                            add {element}: añadir un nuevo elemento a la colección
                            execute_script file_name: Leer y ejecutar el script desde el archivo especificado. El script contiene comandos en la misma forma en que los introduce
                            clear: borrar colección
                            update id: actualizar el valor de un elemento de colección cuyo id es igual al especificado
                            remove_by_id id: eliminar un elemento de la colección por su id
                            count_greater_than_type type: muestra el número de elementos cuyo valor de campo type es mayor que el especificado
                            help: muestra la ayuda de los comandos disponibles
                            exit: finalizar el programa (sin guardar en un archivo)
                            remove_all_by_type type: elimina de la colección todos los elementos cuyo valor de campo type es equivalente al valor especificado
                            info: muestra la información de la colección en el flujo de salida estándar (tipo, fecha de inicialización, número de elementos, etc.)
                            """},
            {"separator", ","},
            {"date_format", "dd/MM/yyyy HH:mm:ss Z"},
            {"col_type", "tipo de colección"},
            {"init_date", "fecha de inicialización"},
            {"amount", "número de elementos"},
            {"delete_ps","solo puede eliminar los vehículos de los que es creador."},
            {"doesnt_exist", "No existe"},
            {"not_ur", "No es Tuyo"},
            {"deleted", "Número de vehículos eliminados"},
            {"count_res", "el Número de TC del tipo es mayor que el dado"},
            {"table", "MESA"}
    };

    public Object[][] getContents() {
        return contents;
    }

}
