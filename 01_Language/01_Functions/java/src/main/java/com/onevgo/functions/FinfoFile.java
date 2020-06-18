package com.onevgo.functions;

import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicException;
import net.sf.jmimemagic.MagicMatchNotFoundException;
import net.sf.jmimemagic.MagicParseException;

import java.io.File;
import java.net.URLConnection;

public class FinfoFile {
    public static String finfoFile(File file) {
        return URLConnection.guessContentTypeFromName(file.getName());
    }

    public static String finfoFile2(File file, boolean extensionHints) {
        try {
            return Magic.getMagicMatch(file, extensionHints).getMimeType();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String finfoFile2(File file, boolean extensionHints, boolean onlyMimeMatch) {
        try {
            return Magic.getMagicMatch(file, extensionHints, onlyMimeMatch).getMimeType();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(finfoFile(new File("test.php")));
        System.out.println(finfoFile2(new File("test.php"), false));
    }
}
