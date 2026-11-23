
package booking.menu;

import booking.model.Admin;
import booking.model.Peminjaman;
import booking.repository.PeminjamanRepository;
import booking.util.Input;

import java.util.List;


public class VerifikasiMenu {

    private final PeminjamanRepository repository =
            new PeminjamanRepository();

    public void tampilkan(Admin admin) {

        boolean kembali = false;

        while (!kembali) {

            System.out.println();
            System.out.println("======================================");
            System.out.println("       VERIFIKASI PENGAJUAN");
            System.out.println("======================================");
            System.out.println("1. Semua pengajuan");
            System.out.println("2. Pengajuan menunggu");
            System.out.println("3. Pengajuan disetujui");
            System.out.println("4. Pengajuan ditolak");
            System.out.println("0. Kembali");
            System.out.println("--------------------------------------");

            int pilihan = Input.angka("Pilih menu : ");

            String status;

            switch (pilihan) {
                case 1 -> status = null;
                case 2 -> status = Peminjaman.MENUNGGU;
                case 3 -> status = Peminjaman.DISETUJUI;
                case 4 -> status = Peminjaman.DITOLAK;
                case 0 -> {
                    kembali = true;
                    continue;
                }
                default -> {
                    System.out.println("> Pilihan tidak valid.");
                    continue;
                }
            }

            tampilkanDaftar(admin, status);
        }
    }

    private void tampilkanDaftar(Admin admin, String status) {

        List<Peminjaman> daftar =
                repository.cariByStatus(status);

        System.out.println();
        System.out.println("======================================");
        System.out.println("          DAFTAR PENGAJUAN");
        System.out.println("======================================");

        if (daftar == null || daftar.isEmpty()) {
            System.out.println("> Belum ada pengajuan pada kategori ini.");
            return;
        }

        for (Peminjaman p : daftar) {
            System.out.println();
            System.out.println("ID Pengajuan : "
                    + p.getIdPeminjaman());

            System.out.println("Mahasiswa    : "
                    + (p.getMahasiswa() != null
                    ? p.getMahasiswa().getNama() : "-"));

            System.out.println("Ruang        : "
                    + (p.getRuang() != null
                    ? p.getRuang().getKodeRuang() : "-"));

            System.out.println("Tanggal      : " + p.getTanggal());

            System.out.println("Waktu        : "
                    + p.getJamMulai() + " - " + p.getJamSelesai());

            System.out.println("Status       : " + p.getStatus());

            System.out.println("--------------------------------------");
        }

        String id = Input.teks(
                "Masukkan ID pengajuan (0 untuk kembali) : ");

        if ("0".equals(id)) {
            return;
        }

        Peminjaman pengajuan = repository.cariById(id);

        if (pengajuan == null) {
            System.out.println("> Pengajuan tidak ditemukan.");
            return;
        }

        tampilkanDetail(admin, pengajuan);
    }

    private void tampilkanDetail(Admin admin, Peminjaman pengajuan) {

        System.out.println();
        System.out.println("======================================");
        System.out.println("          DETAIL PENGAJUAN");
        System.out.println("======================================");

        System.out.println("ID Pengajuan : "
                + pengajuan.getIdPeminjaman());

        System.out.println("Mahasiswa    : "
                + (pengajuan.getMahasiswa() != null
                ? pengajuan.getMahasiswa().getNama() : "-"));

        System.out.println("Ruang        : "
                + (pengajuan.getRuang() != null
                ? pengajuan.getRuang().getKodeRuang() : "-"));

        System.out.println("Tanggal      : " + pengajuan.getTanggal());

        System.out.println("Jam          : "
                + pengajuan.getJamMulai() + " - "
                + pengajuan.getJamSelesai());

        System.out.println("Keperluan    : " + pengajuan.getKeperluan());

        System.out.println("Status       : " + pengajuan.getStatus());

        if (!Peminjaman.MENUNGGU.equals(pengajuan.getStatus())) {
            System.out.println();
            System.out.println("> Pengajuan ini sudah diproses.");
            return;
        }

        System.out.println();
        System.out.println("1. Setujui pengajuan");
        System.out.println("2. Tolak pengajuan");
        System.out.println("0. Kembali");

        int pilihan = Input.angka("Pilih tindakan : ");

        if (pilihan == 1 || pilihan == 2) {

            boolean disetujui = (pilihan == 1);

            String tindakan = disetujui
                    ? "menyetujui" : "menolak";

            if (Input.konfirmasi(
                    "Apakah Anda yakin ingin " + tindakan + " pengajuan ini?")) {

                try {
                    admin.verifikasiPengajuan(
                            pengajuan,
                            disetujui
                    );
                } catch (IllegalStateException | IllegalArgumentException e) {
                    System.out.println("> Gagal memproses: " + e.getMessage());
                } catch (RuntimeException e) {
                    System.out.println("> Gagal menyimpan ke database: "
                            + e.getMessage());
                }

            } else {
                System.out.println("> Tindakan dibatalkan.");
            }

        } else if (pilihan == 0) {
            System.out.println("> Kembali ke daftar pengajuan.");
        } else {
            System.out.println("> Pilihan tidak valid.");
        }
    }
}
