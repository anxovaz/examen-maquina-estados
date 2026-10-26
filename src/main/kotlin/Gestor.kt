package org.danielcastelao.dam

/**
 * Objeto Gestor que contiene gestiona los estados y niveles del ascensor
 */
object Gestor{
// nivel en el que se encuentra el ascensor
    var nivel_actual: Niveles = Niveles.NIVEL0

// estado actual del ascensor
    var estado_actual: Estados = Estados.Idle()
    var siguienteNivel = Niveles.NIVEL0 //Se usa para saber a que planta (nivel) van los ascensores
    /**
     * Gestiona el cambio de estados
     */
    fun gestor(nivel_0: Boolean = false, nivel_1: Boolean = false): Niveles {
        nivel_actual = siguienteNivel //apunto en nivel actual a donde se diriguían los ascensores
        when (estado_actual) {
            //Si el usuario no especifico mediante los botones a que piso se dirigía
            is Estados.Idle if nivel_0 == false && nivel_1 == false -> {
                estado_actual = Estados.Error().mostrarError("Por favor escoje un piso, estoy en estado $estado_actual", Estados.Idle())
                return nivel_actual
            }

            is Estados.Idle -> {
                if (nivel_0 && nivel_actual == Niveles.NIVEL1) { //si está en la planta de arriba y le da al botón de bajar
                    estado_actual = Estados.MovingDOWN()
                    Estados.Idle().entrar(nivel_actual)
                    return nivel_actual
                } else if (nivel_1 && nivel_actual == Niveles.NIVEL0) { //si está en la planta de abajo y le da al botón subir
                    estado_actual = Estados.MovingUP()
                    Estados.Idle().entrar(nivel_actual)
                    return nivel_actual
                } else { //en cualquier otro caso (ej: planta arriba y botón subir
                    estado_actual = Estados.Error()
                    return nivel_actual
                }
            }

            //Si va hacia arriba
            is Estados.MovingUP -> {
                if (detectarErrores()){
                    this.estado_actual = Estados.Error()
                    return Niveles.NIVEL2
                }else {
                    Estados.MovingUP().entrar()
                    nivel_actual = Niveles.NIVEL2
                    siguienteNivel = Niveles.NIVEL1//apunta el siguiente nivel
                    estado_actual = Estados.Idle() //siguiente estado
                    this.simularMovimiento()
                    return Niveles.NIVEL2
                }
            }

            //Si va hacia abajo
            is Estados.MovingDOWN -> {
                if (detectarErrores()){
                    this.estado_actual = Estados.Error()
                    return Niveles.NIVEL2
                }else {
                    Estados.MovingDOWN().entrar()
                    nivel_actual = Niveles.NIVEL2
                    siguienteNivel = Niveles.NIVEL0 //apunta el siguiente nivel
                    estado_actual = Estados.Idle() // siguiente estado
                    this.simularMovimiento()
                    return Niveles.NIVEL2
                }
            }

            //error
            is Estados.Error -> {
                //Se muestra el mensaje de error y devuelve al usuario al estado IDLE para que vuelva a intentarlo
                estado_actual = Estados.Error().mostrarError("Error, vuelve a intentarlo", Estados.Idle())
                nivel_actual = Niveles.NIVELERROR
                return nivel_actual
            }
        }
    }

    /**
     * Función que simula el movimiento del ascensor (subir o bajar) mediante una barra de probreso
     */
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

    //Función que (en un caso real) leería los sensores del ascensor y devolvería false si hay algún error
    fun detectarErrores(): Boolean {
        return false
    }
}