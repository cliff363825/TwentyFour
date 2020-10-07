<?php

$str = "A 'quote' is <b>bold</b>";

// Outputs: A 'quote' is &lt;b&gt;bold&lt;/b&gt;
echo htmlentities($str);
echo "\n";
// Outputs: A &#039;quote&#039; is &lt;b&gt;bold&lt;/b&gt;
echo htmlentities($str, ENT_QUOTES);
echo "\n";

echo htmlentities('<Il était une fois un être>.');
// Output: &lt;Il &eacute;tait une fois un &ecirc;tre&gt;.
//                ^^^^^^^^                 ^^^^^^^
echo "\n";

echo htmlspecialchars('<Il était une fois un être>.');
// Output: &lt;Il était une fois un être&gt;.
//
