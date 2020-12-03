<?php

error_reporting(E_ALL);

// The message
$message = "Line 1\r\nLine 2\r\nLine 3";

// In case any of our lines are larger than 70 characters, we should use wordwrap()
$message = wordwrap($message, 70, "\r\n");

// Send
$success = mail('caffeinated@example.com', 'My Subject', $message);
if (!$success) {
    $errorMessage = error_get_last()['message'];
}
