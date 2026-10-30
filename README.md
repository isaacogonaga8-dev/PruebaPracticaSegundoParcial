# Sistema de Gestión de Becas — Prueba-POO-Grupo-8

**Universidad Técnica de Ambato · Ingeniería en Software**
**Asignatura:** Algoritmos y Lógica de Programación (Primer nivel)

## Integrantes

| Integrante | Rol / responsabilidad principal |
| --- | --- |
| Steven Betancourt| `Evaluacion.java` (validación de notas) |
| Julio Terán | `Estudiante.java` (evaluaciones y promedio) |
| Julio Terán  | `Beca.java` (cupo, elegibilidad, beneficiarios) |
| Isaac Ogonaga | `GestorBecas.java` (ranking, asignación, explicaciones) |
| Isaac Ogonaga | `Main.java` (menú) y documentación / pruebas |

## Objetivo

Aplicación de consola en Java que administra un cupo limitado de becas: registra estudiantes y notas,
calcula promedios, determina elegibilidad (promedio ≥ 8,5), ordena candidatos por promedio descendente
(desempate por código ascendente), asigna becas sin duplicar beneficiarios y explica cada decisión.

## Clases y métodos

| Clase | Responsabilidad | Métodos principales |
| --- | --- | --- |
| `Evaluacion` | Una nota válida (0–10) con nombre | `setNota`, `setNombre`, `getNota` |
| `Estudiante` | Código único, nombre y lista de evaluaciones | `agregarEvaluacion`, `calcularPromedio`, `tieneEvaluaciones` |
| `Beca` | Cupo máximo, nota mínima, beneficiarios | `esElegible`, `agregarBeneficiario`, `hayCupo`, `esBeneficiario`, `reiniciarAsignacion` |
| `GestorBecas` | Reglas de negocio e integración | `registrarEstudiante`, `registrarEvaluacion`, `obtenerRankingElegibles`, `asignarBecas`, `explicarEstudiante`, `generarInformeAsignacion` |
| `Main` | Menú con `Scanner`, `switch` y ciclo `do-while` | `main`, `leerEntero`, `leerDecimal` |

Diagrama de clases: [`docs/diagrama_clases.png`](docs/diagrama_clases.png)

### Demostración rápida
1. Opción `9` (cargar datos de demostración).
2. Opción `4` (ver ranking) → E001, E002, E003.
3. Opción `6` (asignar becas) con cupo 2 → E001 y E002 reciben beca.
4. Opción `7` con `E003` → «RECHAZADO POR CUPO».
5. Opción `8` para el informe completo.

## Menú

| Opción | Acción |
| --- | --- |
| 1 | Registrar estudiante |
| 2 | Registrar evaluación |
| 3 | Listar estudiantes y promedios |
| 4 | Ver ranking de elegibles |
| 5 | Configurar cupo de becas |
| 6 | Asignar becas |
| 7 | Explicar situación de un estudiante |
| 8 | Informe de asignación |
| 9 | Cargar datos de demostración |
| 0 | Salir |

## Validaciones implementadas

- Código y nombre de estudiante no vacíos; código único (sin distinguir mayúsculas).
- Nota entre 0 y 10; se rechazan valores fuera de rango y texto no numérico (se acepta coma o punto decimal).
- No se repite el nombre de una evaluación para el mismo estudiante.
- Cupo mayor que 0; opción de menú numérica y válida.
- Sin duplicar beneficiarios y sin exceder el cupo.
- Operaciones sobre estudiantes inexistentes o asignación sin elegibles → mensaje de error, sin cerrar el programa.
