import java.io.*;
import java.util.Scanner;

/**
 * Jack Robbins
 * CMP 233 Data Structures and Algorithms
 * Professor Sawh
 * This is the inventory class that uses StoreItem to keep track of an inventor
 */
public class Inventory {

    public AList<StoreItem> items;
    public String inventoryFile;


    /**
     * The constructor for the inventory class takes in a specifically formatted file, and converts it accordingly
     * to an AList containing StoreItems
     * @param inventoryFile the specifically formatted file to be read
     * @throws IOException if the file is not found
     */
    public Inventory(String inventoryFile) throws IOException {
        // Read the file into a string alist first, that way its easier to process
        this.inventoryFile = inventoryFile;
        try {
            File myFile = new File(inventoryFile);
            BufferedReader bf = new BufferedReader(new FileReader(myFile));
            AList<String> stringList = new AList<>(20);
            String line = bf.readLine();
            while (line != null) {
                try {
                    stringList.add(line);
                    line = bf.readLine();
                } catch (ListException E) {
                    System.out.println(E);
                }
            }
            bf.close();

            AList<StoreItem> items = new AList<>(20);

            for (int i = 1; i <= stringList.size(); i += 4) {
                try {
                    String upc = stringList.get(i);
                    String description = stringList.get(i + 1);
                    int availability = Integer.parseInt(stringList.get(i + 2));
                    double price = Double.parseDouble(stringList.get(i + 3));
                    items.add(new StoreItem(upc, description, availability, price));
                } catch (ListException E) {
                    System.out.println(E);
                }
            }

            this.items = items;
        } catch (FileNotFoundException F) {
            throw new IOException("File could not be located");
        }
    }


