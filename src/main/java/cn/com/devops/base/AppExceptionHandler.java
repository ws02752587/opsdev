package cn.com.devops.base;

import cn.com.devops.exception.AresException;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.BindException;

/**
 * 统一处理校验异常，并返回错误提示
 */
@ControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler(AresException.class)
    @ResponseBody
    public ResponseData handleAresException(AresException e){
        String code = e.getCode();
        if(!StringUtils.isEmpty(code)){
            return ResponseData.fail(code, e.getMessage());
        }
        return ResponseData.fail(e.getMessage());
    }


    /**
     * 处理 @RequestBody参数校验异常
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseData handleManvException(MethodArgumentNotValidException e){


        return ResponseData.fail(e.getMessage());
    }

    /**
     * 处理不带任何注解的参数绑定校验异常
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ResponseData handleBindException(BindException e){
        return ResponseData.fail(e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseData handleRuntimeException(RuntimeException e){
    	e.printStackTrace();
        return ResponseData.fail(e.getMessage());
    }
}
