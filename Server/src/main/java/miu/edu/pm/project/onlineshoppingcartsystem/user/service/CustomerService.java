package miu.edu.pm.project.onlineshoppingcartsystem.user.service;

import miu.edu.pm.project.onlineshoppingcartsystem.user.domain.Customer;
import miu.edu.pm.project.onlineshoppingcartsystem.user.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public Customer getCustomerById(Long id){
        return customerRepository.findById(id).get();
    }
}
