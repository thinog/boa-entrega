package boa.entrega.customer.exception;

import java.text.MessageFormat;

public class EntityNotFoundException extends BusinessException {

    public EntityNotFoundException(String entityName) {
        super(
                MessageFormat.format("Entidade \"{0}\" não encontrada", entityName),
                ErrorCode.NOT_FOUND);
    }
}
