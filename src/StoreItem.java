/**
 * Jack Robbins
 * CMP 233 Data Structures and Algorithms
 * Professor Sawh
 * The StoreItem class that is used as the building blocks for the inventory class
 */
public class StoreItem {

    private String UPC;
    private String description;
    private int availability;
    private double price;


    /**
     * The constructor for the StoreItem
     * @param UPC the upc
     * @param description description
     * @param availability availability
     * @param price price
     */
    public StoreItem(String UPC, String description, int availability, double price) {
        this.UPC = UPC;
        this.description = description;
        this.availability = availability;
        this.price = price;
    }


    /**
     * The getter for the upc
     * @return the upc
     */
    public String getUPC(){return this.UPC;}


    /**
     * The setter for the upc
     * @param newUPC the new upc
     */
    public void setUPC(String newUPC){
        this.UPC = newUPC;
    }


    /**
     * The getter for the description
     * @return the description
     */
    public String getDescription(){return this.description;}


    /**
     * The setter for the description
     * @param description the description to be set
     */
    public void setDescription(String description){
        this.description = description;

    }


    /**
     * The getter for the availability
     * @return the availability
     */
    public int getAvailability(){return this.availability;}


    /**
     * The setter for the availability
     * @param newAvailability the new availability
     */
    public void setAvailability(int newAvailability){
        this.availability = newAvailability;
    }


    /**
     * The getter for the price
     * @return the price
     */
    public double getPrice(){return this.price;}


    /**
     * The setter for the price
     * @param newPrice the new price
     */
    public void setPrice(double newPrice){
        this.price = newPrice;
    }


    /**
     * The toString for the StoreItem
     * @return a formatted string of all the item's attributes
     */
    public String toString(){
        return "Item: " + this.description + ", UPC: " + this.UPC +
                ", Price per item: " + this.price + ", Quantity: " + this.availability;
    }
}
