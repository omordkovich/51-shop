package ait.shop.exception.handling;

/**
 * @author Oleg Mordkovich
 * {@code @date} 07.10.2025
 */
public class Response {
    private String message;
    private Object result;

    public Response(String message) {
        this.message = message;
    }

    public Response(Object result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }


    public Object getResult() {
        return result;

    }

    public boolean isError() {
        return message == null;
    }


    @Override
    public String toString() {
        return message != null ? message : result.toString();
    }
}
