package com.onevgo.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class ChooseTag extends SimpleTagSupport {
    private boolean flag = true;

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean isFlag() {
        return flag;
    }

    @Override
    public void doTag() throws JspException, IOException {
        getJspBody().invoke(null);
    }
}
