package pe.edu.utp.hoteldescansoeterno_v2.controller;

import pe.edu.utp.hoteldescansoeterno_v2.viewmodel.HabitacionView;

import java.util.List;

/**
 * Datos de ejemplo (hardcoded) usados mientras el entregable no
 * requiere conexión a base de datos. Sustituir por HabitacionService
 * cuando se integre la capa de persistencia en un avance posterior.
 */
final class DatosDemo {

    private DatosDemo() {}

    static List<HabitacionView> habitacionesDestacadas() {
        return List.of(
            new HabitacionView(1L, "101", "Simple", 120.0,
                "Habitación individual con escritorio ergonómico, ideal para viajes de negocios o trabajo remoto.",
                true, List.of("WiFi", "TV 32\"", "Aire acondicionado")),
            new HabitacionView(2L, "205", "Doble", 180.0,
                "Habitación doble espaciosa con vista al jardín interior, TV Smart 50\" y baño de mármol.",
                true, List.of("WiFi", "TV 40\"", "Minibar")),
            new HabitacionView(3L, "310", "Suite", 320.0,
                "Suite ejecutiva amplia de 45m² con jacuzzi privado, sala de estar independiente y minibar libre.",
                false, List.of("WiFi", "TV 50\"", "Jacuzzi", "Minibar")),
            new HabitacionView(4L, "112", "Pet-friendly", 150.0,
                "Diseño pensado para hospedarte con tu mascota sin preocupaciones, piso hipoalergénico y terraza.",
                true, List.of("WiFi", "TV 32\"", "Área para mascotas"))
        );
    }
}