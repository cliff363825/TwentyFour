package com.onevgo.functions;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.StringTokenizer;

public class Exec {
    public static String exec(String command) {
        if (command.length() == 0)
            throw new IllegalArgumentException("Empty command");

        StringTokenizer st = new StringTokenizer(command);
        String[] cmdarray = new String[st.countTokens()];
        for (int i = 0; st.hasMoreTokens(); i++)
            cmdarray[i] = st.nextToken();

        Process process = null;
        InputStream inputStream = null;
        ByteArrayOutputStream outputStream = null;
        try {
//            process = Runtime.getRuntime().exec(command);
            process = new ProcessBuilder(cmdarray).redirectErrorStream(true).start();
            inputStream = process.getInputStream();
            outputStream = new ByteArrayOutputStream();
            int len;
            byte[] b = new byte[8192];
            while ((len = inputStream.read(b)) != -1) {
                outputStream.write(b, 0, len);
            }
            return outputStream.toString(StandardCharsets.UTF_8.name());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (process != null) {
                process.destroy();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(exec("ls -a"));
        System.out.println(exec("whoami"));
    }
}
