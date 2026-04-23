package sistemlaundry;
public class RegularStrategy implements LaundryStrategy {
    private static final int TARIF_PER_KG = 5000;

    @Override
    public double hitungHarga(double beratKg) {
        return beratKg * TARIF_PER_KG;
    }

    @Override
    public String getNamaLayanan() {
        return "Regular";
    }

    @Override
    public String getEstimasiWaktu() {
        return "2-3 Hari";
    }

    @Override
    public int getTarifPerKg() {
        return TARIF_PER_KG;
    }
    @Override
    public int getEstimasiHari() {
        return 3;
    }
}
