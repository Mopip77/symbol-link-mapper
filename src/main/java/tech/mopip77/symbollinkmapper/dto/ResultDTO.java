package tech.mopip77.symbollinkmapper.dto;

import lombok.Data;
import tech.mopip77.symbollinkmapper.exception.CustomizeException;
import tech.mopip77.symbollinkmapper.exception.ICustomizeErrorCode;

@Data
public class ResultDTO<T> {
    private Integer code;
    private String message;
    private String trace;
    private T data;

    public static ResultDTO errorOf(Integer code, String message, String trace) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        resultDTO.setTrace(trace);
        return resultDTO;
    }

    public static ResultDTO errorOf(ICustomizeErrorCode errorCode) {
        return errorOf(errorCode.getCode(), errorCode.getMessage(), "");
    }

    public static ResultDTO errorOf(CustomizeException e) {
        return errorOf(e.getCode(), e.getMessage(), "");
    }

    public static ResultDTO okOf() {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(0);
        resultDTO.setMessage("请求成功");
        return resultDTO;
    }

    public static <T> ResultDTO okOf(T t) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(0);
        resultDTO.setMessage("请求成功");
        resultDTO.setData(t);
        return resultDTO;
    }
}

