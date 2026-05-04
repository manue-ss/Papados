package vaticrimen.papado;

import vaticrimen.papado.estrategia.*;
import java.time.LocalDate;
import java.util.Comparator; // Importar Comparator
import java.util.Objects;

/**
 * Clase principal (Driver) para demostrar el uso y funcionalidades de las distintas
 * implementaciones de listas enlazadas (Simple, Doble, Circular) y la aplicación
 * del patrón Strategy para la ordenación de una lista simple.
 * Incluye ejemplos con objetos {@link Persona} y {@link Papa}.
 *
 * @author devapps
 * @version 1.3 
 */
public class Papado {

    /**
     * Punto de entrada principal de la aplicación.
     * Ejecuta secuencialmente las demostraciones de cada tipo de lista y
     * las pruebas de ordenación para Personas (por edad) y Papas (por inicio de papado).
     *
     * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        System.out.println("===== DEMO LISTA ENLAZADA SIMPLE (PERSONAS) =====");
        testListaSimple();

        System.out.println("\n===== DEMO LISTA ENLAZADA DOBLE (PERSONAS) =====");
        testListaDoble();

        System.out.println("\n===== DEMO LISTA ENLAZADA SIMPLE CIRCULAR (PERSONAS) =====");
        testListaCircular();

        System.out.println("\n===== DEMO ORDENAMIENTO (PERSONAS - USANDO COMPARATOR EXTERNO SI SE IMPLEMENTARA) =====");
        testOrdenamientoPersonas(); // El método ahora explica la situación

        System.out.println("\n===== DEMO LISTAS CON PAPAS (SAMPLE EXTENDIDO) =====");
        testListasDePapas();

         System.out.println("\n===== DEMO ORDENAMIENTO (LISTA SIMPLE POR INICIO PAPADO - PAPAS) =====");
         testOrdenamientoPapas();
    }

    // --- Métodos de Prueba (Personas) ---
    // [testListaSimple, testListaDoble, testListaCircular sin cambios]
    /**
     * Demuestra las operaciones básicas de {@link ListaEnlazadaSimple} con {@link Persona}.
     */
    public static void testListaSimple() {
        System.out.println("Creando Lista Simple con Personas...");
        ListaEnlazadaSimple<Persona> lista = new ListaEnlazadaSimple<>();
        Persona p1 = new Persona("Carolina", 26, 55.0); Persona p2 = new Persona("Andrea", 33, 62.5); Persona p3 = new Persona("Diana", 15, 48.0); Persona p4 = new Persona("Carlos", 40, 75.0); Persona p5 = new Persona("Bruno", 20, 70.0); Persona pNuevoAntes = new Persona("Nuevo Antes S", 99, 99.0);
        try {
            lista.insertarAlFinal(p1); lista.insertarAlFinal(p2); lista.insertarAlFinal(p3); System.out.print("Estado inicial: "); lista.imprimir();
            lista.insertarAlInicio(p4); System.out.print("Tras insertar '" + p4.getNombre() + "' al inicio: "); lista.imprimir();
            lista.insertarDespuesDe(p1, p5); System.out.print("Tras insertar '" + p5.getNombre() + "' después de '" + p1.getNombre() + "': "); lista.imprimir();
            lista.insertarAntesDe(p2, pNuevoAntes); System.out.print("Tras insertar '" + pNuevoAntes.getNombre() + "' antes de '" + p2.getNombre() + "': "); lista.imprimir(); System.out.println("Tamaño actual: " + lista.getTamanno());
            System.out.println("\n--- Eliminaciones (Simple) ---");
            Persona eliminada = lista.eliminarAlInicio(); System.out.println("Eliminado al inicio: " + eliminada); lista.imprimir(); eliminada = lista.eliminarAlFinal(); System.out.println("Eliminado al final: " + eliminada); lista.imprimir();
            boolean fueEliminado = lista.eliminar(p1); System.out.println("Eliminando '" + p1.getNombre() + "': " + fueEliminado); lista.imprimir(); eliminada = lista.eliminarDespuesDe(pNuevoAntes); System.out.println("Eliminado después de '" + pNuevoAntes.getNombre() + "': " + eliminada); lista.imprimir(); eliminada = lista.eliminarAntesDe(pNuevoAntes); System.out.println("Eliminado antes de '" + pNuevoAntes.getNombre() + "': " + eliminada); lista.imprimir();
            System.out.println("\n--- Clonar y Borrar (Simple) ---"); ListaEnlazadaSimple<Persona> clon = lista.clonarLista(); System.out.print("Clon: "); clon.imprimir(); lista.borrarLista(); System.out.print("Original borrada: "); lista.imprimir(); System.out.print("Clon persiste: "); clon.imprimir();
        } catch (Exception e) { System.err.println("ERROR inesperado en testListaSimple: " + e.getMessage()); e.printStackTrace(); } System.out.println("Fin Demo Lista Simple.");
    }
    /**
     * Demuestra las operaciones básicas de {@link ListaEnlazadaDoble} con {@link Persona}.
     */
    public static void testListaDoble() {
         System.out.println("Creando Lista Doble con Personas..."); ListaEnlazadaDoble<Persona> lista = new ListaEnlazadaDoble<>(); Persona p1 = new Persona("David", 30, 78.2); Persona p2 = new Persona("Elena", 25, 59.0); Persona p3 = new Persona("Fernando", 45, 85.5); Persona p4 = new Persona("Gloria", 28, 61.0); Persona p5 = new Persona("Hector", 35, 80.0); Persona pNuevoAntesD = new Persona("Nuevo Antes D", 11, 11.0);
         try {
            lista.insertarAlFinal(p1); lista.insertarAlFinal(p2); lista.insertarAlFinal(p3); System.out.print("Estado inicial: "); lista.imprimir(); lista.imprimirReverso();
            lista.insertarAlInicio(p4); System.out.print("Tras insertar '" + p4.getNombre() + "' al inicio: "); lista.imprimir(); lista.insertarDespuesDe(p1, p5); System.out.print("Tras insertar '" + p5.getNombre() + "' después de '" + p1.getNombre() + "': "); lista.imprimir(); lista.insertarAntesDe(p2, pNuevoAntesD); System.out.print("Tras insertar '" + pNuevoAntesD.getNombre() + "' antes de '" + p2.getNombre() + "': "); lista.imprimir(); System.out.println("Tamaño actual: " + lista.getTamanno());
            System.out.println("\n--- Eliminaciones (Doble) ---"); Persona eliminada = lista.eliminarAlInicio(); System.out.println("Eliminado al inicio: " + eliminada); lista.imprimir(); eliminada = lista.eliminarAlFinal(); System.out.println("Eliminado al final: " + eliminada); lista.imprimir(); boolean fueEliminado = lista.eliminar(p1); System.out.println("Eliminando '" + p1.getNombre() + "': " + fueEliminado); lista.imprimir(); eliminada = lista.eliminarDespuesDe(pNuevoAntesD); System.out.println("Eliminado después de '" + pNuevoAntesD.getNombre() + "': " + eliminada); lista.imprimir(); eliminada = lista.eliminarAntesDe(pNuevoAntesD); System.out.println("Eliminado antes de '" + pNuevoAntesD.getNombre() + "': " + eliminada); lista.imprimir();
            System.out.println("\n--- Clonar y Borrar (Doble) ---"); ListaEnlazadaDoble<Persona> clon = lista.clonarLista(); System.out.print("Clon: "); clon.imprimir(); lista.borrarLista(); System.out.print("Original borrada: "); lista.imprimir(); System.out.print("Clon persiste: "); clon.imprimir();
        } catch (Exception e) { System.err.println("ERROR inesperado en testListaDoble: " + e.getMessage()); e.printStackTrace(); } System.out.println("Fin Demo Lista Doble.");
    }
    /**
     * Demuestra las operaciones básicas de {@link ListaEnlazadaSimpleCircular} con {@link Persona}.
     */
    public static void testListaCircular() {
        System.out.println("Creando Lista Circular con Personas..."); ListaEnlazadaSimpleCircular<Persona> lista = new ListaEnlazadaSimpleCircular<>(); Persona p1 = new Persona("Ivan", 19, 68.0); Persona p2 = new Persona("Julia", 22, 57.5); Persona p3 = new Persona("Kevin", 31, 82.1); Persona p4 = new Persona("Laura", 27, 60.3); Persona p5 = new Persona("Mario", 38, 79.8); Persona pNuevoAntesC = new Persona("Nuevo Antes C", 88, 88.0);
        try {
            lista.insertarAlFinal(p1); lista.insertarAlFinal(p2); lista.insertarAlFinal(p3); System.out.print("Estado inicial: "); lista.imprimir();
            lista.insertarAlInicio(p4); System.out.print("Tras insertar '" + p4.getNombre() + "' al inicio: "); lista.imprimir();
            lista.insertarDespuesDe(p1, p5); System.out.print("Tras insertar '" + p5.getNombre() + "' después de '" + p1.getNombre() + "': "); lista.imprimir(); lista.insertarAntesDe(p2, pNuevoAntesC); System.out.print("Tras insertar '" + pNuevoAntesC.getNombre() + "' antes de '" + p2.getNombre() + "': "); lista.imprimir(); System.out.println("Tamaño actual: " + lista.getTamanno());
            System.out.println("\n--- Eliminaciones (Circular) ---"); Persona eliminada = lista.eliminarAlInicio(); System.out.println("Eliminado al inicio: " + eliminada); lista.imprimir(); eliminada = lista.eliminarAlFinal(); System.out.println("Eliminado al final: " + eliminada); lista.imprimir(); boolean fueEliminado = lista.eliminar(p1); System.out.println("Eliminando '" + p1.getNombre() + "': " + fueEliminado); lista.imprimir(); eliminada = lista.eliminarDespuesDe(pNuevoAntesC); System.out.println("Eliminado después de '" + pNuevoAntesC.getNombre() + "': " + eliminada); lista.imprimir(); eliminada = lista.eliminarAntesDe(pNuevoAntesC); System.out.println("Eliminado antes de '" + pNuevoAntesC.getNombre() + "': " + eliminada); lista.imprimir();
            System.out.println("\n--- Clonar y Borrar (Circular) ---"); ListaEnlazadaSimpleCircular<Persona> clon = lista.clonarLista(); System.out.print("Clon: "); clon.imprimir(); lista.borrarLista(); System.out.print("Original borrada: "); lista.imprimir(); System.out.print("Clon persiste: "); clon.imprimir();
        } catch (Exception e) { System.err.println("ERROR inesperado en testListaCircular: " + e.getMessage()); e.printStackTrace(); } System.out.println("Fin Demo Lista Circular.");
    }

