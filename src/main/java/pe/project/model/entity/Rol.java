package pe.project.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.*;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "roles")
public class Rol implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_rol;
    
    @NotEmpty
    @Column(nullable = false, unique = true, length = 20)
    private String tipo;
    
    @OneToMany(mappedBy = "rol", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Collection<Usuario> lstUsuarios = new ArrayList<>();

    public Rol() {
    }

    public Integer getId_rol() {
        return id_rol;
    }

    public void setId_rol(Integer id_rol) {
        this.id_rol = id_rol;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Collection<Usuario> getLstUsuarios() {
        return lstUsuarios;
    }

    public void setLstUsuarios(Collection<Usuario> lstUsuarios) {
        this.lstUsuarios = lstUsuarios;
    }
    
    
}
