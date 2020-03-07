package pe.project.model.service;

import java.util.Collection;
import pe.project.model.entity.Pedido;

public interface PedidoService 
{
    public abstract void insert(Pedido pedido);
    public abstract void update(Pedido pedido);
    public abstract void delete(Integer id_pedido);
    public abstract Pedido findById(Integer id_pedido);
    public abstract Collection<Pedido> findAll();
    public abstract Collection<Pedido> findAllByIdCliente(Integer id_cliente);
    public abstract boolean isExist(Integer id_pedido);
}
