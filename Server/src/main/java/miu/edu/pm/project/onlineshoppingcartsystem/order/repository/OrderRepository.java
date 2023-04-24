package miu.edu.pm.project.onlineshoppingcartsystem.order.repository;


import miu.edu.pm.project.onlineshoppingcartsystem.order.model.OrderCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface OrderRepository extends JpaRepository<OrderCart, Long> {
}
