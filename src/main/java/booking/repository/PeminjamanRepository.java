package booking.repository;

import booking.database.Koneksi;
import booking.model.Admin;
import booking.model.Mahasiswa;
import booking.model.Peminjaman;
import booking.model.Ruang;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Akses tabel peminjaman.
 * Pemilik: Anggota B (bagian B) dan Anggota C (bagian C)
 * Contoh pola JDBC yang lengkap ada di RuangRepository.semua().
 */
public class PeminjamanRepository {

    // ------------------------- Bagian bersama (Anggota 1) -------------------------

    /** Query dasar: satu baris = satu pengajuan lengkap dengan data mahasiswa, ruang, dan admin. */
    private static final String SELECT_PEMINJAMAN =
            "SELECT p.id_peminjaman, p.tanggal, p.jam_mulai, p.jam_selesai, p.keperluan, p.status, "
          + "m.id_user AS m_id, m.nama AS m_nama, m.password AS m_password, m.nim AS m_nim, "
          + "r.id_ruang, r.kode_ruang, r.nama_ruang, r.kapasitas, r.lokasi, r.keterangan, "
          + "r.status AS r_status, "
          + "a.id_user AS a_id, a.nama AS a_nama, a.password AS a_password, a.username AS a_username "
          + "FROM peminjaman p "
          + "JOIN `user` m ON p.id_mahasiswa = m.id_user "
          + "JOIN ruang r ON p.id_ruang = r.id_ruang "
          + "LEFT JOIN `user` a ON p.id_admin = a.id_user ";

    /** Mengubah satu baris hasil query SELECT_PEMINJAMAN menjadi objek Peminjaman. */
    private Peminjaman petakan(ResultSet rs) throws SQLException {
        Mahasiswa mahasiswa = new Mahasiswa(rs.getString("m_id"), rs.getString("m_nama"),
                rs.getString("m_password"), rs.getString("m_nim"));

        Ruang ruang = new Ruang(rs.getString("id_ruang"), rs.getString("kode_ruang"),
                rs.getString("nama_ruang"), rs.getInt("kapasitas"), rs.getString("r_status"),
                rs.getString("lokasi"), rs.getString("keterangan"));

        Admin admin = null;                       // masih kosong kalau belum diverifikasi
        if (rs.getString("a_id") != null) {
            admin = new Admin(rs.getString("a_id"), rs.getString("a_nama"),
                    rs.getString("a_password"), rs.getString("a_username"));
        }

        return new Peminjaman(rs.getString("id_peminjaman"), mahasiswa, ruang, admin,
                rs.getDate("tanggal").toLocalDate(),
                rs.getTime("jam_mulai").toLocalTime(),
                rs.getTime("jam_selesai").toLocalTime(),
                rs.getString("keperluan"), rs.getString("status"));
    }

    // ---------------------------- Bagian Anggota B ----------------------------

    /** TODO (Anggota B): membuat id berikutnya, misalnya P006 (lihat id terbesar di tabel). */
    public String idBerikutnya() {
String sql = "SELECT MAX(id_peminjaman) FROM peminjaman";
        try (PreparedStatement ps = Koneksi.getKoneksi().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next() && rs.getString(1) != null) {
                int angka = Integer.parseInt(rs.getString(1).substring(1));
                return String.format("P%03d", angka + 1);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Gagal membuat ID: " + e.getMessage(), e);
        }
        return "P001";
    }

