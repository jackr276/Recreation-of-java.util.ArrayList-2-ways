/**
 * Jack Robbins
 * CMP 233
 * Professor Sawh
 * This is the ListInterface for the AList class
 * @param <t> - the generic type parameter
 */
public interface ListInterface<t> {
    public void add(t item) throws ListException;

    public void add(t item, int position) throws ListException;

    public t get(int position) throws ListException;

    public t set(t item, int position) throws ListException;

    public int find(t item, int startPosition, int endPosition) throws ListException;

    public int size();
    public t remove(int position) throws ListException;

    public String toString();

    public void clear();

    public Object[] toArray();

    public boolean isEmpty();


}
