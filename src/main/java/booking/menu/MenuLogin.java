
package booking.menu;

import booking.model.Admin;
import booking.model.Mahasiswa;
import booking.model.User;
import booking.repository.UserRepository;
import booking.util.Input;


public class MenuLogin {

    private final UserRepository userRepository = new UserRepository();

    public void tampilkan() {
        boolean jalan = true;
        while (jalan) {
            System.out.println();
            System.out.println("=== LOGIN ===");
            System.out.println("Silakan login untuk melanjutkan.");
            System.out.println("(Ketik 0 pada Username / NIM untuk menutup program)");

            User user = mintaLogin();
            if (user == null) {
                System.out.println("> Program ditutup. Sampai jumpa!");
                jalan = false;
            } else if (user instanceof Admin admin) {
                System.out.println("> Login berhasil. Selamat datang, " + admin.getNama() + "!");
                System.out.println("> Masuk sebagai: Admin");
                new MenuAdmin().tampilkan(admin);
            } else if (user instanceof Mahasiswa mahasiswa) {
                System.out.println("> Login berhasil. Selamat datang, " + mahasiswa.getNama() + "!");
                System.out.println("> Masuk sebagai: Mahasiswa");
                new MenuMahasiswa().tampilkan(mahasiswa);
            }
        }
    }

    
    private User mintaLogin() {
        while (true) {
            String identitas = Input.teks("Username / NIM : ");
            if (identitas.equals("0")) {
                return null;
            }
            if (identitas.isEmpty()) {
                System.out.println("> Username / NIM tidak boleh kosong.");
                continue;
            }

            String password = Input.teks("Password : ");
            System.out.println("> Memverifikasi data...");

            User user;
            try {
                user = userRepository.cariByIdentitas(identitas);
            } catch (RuntimeException e) {
                System.out.println("> Terjadi masalah pada database: " + e.getMessage());
                System.out.println("> Silakan coba lagi.");
                continue;
            }

            if (user != null && user.login(password)) {
                return user;
            }
            System.out.println("> Login gagal. Username/NIM atau password salah.");
            System.out.println("> Silakan coba lagi.");
        }
    }
}
