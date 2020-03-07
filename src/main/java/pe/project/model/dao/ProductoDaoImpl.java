package pe.project.model.dao;

import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import pe.project.model.entity.Producto;

@Repository
public class ProductoDaoImpl implements ProductoDao
{
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public void insert(Producto producto) {
        entityManager.persist(producto);
    }

    @Override
    public void update(Producto producto) {
        entityManager.merge(producto);
    }

    @Override
    public void delete(Integer id_producto) {
        entityManager.remove(findById(id_producto));
    }

    @Override
    public Producto findById(Integer id_producto) {
        return entityManager.find(Producto.class, id_producto);
    }

    @Override
    public Collection<Producto> findAll() {
        Query query = entityManager.createNativeQuery("select * from productos order by id_producto", Producto.class);
        return query.getResultList();
    }

    @Override
    public boolean isExist(Integer id_producto) {
        return entityManager.contains(findById(id_producto));
    }
    
}
