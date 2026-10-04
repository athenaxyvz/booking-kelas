package booking.menu;

import booking.model.Ruang;
import booking.repository.RuangRepository;
import booking.util.Input;

import java.util.List;

/**
 * Kelola data ruang (UC-09, Layar 18 sampai 21 di mockup).
 * Pemilik: Anggota A
 *
 * Aksi: [T] Tambah, [E] Edit, [H] Hapus, [0] Kembali.
 */
public class KelolaRuangMenu {

    private static final String GARIS =
            "+----+------+-----------------+------+------------+------------+-----------------+";

    private final RuangRepository repo = new RuangRepository();

    public void tampilkan() {
        boolean jalan = true;
        while (jalan) {
            System.out.println();
            System.out.println("=== KELOLA DATA RUANG ===");
            try {
                cetakTabel();
            } catch (RuntimeException e) {
                System.out.println("> " + e.getMessage());
                return;
            }

            System.out.println("Aksi : [T] Tambah [E] Edit [H] Hapus [0] Kembali");
            String aksi = Input.teks("Pilih aksi : ").toUpperCase();
            switch (aksi) {
                case "T" -> tambah();
                case "E" -> edit();
                case "H" -> hapus();
                case "0" -> jalan = false;
                default -> System.out.println("> Aksi tidak valid.");
            }
        }
    }

    // ---------------------------------------------------------------- tabel

    private void cetakTabel() {
        List<Ruang> daftar = repo.semua();
        System.out.println(GARIS);
        System.out.printf("| %-2s | %-4s | %-15s | %-4s | %-10s | %-10s | %-15s |%n",
                "No", "Kode", "Nama Ruang", "Kap.", "Lokasi", "Ket.", "Status");
        System.out.println(GARIS);
        int no = 1;
        for (Ruang r : daftar) {
            String ket = r.getKeterangan() == null ? "" : r.getKeterangan();
            System.out.printf("| %-2d | %-4s | %-15s | %-4d | %-10s | %-10s | %-15s |%n",
                    no++, r.getKodeRuang(), r.getNamaRuang(), r.getKapasitas(),
                    r.getLokasi(), ket, labelStatus(r.getStatus()));
        }
        System.out.println(GARIS);
        System.out.println("Menampilkan " + daftar.size() + " data ruangan");
    }

    // --------------------------------------------------------------- tambah

    private void tambah() {
        System.out.println();
        System.out.println("=== TAMBAH DATA RUANG ===");

        String kode = bacaWajib("Kode ruang : ").toUpperCase();
        try {
            if (repo.cariByKode(kode) != null) {
                System.out.println("> Kode ruang " + kode + " sudah dipakai. Penambahan dibatalkan.");
                return;
            }
        } catch (RuntimeException e) {
            System.out.println("> " + e.getMessage());
            return;
        }

        String nama = bacaWajib("Nama ruang : ");
        int kapasitas = bacaKapasitas("Kapasitas : ");
        String lokasi = bacaWajib("Lokasi : ");
        String keterangan = Input.teks("Keterangan : ");
        String status = bacaStatus("Status (Aktif / Perawatan) : ");

        try {
            repo.tambah(new Ruang(null, kode, nama, kapasitas, status, lokasi, keterangan));
            System.out.println("> Data ruang " + kode + " berhasil ditambahkan.");
        } catch (RuntimeException e) {
            System.out.println("> " + e.getMessage());
        }
    }

    // ----------------------------------------------------------------- edit

    private void edit() {
        System.out.println();
        System.out.println("=== EDIT DATA RUANG ===");

        String kode = bacaWajib("Kode ruang yang diedit : ");
        try {
            Ruang ruang = repo.cariByKode(kode);
            if (ruang == null) {
                System.out.println("> Ruang dengan kode " + kode + " tidak ditemukan.");
                return;
            }

            System.out.println("Data saat ini:");
            System.out.println("Nama       : " + ruang.getNamaRuang());
            System.out.println("Kapasitas  : " + ruang.getKapasitas());
            System.out.println("Lokasi     : " + ruang.getLokasi());
            System.out.println("Keterangan : " + (ruang.getKeterangan() == null ? "" : ruang.getKeterangan()));
            System.out.println("Status     : " + labelStatus(ruang.getStatus()));
            System.out.println();
            System.out.println("Data baru (kosongkan jika tidak diubah):");

            String nama = Input.teks("Nama : ");
            if (!nama.isEmpty()) {
                ruang.setNamaRuang(nama);
            }
            Integer kapasitas = bacaKapasitasBoleh("Kapasitas : ");
            if (kapasitas != null) {
                ruang.setKapasitas(kapasitas);
            }
            String lokasi = Input.teks("Lokasi : ");
            if (!lokasi.isEmpty()) {
                ruang.setLokasi(lokasi);
            }
            String keterangan = Input.teks("Keterangan : ");
            if (!keterangan.isEmpty()) {
                ruang.setKeterangan(keterangan);
            }
            String status = bacaStatusBoleh("Status (Aktif / Perawatan) : ");
            if (status != null) {
                ruang.setStatus(status);
            }

            repo.ubah(ruang);
            System.out.println("> Data ruang " + ruang.getKodeRuang() + " berhasil diperbarui.");
        } catch (RuntimeException e) {
            System.out.println("> " + e.getMessage());
        }
    }

