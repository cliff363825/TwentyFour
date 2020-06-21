package com.onevgo.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class SonTag extends SimpleTagSupport {
    @Override
    public void doTag() throws JspException, IOException {
        JspTag parent = getParent();

        ParentTag parentTag = (ParentTag) parent;
        String name = parentTag.getName();

        getJspContext().getOut().print("子标签输出 name = " + name);
    }
}
