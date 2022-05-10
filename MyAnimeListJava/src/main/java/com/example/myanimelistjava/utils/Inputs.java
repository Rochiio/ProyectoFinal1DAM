package com.example.myanimelistjava.utils;

public class Inputs {

    /**
     * Función para ver si un dato es correcto respecto a una expresión regular
     * @param value Valor a ver si es correcto.
     * @param regex expresion regular.
     * @return true o false
     */
    public static boolean isRegexCorrect(String value,String regex){
        return value.matches(regex);
    }
}
