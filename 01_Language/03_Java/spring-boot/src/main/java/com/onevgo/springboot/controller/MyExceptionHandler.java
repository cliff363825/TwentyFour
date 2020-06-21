package com.onevgo.springboot.controller;

import com.onevgo.springboot.exception.UserNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyExceptionHandler {
    // 浏览器客户端返回的都是json
    @ExceptionHandler(UserNotExistException.class)
    @ResponseBody
    ResponseEntity<?> handleException(HttpServletRequest request, Throwable ex) {
        HttpStatus status = getStatus(request);

        Map<String, Object> map = new HashMap<>();
        map.put("status", status.value());
        map.put("code", "USER_NOT_EXIST");
        map.put("message", ex.getMessage());

        return new ResponseEntity<>(map, status);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }

    // 转发到/error处理异常
//    @ExceptionHandler(UserNotExistException.class)
//    public ModelAndView handleException(HttpServletRequest request, HttpServletResponse response, Throwable ex) throws Exception {
//        HttpStatus status = getStatus(request);
//
//        Map<String, Object> map = new HashMap<>();
//        map.put("status", status.value());
//        map.put("code", "USER_NOT_EXIST");
//        map.put("message", ex.getMessage());
//        request.setAttribute("ext", map);
//
//        response.sendError(status.value(), ex.getMessage());
//
//        return new ModelAndView();
//    }
}
