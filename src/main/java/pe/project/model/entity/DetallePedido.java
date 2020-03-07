package pe.project.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "detalle_pedido")
public class DetallePedido implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_detallepedido;
    
    @NotNull
    @Column(nullable = false, length = 10)
    private Integer cantidad;
    
    @NotNull
    @Column(nullable = false, columnDefinition = "decimal(9,2)")
    private Double importe;
    
    /* Relation DETALLE_PEDIDO TO PEDIDO */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pedido", nullable = false, foreignKey 
            = @ForeignKey(foreignKeyDefinition = "foreign key(id_pedido) references pedidos(id_pedido)"))
    private Pedido pedido;
    
    /* Relation PRODUCTO TO PEDIDO */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_producto", nullable = false, foreignKey 
            = @ForeignKey(foreignKeyDefinition = "foreign key(id_producto) references productos(id_producto)"))
    private Producto producto;

    public DetallePedido() {
    }

    public Integer getId_detallepedido() {
        return id_detallepedido;
    }

    public void setId_detallepedido(Integer id_detallepedido) {
        this.id_detallepedido = id_detallepedido;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
        
    
}
