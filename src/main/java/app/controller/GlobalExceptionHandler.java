package app.controller;

import app.dto.AjaxDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public AjaxDTO exceptionHandler(HttpServletRequest req, Exception ex) {
        logger.debug("Exception", ex);
        AjaxDTO ajaxDTO = new AjaxDTO();
        ajaxDTO.setCode(100);
        ajaxDTO.setError(ex.getMessage());
        return ajaxDTO;
    }
}
