package miu.edu.pm.project.onlineshoppingcartsystem.order.repository;

import miu.edu.pm.project.onlineshoppingcartsystem.order.domain.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {
    List<OrderLine> findByOrdersId(Long orderId);

    List<OrderLine> findByProductId(Long productId);
}
