package com.onevgo.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class ParentTag extends SimpleTagSupport {
    private String name = "onevgo";

    public String getName() {
        return name;
    }

    @Override
    public void doTag() throws JspException, IOException {
        System.out.println("父标签的标签处理器类name: " + name);
        getJspBody().invoke(null);
    }
}
