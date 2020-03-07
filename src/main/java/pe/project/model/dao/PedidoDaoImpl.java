package pe.project.model.dao;

import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import pe.project.model.entity.Pedido;

@Repository
public class PedidoDaoImpl implements PedidoDao
{
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public void insert(Pedido pedido) {
        entityManager.persist(pedido);
    }

    @Override
    public void update(Pedido pedido) {
        entityManager.merge(pedido);
    }

    @Override
    public void delete(Integer id_pedido) {
        entityManager.remove(findById(id_pedido));
    }

    @Override
    public Pedido findById(Integer id_pedido) {
        return entityManager.find(Pedido.class, id_pedido);
    }

    @Override
    public Collection<Pedido> findAll() {
        Query query = entityManager.createNativeQuery("select * from pedidos order by id_pedido", Pedido.class);
        return query.getResultList();
    }

    @Override
    public boolean isExist(Integer id_pedido) {
        return entityManager.contains(findById(id_pedido));
    }

    @Override
    public Collection<Pedido> findAllByIdCliente(Integer id_cliente) {
        Query query = entityManager.createNativeQuery("select * from pedidos where id_cliente=:param", Pedido.class);
        query.setParameter("param", id_cliente);
        return query.getResultList();
    }


    
}
