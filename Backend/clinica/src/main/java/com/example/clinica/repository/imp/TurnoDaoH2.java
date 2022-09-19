package com.example.clinica.repository.imp;

import com.example.clinica.domain.Turno;
import com.example.clinica.repository.IDao;
import com.example.clinica.util.Util;
import org.apache.log4j.Logger;
import org.apache.tomcat.jni.Local;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;

public class TurnoDaoH2 implements IDao<Turno> {
    private static final Logger logger = Logger.getLogger(TurnoDaoH2.class);
    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    private final static String DB_URL = "jdbc:h2:~/test;";
    private final static String DB_USER = "sa";
    private final static String DB_PASSWORD = "";

    @Override
    public Turno guardar(Turno turno) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            logger.info("Se crea una conexion a la base de datos en el metodo Guardar");

            preparedStatement = connection.prepareStatement("INSERT INTO TURNO(fecha,odontologoid,pacienteid) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setDate(1, java.sql.Date.valueOf(turno.getFecha()));
            preparedStatement.setLong(2, turno.getOdontologoId());
            preparedStatement.setLong(3, turno.getPacienteId());

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next()){
                turno.setId(rs.getLong(1));
            }
            logger.info("El turno con el id " + turno.getId() + " fue guardado en la base de datos");
            logger.info("Se cierra la conexion a la base de datos en el metodo Guardar");
            preparedStatement.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return turno;
    }

    @Override
    public void eliminar(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            logger.info("Se crea una conexion a la base de datos en el metodo Eliminar");
            // Odontologo odontologoParaEliminar = buscar(id);

            preparedStatement = connection.prepareStatement("DELETE FROM turno where id=?");
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
            logger.info("El turno con el id " + id + " fue eliminado en la base de datos");
            logger.info("Se cierra la conexion a la base de datos en el metodo Eliminar");
            preparedStatement.close();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Turno buscar(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Turno turno = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            logger.info("Se crea una conexion a la base de datos en el metodo Buscar");
            preparedStatement = connection.prepareStatement("SELECT * FROM turno WHERE id =?");
            preparedStatement.setLong(1, id);

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Long idTurno = result.getLong("id");
                LocalDate fecha = result.getDate("fecha").toLocalDate();
                Long idOdontologo = result.getLong("odontologoId");
                Long idPaciente = result.getLong("pacienteId");
                turno = new Turno(fecha,idOdontologo,idPaciente);
                turno.setId(idTurno);
//                turno.setPacienteId(idPaciente);
//                turno.setOdontologoId(idOdontologo);
            }
            logger.info("El Turno con el id " + id + " fue buscado en la base de datos en el metodo Buscar");
            logger.info("Se cierra la conexion a la base de datos");
            preparedStatement.close();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return turno;
    }

    @Override
    public Turno actualizar(Turno turno, Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            logger.info("Se crea una conexion a la base de datos en el metodo Actualizar");

            preparedStatement = connection.prepareStatement("UPDATE turno SET fecha=? , odontologoId=?, pacienteId=? WHERE id=?");
            preparedStatement.setDate(1, java.sql.Date.valueOf(turno.getFecha()));
            preparedStatement.setLong(2, turno.getOdontologoId());
            preparedStatement.setLong(3, turno.getPacienteId());
            preparedStatement.setLong(4, id);

            logger.info("El turno con el id " + turno.getId() + " fue actualizado en la base de datos");
            logger.info("Se cierra la conexion a la base de datos en el metodo Actualizar");
            preparedStatement.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        turno.setId(id);
        return turno;
    }

    @Override
    public List<Turno> buscarTodos() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Turno> list_turno = new ArrayList<>();
        Turno turno = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            logger.info("Se crea una conexion a la base de datos en el metodo buscarTodos");
            preparedStatement = connection.prepareStatement("SELECT * FROM odontologo");

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Long idTurno = result.getLong("id");
                LocalDate fecha = result.getDate("fecha").toLocalDate();
                Long idOdontologo = result.getLong("odontologoId");
                Long idPaciente = result.getLong("pacienteId");
                turno = new Turno(fecha,idOdontologo,idPaciente);
                turno.setId(idTurno);
//                turno.setPacienteId(idPaciente);
//                turno.setOdontologoId(idOdontologo);

                list_turno.add(turno);
            }
            logger.info("Fue requerida la lista de Turnos a la base de datos buscarTodos");
            logger.info("Se cierra la conexion a la base de datos");
            preparedStatement.close();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list_turno;
    }

    @Override
    public Turno buscar(String email) {
        return null;
    }
}
