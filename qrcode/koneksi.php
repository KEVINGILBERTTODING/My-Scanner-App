<?php

define('host', 'localhost');
define('name', 'root');
define('pass', '');
define('dbase', 'qrcode');

$conn = mysqli_connect(host, name, pass, dbase) or die('Unable to connect');
