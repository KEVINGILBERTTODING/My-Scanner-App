<?php


$sumber = 'http://localhost/qrcode';
include 'qrcode.php';

$konten = file_get_contents($sumber);
$data = json_decode($konten, true);

?>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menampilkan Data Barang JSON</title>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

</head>

<body>


    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#">Data Barang</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Link</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Dropdown
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="#">Action</a>
                        <a class="dropdown-item" href="#">Another action</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="#">Something else here</a>
                    </div>
                </li>
                <li class="nav-item">
                    <a class="nav-link disabled" href="#">Disabled</a>
                </li>
            </ul>
            <form class="form-inline my-2 my-lg-0">
                <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </form>
        </div>
    </nav>


    <div class="container mt-4">
        <div class="row">

            <table class="table table-striped">
                <thead class="thead-dark">

                    <tr>
                        <th>No</th>
                        <th>Barcode</th>
                        <th>Kode</th>
                        <th>Nama Barang</th>
                        <th>Harga</th>
                    </tr>
                </thead>


        </div>
    </div>
    <?php
    $no = 0;
    for ($a = 0; $a < count($data); $a++) {
        $no++;
        $xkode = $data[$a]['kode'];
        echo "<tr>";
        echo "<td>$no</td>";
    ?>
        <td><img src="qrcode.php?s=qr&d=<?= $xkode; ?>" alt="QRCODE" width="150px"></td>



        <!-- <td><img style="width: 100px;" src="qrcode.php?s=qr&d=<?= $xkode; ?>"></td> -->
    <?php
        echo "<td>" . $data[$a]['kode'] . "</td>";
        echo "<td>" . $data[$a]['nama_barang'] . "</td>";
        echo "<td>" . $data[$a]['harga'] . "</td>";
        echo "</tr>";
    }
    ?>
    </Table>
    <?= "<h5>Total barang :" . count($data) . "</h5>"; ?>
</body>

</html>