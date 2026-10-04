package booking;

import booking.database.Koneksi;
import booking.menu.MenuLogin;

/**
 * Titik awal program. Jalankan kelas ini untuk memulai aplikasi.
 * Pemilik: Anggota A
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEM BOOKING KELAS - PEMINJAMAN RUANG ===");

        // Cek koneksi database lebih dulu supaya masalah setup langsung terlihat.
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
