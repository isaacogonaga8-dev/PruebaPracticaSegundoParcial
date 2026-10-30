import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Estudiante {
    private String codigo;
    private String nombre;
    private List<Evaluacion> evaluaciones;

    public Estudiante(String codigo, String nombre) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El código del estudiante no puede estar vacío.");
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del estudiante no puede estar vacío.");
        }
        this.codigo = codigo.trim().toUpperCase();
        this.nombre = nombre.trim();
        this.evaluaciones = new ArrayList<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Evaluacion> getEvaluaciones() {
        return Collections.unmodifiableList(evaluaciones);
    }

    public int cantidadEvaluaciones() {
        return evaluaciones.size();
    }

    public boolean tieneEvaluaciones() {
        return !evaluaciones.isEmpty();
    }

    public void agregarEvaluacion(Evaluacion evaluacion) {
        if (evaluacion == null) {
            throw new IllegalArgumentException("La evaluación no puede ser nula.");
        }
        for (Evaluacion e : evaluaciones) {
            if (e.getNombre().equalsIgnoreCase(evaluacion.getNombre())) {
                throw new IllegalStateException(
                    "El estudiante " + codigo + " ya tiene una evaluación llamada '" + evaluacion.getNombre() + "'.");
            }
        }
        evaluaciones.add(evaluacion);
    }

    public double calcularPromedio() {
        if (evaluaciones.isEmpty()) {
            return 0.0;
        }
        double suma = 0;
        for (Evaluacion e : evaluaciones) {
            suma += e.getNota();
        }
        return suma / evaluaciones.size();
    }

    @Override
    public String toString() {
        return String.format("%s - %s (promedio: %.2f, evaluaciones: %d)",
                codigo, nombre, calcularPromedio(), evaluaciones.size());
    }
}
