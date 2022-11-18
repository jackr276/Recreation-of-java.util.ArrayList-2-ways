/**
 * Jack Robbins
 * CMP 233 Data Structures and Algorithms
 * Professor Sawh
 * This file contains the generic linked list implementation of a list
 * @param <t> The LList class is generic
 */
public class LList<t> implements ListInterface<t> {

    private Node<t> head;
    private Node<t> tail;
    private int count;


    /**
     * The LList constructor needs no parameters, simply sets head and tail to null and count to 0
     */
    public LList(){
        this.head = null;
        this.tail = null;
        this.count = 0;
    }


    /**
     * Getter for the head
     * @return the head
     */
    public Node<t> getHead() {
        return head;
    }

    /**
     * This method adds an item to the END of the list always
     * @param item the item to be added, can't be null
     * @throws ListException if memory is run out of or if item is null
     */
    public void add(t item) throws ListException {
        if (item == null) {
            throw new ListException("Cannot add. Item is null.");
        }

        try {
            Node<t> temp = new Node<>();
            temp.setData(item);
            temp.setNext(null);


            if (this.head == null){
                this.head = temp;
            } else {

                this.tail.setNext(temp);
            }

            this.tail = temp;

            count++;

        } catch(OutOfMemoryError e){
            throw new ListException("Cannot add. No more memory.");
        }
    }


    /**
     * The overloaded add method that is essentially an insert method
     * @param item the item to be inserted, cannot be null
     * @param position the position for it to be inserted into, must be valid
     * @throws ListException if position is bad, item is null, list is empty, or memory is filled
     */
    public void add(t item, int position) throws ListException {
        try {
            if (this.count == 0) {
                throw new ListException("Cannot insert. List is empty.");
            }

            if (item == null){
                throw new ListException("Cannot insert. Null items not acceptable.");
            }

            if (position < 1 || position > count){
                throw new ListException("Cannot insert. Position is bad.");
            }

            Node<t> temp = new Node<>();
            temp.setData(item);

            if (position == 1){
                temp.setNext(head);
                head = temp;
            } else {
                Node<t> before = head;
                Node<t> current = head;
                int i = 1;
                while ( i != position){
                    before = current;
                    current = current.getNext();
                    i++;
                }
                temp.setNext(current);
                before.setNext(temp);
            }

            count++;

        } catch (OutOfMemoryError E) {
            throw new ListException("Cannot insert. No more memory.");
        }

    }


    /**
     * The get method gets an item at a given valid position
     * @param position the position for the item to be gotten from
     * @return the gotten item
     * @throws ListException if list is empty, position is bad
     */
    public t get(int position) throws ListException{
        if (this.count == 0){
            throw new ListException("Error. Unable to get. List is empty");
        }

        if (position < 0 || position > count) {
            throw new ListException("Error. Unable to get. Bad position.");
        }

        Node<t> current = this.head;

        int i = 1;
        while (i < position){
            current = current.getNext();
            i++;
        }
        return current.getData();
    }


    /**
     * The set method sets the item at a given valid position
     * @param item the item to be set, can't be null
     * @param position the position of the new item, must be valid
     * @return the item that was replaced
     * @throws ListException if item is null, list is empty, position is bad
     */
    public t set(t item, int position) throws ListException {
        if (item == null){
            throw new ListException("Cannot set. Item is null");
        }
        if (this.count == 0){
            throw new ListException("Error. Unable to get. List is empty");
        }

        if (position < 0 || position > count) {
            throw new ListException("Error. Unable to get. Bad position.");
        }

        Node<t> current = this.head;
        t temp;
        int i = 1;
        while (i < position){
            current = current.getNext();
            i++;
        }
        temp = current.getData();
        current.setData(item);
        return temp;

    }


