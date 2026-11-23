package booking.model;

import java.time.LocalDate;
import java.time.LocalTime;
import booking.repository.PeminjamanRepository;


public class Peminjaman {
    public static final String MENUNGGU = "MENUNGGU";
    public static final String DISETUJUI = "DISETUJUI";
    public static final String DITOLAK = "DITOLAK";

    private String idPeminjaman;
    private Mahasiswa mahasiswa;
    private Ruang ruang;
    private Admin admin;               
    private LocalDate tanggal;
    private LocalTime jamMulai;
    private LocalTime jamSelesai;
    private String keperluan;
    private String status;

   
    public Peminjaman(String idPeminjaman, Mahasiswa mahasiswa, Ruang ruang, LocalDate tanggal,
                      LocalTime jamMulai, LocalTime jamSelesai, String keperluan) {
        this(idPeminjaman, mahasiswa, ruang, null, tanggal, jamMulai, jamSelesai, keperluan, MENUNGGU);
    }

    
    public Peminjaman(String idPeminjaman, Mahasiswa mahasiswa, Ruang ruang, Admin admin,
                      LocalDate tanggal, LocalTime jamMulai, LocalTime jamSelesai,
                      String keperluan, String status) {
        this.idPeminjaman = idPeminjaman;
        this.mahasiswa = mahasiswa;
        this.ruang = ruang;
        this.admin = admin;
        this.tanggal = tanggal;
        this.jamMulai = jamMulai;
        this.jamSelesai = jamSelesai;
        this.keperluan = keperluan;
        this.status = status;
    }

    public String getIdPeminjaman() { return idPeminjaman; }
    public Mahasiswa getMahasiswa() { return mahasiswa; }
    public Ruang getRuang() { return ruang; }
    public Admin getAdmin() { return admin; }
    public LocalDate getTanggal() { return tanggal; }
    public LocalTime getJamMulai() { return jamMulai; }
    public LocalTime getJamSelesai() { return jamSelesai; }
    public String getKeperluan() { return keperluan; }
    public String getStatus() { return status; }

    
    public void buatPengajuan() {
new PeminjamanRepository().simpan(this);    }

    
    public void ubahStatus(String statusBaru, Admin admin) {
        if (!MENUNGGU.equals(status)) {
            throw new IllegalStateException("Pengajuan " + idPeminjaman + " sudah diproses.");
        }
        if (!DISETUJUI.equals(statusBaru) && !DITOLAK.equals(statusBaru)) {
            throw new IllegalArgumentException("Status baru harus DISETUJUI atau DITOLAK.");
        }
        this.status = statusBaru;
        this.admin = admin;
    }
}
