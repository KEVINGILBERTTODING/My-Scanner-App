<?php
$servername = "localhost";
$username = "root";
$dbname = "qrcode";
$password = "";
try {
    $conn = new PDO("mysql:host=$servername;dbname=$dbname", $username, $password);
    // set the PDO error mode to exception
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
} catch (PDOException $e) {
    echo "Connection failed: " . $e->getMessage();
}
//program tampil data barang
if ($_SERVER['REQUEST_METHOD'] === 'GET') {
    $msql = "select * from data_barang order by kode";
    $stmt = $conn->prepare($msql);
    $stmt->execute();
    $data = $stmt->fetchAll(PDO::FETCH_ASSOC);
    echo json_encode($data);
} else if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $kode = $_POST['kode'];
    $nama = $_POST['nama'];
    $harga = $_POST['harga'];
    $msql = "insert into data_barang (kode,nama_barang,harga) values (?,?,?)";
    $stat = $conn->prepare($msql);
    $res = $stat->execute([$kode, $nama, $harga]);
    if ($res) {
        $data = ['kode' => $kode, 'nama' => $nama, 'harga' => $harga];
        echo json_encode($data);
    } else {
        echo json_encode(['error' => $stat->errorCode()]);
    }
}