    /**
     * Demuestra la ordenación de una {@link ListaEnlazadaSimple} de {@link Persona}.
     * Dado que {@code Persona} ya no es {@code Comparable}, la ordenación natural
     * con las estrategias actuales (que requieren {@code Comparable}) no es posible.
     * Se muestra la lista original y se explica que se necesitaría un {@code Comparator}
     * o estrategias adaptadas para ordenar.
     */
    public static void testOrdenamientoPersonas() {
        System.out.println("Preparando lista de Personas...");
        ListaEnlazadaSimple<Persona> listaOriginal = new ListaEnlazadaSimple<>();
        listaOriginal.agregar(new Persona("Zoe", 25, 60.0)); listaOriginal.agregar(new Persona("Adam", 30, 75.0));
        listaOriginal.agregar(new Persona("Eve", 22, 55.0)); listaOriginal.agregar(new Persona("Peter", 45, 85.0));
        listaOriginal.agregar(new Persona("Maria", 25, 58.0)); listaOriginal.agregar(new Persona("Luke", 18, 70.0));
        System.out.print("Lista Original (Personas): "); listaOriginal.imprimir();

        System.out.println("\nIntentando ordenar Personas (requeriría Comparator o estrategias adaptadas):");
        System.out.println("-> Las estrategias actuales requieren que el elemento sea Comparable.");
        System.out.println("-> Persona ya no implementa Comparable directamente.");
        System.out.println("-> Para ordenar Personas (ej. por edad), se necesitaría un Comparator:");
        System.out.println("   Comparator<Persona> porEdad = Comparator.comparingInt(Persona::getEdad);");
        System.out.println("   (Y adaptar las estrategias o usar métodos de ordenación externos)");

        // Comentado: Las siguientes líneas fallarían en tiempo de ejecución
        // debido a que las estrategias esperan Comparable y Persona no lo es.
        /*
        ejecutarEstrategiaOrdenamiento("Burbuja (Personas por Edad)", new OrdenamientoBurbuja<>(), listaOriginal.clonarLista());
        ejecutarEstrategiaOrdenamiento("Inserción (Personas por Edad)", new OrdenamientoInsercion<>(), listaOriginal.clonarLista());
        ejecutarEstrategiaOrdenamiento("Merge Sort (Personas por Edad)", new OrdenamientoMerge<>(), listaOriginal.clonarLista());
        ejecutarEstrategiaOrdenamiento("Quick Sort (Personas por Edad)", new OrdenamientoQuickSort<>(), listaOriginal.clonarLista());
        */

        System.out.print("Lista Original (Personas - sin modificar): "); listaOriginal.imprimir();
        System.out.println("Fin Demo Ordenamiento Personas.");
    }

