package com.meli.itacademy;

import java.util.Arrays;

class Comparador {

    /**
     * Ordena el array según la propiedad elegida
     * @param array Array de objetos a ordenar
     * @param <T> Clase generica
     * @throws ArrayIndexOutOfBoundsException Excepción si el array es vacio.
     */
    static <T extends Comparable<T>> void ordenar(T[] array) throws ArrayIndexOutOfBoundsException {

        if (array.length <= 0) {
            throw new ArrayIndexOutOfBoundsException("El array esta vacio.");
        }

        Arrays.sort(array);
    }
}
