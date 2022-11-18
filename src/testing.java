import java.io.IOException;

public class testing {
    public static void main(String[] args){
        try {
            Inventory myInventory = new Inventory("/home/jackr/IdeaProjects/Lab 3/src/inventoryFile.txt");
            myInventory.run();
        } catch (IOException E){
            System.out.println(E);
        }
    }
}
