public class Evaluacion {
    public static final double NOTA_MINIMA = 0.0;
    public static final double NOTA_MAXIMA = 10.0;

    private String nombre;
    private double nota;

    public Evaluacion(String nombre, double nota) {
        setNombre(nombre);
        setNota(nota);
    }

    public String getNombre() {
        return nombre;
    }

    public double getNota() {
        return nota;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la evaluación no puede estar vacío.");
        }
        this.nombre = nombre.trim();
    }

    public void setNota(double nota) {
        if (Double.isNaN(nota) || nota < NOTA_MINIMA || nota > NOTA_MAXIMA) {
            throw new IllegalArgumentException(
                "Nota inválida (" + nota + "). Debe estar entre " + NOTA_MINIMA + " y " + NOTA_MAXIMA + ".");
        }
        this.nota = nota;
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f", nombre, nota);
    }
}
