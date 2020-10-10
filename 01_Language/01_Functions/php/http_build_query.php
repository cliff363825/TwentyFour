<?php

$data = array(
    'foo' => 'bar',
    'baz' => 'boom',
    'cow' => 'milk',
    'php' => 'hypertext processor',
    ["a", "b", "c"]
);

echo http_build_query($data) . "\n";
echo http_build_query($data, '', '&amp;') . "\n";


function _format_postkey($post, &$result, $key = null) {
    foreach($post as $k => $v) {
        $_k = $key !== null ? $key.'['.$k.']' : $k;
        if(is_array($v)) {
            _format_postkey($v, $result, $_k);
        } else {
            $result[$_k] = $v;
        }
    }
}
_format_postkey($data, $result);
var_dump($result);
echo http_build_query($result) . "\n";
