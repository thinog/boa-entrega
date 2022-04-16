package boa.entrega.supplier.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Event {
    private String eventType;
    private Object body;
}
