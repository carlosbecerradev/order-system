package pe.project.model.service;

import java.util.Collection;
import pe.project.model.entity.DetallePedido;

public interface DetallePedidoService 
{
    public abstract void insert(DetallePedido detallePedido);
    public abstract void update(DetallePedido detallePedido);
    public abstract void delete(Integer id_pdetallePedido);    
    public abstract void deleteAllByIdPedido(Integer id_pedido);
    public abstract DetallePedido findById(Integer id_pdetallePedido);
    public abstract Collection<DetallePedido> findAllByIdPedido(Integer id_pedido);
    public abstract Collection<DetallePedido> findAll();
    public abstract boolean isExist(Integer id_pdetallePedido);
}
