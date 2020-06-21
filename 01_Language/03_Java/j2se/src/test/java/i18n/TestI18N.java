package i18n;

import org.junit.Test;

import java.text.*;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class TestI18N {
    @Test
    public void testLocale() {
        Locale locale = Locale.CHINA;
        System.out.println("country=" + locale.getDisplayCountry());
        System.out.println("language=" + locale.getLanguage());

        locale = new Locale("en", "US");
        System.out.println(locale.getDisplayCountry());
        System.out.println(locale.getLanguage());
    }

    @Test
    public void testDateFormat() {
        Locale locale = Locale.CHINA;

        Date date = new Date();
        System.out.println(date);

        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, locale);
        String s = dateFormat.format(date);
        System.out.println(s);
    }

    @Test
    public void testDateFormat1() throws ParseException {
        String s = "1990-12-12 12:12:12";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateFormat.parse(s));
    }

    @Test
    public void testNumberFormat() throws ParseException {
        double d = 123456789.123d;
        Locale locale = Locale.CHINA;
        NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);
        String s = numberFormat.format(d);
        System.out.println(s);

        Locale locale1 = Locale.FRANCE;
        NumberFormat numberFormat1 = NumberFormat.getNumberInstance(locale1);
        String s1 = numberFormat1.format(d);
        System.out.println(s1);

        Locale locale2 = Locale.US;
        NumberFormat numberFormat2 = NumberFormat.getCurrencyInstance(locale2);
        String s2 = numberFormat2.format(d);
        System.out.println(s2);

        String s3 = "$123,456,789.12";
        Double number = (Double) numberFormat2.parse(s3);
        System.out.println(number);
    }

    @Test
    public void testMessageFormat() {
        String msg = "Date: {0}, Salary: {1}";
        Locale locale = Locale.CHINA;

        Date date = new Date();
        Double sal = 12345.12d;

        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, locale);
        String dateStr = dateFormat.format(date);

        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
        String salStr = numberFormat.format(sal);

        String s = MessageFormat.format(msg, dateStr, salStr);
        System.out.println(s);
    }

    @Test
    public void testResourceBundle() {
        Locale locale = Locale.CHINA;
        ResourceBundle bundle = ResourceBundle.getBundle("i18n", locale);

//        System.out.println(bundle.getString("date"));
//        System.out.println(bundle.getString("salary"));
        String dateLabel = bundle.getString("date");
        String salaryLabel = bundle.getString("salary");

        String msg = "{0}:{1}, {2}:{3}";

        Date date = new Date();
        Double sal = 12345.12d;

        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, locale);
        String dateStr = dateFormat.format(date);

        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
        String salStr = numberFormat.format(sal);

        String s = MessageFormat.format(msg, dateLabel, dateStr, salaryLabel, salStr);
        System.out.println(s);
    }
}
