package pe.project.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.project.model.dao.PedidoDao;
import pe.project.model.entity.Pedido;

@Service
public class ReporteServiceImpl implements ReporteService
{
    @Autowired
    @Qualifier("pedidoDaoImpl")
    private PedidoDao pedidoDao;

    @Override
    @Transactional(readOnly = true)
    public Double getRecaudado() 
    {
        Double recaudado = 0.0;
        for(Pedido pedido: pedidoDao.findAll())
        {
            recaudado += pedido.getMonto_total();
        }
        
        return recaudado;
    }
    
}
