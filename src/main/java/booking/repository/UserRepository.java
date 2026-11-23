package booking.repository;

import booking.database.Koneksi;
import booking.model.Admin;
import booking.model.Mahasiswa;
import booking.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserRepository {

   
    public User cariByIdentitas(String nimAtauUsername) {
        String sql = "SELECT * FROM `user` WHERE nim = ? OR username = ?";
        try {
            Connection c = Koneksi.getKoneksi();
            try (PreparedStatement ps = c.prepareStatement(sql)) {
                ps.setString(1, nimAtauUsername);
                ps.setString(2, nimAtauUsername);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return petakan(rs);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Gagal membaca data user: " + e.getMessage(), e);
        }
        return null;
    }

    
    private User petakan(ResultSet rs) throws SQLException {
        String id = rs.getString("id_user");
        String nama = rs.getString("nama");
        String password = rs.getString("password");

        if ("ADMIN".equals(rs.getString("role"))) {
            return new Admin(id, nama, password, rs.getString("username"));
        }
        return new Mahasiswa(id, nama, password, rs.getString("nim"));
    }
}
