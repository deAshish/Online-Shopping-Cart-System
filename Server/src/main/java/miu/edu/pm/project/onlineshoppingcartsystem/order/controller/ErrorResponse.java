package miu.edu.pm.project.onlineshoppingcartsystem.order.controller;

import lombok.*;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private String details;

}
