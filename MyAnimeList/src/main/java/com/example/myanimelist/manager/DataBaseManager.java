package com.example.myanimelist.manager;


import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.*;
import java.sql.*;

/**
 * Manejador de Bases de Datos Relacionales
 *
 * @author Jose Luis González Sánchez
 * @version 1.0
 */
public class DataBaseManager implements AutoCloseable {
    // No leemos de propiedades, porque no es necesario, estan hardcodeadas
    private final boolean fromProperties = false;
    private String serverUrl;
    private String serverPort;
    private String dataBaseName;
    private String user;
    private String password;


    private static DataBaseManager instance = null;
    /*
        Tipos de Driver
        SQLite: "org.sqlite.JDBC";
        MySQL: "com.mysql.jdbc.Driver"
        MariaDB: "com.mysql.cj.jdbc.Driver"
        PostgreSQL: "org.postgresql.Driver"
        H2: "org.h2.Driver"
        */
    private String jdbcDriver;
    // Para manejar las conexiones y respuestas de las mismas
    private Connection connection;

    public static DataBaseManager getInstance(){
        if(instance == null)
            instance = new DataBaseManager();
        return instance;
    }

    private DataBaseManager(){
        if (fromProperties) {
            // initConfigFromProperties();
            System.out.println("Comentado el método de leer de propiedades");
        } else {
            initConfig();
        }
    }

    /**
     * Carga la configuración de acceso al servidor de Base de Datos
     * Puede ser directa "hardcodeada" o asignada dinámicamente a traves de ficheros .env o properties
     */
    private void initConfig() {
        String APP_PATH = System.getProperty("user.dir");
        String DB_DIR = APP_PATH + File.separator + "db";
        String DB_FILE = DB_DIR + File.separator + "anime.db";

        // Para SQLite solo necesito el driver...
        serverUrl = "localhost"; // No es necesario
        serverPort = "3306"; // No es necesario
        dataBaseName = DB_FILE; //
        jdbcDriver = "org.sqlite.JDBC"; // SQLite
//        user = "dam"; // No es necesario
//        password = "dam1234"; // No es necesario


    }

    /**
     * Carga la configuración de acceso al servidor de Base de Datos desde properties
     */
    /*private void initConfigFromProperties() {
        ApplicationProperties properties = new ApplicationProperties();
        serverUrl = properties.readProperty("database.server.url");
        serverPort = properties.readProperty("database.server.port");
        dataBaseName = properties.readProperty("database.name");
        jdbcDriver = properties.readProperty("database.jdbc.driver");
        user = properties.readProperty("database.user");
        password = properties.readProperty("database.password");
    }*/

    /**
     * Abre la conexión con el servidor  de base de datos
     *
     * @throws SQLException Servidor no accesible por problemas de conexión o datos de acceso incorrectos
     */
    public void open() throws SQLException {
        String url = "jdbc:sqlite:" + dataBaseName;
        // MySQL jdbc:mysql://localhost/prueba", "root", "1dam"
        //String url = "jdbc:mariadb://" + this.serverUrl + ":" + this.serverPort + "/" + this.dataBaseName;
        // System.out.println(url);
        // Obtenemos la conexión
        // connection = DriverManager.getConnection(url, user, password);
        if (connection == null || !connection.isValid(1))
            connection = DriverManager.getConnection(url);
    }

    /**
     * Cierra la conexión con el servidor de base de datos
     *
     * @throws SQLException Servidor no responde o no puede realizar la operación de cierre
     */
    public void close() throws SQLException {
        if (connection == null) return;

        connection.close();
    }

