package pe.project.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "productos")
public class Producto implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_producto;
    
    @NotEmpty
    @Column(nullable = false, length = 50)
    private String nombre;
    
    @NotEmpty
    @Column(nullable = false, length = 255)
    private String nombre_imagen;
    
    @NotEmpty
    @Column(nullable = false, length = 20)
    private String categoria;
    
    @NotNull
    @Column(nullable = false, columnDefinition = "decimal(7,2)")
    private Double precio;
    
    @NotNull
    @Column(nullable = false)
    private Integer stock;
    
    @NotNull
    @Column(nullable = false, columnDefinition = "bit")
    private Boolean estado;

    @OneToMany(mappedBy = "producto",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Collection<DetallePedido> lstDetallePedido = new ArrayList();
    
    public Producto() {
    }

    public Integer getId_producto() {
        return id_producto;
    }

    public void setId_producto(Integer id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre_imagen() {
        return nombre_imagen;
    }

    public void setNombre_imagen(String nombre_imagen) {
        this.nombre_imagen = nombre_imagen;
    }


    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Collection<DetallePedido> getLstDetallePedido() {
        return lstDetallePedido;
    }

    public void setLstDetallePedido(Collection<DetallePedido> lstDetallePedido) {
        this.lstDetallePedido = lstDetallePedido;
    }
    
}
