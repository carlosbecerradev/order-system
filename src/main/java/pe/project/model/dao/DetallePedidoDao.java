package pe.project.model.dao;

import java.util.Collection;
import pe.project.model.entity.DetallePedido;

public interface DetallePedidoDao 
{
    public abstract void insert(DetallePedido detallePedido);
    public abstract void update(DetallePedido detallePedido);
    public abstract void delete(Integer id_detallePedido);
    public abstract void deleteAllByIdPedido(Integer id_pedido);
    public abstract DetallePedido findById(Integer id_detallePedido);
    public abstract Collection<DetallePedido> findAllByIdPedido(Integer id_pedido);
    public abstract Collection<DetallePedido> findAll();
    public abstract boolean isExist(Integer id_detallePedido);
}
