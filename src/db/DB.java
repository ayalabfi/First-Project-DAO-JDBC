package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {

    // a virável abaixo cria uma conexão em estado null
    private static Connection conn = null;

    /* a função abaixo pega a conexão em estado null e imposrta as informações
    necessárias para iniciar uma nova conexão, sendo elas a URL do banco de dados
    e as propriedades como login, senha e caminho do banco
     */

    public static Connection getConnection() {
        if (conn == null) {
            try {
                Properties props = loadProperties();
                String url = props.getProperty("dburl");
                conn = DriverManager.getConnection(url, props);
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
        return conn;
    }

    /* a função abaixo fecha a conexão com o banco de dados, retornando-a para o estado null
    por meio do comando variávelDaConexão.close();, no caso, conn.close();
     */
    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }


    /*
    A função abaixo cria um arquivo de extensão Properties cujo o nome é props,
    copia por meio do FileInputStream as informações do arquivo db.properties que
    contem as informações de conexão do banco de dados e salva na função props.
     */
    private static Properties loadProperties() {
        try (FileInputStream fs = new FileInputStream("db.properties")) {
            Properties props = new Properties();
            props.load(fs);
            return props;
        } catch (IOException e) {
            throw new DbException(e.getMessage());
        }
    }

    /* Cria o fechamento do Statement lançando a exceção como RuntimeException para não ter que
    tratar sempre com Try Catch
     */

    public static void closeStatement(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    /* Cria o fechamento do ResultSet lançando a exceção como RuntimeException para não ter que
tratar sempre com Try Catch
 */

    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

}
