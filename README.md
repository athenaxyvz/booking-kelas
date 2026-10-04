# Sistem Booking Kelas (Peminjaman Ruang)

Tugas Pemrograman Berorientasi Objek. Aplikasi terminal (CLI) berbasis Java dengan database MySQL,
dirancang mengikuti fase Unified Process (use case, class diagram, sequence diagram).

Repositori ini berisi **kerangka proyek**: struktur kelas sudah sesuai class diagram, sedangkan
logika program ditulis oleh kelompok. Cari tanda `TODO (Anggota X)` untuk melihat bagian yang harus dikerjakan.

## 1. Yang perlu dipasang

- JDK 17 atau lebih baru
- MySQL 8 (boleh lewat XAMPP) dan MySQL Workbench atau phpMyAdmin
- IntelliJ IDEA (Community) atau VS Code dengan Extension Pack for Java
- Git, atau GitHub Desktop

## 2. Cara menjalankan

1. Clone repositori ini, lalu buka foldernya sebagai **proyek Maven** (IDE akan mengunduh driver MySQL otomatis).
2. Di MySQL, jalankan `schema.sql`, lalu `seed.sql`.
3. Salin `config.example.properties` menjadi `config.properties`, lalu isi password MySQL kamu.
   File `config.properties` tidak ikut di-push (sudah ada di `.gitignore`).
4. Jalankan kelas `booking.Main`. Kalau muncul "Koneksi database berhasil", setup kamu sudah benar.
   (Alternatif lewat terminal: `mvn compile exec:java`.)

Akun contoh dari `seed.sql`:

| Peran | Login | Password |
|-------|-------|----------|
| Admin | `admin1` atau `admin2` (username) | `admin123` |
| Mahasiswa | `25210001` (NIM) | `password123` |

## 3. Struktur proyek dan pemilik file

```
booking-kelas/
|-- schema.sql, seed.sql              Anggota A
|-- config.example.properties
|-- docs/SKENARIO_TEST.md             Anggota C
`-- src/main/java/booking/
    |-- Main.java                     Anggota A
    |-- database/Koneksi.java         Anggota A   (sudah jadi)
    |-- util/Input.java                           (sudah jadi, dipakai bersama)
    |-- model/
    |   |-- User, Mahasiswa, Admin, Ruang         Anggota A
    |   `-- Peminjaman                            Anggota B
    |-- repository/
    |   |-- UserRepository, RuangRepository       Anggota A
    |   `-- PeminjamanRepository                  Anggota B dan C (lihat tanda di dalam file)
    `-- menu/
        |-- MenuLogin, MenuMahasiswa, MenuAdmin   Anggota A
        |-- DaftarRuangMenu, KelolaRuangMenu      Anggota A
        |-- DashboardMahasiswaMenu                Anggota B
        |-- AjukanPeminjamanMenu                  Anggota B
        |-- StatusPengajuanMenu                   Anggota B
        `-- DashboardAdminMenu, VerifikasiMenu    Anggota C
```

Aturan utama: **satu file dikerjakan satu orang**. Kalau perlu mengubah file milik orang lain, minta pemiliknya
atau ubah lewat Pull Request yang ditinjau pemiliknya.

## 4. Bagian yang sudah jadi (untuk dipelajari)

| File | Isi contoh |
|------|-----------|
| `database/Koneksi.java` | Koneksi JDBC ke MySQL |
| `util/Input.java` | Membaca angka, tanggal, jam, dan konfirmasi Y/N |
| `model/User.java` | `login()` dan `logout()` serta enkapsulasi |
| `model/Peminjaman.java` | `ubahStatus()` dengan validasi (objek menjaga statusnya sendiri) |
| `repository/RuangRepository.java` | `semua()`: pola lengkap query JDBC |
| `menu/MenuMahasiswa.java` | Pola menu terminal (loop, switch, memanggil layar lain) |

## 5. Alur kerja Git

```
git pull                                  # ambil kode terbaru dulu
git checkout -b fitur/nama-fitur          # satu cabang per fitur
   ... menulis kode ...
git add .
git commit -m "Pesan singkat tentang perubahan"
git push -u origin fitur/nama-fitur
```

Lalu buka GitHub, buat Pull Request, minta satu teman meninjau, dan klik Merge.
Biasakan `git pull` sebelum mulai bekerja dan push dalam potongan kecil (minimal sekali sehari).

## 6. Urutan pengerjaan (iterasi)

1. **Iterasi 1 (Anggota A):** login, daftar ruang, kelola data ruang, dan pastikan database berjalan di semua laptop.
2. **Iterasi 2 (Anggota B):** ajukan peminjaman, cek ketersediaan, dan status pengajuan.
3. **Iterasi 3 (Anggota C):** verifikasi pengajuan, dashboard admin, dan pengujian (`docs/SKENARIO_TEST.md`).

Sebelum iterasi 1 selesai, Anggota B dan C boleh memakai data sementara di memori, lalu beralih ke database.

## 7. Catatan keputusan

- **Login:** mahasiswa memakai **NIM**, admin memakai **username** (`admin1`, `admin2`). Kolom `nim` hanya diisi
  untuk mahasiswa dan kolom `username` hanya untuk admin. Layar login menulis "Username / NIM". Email tetap
  disimpan sebagai data kontak dan tidak dipakai untuk login.
- **Password** disimpan apa adanya agar sederhana untuk tugas. Kalau ada waktu, ubah menjadi hash.
- **Status ruang** (`AKTIF` atau `PERAWATAN`) adalah kondisi umum ruang dan berbeda dari ketersediaan pada jam tertentu.
- **Aturan bentrok:** hanya pengajuan `DISETUJUI` yang memblokir jadwal ruang.
