package org.danielcastelao.dam

sealed class Estados {
    object Idle : Estados(){
        fun entrar(piso: Int): Int {
            println("Estoy en estado IDLE, pulsa un botón para subir o bajar")
            return piso
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