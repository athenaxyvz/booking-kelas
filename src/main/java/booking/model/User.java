package booking.model;

/**
 * Kelas induk untuk Mahasiswa dan Admin (inheritance).
 * Pemilik: Anggota A
 *
 * Method login() dan logout() adalah CONTOH YANG SUDAH JADI supaya kamu melihat gayanya:
 * atribut dibuat private (encapsulation), dan objek memeriksa passwordnya sendiri.
 */
public class User {
    private String idUser;
    private String nama;
    private String password;

    public User(String idUser, String nama, String password) {
        this.idUser = idUser;
        this.nama = nama;
        this.password = password;
    }

    public String getIdUser() { return idUser; }
    public String getNama() { return nama; }

    /** CONTOH SUDAH JADI: true jika password yang diketik sama dengan password user ini. */
    public boolean login(String passwordInput) {
        return password.equals(passwordInput);
    }

    /** CONTOH SUDAH JADI */
    public void logout() {
        System.out.println("> Sesi berakhir. Sampai jumpa!");
    }
}
