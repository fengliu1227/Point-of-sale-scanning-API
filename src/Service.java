import java.util.HashMap;

public class Service {
    private HashMap<String, Product> products;
    private HashMap<String, Integer> scanned;
    private static Service _instance = null;
    private final double TAX = 0.1;
    private VolumeSaleFirstStrategy volumeSaleFirstStrategy;
    private GetFreeFirstStrategy getFreeFirstStrategy;
    private Service(){
        scanned = new HashMap<>();
        products = new HashMap<>();
        volumeSaleFirstStrategy = new VolumeSaleFirstStrategy();
        getFreeFirstStrategy = new GetFreeFirstStrategy();
        setDefaultAttribute2Product();
    }

    public static synchronized Service getInstance(){
        if(_instance == null){
            _instance = new Service();
        }
        return _instance;
    }

    private void setDefaultAttribute2Product(){
        Product productA = new Product(2.0, 99, "A", true, 7, 4, false, null);
        Product productB = new Product(10.0, 99, "B", false, 0, 0, false, null);
        Product productC = new Product(1.25, 99, "C", true, 6.0, 6, false, null);
        Product productD = new Product(0.15, 99, "D", true, 7, 4, false, null);
        Product productE = new Product(2.0, 99, "E", false, 0, 0, true, "B");
        products.put(productA.getCode(), productA);
        products.put(productB.getCode(), productB);
        products.put(productC.getCode(), productC);
        products.put(productD.getCode(), productD);
        products.put(productE.getCode(), productE);
    }
    public void scan(String str){
        for(int i = 0; i < str.length(); ++i){
            if(str.charAt(i) == ' '){
                continue;
            }
            scanned.put(String.valueOf(str.charAt(i)), scanned.getOrDefault(String.valueOf(str.charAt(i)), 0) + 1);
        }
    }

    public double checkout(){
        if(scanned.isEmpty()){
            System.out.println("no select");
            return 0.0;
        }
        else{
            System.out.println(volumeSaleFirstStrategy.checkOut(products, scanned));
            System.out.println(getFreeFirstStrategy.checkOut(products, scanned));
            double result = Math.min(volumeSaleFirstStrategy.checkOut(products, scanned), getFreeFirstStrategy.checkOut(products, scanned));
            scanned.clear();
            return result;
        }
    }

    public void setPrice(String code, Double price){
        products.get(code).setPrice(price);
    }

    public void setPrice(String code, Double price, int volumeSize, double volumePrice){
        products.get(code).setPrice(price);
        products.get(code).setVolumeSize(volumeSize);
        products.get(code).setVolumePrice(volumePrice);
    }
    public HashMap<String, Product> getProducts() {
        return products;
    }

    public void setProducts(HashMap<String, Product> products) {
        this.products = products;
    }
}