    // --- Nuevos Métodos de Prueba (Papas) ---

    /**
     * Demuestra la creación y uso de las tres implementaciones de listas enlazadas
     * (Simple, Doble, Circular) almacenando objetos {@link Papa}.
     * Utiliza una muestra representativa extendida (más de 20 Papas).
     */
    public static void testListasDePapas() {
        System.out.println("Creando muestra extendida de Papas...");
        // --- Crear Instancias de Papa (Muestra Extendida) ---
        Papa[] papasSample = crearMuestraPapas(); // Usa método auxiliar

        // --- Crear las Listas ---
        ListaEnlazadaSimple<Papa> listaPapasSimple = new ListaEnlazadaSimple<>();
        ListaEnlazadaDoble<Papa> listaPapasDoble = new ListaEnlazadaDoble<>();
        ListaEnlazadaSimpleCircular<Papa> listaPapasCircular = new ListaEnlazadaSimpleCircular<>();

        System.out.println("Agregando Papas a las listas...");
        for (Papa papa : papasSample) {
            listaPapasSimple.agregar(papa);
            listaPapasDoble.agregar(papa);
            listaPapasCircular.agregar(papa);
        }

        // --- Imprimir las Listas ---
        System.out.println("\n--- Lista Simple de Papas ---");
        listaPapasSimple.imprimir();

        System.out.println("\n--- Lista Doble de Papas ---");
        listaPapasDoble.imprimir();
        // System.out.println("--- Lista Doble de Papas (Reverso) ---"); // Omitido por longitud
        // listaPapasDoble.imprimirReverso();

        System.out.println("\n--- Lista Circular de Papas ---");
        listaPapasCircular.imprimir();

        System.out.println("\n(Nota: Se muestra una muestra extendida de " + papasSample.length + " Papas)");
        System.out.println("Fin Demo Listas de Papas.");
    }

