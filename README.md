# Sistem Booking Kelas (Peminjaman Ruang)

## Deskripsi Proyek

Sistem Booking Kelas merupakan aplikasi berbasis terminal (*Command-Line Interface/CLI*) yang dikembangkan untuk memenuhi proyek mata kuliah Pemrograman Berorientasi Objek (PBO). Aplikasi ini membantu mahasiswa mengajukan peminjaman ruang kelas dan membantu admin mengelola data ruang serta memverifikasi pengajuan.

Aplikasi dikembangkan menggunakan Java 17 dan MySQL 8. Koneksi antara aplikasi dan database menggunakan JDBC (MySQL Connector/J). Antarmuka aplikasi dijalankan melalui terminal, bukan melalui browser.

## Fitur Utama

### Mahasiswa
- Login menggunakan NIM dan password.
- Melihat dashboard mahasiswa.
- Melihat daftar ruang dan informasi status ruang.
- Mengajukan peminjaman ruang berdasarkan tanggal dan waktu.
- Melihat status pengajuan peminjaman.
- Logout dari aplikasi.

### Admin
- Login menggunakan username dan password.
- Melihat dashboard admin.
- Melihat dan memverifikasi pengajuan peminjaman.
- Menyetujui atau menolak pengajuan.
- Mengelola data ruang, termasuk menambah, mengubah, dan menghapus ruang.

### Aturan Sistem
- Ruang dengan status `PERAWATAN` tidak dapat dipilih.
- Jadwal yang bertabrakan dengan peminjaman berstatus `DISETUJUI` tidak tersedia untuk pengajuan baru.
- Pengajuan baru memiliki status awal `MENUNGGU`.
- Jam selesai harus lebih besar daripada jam mulai.
- Ruang yang masih memiliki data pengajuan tidak dapat dihapus karena keterkaitan data pada database.

## Teknologi yang Digunakan

| Teknologi | Keterangan |
|---|---|
| Java 17 | Bahasa pemrograman utama |
| MySQL 8 | Sistem manajemen basis data |
| JDBC | Koneksi aplikasi Java ke database |
| MySQL Connector/J 8.3.0 | Driver JDBC |
| Apache Maven | Manajemen dependensi dan proses build |
| Git dan GitHub | Version control dan kolaborasi |
| Visual Studio Code / IntelliJ IDEA | Lingkungan pengembangan |
| Terminal / CLI | Antarmuka aplikasi |

## Persyaratan Sistem

Sebelum menjalankan aplikasi, siapkan:
1. JDK 17 atau versi lebih baru.
2. MySQL Server 8 yang sudah aktif.
3. MySQL Workbench atau klien MySQL lain untuk menjalankan file SQL.
4. Apache Maven jika aplikasi dijalankan melalui perintah Maven.
5. Visual Studio Code dengan Extension Pack for Java atau IntelliJ IDEA (opsional).

**Catatan:** MySQL Server adalah layanan database yang harus berjalan. MySQL Workbench hanya merupakan aplikasi klien untuk mengelola database.

## Instalasi dan Konfigurasi

### 1. Clone repository

```bash
git clone https://github.com/athenaxyvz/booking-kelas.git
cd booking-kelas
```

### 2. Buat database dan isi data awal

Pastikan MySQL Server sudah berjalan. Buka MySQL Workbench atau klien MySQL lain, lalu jalankan file berikut secara berurutan:

1. `schema.sql`
2. `seed.sql`

`schema.sql` membuat database `booking_kelas` dan tabel yang diperlukan. **Perhatian:** perintah pada file ini menghapus database `booking_kelas` yang sudah ada sebelum membuatnya kembali. Cadangkan data penting terlebih dahulu jika database tersebut sudah berisi data yang ingin dipertahankan.

`seed.sql` memasukkan data awal untuk pengujian dan demonstrasi.

### 3. Konfigurasi koneksi database

Salin `config.example.properties` menjadi `config.properties`.

Pada Windows PowerShell:

```powershell
Copy-Item config.example.properties config.properties
```

Buka `config.properties`, kemudian sesuaikan URL, username, dan password MySQL lokal:

```properties
db.url=jdbc:mysql://localhost:3306/booking_kelas?useSSL=false&allowPublicKeyRetrieval=true
db.user=root
db.password=ISI_PASSWORD_MYSQL_KAMU
```

