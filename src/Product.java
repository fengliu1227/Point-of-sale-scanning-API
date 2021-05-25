public class Product {
    private double price;
    private int stock;
    private String code;
    private boolean volumeSale;
    private double volumePrice;
    private int volumeSize;
    private boolean buy1Get1;
    private String withWhich;

    public Product() {}

    public Product(double price, int stock, String code, boolean volumeSale, double volumePrice, int volumeSize, boolean buy1Get1, String withWhich) {
        this.price = price;
        this.stock = stock;
        this.code = code;
        this.volumeSale = volumeSale;
        this.volumePrice = volumePrice;
        this.volumeSize = volumeSize;
        this.buy1Get1 = buy1Get1;
        this.withWhich = withWhich;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isVolumeSale() {
        return volumeSale;
    }

    public void setVolumeSale(boolean volumeSale) {
        this.volumeSale = volumeSale;
    }

    public double getVolumePrice() {
        return volumePrice;
    }

    public void setVolumePrice(double volumePrice) {
        this.volumePrice = volumePrice;
    }

    public int getVolumeSize() {
        return volumeSize;
    }

    public void setVolumeSize(int volumeSize) {
        this.volumeSize = volumeSize;
    }

    public boolean isBuy1Get1() {
        return buy1Get1;
    }

    public void setBuy1Get1(boolean buy1Get1) {
        this.buy1Get1 = buy1Get1;
    }

    public String getWithWhich() {
        return withWhich;
    }

    public void setWithWhich(String withWhich) {
        this.withWhich = withWhich;
    }
}
