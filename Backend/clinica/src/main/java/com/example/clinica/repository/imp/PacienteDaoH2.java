package com.example.clinica.repository.imp;

import com.example.clinica.domain.Domicilio;
import com.example.clinica.domain.Paciente;
import com.example.clinica.repository.IDao;

import com.example.clinica.services.imp.DomicilioService;
import com.example.clinica.services.imp.PacienteService;
import com.example.clinica.util.Util;
import org.apache.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PacienteDaoH2 implements IDao<Paciente> {
    private static final Logger logger = Logger.getLogger(PacienteDaoH2.class);
    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    private final static String DB_URL = "jdbc:h2:~/test;";
    private final static String DB_USER = "sa";
    private final static String DB_PASSWORD = "";

    @Override

    public Paciente guardar(Paciente paciente) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Domicilio domicilio = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            logger.info("Se crea una conexion a la base de datos en el metodo Guardar");

            //creo los servicios para primero ingresar en la base de datos el domicilio y luego guardar el id para referenciar.
            DomicilioService domicilioService = new DomicilioService(new DomicilioDaoH2());

            domicilio = domicilioService.guardar(paciente.getDomicilio()); // al guardar el domicilio nos devuelve el mismo con su id ya seteado.
            paciente.setDomicilioId(domicilio.getId()); //y guardamos el id antes de ingresar el domicilio a la base de datos.

            preparedStatement = connection.prepareStatement("INSERT INTO paciente(apellido,nombre,email,dni,fechaIngreso,domicilioId) VALUES (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, paciente.getApellido());
            preparedStatement.setString(2, paciente.getNombre());
            preparedStatement.setString(3, paciente.getEmail());
            preparedStatement.setInt(4, paciente.getDni());
            preparedStatement.setDate(5, java.sql.Date.valueOf(paciente.getFechaIngreso()));
            preparedStatement.setLong(6, paciente.getDomicilioId());

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                paciente.setId(rs.getLong(1));
            }
            logger.info("El paciente con el id " + paciente.getId() + " fue guardado en la base de datos");
            logger.info("Se cierra la conexion a la base de datos en el metodo Guardar");
            preparedStatement.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return paciente;
    }

    @Override
    public void eliminar(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Paciente paciente = null;
        Long idDomicilio = null;

        try {

            //creo el servicio para buscar el paciente a borrar, extraer el id del Domicilio asociado y borrar el Domicilio junto con le paciente.
            PacienteService pacienteService = new PacienteService(new PacienteDaoH2());
            paciente = pacienteService.buscar(id);
            idDomicilio = paciente.getDomicilioId();


            //creo el servicio de domicilio para borrar el domicilio asociado antes de proceder a borrar al paciente.
            DomicilioService domicilioService = new DomicilioService(new DomicilioDaoH2());
            domicilioService.elimarDomicilio(idDomicilio);

            //con el domicilio asociado ya borrado, procedemos a borrar al paciente.
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            logger.info("Se crea una conexion a la base de datos en el metodo Eliminar");

            preparedStatement = connection.prepareStatement("DELETE FROM paciente where id=?");
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
            logger.info("El paciente con el id " + id + " fue eliminado en la base de datos");
            logger.info("Se cierra la conexion a la base de datos en el metodo Eliminar");
            preparedStatement.close();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Paciente buscar(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Paciente paciente = null;

        try {
            //creo el servicio de domicilio para Buscar el domicilio y luego agregarlo cuando construyo el objeto a devolver.
            DomicilioService domicilioService = new DomicilioService(new DomicilioDaoH2());

            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            logger.info("Se crea una conexion a la base de datos en el metodo Buscar");
            preparedStatement = connection.prepareStatement("SELECT * FROM paciente WHERE id =?");
            preparedStatement.setLong(1, id);

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Long idPaciente = result.getLong("id");
                String apellido = result.getString("apellido");
                String nombre = result.getString("nombre");
                String email = result.getString("email");
                int dni = result.getInt("dni");
                Long domicilioId = result.getLong("domicilioId");
                LocalDate fechaIngreso = result.getDate("fechaIngreso").toLocalDate();

                paciente = new Paciente(apellido,nombre,email,dni,fechaIngreso);
                paciente.setId(idPaciente);
                paciente.setDomicilioId(domicilioId);
                paciente.setDomicilio(domicilioService.buscarDomicilio(paciente.getDomicilioId()));

            }
            logger.info("El paciente con el id " + id + " fue buscado en la base de datos en el metodo Buscar");
            logger.info("Se cierra la conexion a la base de datos");
            preparedStatement.close();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return paciente;
    }

    @Override
    public List<Paciente> buscarTodos() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Paciente> list_paciente = new ArrayList<>();
        Paciente paciente = null;

        try {
            //creo el servicio de domicilio para Buscar el domicilio y luego agregarlo cuando construyo el objeto a devolver.
            DomicilioService domicilioService = new DomicilioService(new DomicilioDaoH2());

            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            logger.info("Se crea una conexion a la base de datos en el metodo buscarTodos");
            preparedStatement = connection.prepareStatement("SELECT * FROM paciente");

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Long idPaciente = result.getLong("id");
                String apellido = result.getString("apellido");
                String nombre = result.getString("nombre");
                String email = result.getString("email");
                int dni = result.getInt("dni");
                Long domicilioId = result.getLong("domicilioId");
                LocalDate fechaIngreso = result.getDate("fechaIngreso").toLocalDate();

                paciente = new Paciente(apellido,nombre,email,dni,fechaIngreso);
                paciente.setId(idPaciente);
                paciente.setDomicilioId(domicilioId);
                paciente.setDomicilio(domicilioService.buscarDomicilio(paciente.getDomicilioId()));


                list_paciente.add(paciente);
            }
            logger.info("Fue requerida la lista de pacientes a la base de datos buscarTodos");
            logger.info("Se cierra la conexion a la base de datos");
            preparedStatement.close();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list_paciente;
    }

    @Override
    public Paciente actualizar(Paciente paciente, Long id) {
        return null;
    }

    public Paciente buscar(String email) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Paciente paciente = null;

        try {
            //creo el servicio de domicilio para Buscar el domicilio y luego agregarlo cuando construyo el objeto a devolver.
            DomicilioService domicilioService = new DomicilioService(new DomicilioDaoH2());

            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            logger.info("Se crea una conexion a la base de datos en el metodo Buscar");
            preparedStatement = connection.prepareStatement("SELECT * FROM paciente WHERE email =?");
            preparedStatement.setString(1, email);

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Long idPaciente = result.getLong("id");
                String apellido = result.getString("apellido");
                String nombre = result.getString("nombre");
                String emailToSave = result.getString("email");
                int dni = result.getInt("dni");
                Long domicilioId = result.getLong("domicilioId");
                LocalDate fechaIngreso = result.getDate("fechaIngreso").toLocalDate();

                paciente = new Paciente(apellido,nombre,emailToSave,dni,fechaIngreso);
                paciente.setId(idPaciente);
                paciente.setDomicilioId(domicilioId);
                paciente.setDomicilio(domicilioService.buscarDomicilio(paciente.getDomicilioId()));

            }
            logger.info("El paciente con el Email " + email + " fue buscado en la base de datos en el metodo Actualizar");
            logger.info("Se cierra la conexion a la base de datos");
            preparedStatement.close();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return paciente;
    }


}
