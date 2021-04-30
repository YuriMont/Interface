
package pkginterface;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import model.entites.CarRental;
import model.entites.Veiculo;
import model.service.BrazilTaxService;
import model.service.RentalService;

public class Interface {

    public static void main(String[] args) throws ParseException {
        Scanner t = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:ss");
        System.out.println("Enter rental data: ");
        System.out.print("Car model: ");
        String model = t.nextLine();
        System.out.print("Pickup (dd/MM/yyyy hh:mm): ");
        Date start = sdf.parse(t.nextLine());
        System.out.print("Return (dd/MM/yyyy hh:mm): ");
        Date finish = sdf.parse(t.nextLine());
        
        CarRental cr = new CarRental(start, finish, new Veiculo(model));
        
        System.out.print("Enter price per hour: ");
        double pricePerHour = t.nextDouble();
        System.out.print("Enter price per day: ");
        double pricePerDay = t.nextDouble();
        
        RentalService rental = new RentalService(pricePerHour, pricePerDay, new BrazilTaxService());
        
        rental.processInvoice(cr);
        
        System.out.println("INVOICE:");
        System.out.println("Basic payment: "+ String.format("%.2f", cr.getInvoice().getBasicPayment()));
        System.out.println("Tax: "+String.format("%.2f", cr.getInvoice().getTax()));
        System.out.println("Total payment: "+String.format("%.2f", cr.getInvoice().getTotalPayment()));
                
        
        t.close();
    }
    
}
