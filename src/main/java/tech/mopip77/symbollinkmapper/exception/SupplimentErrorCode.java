package tech.mopip77.symbollinkmapper.exception;

public class SupplimentErrorCode implements ICustomizeErrorCode {

    private Integer code;
    private String message;

    public SupplimentErrorCode(String message) {
        this.code = 5000;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
