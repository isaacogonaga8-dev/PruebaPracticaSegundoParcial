import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GestorBecas gestor = new GestorBecas();
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n=== SISTEMA DE GESTIÓN DE BECAS ===");
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
            System.out.print("Seleccione una opción: ");
            
            while (!sc.hasNextInt()) {
                System.out.print("Opción inválida. Ingrese un número: ");
                sc.next();
            }
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.print("Código: "); String cod = sc.nextLine();
                    System.out.print("Nombre: "); String nom = sc.nextLine();
                    if (gestor.registrarEstudiante(cod, nom)) System.out.println("Registrado con éxito.");
                    else System.out.println("Error al registrar (campos vacíos o código duplicado).");
                    break;
                case 2:
                    System.out.print("Código estudiante: "); String c = sc.nextLine();
                    System.out.print("Nombre Evaluación: "); String evNom = sc.nextLine();
                    System.out.print("Nota (0-10): "); 
                    while (!sc.hasNextDouble()) { System.out.print("Número inválido. Nota: "); sc.next(); }
                    double nota = sc.nextDouble();
                    if (gestor.registrarEvaluacion(c, evNom, nota)) System.out.println("Evaluación añadida.");
                    else System.out.println("Error al registrar nota (Rango 0-10 o evaluación repetida).");
                    break;
                case 3:
                    gestor.listarEstudiantes();
                    break;
                case 4:
                    int[] tam = new int[1];
                    Estudiante[] rank = gestor.obtenerRankingElegibles(tam);
                    System.out.println("--- RANKING DE ELEGIBLES ---");
                    for (int i = 0; i < tam[0]; i++) {
                        System.out.printf("%d. %s - %s (Promedio: %.2f)\n", (i+1), rank[i].getCodigo(), rank[i].getNombre(), rank[i].calcularPromedio());
                    }
                    break;
                case 5:
                    System.out.print("Cantidad de cupos: ");
                    while (!sc.hasNextInt()) { System.out.print("Número entero requerido: "); sc.next(); }
                    int cupo = sc.nextInt();
                    if (cupo > 0) { gestor.configurarCupo(cupo); System.out.println("Cupo actualizado."); }
                    else System.out.println("El cupo debe ser mayor a 0.");
                    break;
                case 6:
                    gestor.asignarBecas();
                    break;
                case 7:
                    System.out.print("Código del estudiante a consultar: ");
                    String codBusq = sc.nextLine();
                    gestor.explicarEstudiante(codBusq);
                    break;
                case 8:
                    gestor.generarInformeAsignacion();
                    break;
                case 9:
                    gestor.cargarDatosDemostracion();
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción fuera de rango.");
            }
        } while (opcion != 0);
        sc.close();
    }
}
