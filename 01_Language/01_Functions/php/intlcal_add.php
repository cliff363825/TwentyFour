<?php

//ini_set('intl.default_locale', 'fr_FR');
//ini_set('date.timezone', 'UTC');

$cal = new IntlGregorianCalendar(2012, 0 /* January */, 31);
echo IntlDateFormatter::formatObject($cal, "yyyy-MM-dd HH:mm:ss Z"), "\n";

$cal->add(IntlCalendar::FIELD_MONTH, 1);
echo IntlDateFormatter::formatObject($cal, "yyyy-MM-dd HH:mm:ss Z"), "\n";

$cal->add(IntlCalendar::FIELD_DAY_OF_MONTH, 1);
echo IntlDateFormatter::formatObject($cal, "yyyy-MM-dd HH:mm:ss Z"), "\n";
