package com.example.clinica.controller;

import com.example.clinica.repository.imp.PacienteDaoH2;
import com.example.clinica.domain.Paciente;
import com.example.clinica.services.imp.PacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@RestController
public class PacienteController {

    PacienteService pacienteService = new PacienteService(new PacienteDaoH2());

//    @GetMapping("/")
//    public String testMostrar2() {
//        return "Para crear un paciente usar la siguiente ruta: /paciente/registrar \n ||" +
//                " Para buscar un paciente por su id usar la siguiente ruta:  /paciente/buscar/{id} \n || " +
//                " Para eliminar un paciente usar la siguiente ruta: /paciente/eliminar/{id} \n" +
//                " ";
//    }

    @GetMapping("/paciente/buscar/id/{id}") //le indicamos que la url va a ser el de la variable que van a ingresar.
    public Paciente buscar(@PathVariable Long id) { //marcamos pathvariable para saber que esa sera la variable.
        return pacienteService.buscar(id);
    }

    @GetMapping("/paciente/buscar/email/{email}") //le indicamos que la url va a ser el de la variable que van a ingresar.
    public Paciente buscar(@PathVariable String email) { //marcamos pathvariable para saber que esa sera la variable.
        return pacienteService.buscarPorMail(email);
    }

    @PostMapping("/paciente/registrar")
    public Paciente guardarPaciente(@RequestBody Paciente paciente) {
        return pacienteService.guardar(paciente);
    }

    @DeleteMapping("/paciente/eliminar/{id}")
//puede ser la misma url que mapping por que es diferente tipo de pedido (delete / get)
    public ResponseEntity eliminar(@PathVariable Long id) {
        ResponseEntity response = null;

        if (pacienteService.buscar(id) == null) {
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            pacienteService.elimar(id);
            response = new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return response;
    }

}
