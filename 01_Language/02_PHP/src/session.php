<?php
echo '---- before session_start ----<br>';
echo 'session_status() == PHP_SESSION_NONE: ' . (session_status() == PHP_SESSION_NONE ? 'True' : 'False') . '<br>';

session_start();

echo '<br>';
echo '---- after session_start ----<br>';
echo 'session_status() == PHP_SESSION_ACTIVE: ' . (session_status() == PHP_SESSION_ACTIVE ? 'True' : 'False') . '<br>';

echo '<br>';
echo '---- set session ----<br>';
$_SESSION['name'] = 'fage';
echo '---- dump session ----' . print_r($_SESSION, true) . '<br>';

echo '<br>';
echo '---- session_unset ---- <br>';
session_unset();
echo 'session_status() == PHP_SESSION_ACTIVE: ' . (session_status() == PHP_SESSION_ACTIVE ? 'True' : 'False') . '<br>';
echo '---- dump session ----' . print_r($_SESSION, true) . '<br>';

echo '<br>';
echo '---- session_destroy ---- <br>';
session_destroy();
echo 'session_status() == PHP_SESSION_ACTIVE: ' . (session_status() == PHP_SESSION_ACTIVE ? 'True' : 'False') . '<br>';
echo '---- dump session ----' . print_r($_SESSION, true) . '<br>';
