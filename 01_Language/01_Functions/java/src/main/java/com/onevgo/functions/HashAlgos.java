package com.onevgo.functions;

import com.google.common.collect.ImmutableSortedSet;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Security;
import java.util.*;

public class HashAlgos {
    public static Set<String> hashAlgos() {
        return ImmutableSortedSet.copyOf(Security.getAlgorithms("MessageDigest"));
    }

    public static void main(String[] args) {
        Security.addProvider(new BouncyCastleProvider());

        System.out.println(hashAlgos());
    }
}
