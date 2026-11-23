package booking.menu;

import booking.model.Mahasiswa;
import booking.model.Peminjaman;
import booking.repository.PeminjamanRepository;
import booking.repository.RuangRepository;
import booking.util.Input;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class DashboardMahasiswaMenu {

    private static final DateTimeFormatter TGL = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter JAM = DateTimeFormatter.ofPattern("HH:mm");
    private static final String[] HARI =
            {"Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu", "Minggu"};

    
    public void tampilkan(Mahasiswa mahasiswa) {
        PeminjamanRepository repo = new PeminjamanRepository();
        String idMhs = mahasiswa.getIdUser();
        LocalDate hariIni = LocalDate.now();

        int totalRuang = new RuangRepository().hitung();
        int total = repo.hitungByMahasiswa(idMhs, null);
        int disetujui = repo.hitungByMahasiswa(idMhs, Peminjaman.DISETUJUI);
        int ditolak = repo.hitungByMahasiswa(idMhs, Peminjaman.DITOLAK);
        List<Peminjaman> daftar = mahasiswa.lihatStatusPeminjaman();

        List<Peminjaman> jadwal = new ArrayList<>();
        for (Peminjaman p : daftar) {
            if (Peminjaman.DISETUJUI.equals(p.getStatus())) {
                jadwal.add(p);
            }
        }

        System.out.println();
        System.out.println("=== DASHBOARD MAHASISWA ===");
        System.out.println("Halo, " + mahasiswa.getNama() + "! "
                + HARI[hariIni.getDayOfWeek().getValue() - 1] + ", " + hariIni.format(TGL));
        System.out.println();

        System.out.println("RINGKASAN");
        System.out.println("Total Ruang : " + totalRuang + "    Pengajuan Saya : " + total);
        System.out.println("Disetujui   : " + disetujui + "    Ditolak        : " + ditolak);
        System.out.println();

        System.out.println("JADWAL RUANG SAYA");
        if (jadwal.isEmpty()) {
            System.out.println("> Belum ada jadwal yang disetujui.");
        } else {
            String batas = garis(10, 18, 11);
            System.out.println(batas);
            System.out.printf("| %-10s | %-18s | %-11s |%n", "Tanggal", "Ruang", "Waktu");
            System.out.println(batas);
            for (Peminjaman p : jadwal) {
                System.out.printf("| %-10s | %-18s | %-11s |%n",
                        p.getTanggal().format(TGL),
                        p.getRuang().getKodeRuang() + " " + p.getRuang().getNamaRuang(),
                        p.getJamMulai().format(JAM) + "-" + p.getJamSelesai().format(JAM));
            }
            System.out.println(batas);
        }
        System.out.println();

        System.out.println("PENGAJUAN SAYA");
        if (daftar.isEmpty()) {
            System.out.println("> Anda belum memiliki pengajuan.");
        } else {
            String batas = garis(2, 4, 18, 10, 9);
            System.out.println(batas);
            System.out.printf("| %-2s | %-4s | %-18s | %-10s | %-9s |%n",
                    "No", "ID", "Ruang", "Tanggal", "Status");
            System.out.println(batas);
            int no = 1;
            for (Peminjaman p : daftar) {
                System.out.printf("| %-2d | %-4s | %-18s | %-10s | %-9s |%n",
                        no++,
                        p.getIdPeminjaman(),
                        p.getRuang().getKodeRuang() + " " + p.getRuang().getNamaRuang(),
                        p.getTanggal().format(TGL),
                        rapikan(p.getStatus()));
            }
            System.out.println(batas);
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

    private static String rapikan(String status) {
        return status.charAt(0) + status.substring(1).toLowerCase();
    }
}