    /**
     * The run method that does almost all of the heavy lifitng for this program. Prompts the user for a numbered
     * command, then runs that numbered command. This repeats until the user enters 7, at which point the contents of
     * the items Alist are written to the inventory file
     */
    public void run() {
        displayMenu();
        Scanner in = new Scanner(System.in);
        int userInput = Integer.parseInt(in.nextLine());

        while (userInput > 0 && userInput <= 6) {
            if (userInput == 1) {
                System.out.println("The current inventory");
                System.out.println("_____________________");
                System.out.println(this.toString());
                System.out.println();
                displayMenu();
                userInput = Integer.parseInt(in.nextLine());
                System.out.println();

            } else if (userInput == 2) {
                System.out.print("Enter UPC Code: ");
                String upc = in.nextLine();
                System.out.print("Enter name: ");
                String name = in.nextLine();
                System.out.print("Enter price per item: ");
                double price = Double.parseDouble(in.nextLine());
                System.out.print("Enter quantity on hand: ");
                int amount = Integer.parseInt(in.nextLine());

                try {
                    this.items.add(new StoreItem(upc, name, amount, price));
                } catch (ListException L) {
                    System.out.println(L);
                }
                System.out.println();
                displayMenu();
                userInput = Integer.parseInt(in.nextLine());
                System.out.println();

            } else if (userInput == 3) {
                System.out.print("Position of insertion?: ");
                int position = Integer.parseInt(in.nextLine());
                if (position < 1 || position > this.items.size()) {
                    System.out.println("Cannot add. Position is bad.");
                } else {
                    System.out.print("Enter UPC Code: ");
                    String upc = in.nextLine();
                    System.out.print("Enter name: ");
                    String name = in.nextLine();
                    System.out.print("Enter price per item: ");
                    double price = Double.parseDouble(in.nextLine());
                    System.out.print("Enter quantity on hand: ");
                    int amount = Integer.parseInt(in.nextLine());

                    try {
                        this.items.add(new StoreItem(upc, name, amount, price), position);
                    } catch (ListException L) {
                        System.out.println(L);
                    }
                }
                System.out.println();
                displayMenu();
                userInput = Integer.parseInt(in.nextLine());
                System.out.println();

            } else if (userInput == 4) {
                System.out.print("Position of deletion?: ");
                int position = Integer.parseInt(in.nextLine());
                if (position < 1 || position > this.items.size()) {
                    System.out.println("Cannot delete. Position is bad.");
                } else {
                    try {
                        StoreItem deletedItem = this.items.remove(position);
                        System.out.println("Deletion successful. You deleted: ");
                        System.out.println(deletedItem.toString());
                    } catch (ListException L) {
                        System.out.println(L);
                    }
                }
                System.out.println();
                displayMenu();
                userInput = Integer.parseInt(in.nextLine());
                System.out.println();

            } else if (userInput == 5) {
                System.out.print("Which record to change?: ");
                int position = Integer.parseInt(in.nextLine());
                if (position < 1 || position > this.items.size()) {
                    System.out.println("Cannot change. Position is bad.");
                } else {
                    System.out.print("Which field to change (1 for UPC, 2 for name, 3 for price, 4 for quantity)? ");
                    int recordChange = Integer.parseInt(in.nextLine());
                    try {
                        if (recordChange == 1) {
                            System.out.print("Enter new UPC: ");
                            String newUPC = in.nextLine();
                            this.items.get(position).setUPC(newUPC);
                        } else if (recordChange == 2) {
                            System.out.print("Enter new name: ");
                            String newName = in.nextLine();
                            this.items.get(position).setDescription(newName);
                        } else if (recordChange == 3) {
                            System.out.print("Enter new price: ");
                            double newPrice = Double.parseDouble(in.nextLine());
                            this.items.get(position).setPrice(newPrice);
                        } else if (recordChange == 4) {
                            System.out.print("Enter new quantity: ");
                            int newQuantity = Integer.parseInt(in.nextLine());
                            this.items.get(position).setAvailability(newQuantity);
                        }
                        System.out.println("The changed record " + this.items.get(position).toString());
                    } catch (ListException L) {
                        System.out.println(L);
                    }
                }

                System.out.println();
                displayMenu();
                userInput = Integer.parseInt(in.nextLine());
                System.out.println();

            } else {
                System.out.print("What would you like to search for?: ");
                String searchTerm = in.nextLine();
                Boolean found = false;
                searchTerm = searchTerm.toLowerCase();

               for (int i = 1; i <= this.items.size(); i++){
                   try {
                       if (this.items.get(i).getDescription().toLowerCase().contains(searchTerm)) {
                           found = true;
                           System.out.println(this.items.get(i).toString());
                           System.out.println();
                       }
                   } catch(ListException L){
                           System.out.println(L);
                       }
               }
               if (!found){
                   System.out.println("No record found with search string " + searchTerm);
               }
                System.out.println("(End-of-search)");
                System.out.println();
                displayMenu();
                userInput = Integer.parseInt(in.nextLine());
                System.out.println();
            }
        }

        // Now perform the write to the inventory file
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.inventoryFile));
            for (int i = 1; i <= this.items.size(); i++){
                try {
                    writer.write(this.items.get(i).getUPC());
                    writer.newLine();
                    writer.write(this.items.get(i).getDescription());
                    writer.newLine();
                    writer.write(Integer.toString(this.items.get(i).getAvailability()));
                    writer.newLine();
                    writer.write(Double.toString(this.items.get(i).getPrice()));
                    writer.newLine();

                } catch (ListException L){
                    System.out.println(L);
                }
            }
            writer.close();
        } catch (IOException i){
            System.out.println(i);
        }
    }


    /**
     * A private helper method that displays the commands menu
     */
    private void displayMenu(){
        System.out.println("1. Display inventory items");
        System.out.println("2. Add inventory item");
        System.out.println("3. Insert inventory item");
        System.out.println("4. Delete inventory item");
        System.out.println("5. Change inventory item");
        System.out.println("6. Find inventory item");
        System.out.println("7. Exit");
        System.out.print("Enter an option: ");
    }


    /**
     * The inventory's to string. Programmatically iterates through the items list and calls the StoreItem to
     * string.
     * @return the formatted string
     */
    public String toString() {
        String returned = "";
        if (this.items.size() == 0){
            return "(Current inventory is empty)";
        }
        int j = 1;
        for (int i = 1; i <= this.items.size(); i++) {
            try {
                returned  = returned + j + ".   " + this.items.get(i).toString() + "\n";
                j += 1;
                {
                }
            } catch (ListException E) {
                System.out.println(E);
            }
        }
        return returned;
    }
}
