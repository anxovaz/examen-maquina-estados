package org.danielcastelao.dam


object Gestor{
// nivel en el que se encuentra el ascensor
    var nivel_actual: Niveles = Niveles.NIVEL0

// estado actual del ascensor
    var estado_actual: Estados = Estados.Idle()
    var siguienteNivel = Niveles.NIVEL0
    /**
     * Gestiona el cambio de estados
     */
    fun gestor(nivel_0: Boolean = false, nivel_1: Boolean = false): Niveles {
        nivel_actual = siguienteNivel
        when (estado_actual) {
            is Estados.Idle if nivel_0 == false && nivel_1 == false -> {
                estado_actual = Estados.Error().mostrarError("Por favor escoje un piso, estoy en estado $estado_actual", Estados.Idle())
                return nivel_actual
            }

            is Estados.Idle -> {
                if (nivel_0 && nivel_actual == Niveles.NIVEL1) {
                    estado_actual = Estados.MovingDOWN()
                    Estados.Idle().entrar(nivel_actual)
                    return nivel_actual
                } else if (nivel_1 && nivel_actual == Niveles.NIVEL0) {
                    estado_actual = Estados.MovingUP()
                    Estados.Idle().entrar(nivel_actual)
                    return nivel_actual
                } else {
                    estado_actual = Estados.Error()
                    return nivel_actual
                }
            }

            is Estados.MovingUP -> {
                Estados.MovingUP().entrar()
                nivel_actual = Niveles.NIVEL2
                siguienteNivel = Niveles.NIVEL1
                estado_actual = Estados.Idle() //siguiente estadio
                this.simularMovimiento()
                return Niveles.NIVEL2
            }

            is Estados.MovingDOWN -> {
                Estados.MovingDOWN().entrar()
                nivel_actual = Niveles.NIVEL2
                siguienteNivel = Niveles.NIVEL0
                estado_actual = Estados.Idle() // siguiente estado
                this.simularMovimiento()
                return Niveles.NIVEL2
            }

            is Estados.Error -> {
                //Se muestra el mensaje de error y devuelve al usuario al estado IDLE para que vuelva a intentarlo
                estado_actual = Estados.Error().mostrarError("Error, vuelve a intentarlo", Estados.Idle())
                nivel_actual = Niveles.NIVELERROR
                return nivel_actual
            }
        }
    }
    fun simularMovimiento(){
        println("El ascensor está en movimiento...")
        println("0%")
        Thread.sleep(200)
        println("20%")
        Thread.sleep(200)
        println("40%")
        Thread.sleep(200)
        println("60%")
        Thread.sleep(200)
        println("80%")
        Thread.sleep(200)
        println("100%")
        println("Abriendo las puertas")
        Thread.sleep(200)
    }
}