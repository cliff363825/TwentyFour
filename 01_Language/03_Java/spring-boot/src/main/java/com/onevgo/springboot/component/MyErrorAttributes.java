package com.onevgo.springboot.component;

import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;

import java.util.Map;

/**
 * 容器中加入我们自己定义的 ErrorAttributes
 */
@Component
public class MyErrorAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes, boolean includeStackTrace) {
        Map<String, Object> map = super.getErrorAttributes(requestAttributes, includeStackTrace);
        map.put("company", "onevgo");

        // 我们的异常处理器携带的数据
        Object ext = requestAttributes.getAttribute("ext", RequestAttributes.SCOPE_REQUEST);
        map.put("ext", ext);
        return map;
    }
}
