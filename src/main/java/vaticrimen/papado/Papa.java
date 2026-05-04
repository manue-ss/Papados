package vaticrimen.papado;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Objects;

/**
 * Representa a un Papa de la Iglesia Católica, extendiendo la clase {@link Persona}.
 * Incluye información específica del pontificado como fechas y acciones destacadas.
 * Implementa {@link Comparable}<{@link Papa}> para definir la ordenación natural
 * cronológica por fecha de inicio del papado.
 *
 * @see Persona
 * @author devapps (modificado)
 * @version 1.2 - Corregido Comparable
 */
// La declaración ahora es correcta y no entra en conflicto con la superclase
public class Papa extends Persona implements Comparable<Papa> {

    /** Formateador estándar para mostrar fechas de forma localizada y legible. */
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);

    /** Fecha de inicio del pontificado (obligatoria). */
    private LocalDate fechaInicioPapado;
    /** Fecha de fin del pontificado (puede ser {@code null}). */
    private LocalDate fechaFinPapado;
    /** Descripción breve de una acción relevante durante el pontificado. */
    private String accionImportante;

    /**
     * Construye una nueva instancia de Papa.
     *
     * @param nombre Nombre papal. No debe ser null.
     * @param edad Edad estimada (no negativa).
     * @param peso Peso estimado (positivo o cero).
     * @param fechaInicioPapado Fecha de inicio del pontificado (no debe ser null).
     * @param fechaFinPapado Fecha de fin del pontificado ({@code null} si aplica).
     * @param accionImportante Breve descripción de una acción clave.
     * @throws NullPointerException si {@code nombre} o {@code fechaInicioPapado} son null.
     * @throws IllegalArgumentException si {@code edad} es negativa.
     */
    public Papa(String nombre, int edad, double peso,
                LocalDate fechaInicioPapado, LocalDate fechaFinPapado,
                String accionImportante) {
        super(nombre, edad, peso);
        Objects.requireNonNull(fechaInicioPapado, "La fecha de inicio del papado no puede ser null.");
        this.fechaInicioPapado = fechaInicioPapado;
        this.fechaFinPapado = fechaFinPapado;
        this.accionImportante = (accionImportante != null && !accionImportante.trim().isEmpty())
                                ? accionImportante.trim() : "No registrada";
    }

    // --- Getters ---
    /** @return La fecha {@link LocalDate} de inicio. */
    public LocalDate getFechaInicioPapado() { return fechaInicioPapado; }
    /** @return La fecha {@link LocalDate} de fin, o {@code null}. */
    public LocalDate getFechaFinPapado() { return fechaFinPapado; }
    /** @return La cadena con la acción importante. */
    public String getAccionImportante() { return accionImportante; }

    // --- Setters ---
    /** @param fechaInicioPapado La nueva fecha (no debe ser null). */
    public void setFechaInicioPapado(LocalDate fechaInicioPapado) {
        Objects.requireNonNull(fechaInicioPapado, "La fecha de inicio del papado no puede ser null.");
        this.fechaInicioPapado = fechaInicioPapado; }
    /** @param fechaFinPapado La nueva fecha de fin (puede ser {@code null}). */
    public void setFechaFinPapado(LocalDate fechaFinPapado) { this.fechaFinPapado = fechaFinPapado; }
    /** @param accionImportante La nueva descripción. */
    public void setAccionImportante(String accionImportante) {
        this.accionImportante = (accionImportante != null && !accionImportante.trim().isEmpty())
            ? accionImportante.trim() : "No registrada"; }

    // --- Overrides ---

    /**
     * Devuelve una representación textual formateada del Papa.
     * @return Una cadena descriptiva del Papa.
     */
    @Override
    public String toString() {
        String periodo;
        String inicioStr = fechaInicioPapado.format(DATE_FORMATTER);
        if (fechaFinPapado != null) {
            periodo = String.format("%s - %s", inicioStr, fechaFinPapado.format(DATE_FORMATTER));
        } else {
            periodo = String.format("%s - Presente", inicioStr);
        }
        String infoPersona = super.toString();
        return String.format("%s | Papado: %s | Acción: %s", infoPersona, periodo, accionImportante);
    }

    /**
     * Compara este Papa con otro objeto para determinar igualdad.
     * @param o El objeto a comparar.
     * @return {@code true} si son el mismo Papa (nombre y fecha inicio), {@code false} en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Papa papa = (Papa) o;
        return this.getNombre().equalsIgnoreCase(papa.getNombre()) &&
               this.fechaInicioPapado.equals(papa.fechaInicioPapado);
    }

    /**
     * Genera un código hash para este Papa.
     * @return El código hash calculado.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getNombre().toLowerCase(), fechaInicioPapado);
    }

    /**
     * Compara este Papa con otro basándose en la fecha de inicio del papado (orden cronológico).
     * Esta es la implementación del contrato {@link Comparable}<{@link Papa}>.
     *
     * @param other El otro Papa a comparar (no debe ser null).
     * @return Un entero negativo, cero o positivo si este Papa inició antes,
     *         en la misma fecha, o después que {@code other}.
     * @throws NullPointerException si {@code other} es null.
     */
    @Override // Esta anotación ahora es válida
    public int compareTo(Papa other) {
        Objects.requireNonNull(other, "No se puede comparar con un Papa null.");
        return this.fechaInicioPapado.compareTo(other.fechaInicioPapado);
    }
}

