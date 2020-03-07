package pe.project.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_cliente;
    
    @NotEmpty
    @Column(nullable = false, length = 50)
    private String nombres;
    
    @NotEmpty
    @Column(nullable = false, length = 50)
    private String apellidos;
    
    @Email
    @NotNull
    @Column(nullable = false, length = 60)
    private String correo;
    
    @NotNull
    @Column(nullable = false, length = 8)
    private Integer dni;
    
    @NotNull
    @Column(nullable = false, length = 1)
    private Character sexo;
    
    @Valid
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario", nullable = false, unique = true, foreignKey 
            = @ForeignKey(foreignKeyDefinition = "foreign key (id_usuario) references usuarios(id_usuario)"))
    private Usuario usuario;
    
    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
    private Collection<Pedido> lstPedidos = new ArrayList();

    public Cliente() {
    }

    public Integer getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Integer id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Collection<Pedido> getLstPedidos() {
        return lstPedidos;
    }

    public void setLstPedidos(Collection<Pedido> lstPedidos) {
        this.lstPedidos = lstPedidos;
    }
    
    
    
}
