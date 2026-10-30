import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Beca {
    public static final double NOTA_MINIMA_DEFECTO = 8.5;
    public static final double TOLERANCIA = 1e-9;

    private String nombre;
    private int cupoMaximo;
    private double notaMinima;
    private List<Estudiante> beneficiarios;

    public Beca(String nombre, int cupoMaximo) {
        this(nombre, cupoMaximo, NOTA_MINIMA_DEFECTO);
    }

    public Beca(String nombre, int cupoMaximo, double notaMinima) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la beca no puede estar vacío.");
        }
        this.nombre = nombre.trim();
        this.beneficiarios = new ArrayList<>();
        setCupoMaximo(cupoMaximo);
        setNotaMinima(notaMinima);
    }

    public String getNombre() {
        return nombre;
    }

    public int getCupoMaximo() {
        return cupoMaximo;
    }

    public double getNotaMinima() {
        return notaMinima;
    }

    public List<Estudiante> getBeneficiarios() {
        return Collections.unmodifiableList(beneficiarios);
    }

    public void setCupoMaximo(int cupoMaximo) {
        if (cupoMaximo <= 0) {
            throw new IllegalArgumentException("El cupo máximo debe ser mayor que 0.");
        }
        this.cupoMaximo = cupoMaximo;
    }

    public void setNotaMinima(double notaMinima) {
        if (Double.isNaN(notaMinima) || notaMinima < Evaluacion.NOTA_MINIMA || notaMinima > Evaluacion.NOTA_MAXIMA) {
            throw new IllegalArgumentException("La nota mínima debe estar entre 0 y 10.");
        }
        this.notaMinima = notaMinima;
    }

    public int cuposOcupados() {
        return beneficiarios.size();
    }

    public int cuposDisponibles() {
        return Math.max(0, cupoMaximo - beneficiarios.size());
    }

    public boolean hayCupo() {
        return beneficiarios.size() < cupoMaximo;
    }

    public boolean esBeneficiario(Estudiante e) {
        for (Estudiante b : beneficiarios) {
            if (b.getCodigo().equals(e.getCodigo())) {
                return true;
            }
        }
        return false;
    }

    public boolean esElegible(Estudiante e) {
        return e.tieneEvaluaciones() && e.calcularPromedio() + TOLERANCIA >= notaMinima;
    }

    public void agregarBeneficiario(Estudiante e) {
        if (!esElegible(e)) {
            throw new IllegalStateException("El estudiante " + e.getCodigo() + " no es elegible para la beca.");
        }
        if (esBeneficiario(e)) {
            throw new IllegalStateException("El estudiante " + e.getCodigo() + " ya es beneficiario (duplicado).");
        }
        if (!hayCupo()) {
            throw new IllegalStateException("No hay cupos disponibles en la beca '" + nombre + "'.");
        }
        beneficiarios.add(e);
    }

    public void reiniciarAsignacion() {
        beneficiarios.clear();
    }

    @Override
    public String toString() {
        return String.format("Beca '%s' | cupo: %d | nota mínima: %.2f | ocupados: %d",
                nombre, cupoMaximo, notaMinima, beneficiarios.size());
    }
}
