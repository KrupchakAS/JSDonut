package app.controller;

import app.dto.AjaxDTO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public AjaxDTO exceptionHandler(HttpServletRequest req, Exception ex) {
        AjaxDTO ajaxDTO = new AjaxDTO();
        ajaxDTO.setCode(100);
        ajaxDTO.setError(ex.getMessage());
        return ajaxDTO;
    }
}
