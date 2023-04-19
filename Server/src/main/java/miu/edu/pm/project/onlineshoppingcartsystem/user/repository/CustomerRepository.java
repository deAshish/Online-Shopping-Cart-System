package miu.edu.pm.project.onlineshoppingcartsystem.user.repository;

import miu.edu.pm.project.onlineshoppingcartsystem.user.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
