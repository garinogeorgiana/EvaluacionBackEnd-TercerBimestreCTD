package com.example.clinica;

import com.example.clinica.repository.imp.TurnoDaoH2;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@SpringBootApplication
public class ClinicaApplication {
    private static final Logger logger = Logger.getLogger(TurnoDaoH2.class);

    private static void cargarBD() {
        Connection connection = null;

        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/test;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "");

            logger.info("Se crea una conexion a la base de para generar las tablas y luego se cierra.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    public static void main(String[] args) {
        cargarBD();
        SpringApplication.run(ClinicaApplication.class, args);


    }


}
