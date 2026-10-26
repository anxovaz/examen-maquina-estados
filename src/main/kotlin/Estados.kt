package org.danielcastelao.dam

sealed class Estados {
    object Idle : Estados(){
        fun entrar(piso: Niveles): Niveles {
            println("Estoy en estado IDLE, pulsa un botón para subir o bajar")
            if (piso == Niveles.NIVEL0) {
                return Niveles.NIVEL0
            }else if(piso== Niveles.NIVEL1) {
                return Niveles.NIVEL1
            }else{
                return Niveles.NIVELERROR
            }
        }
    }
    object MovingUP : Estados() {
        fun entrar() {
            println("El ascenson está subiendo")
        }
    }
    object MovingDOWN : Estados(){
        fun entrar() {
            println("El ascenson está bajando")
        }
    }
    object Error : Estados(){
        fun mostrarError(mensaje: String = "", siguienteEstado: Estados): Estados {
            println(mensaje)
            return siguienteEstado;
        }
    }
}