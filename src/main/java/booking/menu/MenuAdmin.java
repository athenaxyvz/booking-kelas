package booking.menu;

import booking.model.Admin;
import booking.util.Input;


public class MenuAdmin {

    public void tampilkan(Admin admin) {
        boolean jalan = true;
        while (jalan) {
            System.out.println();
            System.out.println("=== MENU ADMIN ===");
            System.out.println("Halo, " + admin.getNama());
            System.out.println();
            System.out.println("1. Lihat dashboard admin");
            System.out.println("2. Verifikasi pengajuan");
            System.out.println("3. Kelola data ruang");
            System.out.println("0. Logout");
            System.out.println();

            int pilihan = Input.angka("Pilih menu : ");
            switch (pilihan) {
                case 1 -> new DashboardAdminMenu().tampilkan(admin);   
                case 2 -> new VerifikasiMenu().tampilkan(admin);       
                case 3 -> new KelolaRuangMenu().tampilkan();           
                case 0 -> {
                    if (Input.konfirmasi("Anda yakin ingin keluar?")) {
                        admin.logout();
                        jalan = false;
                    }
                }
                default -> System.out.println("> Pilihan tidak valid.");
            }
        }
    }
}