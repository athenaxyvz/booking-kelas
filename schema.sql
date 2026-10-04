-- =====================================================================
-- schema.sql : struktur database Sistem Booking Kelas (MySQL 8)
-- Pemilik : Anggota A
-- Cara pakai: jalankan file ini LEBIH DULU, lalu jalankan seed.sql.
-- PERHATIAN : file ini menghapus database lama (booking_kelas) lalu membuat ulang.
-- Kalau struktur tabel berubah, ubah file ini lewat Pull Request dan kabari grup.
-- =====================================================================

DROP DATABASE IF EXISTS booking_kelas;
CREATE DATABASE booking_kelas CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE booking_kelas;

-- Pengguna (mahasiswa dan admin).
-- Login: mahasiswa memakai NIM, admin memakai username (admin1, admin2).
-- Kolom nim hanya diisi untuk mahasiswa, kolom username hanya untuk admin.
CREATE TABLE `user` (
  id_user  VARCHAR(10)  NOT NULL,
  nama     VARCHAR(100) NOT NULL,
  email    VARCHAR(100) NOT NULL,
  password VARCHAR(100) NOT NULL,
  role     ENUM('MAHASISWA', 'ADMIN') NOT NULL,
  nim      VARCHAR(20)  NULL,
  username VARCHAR(30)  NULL,
  PRIMARY KEY (id_user),
  UNIQUE KEY uk_user_email (email),
  UNIQUE KEY uk_user_nim (nim),
  UNIQUE KEY uk_user_username (username)
) ENGINE = InnoDB;

-- Ruang yang bisa dipinjam.
-- status = kondisi umum ruang (diatur admin), BUKAN ketersediaan pada jam tertentu.
CREATE TABLE ruang (
  id_ruang   VARCHAR(10)  NOT NULL,
  kode_ruang VARCHAR(10)  NOT NULL,
  nama_ruang VARCHAR(100) NOT NULL,
  kapasitas  INT          NOT NULL,
  lokasi     VARCHAR(100) NOT NULL,
  keterangan VARCHAR(255) NULL,
  status     ENUM('AKTIF', 'PERAWATAN') NOT NULL DEFAULT 'AKTIF',
  PRIMARY KEY (id_ruang),
  UNIQUE KEY uk_ruang_kode (kode_ruang)
) ENGINE = InnoDB;

-- Pengajuan peminjaman. id_admin kosong (NULL) sampai pengajuan diverifikasi.
-- Ruang yang masih punya pengajuan tidak bisa dihapus (foreign key).
CREATE TABLE peminjaman (
  id_peminjaman VARCHAR(10)  NOT NULL,
  id_mahasiswa  VARCHAR(10)  NOT NULL,
  id_ruang      VARCHAR(10)  NOT NULL,
  id_admin      VARCHAR(10)  NULL,
  tanggal       DATE         NOT NULL,
  jam_mulai     TIME         NOT NULL,
  jam_selesai   TIME         NOT NULL,
  keperluan     VARCHAR(255) NOT NULL,
  status        ENUM('MENUNGGU', 'DISETUJUI', 'DITOLAK') NOT NULL DEFAULT 'MENUNGGU',
  PRIMARY KEY (id_peminjaman),
  CONSTRAINT fk_pinjam_mahasiswa FOREIGN KEY (id_mahasiswa) REFERENCES `user` (id_user),
  CONSTRAINT fk_pinjam_ruang     FOREIGN KEY (id_ruang)     REFERENCES ruang (id_ruang),
  CONSTRAINT fk_pinjam_admin     FOREIGN KEY (id_admin)     REFERENCES `user` (id_user),
  CONSTRAINT ck_jam CHECK (jam_selesai > jam_mulai)
) ENGINE = InnoDB;
