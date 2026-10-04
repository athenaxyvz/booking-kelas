package booking.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Mahasiswa mewarisi User.
 * Pemilik: Anggota A (kelas)  |  method di bawah dikerjakan Anggota B
 */
public class Mahasiswa extends User {
    private String nim;

    public Mahasiswa(String idUser, String nama, String password, String nim) {
        super(idUser, nama, password);
        this.nim = nim;
    }

    public String getNim() { return nim; }

    /**
     * TODO (Anggota B): membuat pengajuan peminjaman atas nama mahasiswa ini.
     * Petunjuk: buat objek Peminjaman, lalu panggil buatPengajuan() (lihat sequence diagram).
     */
    public void ajukanPeminjaman(Ruang ruang, LocalDate tanggal, LocalTime jamMulai,
                                 LocalTime jamSelesai, String keperluan) {
        throw new UnsupportedOperationException("TODO (Anggota B): ajukanPeminjaman()");
    }

    /**
     * TODO (Anggota B): mengambil semua pengajuan milik mahasiswa ini.
     * Petunjuk: gunakan PeminjamanRepository.cariByMahasiswa(...).
     */
    public List<Peminjaman> lihatStatusPeminjaman() {
        throw new UnsupportedOperationException("TODO (Anggota B): lihatStatusPeminjaman()");
    }
}
