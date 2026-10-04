package booking.menu;

import booking.model.Mahasiswa;
import booking.util.Input;

/**
 * Menu utama mahasiswa (Layar 5 di mockup).
 * Pemilik: Anggota A  |  STATUS: CONTOH POLA MENU YANG SUDAH JADI
 *
 * Jadikan file ini contoh untuk menulis MenuAdmin dan menu-menu lain:
 * loop -> cetak pilihan -> baca angka -> switch -> panggil layar yang sesuai.
 */
public class MenuMahasiswa {

    public void tampilkan(Mahasiswa mahasiswa) {
        boolean jalan = true;
        while (jalan) {
            System.out.println();
            System.out.println("=== MENU MAHASISWA ===");
            System.out.println("Halo, " + mahasiswa.getNama());
            System.out.println();
            System.out.println("1. Lihat dashboard");
            System.out.println("2. Lihat daftar ruang");
            System.out.println("3. Ajukan peminjaman ruang");
            System.out.println("4. Lihat status pengajuan");
            System.out.println("0. Logout");
            System.out.println();

            int pilihan = Input.angka("Pilih menu : ");
            switch (pilihan) {
                case 1 -> new DashboardMahasiswaMenu().tampilkan(mahasiswa);   // Anggota B
                case 2 -> new DaftarRuangMenu().tampilkan();                   // Anggota A
                case 3 -> new AjukanPeminjamanMenu().tampilkan(mahasiswa);     // Anggota B
                case 4 -> new StatusPengajuanMenu().tampilkan(mahasiswa);      // Anggota B
                case 0 -> {
                    if (Input.konfirmasi("Anda yakin ingin keluar?")) {
                        mahasiswa.logout();
                        jalan = false;
                    }
                }
                default -> System.out.println("> Pilihan tidak valid.");
            }
        }
    }
}
