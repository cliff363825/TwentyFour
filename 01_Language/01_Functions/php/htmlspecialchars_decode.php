<?php

$str = "<p>this -&gt; &quot;</p>\n";

echo htmlspecialchars_decode($str);
echo "\n";

// note that here the quotes aren't converted
echo htmlspecialchars_decode($str, ENT_NOQUOTES);
