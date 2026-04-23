package sistemlaundry;
public class ExpressStrategy implements LaundryStrategy {
    private static final int TARIF_PER_KG = 8000;

    @Override
    public double hitungHarga(double beratKg) {
        return beratKg * TARIF_PER_KG;
    }

    @Override
    public String getNamaLayanan() {
        return "Express";
    }

    @Override
    public String getEstimasiWaktu() {
        return "Same Day";
    }

    @Override
    public int getTarifPerKg() {
        return TARIF_PER_KG;
    }
    
    @Override
    public int getEstimasiHari() {
        return 1;
    }
}
        