    /**
     * Realiza una consulta a la base de datos de manera "preparada" obteniendo los parametros opcionales si son necesarios
     *
     * @param querySQL consulta SQL de tipo select
     * @param params   parámetros de la consulta parametrizada
     * @return ResultSet de la consulta
     * @throws SQLException No se ha podido realizar la consulta o la tabla no existe
     */
    private ResultSet executeQuery(String querySQL, Object... params) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(querySQL);
        // Vamos a pasarle los parametros usando preparedStatement
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }
        return preparedStatement.executeQuery();
    }

    /**
     * Realiza una consulta select a la base de datos de manera "preparada" obteniendo los parametros opcionales si son necesarios
     *
     * @param querySQL consulta SQL de tipo select
     * @param params   parámetros de la consulta parametrizada
     * @return ResultSet de la consulta
     * @throws SQLException No se ha podido realizar la consulta o la tabla no existe
     */
    public ResultSet select( String querySQL, Object... params) throws SQLException {
        return executeQuery(querySQL, params);
    }

    /**
     * Realiza una consulta select a la base de datos de manera "preparada" obteniendo los parametros opcionales si son necesarios
     *
     * @param querySQL consulta SQL de tipo select
     * @param limit    número de registros de la página
     * @param offset   desplazamiento de registros o número de registros ignorados para comenzar la devolución
     * @param params   parámetros de la consulta parametrizada
     * @return ResultSet de la consulta
     * @throws SQLException No se ha podido realizar la consulta o la tabla no existe o el desplazamiento es mayor que el número de registros
     */
    public ResultSet select(String querySQL, int limit, int offset, Object... params) throws SQLException {
        String query = querySQL + " LIMIT " + limit + " OFFSET " + offset;
        return executeQuery(query, params);
    }

    /**
     * Realiza una consulta de tipo insert de manera "preparada" con los parametros opcionales si son encesarios
     *
     * @param insertSQL consulta SQL de tipo insert
     * @param params    parámetros de la consulta parametrizada
     * @return Clave del registro insertado
     * @throws SQLException tabla no existe o no se ha podido realizar la operación
     */
    public ResultSet insert(String insertSQL, Object... params) throws SQLException {
        // Con return generated keys obtenemos las claves generadas las claves es autonumerica por ejemplo,
        // el id de la tabla si es autonumérico. Si no quitar.
        PreparedStatement preparedStatement = connection.prepareStatement(insertSQL, PreparedStatement.RETURN_GENERATED_KEYS);
        // Vamos a pasarle los parametros usando preparedStatement
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }
        preparedStatement.executeUpdate();
        return preparedStatement.getGeneratedKeys();
    }

    /**
     * Realiza una consulta de tipo update de manera "preparada" con los parametros opcionales si son necesarios
     *
     * @param updateSQL consulta SQL de tipo update
     * @param params    parámetros de la consulta parametrizada
     * @return número de registros actualizados
     * @throws SQLException tabla no existe o no se ha podido realizar la operación
     */
    public int update(String updateSQL, Object... params) throws SQLException {
        return updateQuery(updateSQL, params);
    }

    /**
     * Realiza una consulta de tipo delete de manera "preparada" con los parametros opcionales si son encesarios
     *
     * @param deleteSQL consulta SQL de tipo delete
     * @param params    parámetros de la consulta parametrizada
     * @return número de registros eliminados
     * @throws SQLException tabla no existe o no se ha podido realizar la operación
     */
    public int delete(String deleteSQL, Object... params) throws SQLException {
        return updateQuery(deleteSQL, params);
    }

    /**
     * Realiza una consulta de tipo update, es decir que modifca los datos, de manera "preparada" con los parametros opcionales si son encesarios
     *
     * @param genericSQL consulta SQL de tipo update, delete, creted, etc.. que modifica los datos
     * @param params     parámetros de la consulta parametrizada
     * @return número de registros eliminados
     * @throws SQLException tabla no existe o no se ha podido realizar la operación
     */
    private int updateQuery(String genericSQL, Object... params) throws SQLException {
        // Con return generated keys obtenemos las claves generadas
        PreparedStatement preparedStatement = connection.prepareStatement(genericSQL);
        // Vamos a pasarle los parametros usando preparedStatement
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }
        return preparedStatement.executeUpdate();
    }

    /**
     * Crea una consulta genérica para crear tablas, vistas, procedimientos
     *
     * @param genericSQL consulta genérica
     * @return si ha tenido, 1
     * @throws SQLException no se ha podido realizar la operación
     */
    public int genericUpdate(String genericSQL) throws SQLException {
        // Con return generated keys obtenemos las claves generadas
        PreparedStatement preparedStatement = connection.prepareStatement(genericSQL);
        return preparedStatement.executeUpdate();
    }

    /**
     * Carga los datos desde un fichero externo
     */
    public void initData(String sqlFile, boolean logWriter) throws FileNotFoundException {
        ScriptRunner sr = new ScriptRunner(connection);
        Reader reader = new BufferedReader(new FileReader(sqlFile));
        sr.setLogWriter(logWriter ? new PrintWriter(System.out) : null);
        sr.runScript(reader);
    }

    /**
     * Inicia una transacción
     */
    public void beginTransaction() throws SQLException {
        connection.setAutoCommit(false);
    }

    /**
     * Confirma una transacción
     */
    public void commit() throws SQLException {
        connection.commit();
        connection.setAutoCommit(true);
    }

    /**
     * Cancela una transacción
     */
    public void rollback() throws SQLException {
        connection.rollback();
        connection.setAutoCommit(true);
    }
}
