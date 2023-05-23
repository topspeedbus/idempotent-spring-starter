package cn.chan.idempotentspringstarter.exception;

/**
 * @author: piaoxue
 * @date: 2023/5/23 - 14:54
 * @description:
 **/
public class IdempotentParamNotFoundException extends RuntimeException {

    String message;

    public IdempotentParamNotFoundException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toString() {
        return "IdempotentParamNotFoundException(message=" + this.getMessage() + ")";
    }
}
