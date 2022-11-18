/**
 * Jack Robbins
 * CMP 233
 * Professor Sawh
 * This is the ListException for the AList class
 */
public class ListException extends Exception{
    private String m;

    public ListException(String message){
        this.m = message;
    }

    public String toString(){
        return this.m;
    }
}
