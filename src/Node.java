/**
 * Jack Robbins
 * CMP-233 Data Structures and Algorithms
 * Professor Sawh
 * This class is the Node that is used in LList
 * @param <t> node is generic
 */
public class Node<t> {
    // The generic data of the Node
    private t data;
    // The next node
    private Node<t> next;

    /**
     * Constructor initializes data and next to null
     */
    public Node(){
        this.data = null;
        this.next = null;

    }


    /**
     * Setter for Node
     * @param newData the new Data
     */
    public void setData(t newData){
        this.data = newData;
    }

    /**
     * The getter for Node data
     * @return the data
     */
    public t getData(){
        return this.data;
    }


    /**
     * The setter for the next variable
     * @param newNext the new next node
     */
    public void setNext(Node<t> newNext){
        this.next = newNext;
    }


    /**
     * The getter for the next node
     * @return the next node
     */
    public Node<t> getNext(){
        return this.next;
    }


}
