package booking.repository;

import booking.database.Koneksi;
import booking.model.Ruang;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Akses tabel ruang.
 * Pemilik: Anggota A
 *
 * semua() adalah CONTOH SUDAH JADI untuk pola JDBC. Method lain dikerjakan dengan pola yang sama.
 */
public class RuangRepository {

    /** CONTOH SUDAH JADI: mengambil semua ruang, diurutkan menurut kode. */
    public List<Ruang> semua() {
        List<Ruang> daftar = new ArrayList<>();
        String sql = "SELECT * FROM ruang ORDER BY kode_ruang";
        try (PreparedStatement ps = Koneksi.getKoneksi().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                daftar.add(petakan(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Gagal membaca data ruang: " + e.getMessage(), e);
        }
        return daftar;
    }

    /** Mengubah satu baris hasil query menjadi objek Ruang. */
    private Ruang petakan(ResultSet rs) throws SQLException {
        return new Ruang(
                rs.getString("id_ruang"),
                rs.getString("kode_ruang"),
                rs.getString("nama_ruang"),
                rs.getInt("kapasitas"),
                rs.getString("status"),
                rs.getString("lokasi"),
                rs.getString("keterangan"));
    }

    /** TODO (Anggota A): ruang yang statusnya AKTIF saja (dipakai Anggota B untuk cek ketersediaan). */
    public List<Ruang> semuaAktif() {
        throw new UnsupportedOperationException("TODO (Anggota A): semuaAktif()");
    }

    /** TODO (Anggota A): cari satu ruang berdasarkan kode (misalnya A101), atau null jika tidak ada. */
    public Ruang cariByKode(String kode) {
        throw new UnsupportedOperationException("TODO (Anggota A): cariByKode()");
    }

    /** TODO (Anggota A): jumlah semua ruang (untuk dashboard). */
    public int hitung() {
        throw new UnsupportedOperationException("TODO (Anggota A): hitung()");
    }

    /**
     * TODO (Anggota A): menambah ruang baru. Id ruang dibuat otomatis (misalnya R006).
     * Tangani kode ruang yang sudah dipakai (kolom kode_ruang bersifat unik).
     */
    public void tambah(Ruang ruang) {
        throw new UnsupportedOperationException("TODO (Anggota A): tambah()");
    }

    /** TODO (Anggota A): memperbarui data ruang (nama, kapasitas, lokasi, keterangan, status). */
    public void ubah(Ruang ruang) {
        throw new UnsupportedOperationException("TODO (Anggota A): ubah()");
    }

    /**
     * TODO (Anggota A): menghapus ruang berdasarkan kode.
     * Ruang yang masih punya pengajuan akan ditolak database (foreign key). Tampilkan pesan yang ramah.
     */
    public void hapus(String kode) {
        throw new UnsupportedOperationException("TODO (Anggota A): hapus()");
    }
}
