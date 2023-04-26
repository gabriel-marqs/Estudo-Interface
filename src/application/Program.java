package application;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter rental data");
		System.out.print("Car model: ");
		String name = sc.nextLine();
		System.out.print("Pickup (dd/MM/yyyy hh:ss): ");
		String pickupStr = sc.nextLine();
		String[] line = pickupStr.split(":");
		System.out.print("Return (dd/MM/yyyy hh:ss): ");
		String returnStr = sc.nextLine();
		System.out.print("Enter price per hour: ");
		double pricePerHour = sc.nextDouble();
		System.out.print("Enter price per day: ");
		double pricePerDay = sc.nextDouble();
		System.out.println();
		
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime pickup = LocalDateTime.parse(pickupStr.substring(0, 14) + "00:" + pickupStr.substring(14), fmt);
		LocalDateTime rtrn = LocalDateTime.parse(returnStr.substring(0, 14) + "00:" + returnStr.substring(14), fmt);
		
		Duration duration = Duration.between(pickup, rtrn);
		
		long days = duration.toDays();
		long hours = duration.toHours() % 24;
		long seconds = duration.toSeconds() % 60;
		
		System.out.println("Duration: " + days + " - " + hours + ":" + seconds);
		
		double price = 0;
		double tax = 0;
		
		if (hours <= 12 && days == 0) {
			if (seconds > 0) {
				hours++;
			}
			price = hours * pricePerHour;
		}
		else {
			if (hours > 0 || seconds > 0) {
				days++;
			}
			price = days * pricePerDay;
		}
		
		if (price <= 100) {
			tax = price * 0.20;
		}
		else {
			tax = price * 0.15;
		}
		
		double totalPrice = tax + price;
		
		System.out.println("INVOICE");
		System.out.printf("Basic payment: %.2f%n", price);
		System.out.printf("Tax: %.2f%n", tax);
		System.out.printf("Total payment: %.2f%n", totalPrice);
		
		
		
		sc.close();		

	}

}
