package com.onevgo.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class WhenTag extends SimpleTagSupport {
    private boolean test;

    public void setTest(boolean test) {
        this.test = test;
    }

    @Override
    public void doTag() throws JspException, IOException {
        if (test) {
            JspTag parent = getParent();
            ChooseTag chooseTag = (ChooseTag) parent;
            if (chooseTag.isFlag()) {
                chooseTag.setFlag(false);
                getJspBody().invoke(null);
            }
        }
    }
}
