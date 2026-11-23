package booking.menu;

import booking.model.Ruang;
import booking.repository.RuangRepository;
import booking.util.Input;

import java.util.List;


public class DaftarRuangMenu {

    private static final String GARIS =
            "+----+------+-----------------+-----------+------------+-----------------+";

    public void tampilkan() {
        System.out.println();
        System.out.println("=== DAFTAR RUANG ===");

        List<Ruang> daftar;
        try {
            daftar = new RuangRepository().semua();
        } catch (RuntimeException e) {
            System.out.println("> " + e.getMessage());
            Input.teks("Tekan Enter untuk kembali ke menu...");
            return;
        }

        if (daftar.isEmpty()) {
            System.out.println("> Belum ada data ruang.");
        } else {
            System.out.println(GARIS);
            System.out.printf("| %-2s | %-4s | %-15s | %-9s | %-10s | %-15s |%n",
                    "No", "Kode", "Nama Ruang", "Kapasitas", "Lokasi", "Status");
            System.out.println(GARIS);
            int no = 1;
            for (Ruang r : daftar) {
                System.out.printf("| %-2d | %-4s | %-15s | %-9d | %-10s | %-15s |%n",
                        no++, r.getKodeRuang(), r.getNamaRuang(), r.getKapasitas(),
                        r.getLokasi(), labelStatus(r.getStatus()));
            }
            System.out.println(GARIS);
        }
        Input.teks("Tekan Enter untuk kembali ke menu...");
    }

    
    private String labelStatus(String status) {
        return Ruang.PERAWATAN.equals(status) ? "Dalam Perawatan" : "Aktif";
    }
}