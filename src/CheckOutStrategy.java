import java.util.HashMap;

public interface CheckOutStrategy {
    public double checkOut(HashMap<String, Product> products, HashMap<String, Integer> scanned);
}