    /**
     * Demuestra la ordenación de una {@link ListaEnlazadaSimple} de {@link Papa}.
     * La ordenación se basa en la fecha de inicio del papado (implementación de {@code Papa.compareTo()}).
     * Utiliza una muestra desordenada de Papas.
     */
    public static void testOrdenamientoPapas() {
        System.out.println("Preparando lista desordenada de Papas para ordenar...");
        ListaEnlazadaSimple<Papa> listaOriginal = new ListaEnlazadaSimple<>();
        // Añadir Papas en desorden (muestra de 20)
        listaOriginal.agregar(new Papa("León X", 37, 80.0, LocalDate.of(1513, 3, 9), LocalDate.of(1521, 12, 1), "Inicio de la Reforma, mecenas"));
        listaOriginal.agregar(new Papa("Francisco", 76, 77.0, LocalDate.of(2013, 3, 13), null, "Misericordia, Laudato Si'"));
        listaOriginal.agregar(new Papa("San Pedro", 60, 70.0, LocalDate.of(30, 1, 1), LocalDate.of(67, 6, 29), "Fundación de la Iglesia en Roma"));
        listaOriginal.agregar(new Papa("Juan Pablo II", 58, 78.0, LocalDate.of(1978, 10, 16), LocalDate.of(2005, 4, 2), "Viajes Apostólicos, Caída del Comunismo"));
        listaOriginal.agregar(new Papa("Gregorio I", 50, 68.0, LocalDate.of(590, 9, 3), LocalDate.of(604, 3, 12), "Canto Gregoriano, Misiones"));
        listaOriginal.agregar(new Papa("Inocencio III", 37, 72.0, LocalDate.of(1198, 1, 8), LocalDate.of(1216, 7, 16), "IV Concilio de Letrán, Cénit poder papal"));
        listaOriginal.agregar(new Papa("Bonifacio VIII", 68, 74.0, LocalDate.of(1294, 12, 24), LocalDate.of(1303, 10, 11), "Bula Unam Sanctam"));
        listaOriginal.agregar(new Papa("Pío IX", 54, 75.0, LocalDate.of(1846, 6, 16), LocalDate.of(1878, 2, 7), "Concilio Vaticano I, Pérdida Estados Pontificios"));
        listaOriginal.agregar(new Papa("San León I Magno", 50, 71.0, LocalDate.of(440, 9, 29), LocalDate.of(461, 11, 10), "Concilio de Calcedonia, detuvo a Atila"));
        listaOriginal.agregar(new Papa("Urbano II", 50, 70.0, LocalDate.of(1088, 3, 12), LocalDate.of(1099, 7, 29), "Convocatoria de la Primera Cruzada"));
        listaOriginal.agregar(new Papa("San Pío V", 62, 65.0, LocalDate.of(1566, 1, 7), LocalDate.of(1572, 5, 1), "Implementación Trento, Batalla de Lepanto"));
        listaOriginal.agregar(new Papa("San Juan XXIII", 76, 80.0, LocalDate.of(1958, 10, 28), LocalDate.of(1963, 6, 3), "Convocatoria Concilio Vaticano II"));
        listaOriginal.agregar(new Papa("San Lino", 65, 68.0, LocalDate.of(67, 6, 30), LocalDate.of(76, 9, 23), "Primer sucesor de Pedro"));
        listaOriginal.agregar(new Papa("Benedicto XVI", 78, 76.0, LocalDate.of(2005, 4, 19), LocalDate.of(2013, 2, 28), "Renuncia al pontificado"));
        listaOriginal.agregar(new Papa("Martín V", 49, 70.0, LocalDate.of(1417, 11, 11), LocalDate.of(1431, 2, 20), "Fin del Cisma de Occidente"));
        listaOriginal.agregar(new Papa("San Silvestre I", 50, 69.0, LocalDate.of(314, 1, 31), LocalDate.of(335, 12, 31), "Papado durante Constantino, Concilio de Nicea I"));
        listaOriginal.agregar(new Papa("León XIII", 68, 70.0, LocalDate.of(1878, 2, 20), LocalDate.of(1903, 7, 20), "Encíclica Rerum Novarum (Doctrina Social)"));
        listaOriginal.agregar(new Papa("San Nicolás I Magno", 42, 70.0, LocalDate.of(858, 4, 24), LocalDate.of(867, 11, 13), "Afirmación autoridad papal"));
        listaOriginal.agregar(new Papa("Alejandro VI", 61, 80.0, LocalDate.of(1492, 8, 11), LocalDate.of(1503, 8, 18), "Tratado de Tordesillas, mecenazgo controvertido"));
        listaOriginal.agregar(new Papa("San Paulo VI", 65, 75.0, LocalDate.of(1963, 6, 21), LocalDate.of(1978, 8, 6), "Clausura Vaticano II, Humanae Vitae"));

        System.out.print("Lista Original (Papas desordenados): "); listaOriginal.imprimir();

        // Ejecutar cada estrategia en un clon
        ejecutarEstrategiaOrdenamiento("Burbuja (Papas por Inicio Papado)", new OrdenamientoBurbuja<>(), listaOriginal.clonarLista());
        ejecutarEstrategiaOrdenamiento("Inserción (Papas por Inicio Papado)", new OrdenamientoInsercion<>(), listaOriginal.clonarLista());
        ejecutarEstrategiaOrdenamiento("Merge Sort (Papas por Inicio Papado)", new OrdenamientoMerge<>(), listaOriginal.clonarLista());
        ejecutarEstrategiaOrdenamiento("Quick Sort (Papas por Inicio Papado)", new OrdenamientoQuickSort<>(), listaOriginal.clonarLista());

        System.out.print("Lista Original (Papas - sin modificar): "); listaOriginal.imprimir();
        System.out.println("Fin Demo Ordenamiento Papas.");
    }


