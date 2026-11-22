package booking.menu;

import booking.model.Mahasiswa;
import booking.model.Peminjaman;
import booking.util.Input;

import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Status pengajuan (UC-04).
 * Pemilik: Anggota B
 */
public class StatusPengajuanMenu {

    private static final DateTimeFormatter TGL = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter JAM = DateTimeFormatter.ofPattern("HH:mm");

    /** Menampilkan tabel pengajuan milik mahasiswa yang sedang login (layar 7). */
    public void tampilkan(Mahasiswa mahasiswa) {
        List<Peminjaman> daftar = mahasiswa.lihatStatusPeminjaman();

        System.out.println();
        System.out.println("=== STATUS PENGAJUAN SAYA ===");
        System.out.println("Pengajuan atas nama: " + mahasiswa.getNama());
        System.out.println();

        if (daftar.isEmpty()) {
            System.out.println("> Anda belum memiliki pengajuan.");
        } else {
            String batas = garis(2, 4, 18, 10, 11, 9);
            System.out.println(batas);
            System.out.printf("| %-2s | %-4s | %-18s | %-10s | %-11s | %-9s |%n",
                    "No", "ID", "Ruang", "Tanggal", "Waktu", "Status");
            System.out.println(batas);
            int no = 1;
            for (Peminjaman p : daftar) {
                System.out.printf("| %-2d | %-4s | %-18s | %-10s | %-11s | %-9s |%n",
                        no++,
                        p.getIdPeminjaman(),
                        p.getRuang().getKodeRuang() + " " + p.getRuang().getNamaRuang(),
                        p.getTanggal().format(TGL),
                        p.getJamMulai().format(JAM) + "-" + p.getJamSelesai().format(JAM),
                        rapikan(p.getStatus()));
            }
            System.out.println(batas);
            System.out.println();
            System.out.println("Status: Menunggu | Disetujui | Ditolak");
        }
        System.out.println();
        Input.teks("Tekan Enter untuk kembali ke menu...");
    }

    private static String garis(int... lebar) {
        StringBuilder sb = new StringBuilder("+");
        for (int l : lebar) {
            sb.append("-".repeat(l + 2)).append("+");
        }
        return sb.toString();
    }

    /** MENUNGGU -> Menunggu */
    private static String rapikan(String status) {
        return status.charAt(0) + status.substring(1).toLowerCase();
    }
}