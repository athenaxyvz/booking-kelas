package booking.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Menyediakan satu koneksi ke database MySQL.
 * Pemilik: Anggota A  |  STATUS: SUDAH JADI (boleh dipelajari, tidak perlu diubah)
 *
 * Cara pakai di kelas lain:
 *     Connection c = Koneksi.getKoneksi();
 */
public class Koneksi {
    private static Connection koneksi;

    private Koneksi() { }

    public static Connection getKoneksi() throws SQLException {
        if (koneksi == null || koneksi.isClosed()) {
            Properties config = new Properties();
            try (FileInputStream in = new FileInputStream("config.properties")) {
                config.load(in);
            } catch (IOException e) {
                throw new SQLException("File config.properties tidak ditemukan. "
                        + "Salin dari config.example.properties lalu isi password MySQL kamu.", e);
            }
            koneksi = DriverManager.getConnection(
                    config.getProperty("db.url"),
                    config.getProperty("db.user"),
                    config.getProperty("db.password"));
        }
        return koneksi;
    }
}
