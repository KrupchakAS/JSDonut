package app.controller;

import app.dto.AjaxDTO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public AjaxDTO mainExceptionHandler(HttpServletRequest req, Exception ex) {
        logger.debug("Exception", ex);
        AjaxDTO ajaxDTO = new AjaxDTO();
        ajaxDTO.setCode(100);
        ajaxDTO.setError(ex.getMessage());
        return ajaxDTO;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_MODIFIED)
    @ExceptionHandler({SQLException.class, DataAccessException.class})
    public AjaxDTO DBHandler(HttpServletRequest req, Exception ex) {
        logger.debug("Exception", ex);
        AjaxDTO ajaxDTO = new AjaxDTO();
        ajaxDTO.setCode(HttpStatus.NOT_MODIFIED.value());
        ajaxDTO.setError(ex.getMessage());
        return ajaxDTO;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public AjaxDTO IllegalArgumentException(HttpServletRequest req, Exception ex) {
        logger.debug("Exception", ex);
        AjaxDTO ajaxDTO = new AjaxDTO();
        ajaxDTO.setCode(HttpStatus.BAD_REQUEST.value());
        ajaxDTO.setError(ex.getMessage());
        return ajaxDTO;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handlerNotFound(){
        return "redirect:/jsDonut/404";
    }
}
