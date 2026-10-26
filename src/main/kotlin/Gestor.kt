package org.danielcastelao.dam

// nivel en el que se encuentra el ascensor
var nivel_actual: Niveles = Niveles.NIVEL0
// estado actual del ascensor
var estado_acual: Estados = Estados.Idle

/**
 * Gestiona el cambio de estados
 */
fun gestor(nivel_0: Boolean = false, nivel_1:Boolean = false): Niveles {
    when (estado_acual) {
        is Estados.Idle if nivel_0 == false && nivel_1 == false -> {
            Estados.Idle.entrar(nivel_actual)
            //nivel_actual = 0
            return nivel_actual
        }
        is Estados.Idle -> {
            if (nivel_0 && nivel_actual == Niveles.NIVEL0) {
                estado_acual = Estados.MovingUP
                return nivel_actual
            }else if (nivel_1 && nivel_actual == Niveles.NIVEL1) {
                estado_acual = Estados.MovingDOWN
                return nivel_actual
            }else{
                estado_acual = Estados.Error
                return nivel_actual
            }
        }
        is Estados.MovingUP -> {
            Estados.MovingUP.entrar()
            nivel_actual = Niveles.NIVEL2
            return nivel_actual
        }
        is Estados.MovingDOWN -> {
            Estados.MovingDOWN.entrar()
            nivel_actual = Niveles.NIVEL2
            return nivel_actual
        }
        is Estados.Error -> {
            //Se muestra el mensaje de error y devuelve al usuario al estado IDLE para que vuelva a intentarlo
            Estados.Error.mostrarError("Error, vuelve a intentarlo", Estados.Idle)
            nivel_actual = Niveles.NIVELERROR
            return nivel_actual
        }
    }
}