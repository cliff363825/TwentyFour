<?php

class Utils
{
    public static $i = 0;

    public static function FriendlyErrorType($type)
    {
        switch ($type) {
            case E_ERROR: // 1 //
                return 'E_ERROR';
            case E_WARNING: // 2 //
                return 'E_WARNING';
            case E_PARSE: // 4 //
                return 'E_PARSE';
            case E_NOTICE: // 8 //
                return 'E_NOTICE';
            case E_CORE_ERROR: // 16 //
                return 'E_CORE_ERROR';
            case E_CORE_WARNING: // 32 //
                return 'E_CORE_WARNING';
            case E_COMPILE_ERROR: // 64 //
                return 'E_COMPILE_ERROR';
            case E_COMPILE_WARNING: // 128 //
                return 'E_COMPILE_WARNING';
            case E_USER_ERROR: // 256 //
                return 'E_USER_ERROR';
            case E_USER_WARNING: // 512 //
                return 'E_USER_WARNING';
            case E_USER_NOTICE: // 1024 //
                return 'E_USER_NOTICE';
            case E_STRICT: // 2048 //
                return 'E_STRICT';
            case E_RECOVERABLE_ERROR: // 4096 //
                return 'E_RECOVERABLE_ERROR';
            case E_DEPRECATED: // 8192 //
                return 'E_DEPRECATED';
            case E_USER_DEPRECATED: // 16384 //
                return 'E_USER_DEPRECATED';
        }
        return "";
    }
}

set_error_handler(function ($errno, $errstr, $errfile, $errline, $errcontext) {
    echo '[' . (Utils::$i++) . ']自定义错误处理器: ' . Utils::FriendlyErrorType($errno) . ', ' . $errstr . "\n";
    throw new \Exception('自定义异常：' . $errstr, $errno);
});

set_exception_handler(function ($e) {
    /** @var Exception $e */
    echo '[' . (Utils::$i++) . ']自定义异常处理器: ' . $e->getMessage() . "\n";
});

register_shutdown_function(function () {
    $error = error_get_last();
    if ($error) {
        echo '[' . (Utils::$i++) . ']脚本退出处理: ' . Utils::FriendlyErrorType($error['type']) . ', ' . $error['message'] . "\n";
    } else {
        echo '[' . (Utils::$i++) . ']脚本退出处理: E_NONE' . "\n";
    }
});

/**
 * [0]Error: E_WARNING, require(not_found.php): failed to open stream: No such file or directory
 * PHP Warning:  Uncaught Exception: require(not_found.php): failed to open stream: No such file or directory in /Users/songhongfa/git/my_php/tests/test_exception.php:47
 * Stack trace:
 * #0 /Users/songhongfa/git/my_php/tests/test_exception.php(64): {closure}(2, 'require(not_fou...', '/Users/songhong...', 64, Array)
 * #1 /Users/songhongfa/git/my_php/tests/test_exception.php(64): require()
 * #2 {main}
 * thrown in /Users/songhongfa/git/my_php/tests/test_exception.php on line 47
 * PHP Fatal error:  main(): Failed opening required 'not_found.php' (include_path='.:') in /Users/songhongfa/git/my_php/tests/test_exception.php on line 64
 * [1]Shutdown: E_COMPILE_ERROR, main(): Failed opening required 'not_found.php' (include_path='.:')
 *
 * NOTE:
 * 1. require 一个不存在的文件时，php会发生两个错误。一个是 E_WARNING，一个是 E_COMPILE_ERROR
 * 2. 当发生 E_WARNING 错误时，自定义的 error_handler 将被调用。在本例中，则是输出 E_WARNING 错误信息 并且抛出 \Exception 类型异常
 * 3. 当发生 E_COMPILE_ERROR 错误时，自定义的 error_handler 无法处理该错误。see: http://php.net/manual/en/function.set-error-handler.php
 * 4. php解释器 exit 停止工作，自定义的 exception_handler 无法工作，2中未处理的 \Exception 被默认异常处理器处理，shutdown_function 被调用。在本例中，则是输出 E_COMPILE_ERROR 的错误信息
 * see: http://www.laruence.com/2010/08/03/1697.html
 */
require 'not_found.php';

/**
 * [0]Error: E_WARNING, include(not_found.php): failed to open stream: No such file or directory
 * [1]Exception: include(not_found.php): failed to open stream: No such file or directory
 * [2]Shutdown: E_NONE
 */
//include 'not_found.php';
