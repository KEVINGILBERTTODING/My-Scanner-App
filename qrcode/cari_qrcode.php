<?php
$host = "localhost";
$dbname = "qrcode";
$usere = "root";
$pswd = "";
//$conn=mysqli_connect($host,$usere,$pswd,$dbname);
try {
    $conn = new PDO("mysql:host=$host;dbname=$dbname", $usere, $pswd);
    // set the PDO error mode to exception
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
} catch (PDOException $e) {
    echo "Connection failed: " . $e->getMessage();
}

$query_cari = "SELECT * FROM data_barang WHERE kode = '" . $_GET['kode'] . "'";


$hasil = $conn->prepare($query_cari);
$hasil->execute();
$pesan = $hasil->fetchAll(PDO::FETCH_ASSOC);

echo json_encode($pesan);
