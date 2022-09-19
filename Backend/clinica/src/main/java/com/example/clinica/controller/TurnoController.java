package com.example.clinica.controller;

import com.example.clinica.repository.imp.TurnoDaoH2;
import com.example.clinica.domain.Turno;
import com.example.clinica.services.imp.TurnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TurnoController {
    TurnoService turnoService = new TurnoService(new TurnoDaoH2());


    @GetMapping("/turno/buscar/{id}") //le indicamos que la url va a ser el de la variable que van a ingresar.
    public Turno buscar(@PathVariable Long id) { //marcamos pathvariable para saber que esa sera la variable.
        return turnoService.buscar(id);
    }

    @PutMapping("/turno/actualizar/{id}") //le indicamos que la url va a ser el de la variable que van a ingresar.
    public Turno actualizar(@RequestBody Turno turno, @PathVariable Long id) { //marcamos pathvariable para saber que esa sera la variable.
        return turnoService.actualizarTurno(turno,id);
    }


    @PostMapping("/turno/registrar")
    public Turno guardarTurno(@RequestBody Turno turno){
        return turnoService.guardar(turno);
    }

    @DeleteMapping("/turno/eliminar/{id}")//puede ser la misma url que mapping por que es diferente tipo de pedido (delete / get)
    public ResponseEntity eliminar(@PathVariable Long id){
        ResponseEntity response = null;

        if(turnoService.buscar(id) == null){
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        }else{
            turnoService.elimar(id);
            response = new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return response;
    }
}
