package pe.project.model.dao;

import java.util.Collection;
import pe.project.model.entity.Cliente;

public interface ClienteDao
{
    public abstract void insert(Cliente cliente);
    public abstract void update(Cliente cliente);
    public abstract void delete(Integer id_cliente);
    public abstract Cliente findById(Integer id_cliente);
    public abstract Collection<Cliente> findAll();
    public abstract boolean isExist(Integer id_cliente);
}