    /**
     * This method finds an item, if it exists, between two fixed start and end points
     * @param item the item to be found, can't be null
     * @param startPosition the starting position of the search
     * @param endPosition the ending position of the search
     * @return the item if found, -1 if not
     * @throws ListException if list is empty, positions are bad
     */
    public int find(t item, int startPosition, int endPosition) throws ListException {
        if (this.count == 0){
            throw new ListException("Error. Unable to find. List is empty.");
        }

        // Check to see if the start position exceeds the end position
        if (startPosition > endPosition){
            return -1;
        }

        // Now check to see if the start and end positions are valid
        if (startPosition < 1 || endPosition > this.count){
            throw new ListException("Error. Unable to find. Start and/or end position bad.");
        }

        Node<t> current = this.head;
        //First get the current to the start position
        int i = 1;
        while (i < startPosition){
            current = current.getNext();
            i++;
        }

        // Now current is where we want, iterate from start to end
        int k = startPosition;
        while (k <= endPosition){
            if (current.getData().equals(item)){
                return k;
            }
            else {
                current = current.getNext();
            }
            k++;
        }
        return -1;
    }

    /**
     * Converts the internal linked list into an object array
     * @return the object[]
     */
    public Object[] toArray(){
        Object[] returnedArr = new Object[this.count];
        Node<t> current = this.head;
        int i = 0;
        while (current != null){
            returnedArr[i] = current.getData();
            current = current.getNext();
            i++;
        }
        return returnedArr;
    }


    /**
     * The clear method re-initializes the entire linked list
     */
    public void clear() {
        this.head = null;
        this.tail = null;
        this.count = 0;
    }


    /**
     * The remove method removes an item at a valid position
     * @param position the position of the removed item, must be valid
     * @return the item that was removed
     * @throws ListException if list is empty, position is bad
     */
    public t remove(int position) throws ListException{
        Node<t> current = head;
        Node<t> before = null;
        t temp;

        if (this.count == 0){
            throw new ListException("Cannot delete. List is empty.");
        }

        if (position >= 1 && position <= count){

            // If this is the case there is only one node
            if (count == 1){
                temp = this.head.getData();
                this.head = null;
                this.tail = null;
                count--;
                return temp;


            } else{
                // trying to delete the head
                if (position == 1) {
                    temp = this.head.getData();
                    this.head = this.head.getNext();
                    count--;
                    return temp;

                } else {
                    int i = 1;

                    while (i != position){
                        before = current;
                        current = current.getNext();
                        i++;
                    }
                    // we've arrived at the node we want to delete
                    temp = current.getData();
                    // this deletes the node (gets garbage collected)
                    before.setNext(current.getNext());

                    // Check to see if we deleted the last node
                    if (before.getNext() == null){
                        this.tail = before;
                    }

                    count--;
                    return temp;
                }
            }
        } else {
            throw new ListException("Cannot delete. Position is bad.");
        }
    }


    /**
     * Simply gets the size
     * @return the count
     */
    public int size(){
        return this.count;
    }


    /**
     * Tells user if list is empty
     * @return true if empty
     */
    public boolean isEmpty(){
        return this.count == 0;
    }


    /**
     * Compares two lists for equality
     * @param list1 the LList to be compared with
     * @return true if equal, false if not
     */
    public boolean equals(LList<t> list1){
        if (list1.size() != this.count){
            return false;
        }
        Node<t> current = this.head;
        Node<t> current2 = list1.getHead();
        while (current != null) {
            if (!(current.getData().equals(current2.getData()))){
                return false;
            }
            current = current.getNext();
            current2 = current2.getNext();

        }
        return true;
    }


    /**
     * The toString method for LList
     * @return formatted string representation of the list
     */
    public String toString(){
        if (head == null){
            return "The list is empty.";
        }

        String returnedString = "";
        Node<t> temp;
        temp = head;
        while(temp != null){
            returnedString += temp.getData();
            returnedString += "\n";

            temp = temp.getNext();
        }

        return returnedString;
    }

}