package pe.edu.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "huesped")
public class Huesped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreCompleto;
    private String documento;
    private String telefono;
    private String email;
    private String estado;

    public Huesped() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public String getDocumento() { return documento; }
    public void setDocumento(String documento) { this.documento = documento; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getIniciales() {
        if (nombreCompleto == null || nombreCompleto.isBlank()) return "?";
        String[] p = nombreCompleto.trim().split("\\s+");
        String ini = p[0].substring(0, 1);
        if (p.length > 1) ini += p[1].substring(0, 1);
        return ini.toUpperCase();
    }
}