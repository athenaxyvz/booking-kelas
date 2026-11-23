
package booking.model;

import booking.repository.PeminjamanRepository;

/**
 * Admin mewarisi User.
 * Pemilik: Anggota A (kelas) | verifikasiPengajuan() dikerjakan Anggota C
 */
public class Admin extends User {

    private String username;

    public Admin(String idUser, String nama, String password, String username) {
        super(idUser, nama, password);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    /**
     * Menyetujui atau menolak satu pengajuan peminjaman.
     */
    public void verifikasiPengajuan(Peminjaman pengajuan, boolean disetujui) {

        if (pengajuan == null) {
            throw new IllegalArgumentException("Pengajuan tidak ditemukan.");
        }

        String statusBaru = disetujui
                ? Peminjaman.DISETUJUI
                : Peminjaman.DITOLAK;

        // Mengubah status pengajuan pada objek
        pengajuan.ubahStatus(statusBaru, this);

        // Menyimpan perubahan status ke database
        PeminjamanRepository repository = new PeminjamanRepository();

        repository.perbaruiStatus(
                pengajuan.getIdPeminjaman(),
                statusBaru,
                this.getIdUser()
        );

        System.out.println("> Pengajuan berhasil "
                + (disetujui ? "disetujui." : "ditolak."));
    }

    /**
     * Membuka menu pengelolaan ruang.
     */
    public void kelolaRuang() {
        new booking.menu.KelolaRuangMenu().tampilkan();
    }
}
