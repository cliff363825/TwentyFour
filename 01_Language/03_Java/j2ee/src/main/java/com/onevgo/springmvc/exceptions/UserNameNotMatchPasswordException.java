package com.onevgo.springmvc.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN,reason = "用户名和密码不匹配")
public class UserNameNotMatchPasswordException extends RuntimeException {
    static final long serialVersionUID = 1L;

    public UserNameNotMatchPasswordException() {
    }
}
