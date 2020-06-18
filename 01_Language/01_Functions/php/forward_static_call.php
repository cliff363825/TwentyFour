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
        forward_static_call(array('A', 'test'), 'more', 'args');
        forward_static_call( 'test', 'other', 'args');

        echo "\nforward_static_call vs call_user_func: parent...\n";
        forward_static_call(array('parent', 'test'), 'more', 'args');
        call_user_func(array('parent', 'test'), 'more', 'args');

        echo "\nforward_static_call vs call_user_func: A...\n";
        forward_static_call(array('A', 'test'), 'more', 'args');
        call_user_func(array('A', 'test'), 'more', 'args');
    }
}

B::test('foo');

function test() {
    $args = func_get_args();
    echo "C ".join(',', $args)." \n";
}