    // --- Métodos Auxiliares ---

     /**
     * Método auxiliar genérico para ejecutar una estrategia de ordenación en una lista
     * y mostrar el resultado, manejando posibles errores.
     *
     * @param <T> El tipo de elementos en la lista (debe ser Comparable).
     * @param nombrePrueba Nombre descriptivo de la prueba para imprimir.
     * @param estrategia La instancia de {@link EstrategiaOrdenamiento} a usar.
     * @param lista El clon de la {@link ListaEnlazadaSimple} a ordenar.
     */
    private static <T extends Comparable<T>> void ejecutarEstrategiaOrdenamiento(
            String nombrePrueba, EstrategiaOrdenamiento<T> estrategia, ListaEnlazadaSimple<T> lista)
    {
        System.out.println("\n--- Ordenando con " + nombrePrueba + " ---");
        try {
            Objects.requireNonNull(estrategia, "La estrategia no puede ser null para " + nombrePrueba);
            Objects.requireNonNull(lista, "La lista no puede ser null para " + nombrePrueba);
            // Clonar de nuevo por si acaso la referencia original se pasó por error
            ListaEnlazadaSimple<T> listaParaOrdenar = lista; //.clonarLista(); // Clonar si 'lista' no es ya un clon

            long startTime = System.nanoTime(); // Medir tiempo (opcional)
            listaParaOrdenar.ordenar(estrategia);
            long endTime = System.nanoTime(); // Medir tiempo (opcional)

            System.out.print("Resultado: ");
            listaParaOrdenar.imprimir();
            // System.out.printf("Tiempo empleado: %.3f ms%n", (endTime - startTime) / 1_000_000.0); // Opcional

        } catch (UnsupportedOperationException usoEx) {
             System.err.println("ERROR: La estrategia '" + nombrePrueba + "' requiere una operación no soportada (probablemente Nodo.setDato()). " + usoEx.getMessage());
        } catch (IllegalStateException ise) {
             System.err.println("ERROR: No se puede ordenar con '" + nombrePrueba + "'. " + ise.getMessage());
        } catch (Exception e) {
            System.err.println("ERROR inesperado durante ordenación '" + nombrePrueba + "': " + e.getMessage());
            e.printStackTrace(); // Mostrar traza completa para errores inesperados
        }
    }

