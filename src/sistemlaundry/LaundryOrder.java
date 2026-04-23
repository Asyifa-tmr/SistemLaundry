package sistemlaundry;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LaundryOrder {
    private final String namaPelanggan;
    private final String noTelepon;
    private final double beratKg;
    private final LaundryStrategy strategy;
    private final String jenisPembayaran;
    private final String nomorOrder;
    private final String tanggalMasuk;
    private final String tanggalSelesai;

    public LaundryOrder(String namaPelanggan, String noTelepon,
                        double beratKg, LaundryStrategy strategy,
                        String jenisPembayaran) {
        this.namaPelanggan   = namaPelanggan;
        this.noTelepon       = noTelepon;
        this.beratKg         = beratKg;
        this.strategy        = strategy;
        this.jenisPembayaran = jenisPembayaran;
        this.nomorOrder = "LDR" + (System.currentTimeMillis() % 1000000);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate masuk     = LocalDate.now();
        this.tanggalMasuk   = masuk.format(fmt);
        this.tanggalSelesai = masuk.plusDays(strategy.getEstimasiHari()).format(fmt);
    }

    public String getNamaPelanggan()   { return namaPelanggan; }
    public String getNoTelepon()       { return noTelepon; }
    public double getBeratKg()         { return beratKg; }
    public String getNamaLayanan()     { return strategy.getNamaLayanan(); }
    public String getEstimasiWaktu()   { return strategy.getEstimasiWaktu(); }
    public int    getTarifPerKg()      { return strategy.getTarifPerKg(); }
    public String getJenisPembayaran() { return jenisPembayaran; }
    public String getNomorOrder()      { return nomorOrder; }
    public String getTanggalMasuk()    { return tanggalMasuk; }
    public String getTanggalSelesai()  { return tanggalSelesai; }
    public double getTotalHarga() {
        return strategy.hitungHarga(beratKg);
    }

    public String getTotalHargaFormatted() {
        NumberFormat rupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        return rupiah.format(getTotalHarga());
    }

    public String getTarifFormatted() {
        NumberFormat rupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        return rupiah.format(getTarifPerKg()) + "/kg";
    }
}