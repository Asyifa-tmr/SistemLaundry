package sistemlaundry;
public class LaundryFactory {
    public static LaundryStrategy createService(String jenis) {
        switch (jenis) {
            case "Regular":   return new RegularStrategy();
            case "Express":   return new ExpressStrategy();
            case "Dry Clean": return new DryCleanStrategy();
            default:
                throw new IllegalArgumentException("Jenis layanan tidak dikenali: " + jenis);
        }
    }
}
