package pe.project.model.dao;

import java.util.Collection;
import pe.project.model.entity.Producto;

public interface ProductoDao 
{
    public abstract void insert(Producto producto);
    public abstract void update(Producto producto);
    public abstract void delete(Integer id_producto);
    public abstract Producto findById(Integer id_producto);
    public abstract Collection<Producto> findAll();
    public abstract boolean isExist(Integer id_producto);
}
