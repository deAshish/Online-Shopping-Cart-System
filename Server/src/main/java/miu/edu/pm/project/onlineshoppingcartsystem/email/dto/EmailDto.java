package miu.edu.pm.project.onlineshoppingcartsystem.email.dto;

import lombok.Data;

@Data
public class EmailDto {
    private String fromAddress;
    private String toAddress;
    private String mailSubject;
    private String bodyText;
    private String attachFileAddress;
}
