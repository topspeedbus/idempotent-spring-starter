package cn.chan.idempotentspringstarter.exception;

/**
 * @author: piaoxue
 * @date: 2023/5/23 - 14:57
 * @description:
 **/
public class IdempotentRepetitiveOperationException extends RuntimeException {
    String message;

    public IdempotentRepetitiveOperationException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toString() {
        return "IdempotentRepetitiveOperationException(message=" + this.getMessage() + ")";
    }
}
