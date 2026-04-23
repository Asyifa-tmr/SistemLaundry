package sistemlaundry;
public class DryCleanStrategy implements LaundryStrategy {
    private static final int TARIF_PER_KG = 10000;

    @Override
    public double hitungHarga(double beratKg) {
        return beratKg * TARIF_PER_KG;
    }

    @Override
    public String getNamaLayanan() {
        return "Dry Clean";
    }

    @Override
    public String getEstimasiWaktu() {
        return "1-2 Hari";
    }

    @Override
    public int getTarifPerKg() {
        return TARIF_PER_KG;
    }
    
    @Override
    public int getEstimasiHari() {
        return 2;
    }
}
