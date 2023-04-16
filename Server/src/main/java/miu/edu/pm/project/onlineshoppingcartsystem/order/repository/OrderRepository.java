package miu.edu.pm.project.onlineshoppingcartsystem.order.repository;

import miu.edu.pm.project.onlineshoppingcartsystem.order.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByCustomer_Id(Long customerId);
}
