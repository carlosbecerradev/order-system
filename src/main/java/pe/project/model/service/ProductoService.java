package pe.project.model.service;

import java.util.Collection;
import pe.project.model.entity.Producto;

public interface ProductoService 
{
    public abstract void insert(Producto producto);
    public abstract void update(Producto producto);
    public abstract void delete(Integer id_producto);
    public abstract Producto findById(Integer id_producto);
    public abstract Collection<Producto> findAll();
    public abstract boolean isExist(Integer id_producto);
}
