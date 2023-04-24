package miu.edu.pm.project.onlineshoppingcartsystem.payment.controller;

import miu.edu.pm.project.onlineshoppingcartsystem.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/finance")
public class FinanceController {

    @Autowired
    TransactionService transactionService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/totalRecAmountByUserRole/{userRole}")
    public ResponseEntity<?> getTotalRecAmountByUserRole(@PathVariable String userRole) {
        Double totalAmount = transactionService.getTotalRecAmountByUserRole(userRole);
        return ResponseEntity.status(HttpStatus.OK).body(totalAmount);
    }
}
