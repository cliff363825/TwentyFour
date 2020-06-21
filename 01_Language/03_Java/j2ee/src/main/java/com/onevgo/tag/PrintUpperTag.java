package com.onevgo.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;

public class PrintUpperTag extends SimpleTagSupport {
    private String time;

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public void doTag() throws JspException, IOException {
        JspFragment bodyContent = getJspBody();

        StringWriter sw = new StringWriter();
        bodyContent.invoke(sw);

        String content = sw.toString();
        content = content.toUpperCase();

        JspWriter out = getJspContext().getOut();
        int count = 1;
        try {
            count = Integer.parseInt(time);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < count; i++) {
            out.print(i + " " + content + "<br>");
        }
    }
}
