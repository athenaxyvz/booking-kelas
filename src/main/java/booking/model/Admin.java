package booking.model;

/**
 * Admin mewarisi User.
 * Pemilik: Anggota A (kelas)  |  verifikasiPengajuan() dikerjakan Anggota C, kelolaRuang() Anggota A
 */
public class Admin extends User {
    private String username;   // dipakai admin untuk login, misalnya admin1

    public Admin(String idUser, String nama, String password, String username) {
        super(idUser, nama, password);
        this.username = username;
    }

    public String getUsername() { return username; }

    /**
     * TODO (Anggota C): menyetujui atau menolak satu pengajuan.
     * Petunjuk: panggil pengajuan.ubahStatus(...), lalu simpan perubahannya lewat
     * PeminjamanRepository.perbaruiStatus(...).
     */
    public void verifikasiPengajuan(Peminjaman pengajuan, boolean disetujui) {
        throw new UnsupportedOperationException("TODO (Anggota C): verifikasiPengajuan()");
    }

    /**
     * Pintu masuk untuk tambah, edit, dan hapus ruang (UC-09).
     * Seluruh alur layar dan isiannya ada di KelolaRuangMenu, sedangkan akses datanya
     * ada di RuangRepository. Method ini hanya membukanya sebagai tugas Admin.
     */
    public void kelolaRuang() {
        new booking.menu.KelolaRuangMenu().tampilkan();
    }
}