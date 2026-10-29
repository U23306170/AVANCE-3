package pe.edu.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class HabitacionDTO {

    @NotBlank(message = "El número es obligatorio")
    @Size(min = 1, max = 50)
    private String numero;

    @NotBlank(message = "El tipo es obligatorio")
    @Size(min = 3, max = 50)
    private String tipo;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor que cero")
    private BigDecimal precio;

    @NotNull(message = "La capacidad es obligatoria")
    @Positive(message = "La capacidad debe ser mayor que cero")
    private Integer capacidad;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo no es válido")
    private String correoResponsable;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public String getCorreoResponsable() {
        return correoResponsable;
    }

    public void setCorreoResponsable(String correoResponsable) {
        this.correoResponsable = correoResponsable;
    }
}