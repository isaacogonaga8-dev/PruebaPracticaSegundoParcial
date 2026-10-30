import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GestorBecas gestor = new GestorBecas(new Beca("Beca de Excelencia Académica", 2));
        int opcion = -1;

        do {
            mostrarMenu(gestor);
            opcion = leerEntero(sc, "Elija una opción: ");
            try {
                switch (opcion) {
                    case 1:
                        registrarEstudiante(sc, gestor);
                        break;
                    case 2:
                        registrarEvaluacion(sc, gestor);
                        break;
                    case 3:
                        listarEstudiantes(gestor);
                        break;
                    case 4:
                        mostrarRanking(gestor);
                        break;
                    case 5:
                        int cupo = leerEntero(sc, "Nuevo cupo máximo de becas: ");
                        gestor.configurarCupo(cupo);
                        System.out.println("Cupo actualizado a " + cupo + ". Debe volver a asignar las becas.");
                        break;
                    case 6:
                        int asignadas = gestor.asignarBecas();
                        System.out.println("Asignación realizada. Becas asignadas: " + asignadas);
                        break;
                    case 7:
                        System.out.print("Código del estudiante: ");
                        System.out.println(gestor.explicarEstudiante(sc.nextLine()));
                        break;
                    case 8:
                        System.out.println(gestor.generarInformeAsignacion());
                        break;
                    case 9:
                        gestor.cargarDatosDemo();
                        System.out.println("Datos de demostración cargados.");
                        break;
                    case 0:
                        System.out.println("Fin del programa.");
                        break;
                    default:
                        System.out.println("Opción inválida. Elija un número del 0 al 9.");
                }
            } catch (IllegalArgumentException | IllegalStateException ex) {
                System.out.println("ERROR: " + ex.getMessage());
            }
        } while (opcion != 0);

        sc.close();
    }

    private static void mostrarMenu(GestorBecas gestor) {
        System.out.println("\n===== GESTIÓN DE BECAS =====");
        System.out.println(gestor.getBeca());
        System.out.println("1. Registrar estudiante");
        System.out.println("2. Registrar evaluación");
        System.out.println("3. Listar estudiantes y promedios");
        System.out.println("4. Ver ranking de elegibles");
        System.out.println("5. Configurar cupo de becas");
        System.out.println("6. Asignar becas");
        System.out.println("7. Explicar situación de un estudiante");
        System.out.println("8. Informe de asignación");
        System.out.println("9. Cargar datos de demostración");
        System.out.println("0. Salir");
    }

    private static void registrarEstudiante(Scanner sc, GestorBecas gestor) {
        System.out.print("Código: ");
        String codigo = sc.nextLine();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        Estudiante e = gestor.registrarEstudiante(codigo, nombre);
        System.out.println("Registrado: " + e);
    }

    private static void registrarEvaluacion(Scanner sc, GestorBecas gestor) {
        System.out.print("Código del estudiante: ");
        String codigo = sc.nextLine();
        System.out.print("Nombre de la evaluación: ");
        String nombre = sc.nextLine();
        double nota = leerDecimal(sc, "Nota (0 a 10): ");
        gestor.registrarEvaluacion(codigo, nombre, nota);
        System.out.printf("Evaluación registrada. Promedio actual: %.2f%n", gestor.calcularPromedio(codigo));
    }

    private static void listarEstudiantes(GestorBecas gestor) {
        List<Estudiante> lista = gestor.getEstudiantes();
        if (lista.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
            return;
        }
        for (Estudiante e : lista) {
            String estado = !e.tieneEvaluaciones() ? "sin evaluaciones"
                    : gestor.esElegible(e) ? "elegible" : "no elegible";
            System.out.println(e + " -> " + estado);
        }
    }

    private static void mostrarRanking(GestorBecas gestor) {
        List<Estudiante> ranking = gestor.obtenerRankingElegibles();
        if (ranking.isEmpty()) {
            System.out.println("No hay candidatos elegibles.");
            return;
        }
        int pos = 1;
        for (Estudiante e : ranking) {
            System.out.printf("%d. %s - %s | promedio %.2f%n", pos++, e.getCodigo(), e.getNombre(), e.calcularPromedio());
        }
    }

    
    private static int leerEntero(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String linea = sc.nextLine().trim();
            try {
                return Integer.parseInt(linea);
            } catch (NumberFormatException ex) {
                System.out.println("Entrada inválida: ingrese un número entero.");
            }
        }
    }

    
    private static double leerDecimal(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String linea = sc.nextLine().trim().replace(',', '.');
            try {
                return Double.parseDouble(linea);
            } catch (NumberFormatException ex) {
                System.out.println("Entrada inválida: ingrese un número (ej. 8.5).");
            }
        }
    }
}
