package pe.project.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "pedidos")
public class Pedido implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_pedido;
    
    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    private Date fecha;    
    
    @NotNull
    @Column(nullable = false, columnDefinition = "decimal(10,2)")
    private Double monto_total;
    
    /* Relation CLIENTE TO PEDIDO */
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cliente", nullable = false, foreignKey 
            = @ForeignKey(foreignKeyDefinition = "foreign key(id_cliente) references clientes(id_cliente)"))
    private Cliente cliente;
        
    /* Relation DETALLE_PEDIDO TO PEDIDO */
    @OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY)
    private Collection<DetallePedido> lstDetallePedido = new ArrayList();
    
    public Pedido() {
    }

    public Integer getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(Integer id_pedido) {
        this.id_pedido = id_pedido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getMonto_total() {
        return monto_total;
    }

    public void setMonto_total(Double monto_total) {
        this.monto_total = monto_total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Collection<DetallePedido> getLstDetallePedido() {
        return lstDetallePedido;
    }

    public void setLstDetallePedido(Collection<DetallePedido> lstDetallePedido) {
        this.lstDetallePedido = lstDetallePedido;
    }
    
    
}
