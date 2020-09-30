package com.onevgo.functions;

import com.google.common.collect.ImmutableSortedSet;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Provider;
import java.security.Security;
import java.util.*;

public class HashAlgos {
    public static Set<String> hashAlgos() {
        return ImmutableSortedSet.copyOf(Security.getAlgorithms("MessageDigest"));
    }

    public static void main(String[] args) {
        Security.addProvider(new BouncyCastleProvider());

        Provider[] providers = Security.getProviders();
        for (Provider provider : providers) {
            Set<Provider.Service> services = provider.getServices();
            for (Provider.Service service : services) {
                System.out.println("Provider: " + service.getProvider().getName() + ", Type: " + service.getType() + ", Algo: " + service.getAlgorithm());
            }
        }
        System.out.println(hashAlgos());
    }
}
