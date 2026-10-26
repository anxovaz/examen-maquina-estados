package org.danielcastelao.dam


object Gestor{
// nivel en el que se encuentra el ascensor
    var nivel_actual: Niveles = Niveles.NIVEL0

// estado actual del ascensor
    var estado_actual: Estados = Estados.Idle()

    /**
     * Gestiona el cambio de estados
     */
    fun gestor(nivel_0: Boolean = false, nivel_1: Boolean = false): Niveles {
        when (estado_actual) {
            is Estados.Idle if nivel_0 == false && nivel_1 == false -> {
                estado_actual = Estados.Error().mostrarError("Por favor escoje un piso", Estados.Idle())
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
                estado_actual = Estados.Idle() //siguiente estadio
                return Niveles.NIVEL2
            }

            is Estados.MovingDOWN -> {
                Estados.MovingDOWN().entrar()
                nivel_actual = Niveles.NIVEL2
                estado_actual = Estados.Idle() // siguiente estado
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
}