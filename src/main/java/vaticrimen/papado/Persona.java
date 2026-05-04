
package vaticrimen.papado;

import java.util.Objects;

/**
 * Representa a una persona con atributos básicos como nombre, edad y peso.
 * Esta clase sirve como base para otras clases más específicas (ej. {@link Papa}).
 * <p>
 * Nota: Esta versión NO implementa Comparable directamente. Si se necesita ordenar
 * Personas, se debe usar un {@link java.util.Comparator}.
 * </p>
 *
 * @author devapps
 * @version 1.2 - Comparable removido
 */
public class Persona { // Removido: implements Comparable<Persona>

    /** El nombre de la persona. */
    private String nombre;
    /** La edad de la persona en años. */
    private int edad;
    /** El peso de la persona en kilogramos. */
    private double peso;

    /**
     * Construye una nueva instancia de Persona.
     *
     * @param nombre El nombre de la persona (no debe ser null).
     * @param edad   La edad de la persona (debe ser no negativa).
     * @param peso   El peso de la persona en kg (positivo o cero).
     * @throws NullPointerException si el nombre es null.
     * @throws IllegalArgumentException si la edad es negativa.
     */
    public Persona(String nombre, int edad, double peso) {
        Objects.requireNonNull(nombre, "El nombre no puede ser null.");
        if (edad < 0) {
            throw new IllegalArgumentException("La edad no puede ser negativa.");
        }
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
    }

    // --- Getters ---

    /**
     * Obtiene el nombre de la persona.
     * @return El nombre.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene la edad de la persona.
     * @return La edad en años.
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Obtiene el peso de la persona.
     * @return El peso en kg.
     */
    public double getPeso() {
        return peso;
    }

    // --- Setters ---

    /**
     * Establece el nombre de la persona.
     * @param nombre El nuevo nombre (no debe ser null).
     * @throws NullPointerException si el nombre es null.
     */
    public void setNombre(String nombre) {
        Objects.requireNonNull(nombre, "El nombre no puede ser null.");
        this.nombre = nombre;
    }

    /**
     * Establece la edad de la persona.
     * @param edad La nueva edad (no debe ser negativa).
     * @throws IllegalArgumentException si la edad es negativa.
     */
    public void setEdad(int edad) {
        if (edad < 0) {
            throw new IllegalArgumentException("La edad no puede ser negativa.");
        }
        this.edad = edad;
    }

    /**
     * Establece el peso de la persona.
     * @param peso El nuevo peso en kg.
     */
    public void setPeso(double peso) {
        this.peso = peso;
    }

    // --- Overrides ---

    /**
     * Devuelve una representación textual de la persona.
     * Incluye nombre, edad y peso formateado.
     *
     * @return Una cadena que representa a la persona.
     */
    @Override
    public String toString() {
        return String.format("%s (Edad: %d, Peso: %.1f kg)", nombre, edad, peso);
    }

    /**
     * Compara esta Persona con otro objeto para determinar igualdad.
     * Dos personas son consideradas iguales si tienen el mismo nombre (ignorando mayúsculas/minúsculas)
     * y la misma edad.
     *
     * @param o El objeto a comparar.
     * @return {@code true} si los objetos son iguales, {@code false} en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return edad == persona.edad &&
               nombre.equalsIgnoreCase(persona.nombre);
    }

    /**
     * Genera un código hash para esta Persona.
     * Basado en el nombre (convertido a minúsculas) y la edad.
     *
     * @return El código hash calculado.
     */
    @Override
    public int hashCode() {
        return Objects.hash(nombre.toLowerCase(), edad);
    }

    /* --- compareTo Removido ---
     * El método compareTo(Persona other) ha sido eliminado.
     * Usar un Comparator<Persona> externo si se necesita ordenar por edad.
     */

}
