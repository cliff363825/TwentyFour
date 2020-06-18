<?php

class A
{
    const NAME = 'A';
    public static function test() {
        $args = func_get_args();
        echo static::NAME, " ".join(',', $args)." \n";
    }
}

class B extends A
{
    const NAME = 'B';

    public static function test() {
        echo self::NAME, "\n";
        forward_static_call_array(array('A', 'test'), array('more', 'args'));
        forward_static_call_array( 'test', array('other', 'args'));

        echo "\nforward_static_call_array vs call_user_func_array: parent...\n";
        forward_static_call_array(array('parent', 'test'), array('more', 'args'));
        call_user_func_array(array('parent', 'test'), array('more', 'args'));

        echo "\nforward_static_call_array vs call_user_func_array: A...\n";
        forward_static_call_array(array('A', 'test'), array('more', 'args'));
        call_user_func_array(array('A', 'test'), array('more', 'args'));
    }
}

B::test('foo');

function test() {
    $args = func_get_args();
    echo "C ".join(',', $args)." \n";
}