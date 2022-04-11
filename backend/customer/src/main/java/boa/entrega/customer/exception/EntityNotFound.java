package boa.entrega.customer.exception;

public class EntityNotFound extends BusinessException {

    private String entityName;

    public EntityNotFound(String entityName) {
        this.entityName = entityName;
    }


}
