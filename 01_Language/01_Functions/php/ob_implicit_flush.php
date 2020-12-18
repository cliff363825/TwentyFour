<?php

ob_implicit_flush();
for($i = 0; $i < 10; $i++)
{
    echo "$i\n";
    sleep(1);
}
