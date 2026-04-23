package sistemlaundry;
public interface LaundryStrategy {
    double hitungHarga(double beratKg);
    String getNamaLayanan();
    String getEstimasiWaktu();
    int getTarifPerKg();
    int getEstimasiHari();
}
