package booking;

import booking.database.Koneksi;
import booking.menu.MenuLogin;


public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEM BOOKING KELAS - PEMINJAMAN RUANG ===");

        
        try {
            Koneksi.getKoneksi();
            System.out.println("> Koneksi database berhasil.");
        } catch (Exception e) {
            System.out.println("> Koneksi database GAGAL: " + e.getMessage());
            System.out.println("> Cek config.properties dan pastikan MySQL sudah berjalan.");
        }

        new MenuLogin().tampilkan();
    }
}
