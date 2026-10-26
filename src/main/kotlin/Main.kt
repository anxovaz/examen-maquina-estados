package org.danielcastelao.dam

import org.danielcastelao.dam.Gestor.gestor

/**
 * Main (para probar yo)
 */
fun main() {
    Gestor.gestor(false,true)
    println(" - ")
    Gestor.gestor()
    println(" - ")
    Gestor.gestor(false,false)
    println(" - ")
    Gestor.gestor(true,false)
    println(" - ")
    Gestor.gestor()

}