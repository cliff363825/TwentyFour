package com.onevgo.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

public class ForEachTag extends SimpleTagSupport {
    private Object items;
    private String var;

    public void setItems(Object items) {
        this.items = items;
    }

    public void setVar(String var) {
        this.var = var;
    }

    @Override
    public void doTag() throws JspException, IOException {
        if (items != null) {
            if (items instanceof Collection<?>) {
                for (Object obj : (Collection<?>)items) {
                    getJspContext().setAttribute(var, obj);
                    getJspBody().invoke(null);
                }
            } else if (items instanceof Map<?, ?>) {
                for (Map.Entry<?, ?> entry : ((Map<?, ?>) items).entrySet()) {
                    getJspContext().setAttribute(var, entry);
                    getJspBody().invoke(null);
                }
            }
        }
    }
}
