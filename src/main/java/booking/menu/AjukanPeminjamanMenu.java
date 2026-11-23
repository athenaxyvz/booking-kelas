package booking.menu;

import booking.model.Mahasiswa;
import booking.model.Peminjaman;
import booking.model.Ruang;
import booking.repository.RuangRepository;
import booking.util.Input;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class AjukanPeminjamanMenu {

    private static final DateTimeFormatter TGL = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter JAM = DateTimeFormatter.ofPattern("HH:mm");

   
    public void tampilkan(Mahasiswa mahasiswa) {
        RuangRepository ruangRepo = new RuangRepository();

        System.out.println();
        System.out.println("=== AJUKAN PEMINJAMAN RUANG ===");
        System.out.println("Isi tanggal dan jam dulu. Sistem akan menampilkan ruang yang kosong.");
        System.out.println();

        while (true) {
            LocalDate tanggal = Input.tanggal("Tanggal (DD/MM/YYYY) : ");
            LocalTime mulai = Input.jam("Jam mulai (HH:MM) : ");
            LocalTime selesai = Input.jam("Jam selesai (HH:MM) : ");

            if (!selesai.isAfter(mulai)) {
                System.out.println("> Jam selesai harus setelah jam mulai. Silakan ulangi.");
                continue;
            }

            System.out.println("> Mengecek ketersediaan ruang...");
            List<Ruang> tersedia = new ArrayList<>();
            List<String> tidakTampil = new ArrayList<>();
            for (Ruang r : ruangRepo.semua()) {
                if (r.cekKetersediaan(tanggal, mulai, selesai)) {
                    tersedia.add(r);
                } else if (!Ruang.AKTIF.equals(r.getStatus())) {
                    tidakTampil.add(r.getKodeRuang() + " (dalam perawatan)");
                } else {
                    tidakTampil.add(r.getKodeRuang() + " (sudah dipesan)");
                }
            }

            String rentang = tanggal.format(TGL) + ", " + mulai.format(JAM) + " - " + selesai.format(JAM);

            if (tersedia.isEmpty()) {
                System.out.println("> Tidak ada ruang tersedia pada " + rentang + ".");
                System.out.println("> Pengajuan tidak dibuat. Silakan ubah tanggal atau jam.");
                if (!Input.konfirmasi("Coba tanggal/jam lain?")) {
                    return;
                }
                continue;
            }

            System.out.println("> Ruang tersedia pada " + rentang + ":");
            String batas = garis(2, 4, 15, 9, 10);
            System.out.println(batas);
            System.out.printf("| %-2s | %-4s | %-15s | %-9s | %-10s |%n",
                    "No", "Kode", "Nama Ruang", "Kapasitas", "Lokasi");
            System.out.println(batas);
            int no = 1;
            for (Ruang r : tersedia) {
                System.out.printf("| %-2d | %-4s | %-15s | %-9d | %-10s |%n",
                        no++, r.getKodeRuang(), r.getNamaRuang(), r.getKapasitas(), r.getLokasi());
            }
            System.out.println(batas);
            if (!tidakTampil.isEmpty()) {
                System.out.println("> Tidak ditampilkan: " + String.join(", ", tidakTampil) + ".");
            }

            Ruang pilihan = null;
            while (pilihan == null) {
                String kode = Input.teks("Pilih ruang (kode, 0 = batal) : ");
                if (kode.equals("0")) {
                    System.out.println("> Pengajuan dibatalkan.");
                    return;
                }
                for (Ruang r : tersedia) {
                    if (r.getKodeRuang().equalsIgnoreCase(kode)) {
                        pilihan = r;
                        break;
                    }
                }
                if (pilihan == null) {
                    System.out.println("> Kode ruang tidak ada dalam daftar. Coba lagi.");
                }
            }

            String keperluan = "";
            while (keperluan.isEmpty()) {
                keperluan = Input.teks("Keperluan : ");
                if (keperluan.isEmpty()) {
                    System.out.println("> Keperluan tidak boleh kosong.");
                }
            }

            Peminjaman pengajuan = mahasiswa.ajukanPeminjaman(pilihan, tanggal, mulai, selesai, keperluan);
            System.out.println("> Pengajuan berhasil dibuat. ID: " + pengajuan.getIdPeminjaman());
            System.out.println("> Status: Menunggu Verifikasi");
            System.out.println();
            Input.teks("Tekan Enter untuk kembali ke menu...");
            return;
        }
    }

    private static String garis(int... lebar) {
        StringBuilder sb = new StringBuilder("+");
        for (int l : lebar) {
            sb.append("-".repeat(l + 2)).append("+");
        }
        return sb.toString();
    }
}