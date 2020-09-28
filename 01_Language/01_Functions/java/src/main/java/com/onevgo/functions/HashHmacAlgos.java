package com.onevgo.functions;

import com.google.common.collect.ImmutableSortedSet;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Security;
import java.util.Set;

public class HashHmacAlgos {
    public static Set<String> hashHmacAlgos() {
        return ImmutableSortedSet.copyOf(Security.getAlgorithms("Mac"));
    }

    public static void main(String[] args) {
        Security.addProvider(new BouncyCastleProvider());

        System.out.println(hashHmacAlgos());
    }
}
