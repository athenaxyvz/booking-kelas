package booking.repository;

import booking.database.Koneksi;
import booking.model.Ruang;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class RuangRepository {

    
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

   
    public List<Ruang> semuaAktif() {
        List<Ruang> daftar = new ArrayList<>();
        String sql = "SELECT * FROM ruang WHERE status = 'AKTIF' ORDER BY kode_ruang";
        try (PreparedStatement ps = Koneksi.getKoneksi().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                daftar.add(petakan(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Gagal membaca data ruang aktif: " + e.getMessage(), e);
        }
        return daftar;
    }

   
    public Ruang cariByKode(String kode) {
        String sql = "SELECT * FROM ruang WHERE kode_ruang = ?";
        try (PreparedStatement ps = Koneksi.getKoneksi().prepareStatement(sql)) {
            ps.setString(1, kode);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return petakan(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Gagal mencari ruang: " + e.getMessage(), e);
        }
        return null;
    }

  
    public int hitung() {
        String sql = "SELECT COUNT(*) FROM ruang";
        try (PreparedStatement ps = Koneksi.getKoneksi().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException("Gagal menghitung ruang: " + e.getMessage(), e);
        }
    }

   
    public void tambah(Ruang ruang) {
        if (cariByKode(ruang.getKodeRuang()) != null) {
            throw new IllegalArgumentException(
                    "Kode ruang " + ruang.getKodeRuang() + " sudah dipakai.");
        }
        String sql = "INSERT INTO ruang (id_ruang, kode_ruang, nama_ruang, kapasitas, lokasi, "
                + "keterangan, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = Koneksi.getKoneksi().prepareStatement(sql)) {
            ps.setString(1, buatIdBaru());
            ps.setString(2, ruang.getKodeRuang());
            ps.setString(3, ruang.getNamaRuang());
            ps.setInt(4, ruang.getKapasitas());
            ps.setString(5, ruang.getLokasi());
            ps.setString(6, ruang.getKeterangan());
            ps.setString(7, ruang.getStatus());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Gagal menambah ruang: " + e.getMessage(), e);
        }
    }

  
    public void ubah(Ruang ruang) {
        String sql = "UPDATE ruang SET nama_ruang = ?, kapasitas = ?, lokasi = ?, "
                + "keterangan = ?, status = ? WHERE id_ruang = ?";
        try (PreparedStatement ps = Koneksi.getKoneksi().prepareStatement(sql)) {
            ps.setString(1, ruang.getNamaRuang());
            ps.setInt(2, ruang.getKapasitas());
            ps.setString(3, ruang.getLokasi());
            ps.setString(4, ruang.getKeterangan());
            ps.setString(5, ruang.getStatus());
            ps.setString(6, ruang.getIdRuang());
            if (ps.executeUpdate() == 0) {
                throw new IllegalArgumentException(
                        "Ruang " + ruang.getKodeRuang() + " tidak ditemukan.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Gagal memperbarui ruang: " + e.getMessage(), e);
        }
    }

 
    public void hapus(String kode) {
        String sql = "DELETE FROM ruang WHERE kode_ruang = ?";
        try (PreparedStatement ps = Koneksi.getKoneksi().prepareStatement(sql)) {
            ps.setString(1, kode);
            if (ps.executeUpdate() == 0) {
                throw new IllegalArgumentException("Kode ruang " + kode + " tidak ditemukan.");
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new IllegalStateException("Ruang " + kode
                    + " tidak bisa dihapus karena masih punya pengajuan peminjaman.");
        } catch (SQLException e) {
            if (e.getErrorCode() == 1451) {   
                throw new IllegalStateException("Ruang " + kode
                        + " tidak bisa dihapus karena masih punya pengajuan peminjaman.");
            }
            throw new RuntimeException("Gagal menghapus ruang: " + e.getMessage(), e);
        }
    }

   
    private String buatIdBaru() throws SQLException {
        String sql = "SELECT id_ruang FROM ruang ORDER BY id_ruang DESC LIMIT 1";
        try (PreparedStatement ps = Koneksi.getKoneksi().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            int nomor = 0;
            if (rs.next()) {
                nomor = Integer.parseInt(rs.getString("id_ruang").substring(1));
            }
            return String.format("R%03d", nomor + 1);
        }
    }
}