package co.edu.uniquindio.tallerbeta.model.Enum;

/**
 * Enumeración que representa los posibles estados de una orden de mantenimiento.
 * Los estados son: SINASIGNAR (orden creada pero no asignada a mecánico),
 * ENPROCESO (orden asignada y en mantenimiento), FINALIZADO (orden completada).
 */
public enum Estado {
    SINASIGNAR,ENPROCESO,FINALIZADO;
}
