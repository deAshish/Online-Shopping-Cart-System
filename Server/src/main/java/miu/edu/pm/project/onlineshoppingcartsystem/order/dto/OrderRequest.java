package miu.edu.pm.project.onlineshoppingcartsystem.order.dto;

import lombok.Data;
import miu.edu.pm.project.onlineshoppingcartsystem.payment.model.PurchaseStatus;

import java.time.LocalDate;

@Data
public class OrderRequest {
    private LocalDate dateOrdered;
    private LocalDate dateShipped;
    private PurchaseStatus status;
}
