/**
 * Jack Robbins
 * CMP 233 Data Structures and Algorithms
 * Professor Sawh
 * Assignment 2 part b - the AList class
 *
 * The AList class is a generic implementation of the ArrayList, or a list that uses an array internally
 * @param <t> - the generic type parameter
 */
public class AList<t> implements ListInterface<t> {
    private t[] internalArray;
    private int count;

    /**
     * The constructor for the AList class
     * @param length - the user inputted initial length of the array
     */
    public AList(int length) {
        // Make an object array and cast it to a t[] address
        this.internalArray = (t[])new Object[length];
        this.count = 0;
    }


    /**
     * The add method adds an item to the very end of the list
     * @param item - the item that the user wishes to add to the end of the AList
     * @throws ListException - throws a list exception if item is null
     */
    public void add(t item) throws ListException {
        // Check to see if the user is trying to add a null item
        if (item == null) {
            throw new ListException("Error. Unable to add. Cannot add null entries.");
        }
        // Check to see if the array is full, if it is, call the resize method
        if (this.count == internalArray.length) {
            resize();
        }
        // Add the item at the very end of the array
        this.internalArray[count] = item;
        // Increment count
        count++;
    }


    /**
     * This overloaded add method acts as an insert method, inserts the item at the given position and shifts the list
     * elements accordingly
     * @param item - the item to be added
     * @param position - the position where it is added at
     * @throws ListException - throws a list exception if item is null, position is bad, or if the list is empty
     */
    public void add(t item, int position) throws ListException {
        // Adjust the index so that it is correct upon use
        int index =  position - 1;

        // Ensure that the user is not trying to add a null item
        if (item == null) {
            throw new ListException("Error. Unable to insert. Attempt to insert null object.");
        }

        // Ensure that the user is not trying to insert in an empty list
        if (this.count == 0){
            throw new ListException("Error. Unable to insert. List is empty.");
        }

        // Ensure that the user is not trying to insert at a 0 or negative position. Position must be at least one.
        // Additionally, ensure that the position is not greater than the count
        if (position < 1 || position > this.count){
            throw new ListException("Error. Unable to insert. Bad position");
        }

        // Ensure that the array is big enough for the insertion, if it isn't, call resize()
        if (this.count == internalArray.length){
            resize();
        }

        // Shift everything right by 1 using a right shift
        for (int i = count; i > index; i--){
            this.internalArray[i] = this.internalArray[i-1];
        }

        // put the inserted item in its spot
        this.internalArray[index] = item;
        // increase the count
        count += 1;
    }


    /**
     * The get method returns an item at the given position. This method does not alter the list.
     * @param position - the position of the item that will be gotten
     * @return - returns the item that was gotten
     * @throws ListException - throws a list exception if the list is empty, or if the position is bad
     */
    public t get(int position) throws ListException {
        // Check to see if the list is empty
        if (this.count == 0){
            throw new ListException("Error. Unable to get. List is empty.");
        }

        // Check to see if the position inputted is good
        if (position < 1 || position > count){
            throw new ListException("Error. Unable to get. Bad position.");
        }

        // After all checks are done, return the item at the position-1 index
        return this.internalArray[position-1];
    }


    /**
     * The set method sets the element at the given position to the given item. It replaces the original item at that
     * position with the new one and returns the original item.
     * @param item - the item to be set
     * @param position - the position where the item will be set
     * @return - returns the item that was originally at the position
     * @throws ListException - throws a list exception if item is null, list is empty, or position is bad
     */
    public t set(t item, int position) throws ListException {
        // Check to see if the user is trying to put a null item in the list
        if (item == null){
            throw new ListException("Error. Unable to replace. Replacement cannot be null.");
        }

        // Check to see if the list is empty
        if (this.count == 0) {
            throw new ListException("Error. Unable to replace. List is empty");
        }

        // Check to see if the position is valid
        if (position < 1 || position > count){
            throw new ListException("Error. Unable to replace. Bad position.");
        }

        // Get the replaced item out first so we can return it
        t replacedItem = this.internalArray[position-1];

        // Now replace the item
        this.internalArray[position-1] = item;

        // Return the replaced item
        return replacedItem;

    }


