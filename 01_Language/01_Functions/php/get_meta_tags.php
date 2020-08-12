<?php

// Assuming the above tags are at www.example.com
$tags = get_meta_tags('http://www.onevgo.com/');

var_dump($tags);
// Notice how the keys are all lowercase now, and
// how . was replaced by _ in the key.
//echo $tags['author'] . "\n";       // name
echo $tags['keywords'] . "\n";     // php documentation
echo $tags['description'] . "\n";  // a php manual
//echo $tags['geo_position'] . "\n"; // 49.33;-86.59
