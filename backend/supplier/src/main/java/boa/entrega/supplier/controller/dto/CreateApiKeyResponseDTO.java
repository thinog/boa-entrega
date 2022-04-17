package boa.entrega.supplier.controller.dto;

import boa.entrega.supplier.model.ApiKey;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class CreateApiKeyResponseDTO {
    private long id;
    private String name;
    private String apiKey;
    private Date createdAt;
    private ApiKey.AccessType accessType;
}
