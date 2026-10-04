package booking.repository;

import booking.model.Peminjaman;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Akses tabel peminjaman.
 * Pemilik: Anggota B (bagian B) dan Anggota C (bagian C)
 * Contoh pola JDBC yang lengkap ada di RuangRepository.semua().
 */
public class PeminjamanRepository {

    // ---------------------------- Bagian Anggota B ----------------------------

    /** TODO (Anggota B): membuat id berikutnya, misalnya P006 (lihat id terbesar di tabel). */
    public String idBerikutnya() {
        throw new UnsupportedOperationException("TODO (Anggota B): idBerikutnya()");
    }

    /** TODO (Anggota B): menyimpan pengajuan baru (status MENUNGGU, id_admin kosong). */
    public void simpan(Peminjaman peminjaman) {
        throw new UnsupportedOperationException("TODO (Anggota B): simpan()");
    }

    /** TODO (Anggota B): semua pengajuan milik satu mahasiswa (untuk menu status pengajuan). */
    public List<Peminjaman> cariByMahasiswa(String idMahasiswa) {
        throw new UnsupportedOperationException("TODO (Anggota B): cariByMahasiswa()");
    }

    /**
     * TODO (Anggota B): true jika sudah ada pengajuan DISETUJUI yang jadwalnya bertumpuk.
     * Dua jadwal bertumpuk jika jam mulai lama < jam selesai baru DAN jam selesai lama > jam mulai baru.
     * Petunjuk SQL:
     *   SELECT COUNT(*) FROM peminjaman
     *   WHERE id_ruang = ? AND tanggal = ? AND status = 'DISETUJUI'
     *     AND jam_mulai < ? AND jam_selesai > ?
     */
    public boolean adaBentrok(String idRuang, LocalDate tanggal, LocalTime jamMulai, LocalTime jamSelesai) {
        throw new UnsupportedOperationException("TODO (Anggota B): adaBentrok()");
    }

    /** TODO (Anggota B): jumlah pengajuan milik satu mahasiswa; status null berarti semua status. */
    public int hitungByMahasiswa(String idMahasiswa, String status) {
        throw new UnsupportedOperationException("TODO (Anggota B): hitungByMahasiswa()");
    }

    // ---------------------------- Bagian Anggota C ----------------------------

    /** TODO (Anggota C): daftar pengajuan menurut status; status null berarti semua (untuk filter). */
    public List<Peminjaman> cariByStatus(String status) {
        throw new UnsupportedOperationException("TODO (Anggota C): cariByStatus()");
    }

    /** TODO (Anggota C): satu pengajuan berdasarkan id (misalnya P004), atau null jika tidak ada. */
    public Peminjaman cariById(String idPeminjaman) {
        throw new UnsupportedOperationException("TODO (Anggota C): cariById()");
    }

    /** TODO (Anggota C): mengubah status pengajuan dan mencatat admin yang memverifikasi. */
    public void perbaruiStatus(String idPeminjaman, String status, String idAdmin) {
        throw new UnsupportedOperationException("TODO (Anggota C): perbaruiStatus()");
    }

    /** TODO (Anggota C): jumlah pengajuan menurut status; status null berarti semua (untuk dashboard admin). */
    public int hitung(String status) {
        throw new UnsupportedOperationException("TODO (Anggota C): hitung()");
    }
}
