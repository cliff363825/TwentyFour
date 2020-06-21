package com.onevgo.ssm;

import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;

public class MBGTest {
    @Test
    public void testMbg() throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
        ArrayList<String> warnings = new ArrayList<>();
        boolean overwrite = true;
        InputStream inputStream = null;
        try {
            ConfigurationParser cp = new ConfigurationParser(warnings);
            inputStream = getClass().getResourceAsStream("/mbg-ssm.xml");
            Configuration config = cp.parseConfiguration(inputStream);
            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            myBatisGenerator.generate(null);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }
}
