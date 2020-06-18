<?php

// cos(0)=1 cos(PI/6)=sqrt(3)/2 cos(PI/4)=sqrt(2)/2 cos(PI/3)=1/2 cos(PI/2)=0
var_dump(acos(0));
var_dump(acos(1 / 2));
var_dump(acos(sqrt(2) / 2));
var_dump(acos(sqrt(3) / 2));

//float(1.5707963267949) PI/2
//float(1.0471975511966) PI/3
//float(0.78539816339745) PI/4
//float(0.5235987755983) PI/6
