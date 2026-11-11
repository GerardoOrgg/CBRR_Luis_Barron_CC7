package utez.edu.mx.CBRR_Luis_Barron_CC7.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utez.edu.mx.CBRR_Luis_Barron_CC7.model.EstadoPedido;
import utez.edu.mx.CBRR_Luis_Barron_CC7.model.Pedido;

import java.util.List;


@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByEstado(EstadoPedido payload);
    List<Pedido> findByEstadoAndId(Long id, EstadoPedido payload);
}