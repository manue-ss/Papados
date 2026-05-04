/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vaticrimen.papado.estrategia;
import vaticrimen.papado.Nodo;
import vaticrimen.papado.ListaEnlazadaSimple;
import java.util.Objects;

/**
 * Implementación de la {@link EstrategiaOrdenamiento} utilizando el algoritmo Merge Sort (ordenación por mezcla).
 * Es un algoritmo eficiente de tipo "divide y vencerás", particularmente adecuado para listas enlazadas
 * ya que no requiere acceso aleatorio. Divide la lista recursivamente, ordena las sublistas y
 * luego las fusiona (merge) de manera ordenada.
 *
 * <p>Esta implementación modifica los enlaces {@code siguiente} de los {@link Nodo}s.</p>
 *
 * <p>Complejidad Temporal: O(n log n) en todos los casos (peor, promedio, mejor).</p>
 * <p>Complejidad Espacial: O(log n) debido a la pila de recursión (puede ser O(n) en algunas implementaciones iterativas).</p>
 *
 * @param <T> El tipo de elementos en la lista, debe ser {@link Comparable}.
 * @author devapps
 * @version 1.1
 */
public class OrdenamientoMerge<T extends Comparable<T>> implements EstrategiaOrdenamiento<T> {

    /**
     * Ordena la lista dada usando el algoritmo Merge Sort, re-enlazando nodos.
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

        // Inicia el proceso recursivo desde la cabeza original
        Nodo<T> nuevaCabeza = mergeSortRecursivo(lista.getCabeza());

        // Actualiza la cabeza de la lista original con el resultado ordenado
        lista.setCabeza(nuevaCabeza); // setCabeza también actualiza la cola
    }

    /**
     * Función recursiva principal de Merge Sort.
     * Divide la (sub)lista, ordena recursivamente las mitades y las fusiona.
     *
     * @param cabeza El {@link Nodo} cabeza de la (sub)lista a ordenar.
     * @return El {@link Nodo} cabeza de la (sub)lista ya ordenada.
     */
    private Nodo<T> mergeSortRecursivo(Nodo<T> cabeza) {
        // Caso base: lista vacía o con un solo elemento ya está ordenada
        if (cabeza == null || cabeza.getSiguiente() == null) {
            return cabeza;
        }

        // 1. Dividir la lista en dos mitades
        Nodo<T> medio = encontrarMedio(cabeza);
        Nodo<T> segundaMitadCabeza = medio.getSiguiente();
        medio.setSiguiente(null); // Cortar el enlace para separar las listas

        // 2. Ordenar recursivamente cada mitad
        Nodo<T> izquierdaOrdenada = mergeSortRecursivo(cabeza);
        Nodo<T> derechaOrdenada = mergeSortRecursivo(segundaMitadCabeza);

        // 3. Fusionar las dos mitades ordenadas
        return fusionar(izquierdaOrdenada, derechaOrdenada);
    }

    /**
     * Encuentra el nodo medio de una lista enlazada dada su cabeza.
     * Si la lista tiene un número par de elementos, devuelve el nodo final de la primera mitad.
     * Utiliza la técnica de punteros lento y rápido.
     *
     * @param cabeza El {@link Nodo} inicial de la lista.
     * @return El {@link Nodo} que representa el final de la primera mitad.
     */
    private Nodo<T> encontrarMedio(Nodo<T> cabeza) {
        // No necesitamos verificar null aquí, ya que mergeSortRecursivo lo hace.
        Nodo<T> lento = cabeza;
        Nodo<T> rapido = cabeza.getSiguiente();

        // Mover rápido dos pasos, lento un paso
        while (rapido != null && rapido.getSiguiente() != null) {
            lento = lento.getSiguiente();
            rapido = rapido.getSiguiente().getSiguiente();
        }
        // 'lento' apunta al nodo medio (o al último de la primera mitad si es par)
        return lento;
    }

    /**
     * Fusiona (merge) dos listas enlazadas, {@code izquierda} y {@code derecha},
     * que ya se encuentran ordenadas. Devuelve una nueva lista enlazada ordenada
     * que contiene todos los elementos de ambas listas originales.
     *
     * @param izquierda Cabeza de la primera lista ordenada.
     * @param derecha Cabeza de la segunda lista ordenada.
     * @return Cabeza de la lista fusionada y ordenada.
     */
    private Nodo<T> fusionar(Nodo<T> izquierda, Nodo<T> derecha) {
        // Casos base: si una lista está vacía, la fusión es la otra lista.
        if (izquierda == null) {
            return derecha;
        }
        if (derecha == null) {
            return izquierda;
        }

        // Nodo cabeza de la lista fusionada resultante
        Nodo<T> cabezaResultado;

        // Comparar los primeros nodos de cada lista para decidir la cabeza del resultado
        if (izquierda.getDato().compareTo(derecha.getDato()) <= 0) {
            // El elemento de la izquierda es menor o igual
            cabezaResultado = izquierda;
            // El siguiente del resultado será la fusión del resto de la izquierda con toda la derecha
            cabezaResultado.setSiguiente(fusionar(izquierda.getSiguiente(), derecha));
        } else {
            // El elemento de la derecha es menor
            cabezaResultado = derecha;
            // El siguiente del resultado será la fusión de toda la izquierda con el resto de la derecha
            cabezaResultado.setSiguiente(fusionar(izquierda, derecha.getSiguiente()));
        }
        return cabezaResultado;
    }
}