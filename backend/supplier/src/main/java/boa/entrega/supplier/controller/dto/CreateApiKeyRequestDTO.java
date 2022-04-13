package boa.entrega.supplier.controller.dto;

import boa.entrega.supplier.model.ApiKey;
import lombok.Data;

@Data
public class CreateApiKeyRequestDTO {
    private String keyName;

    private ApiKey.AccessType accessType;
}
