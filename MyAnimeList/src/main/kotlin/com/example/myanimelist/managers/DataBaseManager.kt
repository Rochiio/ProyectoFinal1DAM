package com.example.myanimelist.managers


import org.apache.ibatis.jdbc.ScriptRunner
import java.io.*
import java.sql.*
import java.time.LocalDate

/**
 * Manejador de Bases de Datos Relacionales
 *
 * @author Jose Luis González Sánchez
 * @version 1.0
 */
class DataBaseManager(databaseName: String) : AutoCloseable {
    // No leemos de propiedades, porque no es necesario, estan hardcodeadas
    private val fromProperties = false
    private var serverUrl: String? = null
    private var serverPort: String? = null
    private var dataBaseName: String? = null
    private val user: String? = null
    private val password: String? = null
    private var jdbcDriver: String? = null

    // Para manejar las conexiones y respuestas de las mismas
    private lateinit var connection: Connection

    init {
        if (fromProperties) {
            // initConfigFromProperties();
            println("Comentado el método de leer de propiedades")
        } else {
            initConfig(databaseName)
        }
    }

    /**
     * Carga la configuración de acceso al servidor de Base de Datos
     * Puede ser directa "hardcodeada" o asignada dinámicamente a traves de ficheros .env o properties
     */
    private fun initConfig(db: String) {
        val appPath = System.getProperty("user.dir")
        val dbDir = appPath + File.separator + "db"
        val dbFile = dbDir + File.separator + db

        // Para SQLite solo necesito el driver...
        serverUrl = "localhost" // No es necesario
        serverPort = "3306" // No es necesario
        dataBaseName = dbFile //
        jdbcDriver = "org.sqlite.JDBC" // SQLite
    }

    /**
     * Abre la conexión con el servidor  de base de datos
     *
     * @throws SQLException Servidor no accesible por problemas de conexión o datos de acceso incorrectos
     */
    @Throws(SQLException::class)
    fun open() {
        val url = "jdbc:sqlite:$dataBaseName"
        if (!this::connection.isInitialized || !connection.isValid(1))
            connection = DriverManager.getConnection(url)
    }

    /**
     * Cierra la conexión con el servidor de base de datos
     *
     * @throws SQLException Servidor no responde o no puede realizar la operación de cierre
     */
    @Throws(SQLException::class)
    override fun close() {
        connection.close()
    }

    /**
     * Realiza una consulta a la base de datos de manera "preparada" obteniendo los parametros opcionales si son necesarios
     *
     * @param querySQL consulta SQL de tipo select
     * @param params   parámetros de la consulta parametrizada
     * @return ResultSet de la consulta
     * @throws SQLException No se ha podido realizar la consulta o la tabla no existe
     */
    @Throws(SQLException::class)
    private fun executeQuery(querySQL: String, vararg params: Any?): ResultSet {
        val preparedStatement = connection.prepareStatement(querySQL)
        // Vamos a pasarle los parametros usando preparedStatement
        for (i in params.indices) {
            preparedStatement.setObject(i + 1, parseValue(params[i]))
        }
        return preparedStatement.executeQuery()
    }

