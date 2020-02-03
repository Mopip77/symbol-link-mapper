package tech.mopip77.symbollinkmapper.advice;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import tech.mopip77.symbollinkmapper.dto.ResultDTO;
import tech.mopip77.symbollinkmapper.exception.CustomizeErrorCode;
import tech.mopip77.symbollinkmapper.exception.CustomizeException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable e, HttpServletResponse response, HttpServletRequest request) {
        ResultDTO resultDTO;
        // 返回 JSON
        if (e instanceof CustomizeException) {
            resultDTO = ResultDTO.errorOf((CustomizeException) e);
        } else {
            resultDTO = ResultDTO.errorOf(5000, "系统运行出错", e.toString());
        }
        try {
            response.setContentType("application/json");
            response.setStatus(200);
            response.setCharacterEncoding("utf-8");
            PrintWriter writer = response.getWriter();
            writer.write(JSON.toJSONString(resultDTO));
            writer.close();
        } catch (IOException ioe) {
        }
        return null;
    }
}
