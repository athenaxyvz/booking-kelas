package booking.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import booking.repository.PeminjamanRepository;


public class Mahasiswa extends User {
    private String nim;

    public Mahasiswa(String idUser, String nama, String password, String nim) {
        super(idUser, nama, password);
        this.nim = nim;
    }

    public String getNim() { return nim; }

   
    public Peminjaman ajukanPeminjaman(Ruang ruang, LocalDate tanggal, LocalTime jamMulai,
                                 LocalTime jamSelesai, String keperluan) {
        PeminjamanRepository repo = new PeminjamanRepository();
        Peminjaman pengajuan = new Peminjaman(repo.idBerikutnya(), this, ruang,
                tanggal, jamMulai, jamSelesai, keperluan);
        pengajuan.buatPengajuan();
        return pengajuan;    }

    
    public List<Peminjaman> lihatStatusPeminjaman() {
        return new PeminjamanRepository().cariByMahasiswa(getIdUser());    }
}
