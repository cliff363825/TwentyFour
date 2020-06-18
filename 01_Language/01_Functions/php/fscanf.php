<?php

//javier  argonaut        pe
//hiroshi sculptor        jp
//robert  slacker us
//luigi   florist it
$fh = fopen("php://stdin", "r");
$userinfo = fscanf($fh, "%s\t%s\t%s\n");
fclose($fh);
var_dump($userinfo);