    /**
     * Realiza una consulta select a la base de datos de manera "preparada" obteniendo los parametros opcionales si son necesarios
     *
     * @param querySQL consulta SQL de tipo select
     * @param params   parámetros de la consulta parametrizada
     * @return ResultSet de la consulta
     * @throws SQLException No se ha podido realizar la consulta o la tabla no existe
     */
    @Throws(SQLException::class)
    fun select(querySQL: String, vararg params: Any?): ResultSet {
        return executeQuery(querySQL, *params)
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
    @Throws(SQLException::class)
    fun select(querySQL: String, limit: Int, offset: Int, vararg params: Any): ResultSet {
        val query = "$querySQL LIMIT $limit OFFSET $offset"
        return executeQuery(query, *params)
    }

    /**
     * Realiza una consulta de tipo insert de manera "preparada" con los parametros opcionales si son encesarios
     *
     * @param insertSQL consulta SQL de tipo insert
     * @param params    parámetros de la consulta parametrizada
     * @return Clave del registro insertado
     * @throws SQLException tabla no existe o no se ha podido realizar la operación
     */
    @Throws(SQLException::class)
    fun insert(insertSQL: String?, vararg params: Any?): ResultSet {
        // Con return generated keys obtenemos las claves generadas las claves es autonumerica por ejemplo,
        // el id de la tabla si es autonumérico. Si no quitar.
        val preparedStatement = connection.prepareStatement(insertSQL, PreparedStatement.RETURN_GENERATED_KEYS)
        // Vamos a pasarle los parametros usando preparedStatement
        for (i in params.indices) {
            preparedStatement.setObject(i + 1, parseValue(params[i]))
        }
        preparedStatement.executeUpdate()
        return preparedStatement.generatedKeys
    }

    /**
     * Realiza una consulta de tipo update de manera "preparada" con los parametros opcionales si son necesarios
     *
     * @param updateSQL consulta SQL de tipo update
     * @param params    parámetros de la consulta parametrizada
     * @return número de registros actualizados
     * @throws SQLException tabla no existe o no se ha podido realizar la operación
     */
    @Throws(SQLException::class)
    fun update(updateSQL: String, vararg params: Any?): Int {
        return updateQuery(updateSQL, *params)
    }

    /**
     * Realiza una consulta de tipo delete de manera "preparada" con los parametros opcionales si son encesarios
     *
     * @param deleteSQL consulta SQL de tipo delete
     * @param params    parámetros de la consulta parametrizada
     * @return número de registros eliminados
     * @throws SQLException tabla no existe o no se ha podido realizar la operación
     */
    @Throws(SQLException::class)
    fun delete(deleteSQL: String, vararg params: Any?): Int {
        return updateQuery(deleteSQL, *params)
    }

    /**
     * Realiza una consulta de tipo update, es decir que modifca los datos, de manera "preparada" con los parametros opcionales si son encesarios
     *
     * @param genericSQL consulta SQL de tipo update, delete, creted, etc.. que modifica los datos
     * @param params     parámetros de la consulta parametrizada
     * @return número de registros eliminados
     * @throws SQLException tabla no existe o no se ha podido realizar la operación
     */
    @Throws(SQLException::class)
    private fun updateQuery(genericSQL: String, vararg params: Any?): Int {
        // Con return generated keys obtenemos las claves generadas
        val preparedStatement = connection.prepareStatement(genericSQL)
        // Vamos a pasarle los parametros usando preparedStatement
        for (i in params.indices) {
            preparedStatement.setObject(i + 1, parseValue(params[i]))
        }
        return preparedStatement.executeUpdate()
    }

    /**
     * Crea una consulta genérica para crear tablas, vistas, procedimientos
     *
     * @param genericSQL consulta genérica
     * @return si ha tenido, 1
     * @throws SQLException no se ha podido realizar la operación
     */
    @Throws(SQLException::class)
    fun genericUpdate(genericSQL: String?): Int {
        // Con return generated keys obtenemos las claves generadas
        val preparedStatement = connection.prepareStatement(genericSQL)
        return preparedStatement.executeUpdate()
    }

    /**
     * Carga los datos desde un fichero externo
     */
    @Throws(FileNotFoundException::class)
    fun initData(sqlFile: String, logWriter: Boolean) {
        val sr = ScriptRunner(connection)
        sr.setEscapeProcessing(false)
        val reader: Reader = BufferedReader(FileReader(sqlFile))
        sr.setLogWriter(if (logWriter) PrintWriter(System.out) else null)
        sr.runScript(reader)
    }

    /**
     * Inicia una transacción
     */
    @Throws(SQLException::class)
    fun beginTransaction() {
        connection.autoCommit = false
    }

    /**
     * Confirma una transacción
     */
    @Throws(SQLException::class)
    fun commit() {
        connection.commit()
        connection.autoCommit = true
    }

    /**
     * Cancela una transacción
     */
    @Throws(SQLException::class)
    fun rollback() {
        connection.rollback()
        connection.autoCommit = true
    }

    // JDBC no sabe convertir localDate a sql date asi que parsea aqui
    private fun parseValue(value: Any?): Any? {
        return if (value is LocalDate) Date.valueOf(value) else value
    }
}