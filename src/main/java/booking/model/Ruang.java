package booking.model;

import java.time.LocalDate;
import java.time.LocalTime;
import booking.repository.PeminjamanRepository;


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

    
    public boolean cekKetersediaan(LocalDate tanggal, LocalTime jamMulai, LocalTime jamSelesai) {
        if (!AKTIF.equals(status)) {
            return false;
        }
        return !new PeminjamanRepository().adaBentrok(idRuang, tanggal, jamMulai, jamSelesai);    }
}
