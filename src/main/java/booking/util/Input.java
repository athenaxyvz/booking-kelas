package booking.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Pembantu untuk membaca input dari terminal.
 * STATUS: SUDAH JADI (dipakai bersama oleh semua menu)
 */
public class Input {
    private static final Scanner SC = new Scanner(System.in);
    private static final DateTimeFormatter FORMAT_TANGGAL = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter FORMAT_JAM = DateTimeFormatter.ofPattern("HH:mm");

    private Input() { }

    /** Membaca satu baris teks. */
    public static String teks(String label) {
        System.out.print(label);
        return SC.nextLine().trim();
    }

    /** Membaca angka bulat; mengulang sampai input valid. */
    public static int angka(String label) {
        while (true) {
            try {
                return Integer.parseInt(teks(label));
            } catch (NumberFormatException e) {
                System.out.println("> Masukkan angka yang valid.");
            }
        }
    }

    /** Membaca tanggal format DD/MM/YYYY; mengulang sampai input valid. */
    public static LocalDate tanggal(String label) {
        while (true) {
            try {
                return LocalDate.parse(teks(label), FORMAT_TANGGAL);
            } catch (DateTimeParseException e) {
                System.out.println("> Format tanggal salah. Contoh: 07/10/2026");
            }
        }
    }

    /** Membaca jam format HH:MM; mengulang sampai input valid. */
    public static LocalTime jam(String label) {
        while (true) {
            try {
                return LocalTime.parse(teks(label), FORMAT_JAM);
            } catch (DateTimeParseException e) {
                System.out.println("> Format jam salah. Contoh: 14:00");
            }
        }
    }

    /** Pertanyaan Ya/Tidak. Mengembalikan true jika jawabannya Y. */
    public static boolean konfirmasi(String pertanyaan) {
        return teks(pertanyaan + " (Y/N) : ").equalsIgnoreCase("Y");
    }
}
