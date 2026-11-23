package booking.menu;

import booking.model.Mahasiswa;
import booking.util.Input;


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
                case 1 -> new DashboardMahasiswaMenu().tampilkan(mahasiswa);   
                case 2 -> new DaftarRuangMenu().tampilkan();                   
                case 3 -> new AjukanPeminjamanMenu().tampilkan(mahasiswa);     
                case 4 -> new StatusPengajuanMenu().tampilkan(mahasiswa);     
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
