import java.util.HashMap;

public class VolumeSaleFirstStrategy implements CheckOutStrategy{
    private final double TAX = 0.1;

    @Override
    public double checkOut(HashMap<String, Product> products, HashMap<String, Integer> scanned) {
        double result = 0;
        for(String key : scanned.keySet()){
            if(!products.containsKey(key)){
                System.out.println("No product code as " + key + ", this order doesn't count");
            }
            if(products.get(key).getStock() < scanned.get(key)){
                scanned.clear();
                System.out.println("Stock of " + key + " is  not enough for this order!   Stock for this product :" + products.get(key).getStock());
                return 0.0;
            }

            if(products.get(key).isVolumeSale() && products.get(key).getVolumeSize() != 0 && scanned.get(key) >= products.get(key).getVolumeSize()){
                result += products.get(key).getVolumePrice() * (scanned.get(key) / products.get(key).getVolumeSize())
                        + (scanned.get(key) % products.get(key).getVolumeSize()) * products.get(key).getPrice();
            }else{
                result += scanned.get(key) * products.get(key).getPrice();
            }

            if(products.get(key).isBuy1Get1() && scanned.containsKey(products.get(key).getWithWhich())){
                if(products.get(key).isVolumeSale() && scanned.get(key) >= products.get(key).getVolumeSize()){
                    int nums = scanned.get(key) - products.get(key).getVolumeSize();
                    result -= scanned.get(products.get(key).getWithWhich()) * nums;
                }else{
                    result -= scanned.get(products.get(key).getWithWhich()) * products.get(key).getPrice();
                }
            }
        }
        return result + result * TAX;
    }
}
