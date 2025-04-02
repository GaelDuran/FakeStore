package com.example.fakestoreapp.coroutines

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    //Launch
    //Scope
    //SOLO PARA PROBAR
    //runBlocking
    println("===  Corrutinas  ===")
}

fun cLaunch() {
    runBlocking {
        //Hacer una consulta a una base de datos
        println("Cargando interfaz grafica")
        launch {
            consultaDB()
            var t = 2
            println("sd")
        }
        println("Continuo cargando interfaz grafica")
        println("Interfaz cargada")
    }
}

fun cAsync() {
    //SOLO PARA PRUEBAS
    println("Cargando interfaz grafica")
    runBlocking {
        val result = async {
            println("Hacuendo GET a API de FakeStore")
            delay(2000)
            "Datos de la API en Json { id:Hola }"
        }
        val resultString = result.await()
        println("El resultado es $resultString")
    }
}

suspend fun consultaDB() {
    println("Consulta a base de datos")
    delay(3000)
    println("Termina consulta de base de datos")
}