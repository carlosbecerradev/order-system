package pe.project.model.service;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.project.model.dao.DetallePedidoDao;
import pe.project.model.entity.DetallePedido;

@Service
public class DetallePedidoServiceImpl implements DetallePedidoService
{
    @Autowired
    @Qualifier("detallePedidoDaoImpl")
    private DetallePedidoDao detallePedidoDao;
    
    @Override
    @Transactional(readOnly = false)
    public void insert(DetallePedido detallePedido) {
        detallePedidoDao.insert(detallePedido);
    }

    @Override
    @Transactional(readOnly = false)
    public void update(DetallePedido detallePedido) {
        detallePedidoDao.update(detallePedido);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Integer id_pdetallePedido) {
        detallePedidoDao.delete(id_pdetallePedido);
    }

    @Override
    @Transactional(readOnly = true)
    public DetallePedido findById(Integer id_pdetallePedido) {
        return detallePedidoDao.findById(id_pdetallePedido);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<DetallePedido> findAll() {
        return detallePedidoDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isExist(Integer id_pdetallePedido) {
        return detallePedidoDao.isExist(id_pdetallePedido);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<DetallePedido> findAllByIdPedido(Integer id_pedido) {
        return detallePedidoDao.findAllByIdPedido(id_pedido);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteAllByIdPedido(Integer id_pedido) {
        detallePedidoDao.deleteAllByIdPedido(id_pedido);
    }
    
}
