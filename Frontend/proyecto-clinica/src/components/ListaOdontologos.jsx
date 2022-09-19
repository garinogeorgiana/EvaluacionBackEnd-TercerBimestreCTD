import React, { Component } from "react";
/* import listaOdontologos from "../db/buscarOdontologos"; */
/* listaOdontologos() */

const urlOdontologos = "http://localhost:8080/odontologos"
let settings = {
  method: "GET",
  headers: {
    "Content-Type": "application/json; chartset=UTF-8"
  }
}


class ListaOdontologos extends Component {
  constructor(props) {
    super(props)
    this.state = {
      odontologos: []
    }

    this.getOdontologos = this.getOdontologos.bind(this)
    this.handleChange = this.handleChange.bind(this)
  }


  componentDidMount() {
    this.getOdontologos()
  }

  getOdontologos = () => {
    fetch(urlOdontologos, settings)
      .then(response => {
        return response.json()
      })
      .then(data => {
        console.log(data)
        this.handleChange(data)
      })
      .catch(error => {
        console.log("Ocurrio un error al llamar a la API", error)
      })
  }

  handleChange = (data) => {
    this.setState({
      odontologos: data
    })
  }

  componentDidUpdate() {
    console.log(this.state.odontologos)
  }



  render() {
    return (
      <>
        {this.state.odontologos.map((elemento, index) => {
          return (
            <div key={("div"+index).toString()} style={{marginTop:"10px"}}>
              <h3 key={(index + 1 + elemento.matricula).toString()}>Odontologo {index+1}</h3>
              <ul key={(index + elemento.nombre + elemento.apellido).toString()}>
                <li key={(elemento.id + elemento.nombre + index).toString()}>Nombre: {elemento.nombre}</li>
                <li key={(elemento.id + elemento.apellido + index).toString()}>Nombre: {elemento.apellido}</li>
                <li key={(elemento.id + elemento.matricula + index).toString()}>Nombre: {elemento.matricula}</li>
              </ul>
            </div>)
        })}
      </>
    )
  }
}

export default ListaOdontologos;