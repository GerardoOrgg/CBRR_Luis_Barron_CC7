package utez.edu.mx.CBRR_Luis_Barron_CC7.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utez.edu.mx.CBRR_Luis_Barron_CC7.model.Producto;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByAcivos(boolean t);
}