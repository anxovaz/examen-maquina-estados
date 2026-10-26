package org.danielcastelao.dam

sealed class Estados {
    object Idle : Estados(){
        fun entrar() {
            println("Estoy en estado IDLE, pulsa un botón para subir o bajar")
        }
    }
    object MovingUP : Estados() {
        fun entrar() {
            println("El ascenson está subiendo")
        }
    }
    object MovingDOWN : Estados()
    object Error : Estados(){
        fun mostrarError(mensaje: String = "", siguienteEstado: Estados): Estados {
            println(mensaje)
            return siguienteEstado;
        }
    }
}