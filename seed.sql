-- =====================================================================
-- seed.sql : data contoh (jalankan SETELAH schema.sql)
-- Data ini sama dengan contoh di mockup terminal, supaya demo ketiga anggota seragam.
-- Akun contoh:
--   Admin     -> login: admin@kampus.ac.id  | password: admin123
--   Mahasiswa -> login: 25210001 (NIM)      | password: password123
-- =====================================================================

USE booking_kelas;

INSERT INTO `user` (id_user, nama, email, password, role, nim) VALUES
('U001', 'Admin',          'admin@kampus.ac.id', 'admin123',    'ADMIN',     NULL),
('U002', 'Andi Pratama',   'andi@kampus.ac.id',  'password123', 'MAHASISWA', '25210001'),
('U003', 'Siti Aisyah',    'siti@kampus.ac.id',  'password123', 'MAHASISWA', '25210002'),
('U004', 'Budi Santoso',   'budi@kampus.ac.id',  'password123', 'MAHASISWA', '25210003'),
('U005', 'Rina Wulandari', 'rina@kampus.ac.id',  'password123', 'MAHASISWA', '25210004');

INSERT INTO ruang (id_ruang, kode_ruang, nama_ruang, kapasitas, lokasi, keterangan, status) VALUES
('R001', 'A101', 'Lab Komputer',  30, 'Gedung B', 'Lab 1',    'AKTIF'),
('R002', 'A102', 'Ruang Rapat',   30, 'Gedung A', 'Ruang 1',  'AKTIF'),
('R003', 'A103', 'Lab Komputer',  30, 'Gedung D', 'Lab 3',    'PERAWATAN'),
('R004', 'A104', 'Aula',          50, 'Gedung C', 'Aula 1',   'AKTIF'),
('R005', 'A105', 'Ruang Seminar', 40, 'Gedung E', 'Sem 1',    'AKTIF');

INSERT INTO peminjaman (id_peminjaman, id_mahasiswa, id_ruang, id_admin, tanggal, jam_mulai, jam_selesai, keperluan, status) VALUES
('P001', 'U004', 'R004', 'U001', '2026-10-07', '10:00:00', '12:00:00', 'Seminar himpunan',   'DISETUJUI'),
('P002', 'U005', 'R003', 'U001', '2026-10-04', '14:00:00', '16:00:00', 'Kelas pengganti',    'DITOLAK'),
('P003', 'U002', 'R004', 'U001', '2026-10-08', '09:00:00', '11:00:00', 'Diskusi kelompok',   'DISETUJUI'),
('P004', 'U003', 'R002', NULL,   '2026-10-06', '13:00:00', '15:00:00', 'Rapat organisasi',   'MENUNGGU'),
('P005', 'U002', 'R001', NULL,   '2026-10-05', '08:00:00', '10:00:00', 'Praktikum tambahan', 'MENUNGGU');