    /** TODO (Anggota B): menyimpan pengajuan baru (status MENUNGGU, id_admin kosong). */
    public void simpan(Peminjaman peminjaman) {
String sql = "INSERT INTO peminjaman (id_peminjaman, id_mahasiswa, id_ruang, id_admin, "
                + "tanggal, jam_mulai, jam_selesai, keperluan, status) "
                + "VALUES (?, ?, ?, NULL, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = Koneksi.getKoneksi().prepareStatement(sql)) {
            ps.setString(1, peminjaman.getIdPeminjaman());
            ps.setString(2, peminjaman.getMahasiswa().getIdUser());
            ps.setString(3, peminjaman.getRuang().getIdRuang());
            ps.setDate(4, java.sql.Date.valueOf(peminjaman.getTanggal()));
            ps.setTime(5, java.sql.Time.valueOf(peminjaman.getJamMulai()));
            ps.setTime(6, java.sql.Time.valueOf(peminjaman.getJamSelesai()));
            ps.setString(7, peminjaman.getKeperluan());
            ps.setString(8, Peminjaman.MENUNGGU);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Gagal menyimpan pengajuan: " + e.getMessage(), e);
        }    }

    /** TODO (Anggota B): semua pengajuan milik satu mahasiswa (untuk menu status pengajuan). */
    public List<Peminjaman> cariByMahasiswa(String idMahasiswa) {
        List<Peminjaman> hasil = new java.util.ArrayList<>();
        String sql = SELECT_PEMINJAMAN + "WHERE p.id_mahasiswa = ? ORDER BY p.tanggal, p.jam_mulai";
        try (PreparedStatement ps = Koneksi.getKoneksi().prepareStatement(sql)) {
            ps.setString(1, idMahasiswa);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    hasil.add(petakan(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Gagal mengambil pengajuan: " + e.getMessage(), e);
        }
        return hasil;    }

    /**
     * TODO (Anggota B): true jika sudah ada pengajuan DISETUJUI yang jadwalnya bertumpuk.
     * Dua jadwal bertumpuk jika jam mulai lama < jam selesai baru DAN jam selesai lama > jam mulai baru.
     * Petunjuk SQL:
     *   SELECT COUNT(*) FROM peminjaman
     *   WHERE id_ruang = ? AND tanggal = ? AND status = 'DISETUJUI'
     *     AND jam_mulai < ? AND jam_selesai > ?
     */
    public boolean adaBentrok(String idRuang, LocalDate tanggal, LocalTime jamMulai, LocalTime jamSelesai) {
   String sql = "SELECT COUNT(*) FROM peminjaman "
                + "WHERE id_ruang = ? AND tanggal = ? AND status = 'DISETUJUI' "
                + "AND jam_mulai < ? AND jam_selesai > ?";
        try (PreparedStatement ps = Koneksi.getKoneksi().prepareStatement(sql)) {
            ps.setString(1, idRuang);
            ps.setDate(2, java.sql.Date.valueOf(tanggal));
            ps.setTime(3, java.sql.Time.valueOf(jamSelesai));
            ps.setTime(4, java.sql.Time.valueOf(jamMulai));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Gagal mengecek bentrok: " + e.getMessage(), e);
        }
        return false;
    }

    /** TODO (Anggota B): jumlah pengajuan milik satu mahasiswa; status null berarti semua status. */
    public int hitungByMahasiswa(String idMahasiswa, String status) {
        String sql = "SELECT COUNT(*) FROM peminjaman WHERE id_mahasiswa = ?";
        if (status != null) {
            sql += " AND status = ?";
        }
        try (PreparedStatement ps = Koneksi.getKoneksi().prepareStatement(sql)) {
            ps.setString(1, idMahasiswa);
            if (status != null) {
                ps.setString(2, status);
            }
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Gagal menghitung pengajuan: " + e.getMessage(), e);
        }
        return 0;    }

    // ---------------------------- Bagian Anggota C ----------------------------

    /** TODO (Anggota C): daftar pengajuan menurut status; status null berarti semua (untuk filter). */
    public List<Peminjaman> cariByStatus(String status) {
        throw new UnsupportedOperationException("TODO (Anggota C): cariByStatus()");
    }

    /** TODO (Anggota C): satu pengajuan berdasarkan id (misalnya P004), atau null jika tidak ada. */
    public Peminjaman cariById(String idPeminjaman) {
        String sql = SELECT_PEMINJAMAN + "WHERE p.id_peminjaman = ?";
        try (PreparedStatement ps = Koneksi.getKoneksi().prepareStatement(sql)) {
            ps.setString(1, idPeminjaman);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return petakan(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Gagal mencari pengajuan: " + e.getMessage(), e);
        }
        return null;
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