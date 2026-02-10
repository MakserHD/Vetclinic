import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        PetDAO dao = new PetDAO();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== VET CLINIC MENU ===");
            System.out.println("1. Add Dog");
            System.out.println("2. Add Cat");
            System.out.println("3. View All Pets (From DB)");
            System.out.println("4. Delete Pet");
            System.out.println("0. Exit");
            System.out.print("Choice: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Please enter a number!");
                scanner.next();
                continue;
            }
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("Name: "); String n = scanner.nextLine();
                System.out.print("Age: "); int a = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Breed: "); String b = scanner.nextLine();
                dao.addPet(new Dog(n, a, b));
            }
            else if (choice == 2) {
                System.out.print("Name: "); String n = scanner.nextLine();
                System.out.print("Age: "); int a = scanner.nextInt();
                dao.addPet(new Cat(n, a, true));
            }
            else if (choice == 3) {
                List<Pet> list = dao.getAll();
                System.out.println("\n--- LIST OF PETS ---");
                for (Pet p : list) {
                    System.out.println(p);
                }
            }
            else if (choice == 4) {
                System.out.print("Enter ID to delete: ");
                int id = scanner.nextInt();
                dao.delete(id);
            }
            else if (choice == 0) {
                break;
            }
        }
    }
}