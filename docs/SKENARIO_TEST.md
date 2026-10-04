# Skenario Test (pemilik: Anggota C)

Jalankan skenario ini di akhir setiap iterasi dan sebelum demo. Centang kalau lulus,
dan catat masalah yang ditemukan.

| No | Skenario | Langkah singkat | Hasil yang diharapkan | Lulus |
|----|----------|-----------------|------------------------|-------|
| 1 | Login dengan data salah | Login dengan password yang salah | Muncul pesan error, diminta login ulang | [ ] |
| 2 | Ruang bentrok tidak muncul | Ajukan 07/10/2026, 11:00 sampai 13:00 | A104 (sudah dipesan 10:00 sampai 12:00) dan A103 (perawatan) tidak ada di daftar ruang tersedia | [ ] |
| 3 | Pengajuan baru berstatus MENUNGGU | Ajukan peminjaman yang valid | Status pengajuan MENUNGGU, id_admin kosong | [ ] |
| 4 | Admin memverifikasi | Setujui dan tolak masing-masing satu pengajuan | Status menjadi DISETUJUI atau DITOLAK, pengajuan yang sama tidak bisa diproses dua kali | [ ] |
| 5 | Dashboard sesuai data | Bandingkan angka dashboard dengan tabel peminjaman | Total, menunggu, disetujui, dan ditolak cocok | [ ] |
| 6 | Hapus ruang yang punya pengajuan | Hapus ruang A104 | Muncul pesan ramah, ruang tidak terhapus | [ ] |
| 7 | Tidak ada ruang tersedia | Pilih jam yang semua ruangnya terpakai | Muncul pesan "Tidak ada ruang tersedia" | [ ] |