    // ---------------------------------------------------------------- hapus

    private void hapus() {
        System.out.println();
        System.out.println("=== HAPUS DATA RUANG ===");

        String kode = bacaWajib("Kode ruang yang dihapus : ");
        try {
            Ruang ruang = repo.cariByKode(kode);
            if (ruang == null) {
                System.out.println("> Ruang dengan kode " + kode + " tidak ditemukan.");
                return;
            }
            if (!Input.konfirmasi("Yakin menghapus " + ruang.getKodeRuang() + " - "
                    + ruang.getNamaRuang() + "?")) {
                System.out.println("> Penghapusan dibatalkan.");
                return;
            }
            repo.hapus(ruang.getKodeRuang());
            System.out.println("> Data ruang " + ruang.getKodeRuang() + " berhasil dihapus.");
        } catch (RuntimeException e) {
            System.out.println("> " + e.getMessage());
        }
    }

    // ------------------------------------------------------- pembantu input

    /** Mengulang pertanyaan sampai jawabannya tidak kosong. */
    private String bacaWajib(String label) {
        while (true) {
            String isi = Input.teks(label);
            if (!isi.isEmpty()) {
                return isi;
            }
            System.out.println("> Isian ini tidak boleh kosong.");
        }
    }

    /** Kapasitas wajib: angka lebih dari 0. */
    private int bacaKapasitas(String label) {
        while (true) {
            int nilai = Input.angka(label);
            if (nilai > 0) {
                return nilai;
            }
            System.out.println("> Kapasitas harus lebih dari 0.");
        }
    }

    /** Kapasitas untuk edit: kosong berarti tidak diubah (mengembalikan null). */
    private Integer bacaKapasitasBoleh(String label) {
        while (true) {
            String isi = Input.teks(label);
            if (isi.isEmpty()) {
                return null;
            }
            try {
                int nilai = Integer.parseInt(isi);
                if (nilai > 0) {
                    return nilai;
                }
                System.out.println("> Kapasitas harus lebih dari 0.");
            } catch (NumberFormatException e) {
                System.out.println("> Masukkan angka yang valid.");
            }
        }
    }

    /** Status wajib: Aktif atau Perawatan. */
    private String bacaStatus(String label) {
        while (true) {
            String status = ubahKeStatus(Input.teks(label));
            if (status != null) {
                return status;
            }
            System.out.println("> Status harus Aktif atau Perawatan.");
        }
    }

    /** Status untuk edit: kosong berarti tidak diubah (mengembalikan null). */
    private String bacaStatusBoleh(String label) {
        while (true) {
            String isi = Input.teks(label);
            if (isi.isEmpty()) {
                return null;
            }
            String status = ubahKeStatus(isi);
            if (status != null) {
                return status;
            }
            System.out.println("> Status harus Aktif atau Perawatan.");
        }
    }

    /** "aktif" -> AKTIF; "perawatan" atau "dalam perawatan" -> PERAWATAN; selain itu null. */
    private String ubahKeStatus(String teks) {
        String t = teks.trim().toLowerCase();
        if (t.equals("aktif")) {
            return Ruang.AKTIF;
        }
        if (t.equals("perawatan") || t.equals("dalam perawatan")) {
            return Ruang.PERAWATAN;
        }
        return null;
    }

    /** Mengubah nilai database (AKTIF / PERAWATAN) menjadi tulisan untuk layar. */
    private String labelStatus(String status) {
        return Ruang.PERAWATAN.equals(status) ? "Dalam Perawatan" : "Aktif";
    }
}
