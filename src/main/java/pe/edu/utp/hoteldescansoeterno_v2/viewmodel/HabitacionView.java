package pe.edu.utp.hoteldescansoeterno_v2.viewmodel;

import java.util.List;

/**
 * Clase de solo lectura para mostrar habitaciones en las vistas Thymeleaf.
 * NO es una entidad JPA: los datos son estáticos (hardcoded), definidos
 * directamente en los controladores. No requiere base de datos.
 */
public class HabitacionView {

    private final Long id;
    private final String numero;
    private final String tipo;
    private final double precio;
    private final String descripcion;
    private final boolean disponible;
    private final List<String> amenidades;

    public HabitacionView(Long id, String numero, String tipo, double precio,
                           String descripcion, boolean disponible, List<String> amenidades) {
        this.id = id;
        this.numero = numero;
        this.tipo = tipo;
        this.precio = precio;
        this.descripcion = descripcion;
        this.disponible = disponible;
        this.amenidades = amenidades;
    }

    public Long getId() { return id; }
    public String getNumero() { return numero; }
    public String getTipo() { return tipo; }
    public double getPrecio() { return precio; }
    public String getDescripcion() { return descripcion; }
    public boolean isDisponible() { return disponible; }
    public List<String> getAmenidades() { return amenidades; }

    // Usados directamente por el HTML para pintar el badge de estado
    public String getEstadoMostrado() { return disponible ? "Disponible" : "Ocupada hoy"; }
    public String getEstadoBadgeClass() { return disponible ? "bg-success" : "bg-warning text-dark"; }
    public String getHeaderClass() {
        return "Pet-friendly".equals(tipo) ? "bg-warning-subtle" : "bg-light";
    }
    public String getAmenidadesStr() { return String.join(", ", amenidades); }

    // Imagen opcional de la habitación (ruta dentro de /static) y un icono de respaldo
    public String getImagen() {
        return "Pet-friendly".equals(tipo) ? "img/isidro3.jpg" : null;
    }
    public String getIcono() {
        switch (tipo) {
            case "Simple": return "bi-person-workspace";
            case "Doble": return "bi-houses";
            case "Suite": return "bi-gem";
            default: return "bi-door-open";
        }
    }
}