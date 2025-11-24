import java.util.*;

class Ticket {
    public String passengerName;
    public String trainName;
    public int seatNumber;
    public String source;
    public String destination;
    public String dateOfJourney;

    public Ticket(String passengerName, String trainName, int seatNumber, 
                String source, String destination, String dateOfJourney) {
        this.passengerName = passengerName;
        this.trainName = trainName;
        this.seatNumber = seatNumber;
        this.source = source;
        this.destination = destination;
        this.dateOfJourney = dateOfJourney;
    }

    public void displayTicket() {
        System.out.println("\nTicket Details: ");
        System.out.println("Passenger Name: " + passengerName);
        System.out.println("Train Name: " + trainName);
        System.out.println("Seat Number: " + seatNumber);
        System.out.println("Source: " + source);
        System.out.println("Destination: " + destination);
        System.out.println("Date of Journey: " + dateOfJourney);
    }
}

class RailwayBookingSystem {
    private ArrayList<Ticket> tickets;
    private ArrayList<String> availableTrains;
    private ArrayList<String> stations;

    public RailwayBookingSystem() {
        tickets = new ArrayList<>();
        availableTrains = new ArrayList<>();
        stations = new ArrayList<>();

        availableTrains.add("Darjeeling Limited");
        availableTrains.add("Vande Bharat Express");
        availableTrains.add("Royal Rajasthan On Wheels");
        
        stations.add("New Delhi Junction");
        stations.add("Jaipur Station");
        stations.add("Darjeeling Railway Station");
        stations.add("Jammu Tawi Station");
    }

    public void displayTrains() {
        System.out.println("Available Trains: ");
        for (int i = 0; i < availableTrains.size(); i++) {
            System.out.println((i + 1) + ". " + availableTrains.get(i));
        }
    }

    public void displayStations() {
        System.out.println("Available Stations: ");
        for (int i = 0; i < stations.size(); i++) {
            System.out.println((i + 1) + ". " + stations.get(i));
        }
    }

    private boolean isValidStation(int station) {
        return station > 0 && station <= stations.size();
    }

    private boolean isValidTrain(int train) {
        return train > 0 && train <= availableTrains.size();
    }

    public void bookTicket() {
        Scanner scanner = new Scanner(System.in);
        String passengerName, source, destination, dateOfJourney;
        int trainChoice, sourceStation, destinationStation, seatNumber;

        System.out.print("\nEnter passenger name: ");
        passengerName = scanner.nextLine();
        
        displayStations();
        System.out.print("\nSelect source station: ");
        sourceStation = scanner.nextInt();

        if (!isValidStation(sourceStation)) {
            System.out.println("Invalid station choice!");
            return;
        }
        source = stations.get(sourceStation - 1);

        displayStations();
        System.out.print("\nSelect destination station: ");
        destinationStation = scanner.nextInt();
        
        if (!isValidStation(destinationStation) || sourceStation == destinationStation) {
            System.out.println("Invalid destination choice!");
            return;
        }
        destination = stations.get(destinationStation - 1);
        
        displayTrains();
        System.out.print("\nSelect train: ");
        trainChoice = scanner.nextInt();

        if (!isValidTrain(trainChoice)) {
            System.out.println("Invalid train choice!");
            return;
        }

        System.out.print("\nEnter date of journey (YYYY-MM-DD): ");
        dateOfJourney = scanner.next();

        System.out.print("\nEnter seat number: ");
        seatNumber = scanner.nextInt();
        
        Ticket newTicket = new Ticket(passengerName, availableTrains.get(trainChoice - 1), 
                                    seatNumber, source, destination, dateOfJourney);
        tickets.add(newTicket);
        System.out.println("\nTicket booked successfully!");
        newTicket.displayTicket();
    }

    public void showAllTickets() {
        if (tickets.isEmpty()) {
            System.out.println("\nNo tickets booked yet!");
        } else {
            for (int i = 0; i < tickets.size(); i++) {
                tickets.get(i).displayTicket();
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("🚂 Welcome To RailEase");
        System.out.println("Book Your Ticket with Ease!!");
        
        RailwayBookingSystem system = new RailwayBookingSystem();
        int choice;
        
        do {
            System.out.println("\nRailway Ticket Booking System");
            System.out.println("1️⃣ Book a ticket");
            System.out.println("2️⃣ Show all booked tickets");
            System.out.println("3️⃣ Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    system.bookTicket();
                    break;
                case 2:
                    system.showAllTickets();
                    break;
                case 3:
                    System.out.println("👋 Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("❌ Invalid choice! Please try again.");
            }
        } while (choice != 3);
        
        scanner.close();
    }
}