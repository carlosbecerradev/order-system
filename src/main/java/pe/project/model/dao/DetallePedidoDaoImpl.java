package pe.project.model.dao;

import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import pe.project.model.entity.DetallePedido;

@Repository
public class DetallePedidoDaoImpl implements DetallePedidoDao
{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void insert(DetallePedido detallePedido) {
        entityManager.persist(detallePedido);
    }

    @Override
    public void update(DetallePedido detallePedido) {
        entityManager.merge(detallePedido);
    }

    @Override
    public void delete(Integer id_detallePedido) {
        entityManager.remove(findById(id_detallePedido));
    }

    @Override
    public DetallePedido findById(Integer id_detallePedido) {
        return entityManager.find(DetallePedido.class, id_detallePedido);
    }

    @Override
    public Collection<DetallePedido> findAll() {
        Query query = entityManager.createNativeQuery("select * from detalle_pedido order by id_pedido", DetallePedido.class);
        return query.getResultList();
    }

    @Override
    public boolean isExist(Integer id_detallePedido) {
        return entityManager.contains(findById(id_detallePedido));
    }

    @Override
    public Collection<DetallePedido> findAllByIdPedido(Integer id_pedido) {
        Query query = entityManager.createNativeQuery("select * from detalle_pedido where id_pedido=:param", DetallePedido.class);
        query.setParameter("param", id_pedido);
        return query.getResultList();
    }

    @Override
    public void deleteAllByIdPedido(Integer id_pedido) {
        Query query = entityManager.createNativeQuery("delete from detalle_pedido where id_pedido=:param", DetallePedido.class);
        query.setParameter("param", id_pedido);
        query.executeUpdate();
    }
    
}
