package com.onevgo.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;

public class TestJspFragment extends SimpleTagSupport {
    @Override
    public void doTag() throws JspException, IOException {
        JspFragment bodyContent = getJspBody();
        // JspFragment.invoke(Writer): Writer即为标签体内容输出的字节流，若为null, 则输出到 getJspContext().getOut(),
        // 即输出到页面上
//        bodyContent.invoke(null);

        StringWriter sw = new StringWriter();
        bodyContent.invoke(sw);

        String content = sw.toString().toUpperCase();

        getJspContext().getOut().print(content);
    }
}
