public class Estudiante {
    private String codigo;
    private String nombre;
    // REEMPLAZO: De ArrayList<Evaluacion> a Evaluacion[]
    private Evaluacion[] evaluaciones;
    private int contadorEvaluaciones;
    private final int MAX_EVALUACIONES = 10; // Ajusta el tamaño máximo según necesites

    public Estudiante(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.evaluaciones = new Evaluacion[MAX_EVALUACIONES];
        this.contadorEvaluaciones = 0;
    }

    public boolean agregarEvaluacion(Evaluacion ev) {
        if (contadorEvaluaciones < MAX_EVALUACIONES) {
            evaluaciones[contadorEvaluaciones] = ev;
            contadorEvaluaciones++;
            return true;
        }
        return false; // Arreglo lleno
    }

    public double calcularPromedio() {
        if (contadorEvaluaciones == 0) return 0.0;
        double suma = 0;
        for (int i = 0; i < contadorEvaluaciones; i++) {
            suma += evaluaciones[i].getNota();
        }
        return suma / contadorEvaluaciones;
    }
    
    // Getters y Setters...
}
