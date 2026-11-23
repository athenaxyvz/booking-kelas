package booking.model;


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

   
    public boolean login(String passwordInput) {
        return password.equals(passwordInput);
    }

    
    public void logout() {
        System.out.println("> Sesi berakhir. Sampai jumpa!");
    }
}
