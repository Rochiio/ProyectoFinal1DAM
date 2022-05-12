package com.example.myanimelist.utils

import java.awt.Desktop

class Input {

    companion object {
        /**
         * Para saber si un valor introducido es correcto respecto a una expresión regular añadida.
         * @param value valor a ver si es correcto.
         * @param expression expresion regular con el que se va a filtrar.
         * @return verdadero o falso dependiendo de si es correcto.
         */
        fun isCorrectWithRegex(value: String, expression: String): Boolean {
            val regex=Regex(expression)
            return value.matches(regex)
        }
    }



}