    /**
     * Método auxiliar para crear el array de muestra extendida de Papas.
     * Separa la creación de datos del método de prueba principal.
     *
     * @return Un array de objetos {@link Papa}.
     */
    private static Papa[] crearMuestraPapas() {
         return new Papa[]{
            new Papa("San Pedro", 60, 70.0, LocalDate.of(30, 1, 1), LocalDate.of(67, 6, 29), "Fundación de la Iglesia en Roma"),
            new Papa("San Lino", 65, 68.0, LocalDate.of(67, 6, 30), LocalDate.of(76, 9, 23), "Primer sucesor de Pedro"),
            new Papa("San Clemente I", 60, 70.0, LocalDate.of(88, 1, 1), LocalDate.of(99, 11, 23), "Carta a los Corintios"),
            new Papa("San Víctor I", 55, 72.0, LocalDate.of(189, 1, 1), LocalDate.of(199, 7, 28), "Controversia sobre la fecha de Pascua"),
            new Papa("San Silvestre I", 50, 69.0, LocalDate.of(314, 1, 31), LocalDate.of(335, 12, 31), "Papado durante Constantino, Concilio de Nicea I"),
            new Papa("San León I Magno", 50, 71.0, LocalDate.of(440, 9, 29), LocalDate.of(461, 11, 10), "Concilio de Calcedonia, detuvo a Atila"),
            new Papa("San Gregorio I Magno", 50, 68.0, LocalDate.of(590, 9, 3), LocalDate.of(604, 3, 12), "Canto Gregoriano, Misiones"),
            new Papa("San Nicolás I Magno", 42, 70.0, LocalDate.of(858, 4, 24), LocalDate.of(867, 11, 13), "Afirmación autoridad papal"),
            new Papa("San Gregorio VII", 50, 65.0, LocalDate.of(1073, 4, 22), LocalDate.of(1085, 5, 25), "Querella de las Investiduras"),
            new Papa("Beato Urbano II", 50, 70.0, LocalDate.of(1088, 3, 12), LocalDate.of(1099, 7, 29), "Convocatoria de la Primera Cruzada"),
            new Papa("Inocencio III", 37, 72.0, LocalDate.of(1198, 1, 8), LocalDate.of(1216, 7, 16), "IV Concilio de Letrán, Cénit poder papal"),
            new Papa("Bonifacio VIII", 68, 74.0, LocalDate.of(1294, 12, 24), LocalDate.of(1303, 10, 11), "Bula Unam Sanctam"),
            new Papa("Clemente VI", 51, 75.0, LocalDate.of(1342, 5, 7), LocalDate.of(1352, 12, 6), "Papado de Aviñón, Peste Negra"),
            new Papa("Martín V", 49, 70.0, LocalDate.of(1417, 11, 11), LocalDate.of(1431, 2, 20), "Fin del Cisma de Occidente"),
            new Papa("Nicolás V", 50, 68.0, LocalDate.of(1447, 3, 6), LocalDate.of(1455, 3, 24), "Fundación Biblioteca Vaticana"),
            new Papa("Alejandro VI", 61, 80.0, LocalDate.of(1492, 8, 11), LocalDate.of(1503, 8, 18), "Tratado de Tordesillas, mecenazgo controvertido"),
            new Papa("Julio II", 60, 78.0, LocalDate.of(1503, 11, 1), LocalDate.of(1513, 2, 21), "Guerrero, mecenas (Miguel Ángel, Rafael)"),
            new Papa("León X", 37, 80.0, LocalDate.of(1513, 3, 9), LocalDate.of(1521, 12, 1), "Inicio de la Reforma, mecenas"),
            new Papa("Paulo III", 66, 75.0, LocalDate.of(1534, 10, 13), LocalDate.of(1549, 11, 10), "Convocatoria Concilio de Trento"),
            new Papa("San Pío V", 62, 65.0, LocalDate.of(1566, 1, 7), LocalDate.of(1572, 5, 1), "Implementación Trento, Batalla de Lepanto"),
            new Papa("Urbano VIII", 55, 77.0, LocalDate.of(1623, 8, 6), LocalDate.of(1644, 7, 29), "Caso Galileo, mecenas (Bernini)"),
            new Papa("Beato Pío IX", 54, 75.0, LocalDate.of(1846, 6, 16), LocalDate.of(1878, 2, 7), "Concilio Vaticano I, Pérdida Estados Pontificios"),
            new Papa("León XIII", 68, 70.0, LocalDate.of(1878, 2, 20), LocalDate.of(1903, 7, 20), "Encíclica Rerum Novarum (Doctrina Social)"),
            new Papa("San Pío X", 68, 72.0, LocalDate.of(1903, 8, 4), LocalDate.of(1914, 8, 20), "Lucha contra el modernismo, reforma litúrgica"),
            new Papa("Benedicto XV", 59, 70.0, LocalDate.of(1914, 9, 3), LocalDate.of(1922, 1, 22), "Papado durante la Primera Guerra Mundial"),
            new Papa("San Juan XXIII", 76, 80.0, LocalDate.of(1958, 10, 28), LocalDate.of(1963, 6, 3), "Convocatoria Concilio Vaticano II"),
            new Papa("San Paulo VI", 65, 75.0, LocalDate.of(1963, 6, 21), LocalDate.of(1978, 8, 6), "Clausura Vaticano II, Humanae Vitae"),
            new Papa("San Juan Pablo II", 58, 78.0, LocalDate.of(1978, 10, 16), LocalDate.of(2005, 4, 2), "Viajes Apostólicos, Caída del Comunismo"),
            new Papa("Benedicto XVI", 78, 76.0, LocalDate.of(2005, 4, 19), LocalDate.of(2013, 2, 28), "Renuncia al pontificado"),
            new Papa("Francisco", 76, 77.0, LocalDate.of(2013, 3, 13), null, "Misericordia, Laudato Si'")
        };
    }
}
