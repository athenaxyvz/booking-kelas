package booking.repository;

import booking.model.User;

/**
 * Akses tabel `user`.
 * Pemilik: Anggota A
 */
public class UserRepository {

    /**
     * TODO (Anggota A): cari user berdasarkan NIM (mahasiswa) atau email (siapa pun).
     * Kembalikan objek Mahasiswa atau Admin sesuai kolom role, atau null jika tidak ada.
     * Petunjuk SQL: SELECT * FROM `user` WHERE nim = ? OR email = ?
     * (user adalah nama bawaan MySQL, jadi tulis dengan tanda backtick)
     */
    public User cariByIdentitas(String identitas) {
        throw new UnsupportedOperationException("TODO (Anggota A): cariByIdentitas()");
    }
}
