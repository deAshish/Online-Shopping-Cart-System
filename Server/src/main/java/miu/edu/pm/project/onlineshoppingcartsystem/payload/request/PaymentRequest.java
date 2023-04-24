package miu.edu.pm.project.onlineshoppingcartsystem.payload.request;


import lombok.Data;
import miu.edu.pm.project.onlineshoppingcartsystem.order.model.OrderCart;
import miu.edu.pm.project.onlineshoppingcartsystem.payment.model.PaymentMethod;

@Data
public class PaymentRequest {
    private PaymentMethod paymentMethod;
    private OrderCart orderCart;
}
