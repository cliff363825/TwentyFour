package com.onevgo.functions;

import cn.hutool.core.text.csv.CsvParser;
import cn.hutool.core.text.csv.CsvReadConfig;
import cn.hutool.core.text.csv.CsvRow;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Fgetcsv {
    public static void main(String[] args) {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(new File("test.csv"));
            CsvParser csvParser = new CsvParser(fileReader, CsvReadConfig.defaultConfig());
            CsvRow csvRow;
            while ((csvRow = csvParser.nextRow()) != null) {
                System.out.println(csvRow.getRawList());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