Ganti nilai `db.user` dan `db.password` sesuai konfigurasi MySQL pada komputer masing-masing.

**Jangan mengunggah `config.properties` yang berisi kredensial pribadi ke GitHub.** File tersebut harus tetap diabaikan oleh Git melalui `.gitignore`. Repository menyediakan `config.example.properties` sebagai contoh konfigurasi.

### 4. Jalankan aplikasi

Dari direktori utama proyek, jalankan:

```bash
mvn clean compile
mvn exec:java
```

Atau gunakan:

```bash
mvn compile exec:java
```

Alternatifnya, buka proyek melalui Visual Studio Code atau IntelliJ IDEA, tunggu dependensi Maven dimuat, lalu jalankan kelas utama `booking.Main`.

Pastikan MySQL Server aktif dan konfigurasi koneksi sudah benar sebelum menjalankan aplikasi.

## Akun Uji Coba

Akun berikut tersedia pada data awal `seed.sql`.

| Peran | Login | Password |
|---|---|---|
| Admin | `admin1` | `admin123` |
| Admin | `admin2` | `admin123` |
| Mahasiswa | `25210001` | `password123` |
| Mahasiswa | `25210002` | `password123` |
| Mahasiswa | `25210003` | `password123` |
| Mahasiswa | `25210004` | `password123` |

Mahasiswa login menggunakan NIM, sedangkan admin login menggunakan username. Akun tersebut merupakan akun contoh untuk pengujian lokal proyek, bukan kredensial untuk sistem produksi.

## Struktur Direktori

```text
booking-kelas/
├── docs/
│   └── DEMO_SCRIPT.md
│   └── Laporan_Akhir_PBO_Kelompok3.docx
├── src/
│   └── main/
│       └── java/
│           └── booking/
│               ├── database/
│               │   └── Koneksi.java
│               ├── menu/
│               │   ├── AjukanPeminjamanMenu.java
│               │   ├── DaftarRuangMenu.java
│               │   ├── DashboardAdminMenu.java
│               │   ├── DashboardMahasiswaMenu.java
│               │   ├── KelolaRuangMenu.java
│               │   ├── MenuAdmin.java
│               │   ├── MenuLogin.java
│               │   ├── MenuMahasiswa.java
│               │   ├── StatusPengajuanMenu.java
│               │   └── VerifikasiMenu.java
│               ├── model/
│               │   ├── Admin.java
│               │   ├── Mahasiswa.java
│               │   ├── Peminjaman.java
│               │   ├── Ruang.java
│               │   └── User.java
│               ├── repository/
│               │   ├── PeminjamanRepository.java
│               │   ├── RuangRepository.java
│               │   └── UserRepository.java
│               ├── util/
│               │   └── Input.java
│               └── Main.java
├── config.example.properties
├── pom.xml
├── schema.sql
├── seed.sql
└── README.md
```

## Pengujian

Dokumen skenario pengujian tersedia pada `docs/SKENARIO_TEST.md`. Pengujian mencakup proses login, daftar dan pengelolaan ruang, pengajuan peminjaman, pemeriksaan ketersediaan ruang, verifikasi admin, dan ringkasan data.

Hasil pengujian akhir dicatat berdasarkan pelaksanaan aplikasi yang sebenarnya.

## Demo Aplikasi

Aplikasi ini menggunakan antarmuka terminal (CLI). Sesuai arahan dosen untuk proyek berbasis terminal, demonstrasi aplikasi disediakan dalam bentuk video.

**Tautan video demonstrasi:** (https://youtu.be/sHBKFb786vc)

Tidak ada tautan live website karena aplikasi ini bukan aplikasi web yang di-hosting.

## Dokumentasi Proyek

- [Laporan Akhir PBO (Word)](docs/Laporan_Akhir_PBO_Kelompok3.pdf)

## Repository

https://github.com/athenaxyvz/booking-kelas

## Tim Pengembang

Proyek ini dikerjakan secara berkelompok untuk mata kuliah Pemrograman Berorientasi Objek, Program Studi Teknik Informatika, Fakultas Teknik, Universitas Negeri Manado.

---

Pastikan MySQL Server aktif, database telah disiapkan menggunakan `schema.sql` dan `seed.sql`, serta `config.properties` telah disesuaikan sebelum menjalankan aplikasi.
