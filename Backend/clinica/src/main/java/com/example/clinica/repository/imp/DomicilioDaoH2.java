package com.example.clinica.repository.imp;

import com.example.clinica.domain.Domicilio;
import com.example.clinica.repository.IDao;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DomicilioDaoH2 implements IDao<Domicilio> {
    private static final Logger logger = Logger.getLogger(DomicilioDaoH2.class);
    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    private final static String DB_URL = "jdbc:h2:~/test;";
    private final static String DB_USER = "sa";
    private final static String DB_PASSWORD = "";

    @Override

    public Domicilio guardar(Domicilio domicilio) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            logger.info("Se crea una conexion a la base de datos en el metodo Guardar");

            preparedStatement = connection.prepareStatement("INSERT INTO domicilio(calle,numero,localidad,provincia) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, domicilio.getCalle());
            preparedStatement.setString(2, domicilio.getNumero());
            preparedStatement.setString(3, domicilio.getLocalidad());
            preparedStatement.setString(4, domicilio.getProvincia());

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next()){
                domicilio.setId(rs.getLong(1));
            }
            logger.info("El domicilio con el id " + domicilio.getId() + " fue guardado en la base de datos");
            logger.info("Se cierra la conexion a la base de datos en el metodo Guardar");
            preparedStatement.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return domicilio;
    }

    @Override
    public void eliminar(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            logger.info("Se crea una conexion a la base de datos en el metodo Eliminar");

            preparedStatement = connection.prepareStatement("DELETE FROM domicilio where id=?");
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
            logger.info("El domicilio con el id " + id + " fue eliminado en la base de datos");
            logger.info("Se cierra la conexion a la base de datos en el metodo Eliminar");
            preparedStatement.close();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Domicilio buscar(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Domicilio domicilio = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            logger.info("Se crea una conexion a la base de datos en el metodo Buscar");
            preparedStatement = connection.prepareStatement("SELECT * FROM domicilio WHERE id =?");
            preparedStatement.setLong(1, id);

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Long idTurno = result.getLong("id");
                String calle = result.getString("calle");
                String numero = result.getString("numero");
                String localidad = result.getString("localidad");
                String provincia = result.getString("provincia");
                domicilio = new Domicilio(calle,numero,localidad,provincia);
                domicilio.setId(idTurno);

            }
            logger.info("El domicilio con el id " + id + " fue buscado en la base de datos en el metodo Buscar");
            logger.info("Se cierra la conexion a la base de datos");
            preparedStatement.close();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return domicilio;
    }

    @Override
    public Domicilio buscar(String email) {
        return null;
    }

    @Override
    public Domicilio actualizar(Domicilio domicilio, Long id) {
        return null;
    }

    @Override
    public List<Domicilio> buscarTodos() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Domicilio> list_domicilio = new ArrayList();
        Domicilio domicilio = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            logger.info("Se crea una conexion a la base de datos en el metodo buscarTodos");
            preparedStatement = connection.prepareStatement("SELECT * FROM domicilio");

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Long id = result.getLong("id");
                String calle = result.getString("calle");
                String numero = result.getString("numero");
                String localidad = result.getString("localidad");
                String provincia = result.getString("provincia");
                domicilio = new Domicilio(calle,numero,localidad,provincia);
                domicilio.setId(id);


                list_domicilio.add(domicilio);
            }
            logger.info("Fue requerida la lista de domicilios a la base de datos buscarTodos");
            logger.info("Se cierra la conexion a la base de datos");
            preparedStatement.close();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list_domicilio;
    }
}
