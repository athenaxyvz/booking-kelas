
package booking.model;

import booking.repository.PeminjamanRepository;


public class Admin extends User {

    private String username;

    public Admin(String idUser, String nama, String password, String username) {
        super(idUser, nama, password);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    
    public void verifikasiPengajuan(Peminjaman pengajuan, boolean disetujui) {

        if (pengajuan == null) {
            throw new IllegalArgumentException("Pengajuan tidak ditemukan.");
        }

        String statusBaru = disetujui
                ? Peminjaman.DISETUJUI
                : Peminjaman.DITOLAK;

        
        pengajuan.ubahStatus(statusBaru, this);

        
        PeminjamanRepository repository = new PeminjamanRepository();

        repository.perbaruiStatus(
                pengajuan.getIdPeminjaman(),
                statusBaru,
                this.getIdUser()
        );

        System.out.println("> Pengajuan berhasil "
                + (disetujui ? "disetujui." : "ditolak."));
    }

    
    public void kelolaRuang() {
        new booking.menu.KelolaRuangMenu().tampilkan();
    }
}
