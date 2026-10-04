package booking.model;

/**
 * Admin mewarisi User.
 * Pemilik: Anggota A (kelas)  |  verifikasiPengajuan() dikerjakan Anggota C, kelolaRuang() Anggota A
 */
public class Admin extends User {

    public Admin(String idUser, String nama, String email, String password) {
        super(idUser, nama, email, password);
    }

    /**
     * TODO (Anggota C): menyetujui atau menolak satu pengajuan.
     * Petunjuk: panggil pengajuan.ubahStatus(...), lalu simpan perubahannya lewat
     * PeminjamanRepository.perbaruiStatus(...).
     */
    public void verifikasiPengajuan(Peminjaman pengajuan, boolean disetujui) {
        throw new UnsupportedOperationException("TODO (Anggota C): verifikasiPengajuan()");
    }

    /**
     * TODO (Anggota A): pintu masuk untuk tambah, edit, dan hapus ruang.
     * Petunjuk: boleh dibiarkan kosong dan semua alurnya ditulis di KelolaRuangMenu
     * + RuangRepository, selama alasan desainnya bisa kalian jelaskan ke dosen.
     */
    public void kelolaRuang() {
        throw new UnsupportedOperationException("TODO (Anggota A): kelolaRuang()");
    }
}
