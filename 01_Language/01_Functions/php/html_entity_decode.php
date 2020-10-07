<?php

$orig = "I'll \"walk\" the <b>dog</b> now";

$a = htmlentities($orig, ENT_QUOTES | ENT_HTML401);

$b = html_entity_decode($a);

echo $a; // I'll &quot;walk&quot; the &lt;b&gt;dog&lt;/b&gt; now
echo "\n";
echo $b; // I'll "walk" the <b>dog</b> now
echo "\n";
