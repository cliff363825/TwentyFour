package com.onevgo.functions;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import java.util.List;

public class Join {
    public static String join(String glue, List<String> pieces) {
        return Joiner.on(glue).join(pieces);
    }

    public static void main(String[] args) {
        ImmutableList<String> array = ImmutableList.of("lastname", "email", "phone");
        System.out.println(join(",", array));

        System.out.println(join("hello", Lists.newArrayList()));
    }
}
