package com.onevgo.functions;

import cn.hutool.extra.mail.MailUtil;

public class Mail {
    public static void main(String[] args) {
        MailUtil.send("caffeinated@example.com", "Hello world", "Hello", false);
    }
}
