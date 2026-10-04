package booking.repository;

import booking.model.User;

/**
 * Akses tabel `user`.
 * Pemilik: Anggota A
 */
public class UserRepository {

    /**
     * TODO (Anggota A): cari user berdasarkan NIM (mahasiswa) atau username (admin).
     * Kembalikan objek Mahasiswa atau Admin sesuai kolom role, atau null jika tidak ada.
     * Petunjuk SQL: SELECT * FROM `user` WHERE nim = ? OR username = ?
     * (user adalah nama bawaan MySQL, jadi tulis dengan tanda backtick)
     * Saat membuat objek Admin, isi juga atribut username dari kolom username.
     */
    public User cariByIdentitas(String nimAtauUsername) {
        throw new UnsupportedOperationException("TODO (Anggota A): cariByIdentitas()");
    }
}

