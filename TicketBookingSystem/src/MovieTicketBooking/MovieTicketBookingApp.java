package MovieTicketBooking;

import java.sql.SQLException;
import java.util.Scanner;
import java.sql.SQLException;
import java.util.Scanner;
 
public class MovieTicketBookingApp {
 
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter your choice:");
            System.out.println("1 - Register");
            System.out.println("2 - Login");
            System.out.println("3 - Exit");
 
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
 
            switch (choice) {
                case 1:
                    UserRegistration.registerUser();
                    break;
                case 2:
                    boolean isAdmin = UserLogin.loginUser();
                    if (isAdmin) {
                        showAdminMenu();
                    } else {
                        showUserMenu();
                    }
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return; // Exit the application
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }
 
    private static void showAdminMenu() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Admin Menu:");
            System.out.println("1 - View Movies");
            System.out.println("2 - Book Ticket");
            System.out.println("3 - Confirm Booking");
            System.out.println("4 - Cancel Ticket");
            System.out.println("5 - Create Cancel Booking Procedure");
            System.out.println("6 - Add New Show");
            System.out.println("7 - Logout");
 
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    ShowAvailableMovies.display();
                    break;
                case 2:
                    TicketBooking.bookTicket();
                    break;
                case 3:
                    BookingConfirmation.confirmBooking();
                    break;
                case 4:
                    TicketCancellation.cancelTicket();
                    break;
                case 5:
                    TicketCancellationProcedure.createStoredProcedures();
                    break;
                case 6:
                    AddShow.addNewShow();
                    break;
                case 7:
                    System.out.println("Logging out...");
                    return;         //admin

                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }
    private static void showUserMenu() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("User Menu:");
            System.out.println("1 - View Movies");
            System.out.println("2 - Book Ticket");
            System.out.println("3 - Confirm Booking");
            System.out.println("4 - Cancel Ticket");
            System.out.println("5 - Logout");
 
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    ShowAvailableMovies.display();
                    break;
                case 2:
                    TicketBooking.bookTicket();
                    break;
                case 3:
                    BookingConfirmation.confirmBooking();
                    break;
                case 4:
                    TicketCancellation.cancelTicket();
                    break;
                case 5:
                    System.out.println("Logging out...");
                    return; // Exit the user menu
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }
}
 




























    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //public class MovieTicketBookingApp {
//
//	public static void main(String[] args) throws SQLException {
//		Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter your choice");
//        System.out.println("1 - Movies Running");
// 
//        int choice = scanner.nextInt();
//        switch (choice) {
//            case 1:
//                ShowAvailableMovies.display();
//                break;
//            case 2:
//                TicketBooking.bookTicket();
//                break;
//            case 3:
//            	BookingConfirmation.confirmBooking();
//            	break;
//            default:
//                System.out.println("Invalid choice");
//                break;
//        }
//        scanner.close();
//    }
//		// TODO Auto-generated method stub
//		//movie ticket booking app
//		//choice 1:->show available movies are there
//		 //          2->book ticket
//		 //          3->view booked ticket
////		System.out.println("Welcome to Movie Ticket Booking System");
////		ShowAvailableMovies obj=new ShowAvailableMovies();
////		obj.display();
//}

