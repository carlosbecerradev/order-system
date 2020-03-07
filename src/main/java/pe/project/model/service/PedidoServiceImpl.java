package pe.project.model.service;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.project.model.dao.PedidoDao;
import pe.project.model.entity.Pedido;

@Service
public class PedidoServiceImpl implements PedidoService
{
    @Autowired
    @Qualifier("pedidoDaoImpl")
    private PedidoDao pedidoDao;
    
    @Override
    @Transactional(readOnly = false)
    public void insert(Pedido pedido) {
        pedidoDao.insert(pedido);
    }

    @Override
    @Transactional(readOnly = false)
    public void update(Pedido pedido) {
        pedidoDao.update(pedido);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Integer id_pedido) {
        pedidoDao.delete(id_pedido);
    }

    @Override
    @Transactional(readOnly = true)
    public Pedido findById(Integer id_pedido) {
        return pedidoDao.findById(id_pedido);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Pedido> findAll() {
        return pedidoDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isExist(Integer id_pedido) {
        return pedidoDao.isExist(id_pedido);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Pedido> findAllByIdCliente(Integer id_cliente) {
        return pedidoDao.findAllByIdCliente(id_cliente);
    }
    
}
