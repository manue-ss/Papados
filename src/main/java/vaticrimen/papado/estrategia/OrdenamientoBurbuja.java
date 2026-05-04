/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vaticrimen.papado.estrategia;
import vaticrimen.papado.Nodo;
import vaticrimen.papado.ListaEnlazadaSimple;
import java.util.Objects;

/**
 * Implementación de la {@link EstrategiaOrdenamiento} utilizando el algoritmo Burbuja (Bubble Sort).
 * Este algoritmo itera repetidamente sobre la lista, comparando elementos adyacentes
 * e intercambiándolos si están en el orden incorrecto. Es simple pero ineficiente para grandes listas.
 *
 * <p><b>Nota Importante:</b> Esta implementación específica intercambia los <strong>datos</strong>
 * contenidos en los nodos, no los nodos en sí. Por lo tanto, requiere que la clase {@link Nodo}
 * posea un método {@code setDato(T)}.</p>
 *
 * <p>Complejidad Temporal: O(n^2) en peor y caso promedio, O(n) en mejor caso (ya ordenada).</p>
 * <p>Complejidad Espacial: O(1).</p>
 *
 * @param <T> El tipo de elementos en la lista, debe ser {@link Comparable}.
 * @author devapps
 * @version 1.1
 */
public class OrdenamientoBurbuja<T extends Comparable<T>> implements EstrategiaOrdenamiento<T> {

    /**
     * Ordena la lista dada usando el algoritmo de Burbuja mediante intercambio de datos.
     *
     * @param lista La lista {@link ListaEnlazadaSimple} a ordenar. No debe ser null.
     * @throws NullPointerException si {@code lista} es null.
     * @throws ClassCastException si los elementos no son {@code Comparable}.
     * @throws UnsupportedOperationException si {@link Nodo#setDato(Object)} no está disponible (implícito).
     */
    @Override
    public void ordenar(ListaEnlazadaSimple<T> lista) {
        Objects.requireNonNull(lista, "La lista a ordenar no puede ser null.");

        int n = lista.getTamanno();
        if (n <= 1) {
            return; // Nada que ordenar
        }

        boolean intercambiado;
        Nodo<T> cabezaActual = lista.getCabeza(); // Usar la cabeza actual

        // Bucle externo: controla las pasadas
        for (int i = 0; i < n - 1; i++) {
            Nodo<T> actual = cabezaActual;
            Nodo<T> siguiente = (actual != null) ? actual.getSiguiente() : null;
            intercambiado = false;

            // Bucle interno: comparaciones e intercambios en cada pasada
            // En cada pasada, el elemento más grande "flota" hacia el final
            for (int j = 0; j < n - i - 1; j++) {
                // Asegurar que no intentamos comparar o acceder a nodos nulos
                if (actual == null || siguiente == null) {
                    break; // No debería ocurrir en una lista bien formada, pero es una salvaguarda
                }

                // Comparar datos del nodo actual y el siguiente
                if (actual.getDato().compareTo(siguiente.getDato()) > 0) {
                    // Intercambiar los datos entre los nodos
                    T temp = actual.getDato();
                    try {
                        actual.setDato(siguiente.getDato());
                        siguiente.setDato(temp);
                    } catch (Exception e) {
                        // Captura genérica, idealmente sería una excepción más específica
                        // si supiéramos que setDato podría fallar de otra manera.
                        // O simplemente dejar que NoSuchMethodError se propague si setDato no existe.
                        throw new UnsupportedOperationException(
                            "La ordenación por Burbuja requiere un método setDato(T) en la clase Nodo.", e);
                    }
                    intercambiado = true;
                }
                // Avanzar al siguiente par
                actual = siguiente;
                siguiente = actual.getSiguiente();
            } // Fin bucle interno (j)

            // Optimización: si no hubo intercambios en una pasada, la lista está ordenada
            if (!intercambiado) {
                break;
            }
        } // Fin bucle externo (i)

        // Como solo se intercambian datos, la cabeza estructural no cambia necesariamente.
        // No es estrictamente necesario llamar a lista.setCabeza() aquí,
        // pero hacerlo asegura la correcta actualización de la cola.
        lista.setCabeza(cabezaActual);
    }
}
