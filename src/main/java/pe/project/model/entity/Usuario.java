package pe.project.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_usuario;
    
    @NotEmpty
    @Column(nullable = false, unique = true, length = 15)
    private String username;
    
    @NotEmpty
    @Column(nullable = false, length = 60)
    private String contrasenia;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_rol", nullable = false, foreignKey 
            = @ForeignKey(foreignKeyDefinition = "foreign key (id_rol) references roles(id_rol)"))
    private Rol rol;
    
    @OneToOne(mappedBy = "usuario", fetch = FetchType.EAGER)
    private Cliente cliente;
    
    public Usuario() {
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}
