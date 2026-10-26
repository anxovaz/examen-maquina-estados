package org.danielcastelao.dam

sealed class Estados {
    data class Idle(val mensaje: String = "Estado IDLE") : Estados(){
        fun entrar(piso: Niveles): Niveles {
            println("Estoy en estado IDLE")
            if (piso == Niveles.NIVEL0) {
                return Niveles.NIVEL0
            }else if(piso== Niveles.NIVEL1) {
                return Niveles.NIVEL1
            }else{
                return Niveles.NIVELERROR
            }
        }
    }
    data class MovingUP(val mensaje: String = "Estado MovingUP") : Estados() {
        fun entrar() {
            println("El ascensor está subiendo")
        }
    }
    data class MovingDOWN(val mensaje: String = "Estado MovingDOWN") : Estados(){
        fun entrar() {
            println("El ascensor está bajando")
        }
    }
    data class Error(val mensaje: String = "Estado Error") : Estados(){
        fun mostrarError(mensaje: String = "", siguienteEstado: Estados): Estados {
            println(mensaje)
            return siguienteEstado;
        }
    }
}