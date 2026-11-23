
# SKENARIO TEST
Pemilik: Anggota C

Jalankan skenario ini di akhir setiap iterasi dan sebelum demo.
Centang jika lulus dan catat masalah yang ditemukan.

| No | Skenario | Langkah singkat | Hasil yang diharapkan | Lulus |
|----|----------|-----------------|------------------------|-------|
| 1 | Login dengan data salah | Login dengan password yang salah | Muncul pesan error dan diminta login ulang | [ ] |
| 2 | Ruang bentrok tidak muncul | Ajukan 07/10/2026, 11:00 sampai 13:00 | A104 dan A103 tidak muncul dalam daftar ruang tersedia | [ ] |
| 3 | Pengajuan baru berstatus MENUNGGU | Ajukan peminjaman yang valid | Status MENUNGGU dan id_admin kosong | [ ] |
| 4 | Admin memverifikasi | Setujui dan tolak masing-masing satu pengajuan | Status berubah menjadi DISETUJUI atau DITOLAK | [ ] |
| 5 | Verifikasi dua kali | Proses kembali pengajuan yang sudah diverifikasi | Sistem menolak pemrosesan kedua | [ ] |
| 6 | Dashboard sesuai data | Bandingkan angka dashboard dengan tabel peminjaman | Total, menunggu, disetujui, dan ditolak cocok | [ ] |
| 7 | Hapus ruang yang punya pengajuan | Hapus ruang A104 | Muncul pesan bahwa ruang tidak bisa dihapus | [ ] |
| 8 | Tidak ada ruang tersedia | Pilih jam ketika semua ruang tidak tersedia | Muncul pesan "Tidak ada ruang tersedia" | [ ] |
| 9 | Kode ruang duplikat | Tambahkan ruang menggunakan kode yang sudah ada | Sistem menolak penambahan ruang | [ ] |
| 10 | Pengajuan tidak ditemukan | Masukkan ID pengajuan yang tidak terdaftar | Muncul pesan "Pengajuan tidak ditemukan" | [ ] |

## Catatan Pengujian

| No | Masalah yang ditemukan | Perbaikan | Status |
|----|------------------------|-----------|--------|
| -  | Belum ada pengujian | - | Belum diuji |
