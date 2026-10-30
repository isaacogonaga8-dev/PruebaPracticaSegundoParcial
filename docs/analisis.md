# Análisis — Sistema de gestión de becas (Grupo 8)

## 1. Problema
La universidad dispone de un **cupo limitado de becas** y debe asignarlas según el rendimiento académico.
Se necesita un programa que registre estudiantes y notas, determine quién es elegible (promedio ≥ 8,5),
construya un ranking justo y asigne las becas, justificando cada decisión (asignada, no elegible o rechazada por cupo).

## 2. Entradas
| Entrada | Tipo | Restricción |
| --- | --- | --- |
| Código del estudiante | Texto | No vacío, único (se normaliza a mayúsculas) |
| Nombre del estudiante | Texto | No vacío |
| Nombre de la evaluación | Texto | No vacío, no repetido por estudiante |
| Nota | Decimal | 0 ≤ nota ≤ 10 (acepta `8,5` u `8.5`) |
| Cupo máximo de becas | Entero | > 0 |
| Opción de menú | Entero | 0 – 9 |

## 3. Procesos
1. **Registro**: crear `Estudiante` y `Evaluacion` validando datos y duplicados.
2. **Promedio**: suma de notas / cantidad de evaluaciones (`Estudiante.calcularPromedio`).
3. **Elegibilidad**: tiene evaluaciones **y** promedio ≥ 8,5 (`Beca.esElegible`), usando tolerancia 1e-9 para evitar errores de punto flotante.
4. **Ranking**: filtrar elegibles y ordenar por promedio descendente; si empatan, por código ascendente (`GestorBecas.obtenerRankingElegibles`).
5. **Asignación**: se reinicia la lista de beneficiarios y se recorren los primeros del ranking hasta agotar el cupo (`GestorBecas.asignarBecas`). Como se reconstruye desde cero, nunca hay beneficiarios duplicados.
6. **Explicación**: según el caso, el sistema responde *no elegible* (con los puntos que faltan), *elegible pendiente*, *beca asignada* o *rechazado por cupo* con su posición.
7. **Invalidación**: si cambian estudiantes, notas o cupo después de asignar, la asignación previa se anula para no mostrar datos desactualizados.

## 4. Salidas
- Listado de estudiantes con promedio y estado.
- Ranking numerado de elegibles.
- Resultado de la asignación (becas otorgadas).
- Explicación individual y informe general (beneficiarios, rechazados por cupo, no elegibles).
- Mensajes de error claros ante entradas o operaciones inválidas.
