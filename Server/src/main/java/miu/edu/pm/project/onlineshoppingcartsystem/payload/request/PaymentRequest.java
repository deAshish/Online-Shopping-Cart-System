package miu.edu.pm.project.onlineshoppingcartsystem.payload.request;


import com.pm490.PM490.payment.model.PaymentMethod;
import lombok.Data;
import miu.edu.pm.project.onlineshoppingcartsystem.order.model.OrderCart;

@Data
public class PaymentRequest {
    private PaymentMethod paymentMethod;
    private OrderCart orderCart;
}
