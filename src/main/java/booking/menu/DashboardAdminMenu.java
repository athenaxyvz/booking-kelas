
package booking.menu;

import booking.model.Admin;
import booking.model.Peminjaman;
import booking.repository.PeminjamanRepository;

import java.util.List;


public class DashboardAdminMenu {

    private final PeminjamanRepository repository =
            new PeminjamanRepository();

    public void tampilkan(Admin admin) {

        
        int total = repository.hitung(null);
        int menunggu = repository.hitung(Peminjaman.MENUNGGU);
        int disetujui = repository.hitung(Peminjaman.DISETUJUI);
        int ditolak = repository.hitung(Peminjaman.DITOLAK);

        
        List<Peminjaman> daftar =
                repository.cariByStatus(null);

        System.out.println();
        System.out.println("======================================");
        System.out.println("          DASHBOARD ADMIN");
        System.out.println("======================================");
        System.out.println("Selamat datang, " + admin.getNama());
        System.out.println();

        System.out.println("RINGKASAN PENGAJUAN");
        System.out.println("--------------------------------------");
        System.out.println("Total pengajuan     : " + total);
        System.out.println("Menunggu            : " + menunggu);
        System.out.println("Disetujui            : " + disetujui);
        System.out.println("Ditolak              : " + ditolak);

        System.out.println();
        System.out.println("TIGA PENGAJUAN TERBARU");
        System.out.println("--------------------------------------");

        if (daftar == null || daftar.isEmpty()) {
            System.out.println("Belum ada pengajuan.");
        } else {

            int batas = Math.min(3, daftar.size());

            for (int i = 0; i < batas; i++) {
                Peminjaman p = daftar.get(i);

                System.out.println();
                System.out.println("ID Pengajuan : "
                        + p.getIdPeminjaman());

                System.out.println("Mahasiswa    : "
                        + (p.getMahasiswa() != null
                        ? p.getMahasiswa().getNama()
                        : "-"));

                System.out.println("Ruang        : "
                        + (p.getRuang() != null
                        ? p.getRuang().getKodeRuang()
                        : "-"));

                System.out.println("Tanggal      : "
                        + p.getTanggal());

                System.out.println("Waktu        : "
                        + p.getJamMulai() + " - "
                        + p.getJamSelesai());

                System.out.println("Status       : "
                        + p.getStatus());
            }
        }

        System.out.println();
        System.out.println("======================================");
    }
}
