package com.onevgo.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ReadFileTag extends SimpleTagSupport {
    private String src;

    public void setSrc(String src) {
        this.src = src;
    }

    @Override
    public void doTag() throws JspException, IOException {
        PageContext pageContext = (PageContext) getJspContext();
        InputStream is = pageContext.getServletContext().getResourceAsStream(src);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;
        while ((line = br.readLine()) != null) {
            line = line.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
            pageContext.getOut().println(line);
            pageContext.getOut().println("<br>");
        }
    }
}
