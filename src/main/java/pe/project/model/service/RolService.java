package pe.project.model.service;

import java.util.Collection;
import pe.project.model.entity.Rol;

public interface RolService 
{
    public abstract Rol findById(Integer id_role);
    public abstract Rol findByTipo(String tipo);
    
    public abstract Collection<Rol> findAll();
}
