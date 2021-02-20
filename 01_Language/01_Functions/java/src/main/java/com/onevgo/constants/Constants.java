package com.onevgo.constants;

import cn.hutool.core.util.HexUtil;

import javax.validation.constraints.NotEmpty;

@NotEmpty
public class Constants {
    public static void main(String[] args) {
        System.out.println(Constants.class.getProtectionDomain().getCodeSource().getLocation().getFile());
        System.out.println(HexUtil.class.getProtectionDomain().getCodeSource().getLocation().getFile());
    }
}
