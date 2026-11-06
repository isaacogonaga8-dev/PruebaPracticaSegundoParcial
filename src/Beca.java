public class Beca {
    private int cupoMaximo;
    private double notaMinima;
    private Estudiante[] beneficiarios;
    private int contadorBeneficiarios;

    public Beca() {
        this.cupoMaximo = 0; 
        this.notaMinima = 8.5; // Según el objetivo del proyecto
        this.beneficiarios = new Estudiante[100]; // Tamaño máximo estimado
        this.contadorBeneficiarios = 0;
    }

    public void configurarCupo(int cupo) {
        this.cupoMaximo = cupo;
        this.beneficiarios = new Estudiante[cupoMaximo];
        this.contadorBeneficiarios = 0;
    }

    public boolean esElegible(Estudiante e) {
        return e.calcularPromedio() >= notaMinima;
    }

    public boolean hayCupo() {
        return contadorBeneficiarios < cupoMaximo;
    }

    public boolean agregarBeneficiario(Estudiante e) {
        if (hayCupo() && !esBeneficiario(e)) {
            beneficiarios[contadorBeneficiarios++] = e;
            return true;
        }
        return false;
    }

    public boolean esBeneficiario(Estudiante e) {
        for (int i = 0; i < contadorBeneficiarios; i++) {
            if (beneficiarios[i].getCodigo().equalsIgnoreCase(e.getCodigo())) {
                return true;
            }
        }
        return false;
    }

    public void reiniciarAsignacion() {
        this.contadorBeneficiarios = 0;
        if (cupoMaximo > 0) {
            this.beneficiarios = new Estudiante[cupoMaximo];
        }
    }

    public int getCupoMaximo() { return cupoMaximo; }
    public int getContadorBeneficiarios() { return contadorBeneficiarios; }
    public Estudiante[] getBeneficiarios() { return beneficiarios; }
}
