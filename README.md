# Todolist Clean Architecture

![Java](https://img.shields.io/badge/Java-20%2B-orange) 
![IntelliJ IDEA](https://www.jetbrains.com/idea/)

Ini adalah proyek Todolist dengan Clean Architecture yang ditulis dalam bahasa Java. Clean Architecture adalah pendekatan perancangan perangkat lunak yang memisahkan aplikasi ke dalam beberapa lapisan, yang memungkinkan fleksibilitas dan pengujian yang lebih baik.

## Arsitektur

Proyek ini mengikuti pola Clean Architecture, yang terdiri dari beberapa lapisan:

1. **Controller**: Lapisan ini berhubungan langsung dengan antarmuka pengguna dan menerima permintaan dari pengguna. Ini berfungsi sebagai penghubung antara dunia luar dan lapisan aplikasi.

2. **Service**: Lapisan ini berisi bisnis logika aplikasi. Semua aturan bisnis yang berkaitan dengan todolist diimplementasikan di sini.

3. **Repository**: Lapisan ini bertanggung jawab untuk berinteraksi dengan penyimpanan data, seperti database. Ini memisahkan detail teknis dari lapisan bisnis.

4. **Model**: Lapisan ini berisi entitas dan objek yang digunakan dalam aplikasi. Ini merupakan lapisan pusat yang menggambarkan struktur data dalam proyek ini.

5. **View**: Lapisan ini dapat berisi tampilan atau antarmuka pengguna jika aplikasi memiliki antarmuka pengguna, atau dapat diabaikan jika aplikasi ini berfokus pada backend tanpa antarmuka pengguna.


## Kontribusi

Jika Anda ingin berkontribusi pada proyek ini, silakan buat fork proyek ini dan ajukan pull request dengan perubahan Anda. Kami sangat menghargai kontribusi dari komunitas!

## Lisensi

Proyek ini dilisensikan di bawah [MIT License](LICENSE).
