package org.danielcastelao.dam


import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals

class GestorTest {
    @AfterTest
    fun restaurarEstado() {
        println("After Test")
        Gestor.estado_actual = Estados.Idle()
        Gestor.nivel_actual = Niveles.NIVEL0
    }

    @Test
    fun `gestor reinicia el nivel cuando el estado es Idle`() {
        println("test1")
        Gestor.estado_actual = Estados.Idle()

        assertEquals(Niveles.NIVEL0, Gestor.gestor())
        assertEquals(Niveles.NIVEL0, Gestor.nivel_actual)
    }

    @Test
    fun `gestor establece el nivel cuando el estado es MovingUP`() {
        println("test2")
        Gestor.estado_actual = Estados.MovingUP()
        Gestor.nivel_actual = Niveles.NIVEL2

        assertEquals(Niveles.NIVEL2, Gestor.gestor())
        println("--")
        assertEquals(Niveles.NIVEL2, Gestor.nivel_actual)
    }

    @Test
    fun `gestor establece el nivel cuando el estado es MovingDOWN`() {
        println("test3")
        Gestor.estado_actual = Estados.MovingDOWN()

        assertEquals(Niveles.NIVEL2, Gestor.gestor())
        println("---")
        assertEquals(Niveles.NIVEL2, Gestor.nivel_actual)
    }

    @Test
    fun `gestor establece el nivel de error cuando el estado es Error`() {
        println("test4")
        Gestor.estado_actual = Estados.Error()

        assertEquals(Niveles.NIVELERROR, Gestor.gestor())
        assertEquals(Niveles.NIVELERROR, Gestor.nivel_actual)
    }
}