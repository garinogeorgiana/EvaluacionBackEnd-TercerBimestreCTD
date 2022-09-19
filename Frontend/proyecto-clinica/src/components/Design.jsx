import React, { Component } from "react";
import ListaOdontologos from "./ListaOdontologos";
import CrearOdontologo from "./CrearOdontologo";

const Design = () => {

  return (
    <>
      
      <CrearOdontologo/>
      <hr/>
      <h2>Lista de Odontologos</h2>
      <ListaOdontologos/>
    </>
  )

}


export default Design;