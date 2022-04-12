package boa.entrega.supplier.exception;

import lombok.Getter;

import java.util.Optional;

public class BusinessException extends RuntimeException {

    private Optional<String> message;

    private ErrorCode code;

    public BusinessException(ErrorCode code) {
        this.code = code;
    }

    public BusinessException(String message, ErrorCode code) {
        this.message = Optional.ofNullable(message);
        this.code = code;
    }

    @Getter
    public enum ErrorCode {
        BAD_REQUEST(400, "Parâmetros informados incorretos"),
        NOT_FOUND(404, "Entidade não encontrada"),
        INTERNAL_SERVER_ERROR(500, "Ocorreu um erro");

        private int code;
        private String defaultMessage;

        ErrorCode(int code, String defaultMessage) {
            this.code = code;
            this.defaultMessage = defaultMessage;
        }
    }

    @Override
    public String getMessage() {
        return message.orElse(code.getDefaultMessage());
    }

    public int getCode() {
        return code.getCode();
    }
}
