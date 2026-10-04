package booking.model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Ruang yang bisa dipinjam.
 * Pemilik: Anggota A (kelas)  |  cekKetersediaan() dikerjakan Anggota B
 *
 * status = kondisi umum ruang (AKTIF atau PERAWATAN) yang diatur admin.
 * Ini BERBEDA dari ketersediaan pada tanggal dan jam tertentu (cekKetersediaan).
 */
public class Ruang {
    public static final String AKTIF = "AKTIF";
    public static final String PERAWATAN = "PERAWATAN";

    private String idRuang;
    private String kodeRuang;
    private String namaRuang;
    private int kapasitas;
    private String status;
    private String lokasi;
    private String keterangan;

    public Ruang(String idRuang, String kodeRuang, String namaRuang, int kapasitas,
                 String status, String lokasi, String keterangan) {
        this.idRuang = idRuang;
        this.kodeRuang = kodeRuang;
        this.namaRuang = namaRuang;
        this.kapasitas = kapasitas;
        this.status = status;
        this.lokasi = lokasi;
        this.keterangan = keterangan;
    }

    public String getIdRuang() { return idRuang; }
    public String getKodeRuang() { return kodeRuang; }
    public String getNamaRuang() { return namaRuang; }
    public int getKapasitas() { return kapasitas; }
    public String getStatus() { return status; }
    public String getLokasi() { return lokasi; }
    public String getKeterangan() { return keterangan; }

    public void setNamaRuang(String namaRuang) { this.namaRuang = namaRuang; }
    public void setKapasitas(int kapasitas) { this.kapasitas = kapasitas; }
    public void setStatus(String status) { this.status = status; }
    public void setLokasi(String lokasi) { this.lokasi = lokasi; }
    public void setKeterangan(String keterangan) { this.keterangan = keterangan; }

    /**
     * TODO (Anggota B): true jika ruang ini kosong pada tanggal dan jam tersebut.
     * Aturan: ruang TIDAK tersedia jika statusnya PERAWATAN, atau jika sudah ada
     * pengajuan DISETUJUI yang jadwalnya bertumpuk.
     * Petunjuk: gunakan PeminjamanRepository.adaBentrok(...).
     */
    public boolean cekKetersediaan(LocalDate tanggal, LocalTime jamMulai, LocalTime jamSelesai) {
        throw new UnsupportedOperationException("TODO (Anggota B): cekKetersediaan()");
    }
}
