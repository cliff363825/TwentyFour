package com.onevgo.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class OtherwiseTag extends SimpleTagSupport {

    @Override
    public void doTag() throws JspException, IOException {
        JspTag parent = getParent();
        ChooseTag chooseTag = (ChooseTag) parent;
        if (chooseTag.isFlag()) {
            getJspBody().invoke(null);
        }
    }
}
