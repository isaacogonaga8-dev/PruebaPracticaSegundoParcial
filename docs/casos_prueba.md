# Casos de prueba — Sistema de gestión de becas (Grupo 8)

Pruebas ejecutadas con `java -cp out Main` (JDK 21). Los resultados obtenidos corresponden a la salida real del programa.
Cupo inicial de la beca: **2**. Datos de demostración (opción 9):

| Código | Nombre | Notas | Promedio |
| --- | --- | --- | --- |
| E001 | Ana Torres | 9.5, 9.0 | 9.25 |
| E002 | Bruno Salazar | 9.0, 9.5 | 9.25 |
| E003 | Carla Mena | 8.5, 8.5 | 8.50 |
| E004 | Diego Paz | 7.0, 8.0 | 7.50 |

---

## CP1 — Caso de referencia: dos cupos y tres elegibles + desempate
- **Entrada (menú):** `9` → `4` → `6` → `8`
- **Resultado esperado:** elegibles E001, E002, E003. E001 y E002 empatan en 9.25 y se ordenan por código. Se asignan E001 y E002; E003 queda rechazado por cupo; E004 no elegible.
- **Resultado obtenido:** ranking `1. E001, 2. E002, 3. E003`; «Asignación realizada. Becas asignadas: 2»; informe: `[ASIGNADA] E001`, `[ASIGNADA] E002`, `[SIN CUPO] E003`, `[NO ELEGIBLE] E004`.

## CP2 — Validación de notas inválidas
- **Entrada:** registrar E010 «Luis»; registrar evaluación «Parcial» con notas `11`, `-1`, `abc` y `8,5`.
- **Resultado esperado:** rechazo de 11 y -1 (fuera de 0–10), aviso por texto no numérico, y registro correcto de 8,5 con promedio 8.50.
- **Resultado obtenido:** `ERROR: Nota inválida (11.0). Debe estar entre 0.0 y 10.0.`; `ERROR: Nota inválida (-1.0)...`; `Entrada inválida: ingrese un número (ej. 8.5).`; `Evaluación registrada. Promedio actual: 8.50`.

## CP3 — Duplicados y datos vacíos
- **Entrada:** registrar `E020 / Mia`; registrar de nuevo `e020 / Otra`; registrar código vacío.
- **Resultado esperado:** el segundo registro se rechaza por duplicado (sin importar mayúsculas); el código vacío se rechaza.
- **Resultado obtenido:** `ERROR: Ya existe un estudiante con el código E020.`; `ERROR: El código del estudiante no puede estar vacío.`

## CP4 — Límite de elegibilidad (8,5 vs 8,49)
- **Entrada:** A1 con nota 8.5; A2 con nota 8.49; opción `3` y opción `7` para cada uno.
- **Resultado esperado:** A1 elegible (el límite 8,5 es inclusivo); A2 no elegible, le faltan 0.01.
- **Resultado obtenido:** `A1 ... promedio: 8.50 -> elegible`; `A2 ... promedio: 8.49 -> no elegible`; `NO ELEGIBLE: le faltan 0.01 puntos para llegar al mínimo.`

## CP5 — Estudiante sin evaluaciones, inexistente y sin elegibles
- **Entrada:** registrar `S1`; opción `7` con `S1`; opción `7` con `ZZZ`; opción `6`.
- **Resultado esperado:** S1 no elegible por no tener evaluaciones; ZZZ genera error; asignar sin elegibles genera error y no se cae el programa.
- **Resultado obtenido:** `NO ELEGIBLE: no tiene evaluaciones registradas.`; `ERROR: No existe un estudiante con el código ZZZ.`; `ERROR: No hay estudiantes elegibles para asignar becas.`

## CP6 — Asignar dos veces sin duplicar y cambiar el cupo
- **Entrada:** `9` → `6` → `6` → `8` → `5` con `0` → `5` con `3` → `6` → `8`
- **Resultado esperado:** la segunda asignación mantiene 2 beneficiarios (sin duplicados); cupo 0 se rechaza; con cupo 3 reciben beca E001, E002 y E003 y ya no hay rechazados por cupo.
- **Resultado obtenido:** ambas asignaciones muestran «Becas asignadas: 2»; `ERROR: El cupo máximo debe ser mayor que 0.`; con cupo 3: `[ASIGNADA] E001, E002, E003` y «Elegibles rechazados por cupo: (ninguno)».

## CP7 — Opción de menú inválida
- **Entrada:** `x`, `55`, `0`
- **Resultado esperado:** mensaje de entrada inválida para `x`, «Opción inválida» para `55`, y salida normal con `0`.
- **Resultado obtenido:** `Entrada inválida: ingrese un número entero.`; `Opción inválida. Elija un número del 0 al 9.`; `Fin del programa.`