    /**
     * Searches the list for the given item between the two given positions
     * @param item - the item to be found
     * @param startPosition - the starting position of the search
     * @param endPosition - the ending position of the search
     * @return - returns the position of the item if found, -1 if not found
     * @throws ListException - throws list exception is list is empty, or if the positions given are bad
     */
    public int find(t item, int startPosition, int endPosition) throws ListException{
        // Check to see if the list is empty
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

        // Change these to indices for ease of use
        int startIndex = startPosition - 1;
        int endIndex = endPosition - 1;

        // Use a for loop to search through the list
        for (int i = startIndex; i <= endIndex; i++){
            if (this.internalArray[i].equals(item)){
                return i + 1;
            }
        }

        // If the for loop finishes, then the item was not found, so return -1
        return -1;
    }


    /**
     * A helper method that gives the size, which also happens to be the same as the count
     * @return - the count(size)
     */
    public int size(){
        return this.count;
    }


    /**
     * Removes the item at the given position, shifts the remaining elements accordingly, and returns the removed
     * item
     * @param position - the position to be removed
     * @return - returns the removed item
     * @throws ListException - throws a list exception if the list is empty, or if the positions are bad
     */
    public t remove(int position) throws ListException {
        // Adjust this to an index for ease of use later
        int index = position -1;

        // Cannot delete from an empty list
        if (count == 0){
            throw new ListException("Error. Unable to remove. List is empty");
        }

        // Cannot delete if position is bad
        if (position < 1 || position > count){
            throw new ListException("Error. Unable to remove. Bad position.");
        }

        // Fetch the removed item to be returned later
        t returnedItem = this.internalArray[index];

        // Now we have to left shift
        for (int i = index; i < count-1; i++){
            this.internalArray[i] = this.internalArray[i+1];
        }

        // Decrement count and return the removed item
        count--;

        return returnedItem;
    }


    /**
     * Clears the list by setting the internal array to a new t[] and setting the count back to 0
     */
    public void clear(){
        int length = this.internalArray.length;
        this.internalArray = (t[])new Object[length];
        this.count = 0;
    }


    /**
     * Returns the internal array of the list, copied into an object[]
     * @return - the copied object[]
     */
    public Object[] toArray(){
        Object[] temp = new Object[this.internalArray.length];
        for (int i= 0; i < internalArray.length; i++) {
            temp[i] = internalArray[i];
        }

        return temp;

    }


    /**
     * A private helper function that is used when the add methods try to add to a full array. Resizes the array by
     * copying it to a new array with 100 more size
     * @throws ListException - throws a list exception is the program runs out of memory
     */
    private void resize() throws ListException{
        try {
            t[] resizedArr = (t[])new Object[internalArray.length + 100];
            for (int k = 0; k < internalArray.length; k++) {
                resizedArr[k] = internalArray[k];
            }

            this.internalArray = resizedArr;

        } catch (OutOfMemoryError E) {
            throw new ListException("Out of memory, cannot add to array");
        }
    }


    /**
     * A simple method that returns true if the list is empty
     * @return - true if empty, false if not
     */
    public boolean isEmpty(){
        return this.count == 0;
    }

    /**
     * A simple method that returns the count
     * @return one less than the count, which is the current capacity of the array
     */
    public int getCapacity(){
        return this.count-1;
    }

    /**
     * The equals method that compares this Alist with another list that is given as input
     * @param list1 the list to be compared to
     * @return return true if the lists are equal, false if they are not
     */
    public boolean equals (AList<t> list1){
        if (this.count != list1.size()){
            return false;
        } else {
            for (int i = 0; i < this.count; i++){
                try {
                    if (!(this.internalArray[i].equals(list1.get(i+1)))) {
                        return false;
                    }
                } catch (ListException l){
                    System.out.println(l);
                }

            }
            return true;
        }
    }

    /**
     * The toString method returns a string representation of the list
     * @return - A string representation of the list
     */
    public String toString() {
        // If the list is empty, return this message
        if (this.count == 0){
            return "The list is empty. \n";
        }

        String listString = "";

        // For each item in the list, add the item with a newline character to the string
        for (int i = 0; i<count; i++) {
            listString = listString + this.internalArray[i] + "\n";
        }

        return listString;
    }
}
