import React, { Component } from "react";




const CrearOdontologo = () => {

  return (
    <>
      <h2 style={{ marginTop: "10px", marginBotton: "10px" }}>Crear Odontologo</h2>
      <label key={1} htmlFor="nombre">
        Nombre: <input key={2} type="text" name="nombre" id="nombre" />
      </label>

      <label key={3} htmlFor="Apellido">
        Apellido: <input key={4} type="text" name="apellido" id="apellido" />
      </label>

      <label key={5} htmlFor="matricula">
        Matricula: <input key={6} type="text" name="matricula" id="matricula" />
      </label>

      <input type="button" value="Crear" onClick={() => submitOdontologo()} />
    </>
  )

}

function submitOdontologo() {
  let nombreValor = document.querySelector("#nombre").value
  let apellidoValor = document.querySelector("#apellido").value
  let matriculaValor = document.querySelector("#matricula").value

  let odontologoData = {
    apellido: apellidoValor,
    nombre: nombreValor,
    matricula: matriculaValor
  }

  let settings = {
    method: "POST",
    body: JSON.stringify(odontologoData),
    headers: {
      "Content-Type": "application/json; chartset=UTF-8"
    }
  }

  let urlRegistrarOdontologo = "http://localhost:8080/odontologo/registrar"
  
  fetch(urlRegistrarOdontologo, settings)
    .then(response => {
      return response.json()
    })
    .then(data => {
      console.log(data)
    })
    .catch(error => {
      console.log("Ocurrio un error al llamar a la API", error)
    })

}

export default CrearOdontologo;