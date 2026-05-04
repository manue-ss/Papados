/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vaticrimen.papado.estrategia;
import vaticrimen.papado.Nodo;
import vaticrimen.papado.ListaEnlazadaSimple;
import java.util.Objects;

/**
 * Implementación de la {@link EstrategiaOrdenamiento} utilizando el algoritmo de Inserción (Insertion Sort).
 * Este algoritmo construye la lista ordenada final un elemento a la vez, tomando elementos
 * de la lista de entrada y colocándolos en su posición correcta dentro de una sublista ya ordenada.
 * Es eficiente para listas pequeñas o casi ordenadas.
 *
 * <p>Esta implementación modifica los enlaces {@code siguiente} de los {@link Nodo}s para reordenar la lista.</p>
 *
 * <p>Complejidad Temporal: O(n^2) en peor y caso promedio, O(n) en mejor caso (ya ordenada).</p>
 * <p>Complejidad Espacial: O(1) (ordenación in situ).</p>
 *
 * @param <T> El tipo de elementos en la lista, debe ser {@link Comparable}.
 * @author devapps
 * @version 1.1
 */
public class OrdenamientoInsercion<T extends Comparable<T>> implements EstrategiaOrdenamiento<T> {

    /** Referencia a la cabeza de la sublista ordenada que se está construyendo. */
    private Nodo<T> cabezaOrdenada;

    /**
     * Ordena la lista dada usando el algoritmo de Inserción, re-enlazando nodos.
     *
     * @param lista La lista {@link ListaEnlazadaSimple} a ordenar. No debe ser null.
     * @throws NullPointerException si {@code lista} es null.
     * @throws ClassCastException si los elementos no son {@code Comparable}.
     */
    @Override
    public void ordenar(ListaEnlazadaSimple<T> lista) {
        Objects.requireNonNull(lista, "La lista a ordenar no puede ser null.");

        if (lista.getTamanno() <= 1) {
            return; // Nada que ordenar
        }

        Nodo<T> actualOriginal = lista.getCabeza(); // Iterador sobre la lista original
        this.cabezaOrdenada = null; // Inicializa la lista ordenada como vacía

        // Itera sobre cada nodo de la lista original
        while (actualOriginal != null) {
            // Guarda la referencia al siguiente nodo original ANTES de modificar los enlaces de 'actualOriginal'
            Nodo<T> siguienteOriginal = actualOriginal.getSiguiente();

            // Desconecta 'actualOriginal' de la lista original para insertarlo en la ordenada
            // (necesario si 'insertarEnOrden' modifica el 'siguiente' del nodo insertado)
            // actualOriginal.setSiguiente(null); // Comentado - insertarEnOrden ya lo maneja

            // Inserta el nodo actual en la posición correcta de la lista 'cabezaOrdenada'
            insertarEnOrden(actualOriginal);

            // Avanza al siguiente nodo de la lista original
            actualOriginal = siguienteOriginal;
        }

        // Al finalizar, la lista 'cabezaOrdenada' contiene todos los nodos ordenados.
        // Actualiza la cabeza de la lista original.
        lista.setCabeza(this.cabezaOrdenada); // setCabeza actualiza también la cola
    }

    /**
     * Método auxiliar privado para insertar un {@code nodoAInsertar} en la lista
     * ordenada referenciada por {@code cabezaOrdenada}, manteniendo el orden.
     *
     * @param nodoAInsertar El {@link Nodo} que se va a insertar.
     */
    private void insertarEnOrden(Nodo<T> nodoAInsertar) {
        // Caso 1: La lista ordenada está vacía o el nuevo nodo es menor o igual que la cabeza actual.
        if (this.cabezaOrdenada == null ||
            this.cabezaOrdenada.getDato().compareTo(nodoAInsertar.getDato()) >= 0)
        {
            // Insertar al principio de la lista ordenada
            nodoAInsertar.setSiguiente(this.cabezaOrdenada);
            this.cabezaOrdenada = nodoAInsertar;
        } else {
            // Caso 2: Buscar la posición correcta dentro de la lista ordenada.
            Nodo<T> actualOrdenado = this.cabezaOrdenada;
            // Avanzar mientras no lleguemos al final Y el siguiente sea menor que el nodo a insertar
            while (actualOrdenado.getSiguiente() != null &&
                   actualOrdenado.getSiguiente().getDato().compareTo(nodoAInsertar.getDato()) < 0)
            {
                actualOrdenado = actualOrdenado.getSiguiente();
            }
            // Insertar 'nodoAInsertar' después de 'actualOrdenado'
            nodoAInsertar.setSiguiente(actualOrdenado.getSiguiente());
            actualOrdenado.setSiguiente(nodoAInsertar);
        }
    }
}