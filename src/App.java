import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
    private Service service;
    public App(){
        this.service = Service.getInstance();
    }

    public void run(){
        String setPricingFormat = "Enter new Pricing in the format <Product Code> <Per Unit Price> <Volume Size(optional)> <Volume Price(optional), split with one space";

       this.printMenu();
        Scanner input = new Scanner(System.in);

        boolean setPrice = false;
        boolean added = false;
        boolean continueAdd = false;
        while (input.hasNext()) {
            String line = input.nextLine().trim();
                // Exit
                if (line.equals("exit")) {
                    break;
                }
                //set price
                if(setPrice){
                    String pattern = "^(A|B|C|D|E)(\\s+)(([1-9]\\d*\\.?\\d*)|(0\\.\\d*[1-9]))(\\s*)(\\d*)(\\s*)(([1-9]\\d*\\.?\\d*)|(0\\.\\d*[1-9]))*";
                    Pattern r = Pattern.compile(pattern);
                    Matcher m = r.matcher(line);
                    if (m.find()) {
                        String update = m.group(0);
                        String[] param = update.split("\\s+");
                        if(param.length == 2){
                            service.setPrice(param[0], Double.parseDouble(param[1]));
                            this.printMenu();
                            setPrice = false;
                        }else if(param.length == 4){
                            service.setPrice(param[0], Double.parseDouble(param[1]), Integer.parseInt(param[2]), Double.parseDouble(param[3]));
                            this.printMenu();
                            setPrice = false;
                        }else{
                            System.out.println("Invalid input(if you input the Volume Size, you must provide Volume Price too)");
                            System.out.print(">>>");
                        }
                    } else {
                        System.out.println("NO MATCH!! Invalid input");
                        System.out.print(">>>");
                    }
                    //add to select list
                }else{
                    if(line.equals("r")){
                        double result = service.checkout();
                        added = false;
                        if(result <= 0){
                            System.out.println("Invalid order!!! do it again!");
                        }else {
                            System.out.println("The total price is $" + result);
                        }
                        System.out.println("Please enter 'continue' to continue with current pricing or 'set' to add new pricing");
                        System.out.println("Please enter 'exit' to exit this program");
                        System.out.print(">>>");
                    }else if(line.equals("continue")|| added){
                        System.out.println("Enter the products code you want to buy in a single line then Enter 'r' to get the total price(no space)");
                        System.out.print(">>>");
                        line.trim();
                        added = true;
                        if(line.equals("continue")){
                            continue;
                        }
                        String pattern = "^(A|B|C|D|E)*$";
                        Pattern r = Pattern.compile(pattern);
                        Matcher m = r.matcher(line);
                        if (m.find()) {
                            service.scan(line);
                        }else{
                            System.out.println("Invalid product code(Only A - E)");
                        }
                    }else if(line.equals("set")){
                        System.out.println(setPricingFormat);
                        System.out.print(">>>");
                        setPrice = true;
                    }else{
                        System.out.println("Invalid input");
                        System.out.print(">>>");
                    }
                }
        }
    }

    private void printMenu(){
        System.out.println("Product Code | Price");
        List<Product> products = new ArrayList<>(service.getProducts().values());
        for(Product p: products) {
            System.out.print(p.getCode() + "\t\t\t |" + "$" + p.getPrice() + " each");
            if (p.isVolumeSale()) {
                System.out.print(" or " + p.getVolumeSize() + " for $" + p.getVolumePrice());
            }
            if (p.isBuy1Get1()) {
                System.out.print(" AND if buy one " + p.getWithWhich() + " get one " + p.getCode());
            }
            System.out.println("");
        }
        System.out.println("Please enter 'continue' to continue with current pricing or 'set' to add new pricing");
        System.out.println("Please enter 'exit' to exit this program");
        System.out.print(">>>");
    }
}
