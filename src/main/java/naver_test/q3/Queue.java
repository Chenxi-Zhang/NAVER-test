package naver_test.q3;

/**
 * A simple implementation of Queue. Some ideas come from {@link java.util.Deque}
 */
public class Queue<E> {

    private static final int INIT_CAPACITY = 10;

    private transient Object[] elements;

    private int head;

    private int tail;

    public Queue (){
        elements = new Object[INIT_CAPACITY];
    }

    public Queue(int capacity) {
        elements = new Object[capacity];
    }

    public E pop() {
        int h = head;
        E result = (E) elements[h];
        if (result == null)
            return null;
        elements[h] = null;
        head = (h + 1) & (elements.length - 1);
        return result;
    }

    public void push(E elem) {
        if (elem == null)
            throw new NullPointerException();
        elements[head = (head - 1) & (elements.length - 1)] = elem;
        if (head == tail)
            doubleCapacity();
    }

    public E peek() {
        return (E) elements[head];
    }

    public boolean empty() {
        return head == tail;
    }

    private void doubleCapacity() {
        assert head == tail;
        int p = head;
        int n = elements.length;
        int r = n - p;
        int newCapacity = n << 1;
        if (newCapacity < 0)
            throw new IllegalStateException("Sorry, deque too big");
        Object[] a = new Object[newCapacity];
        System.arraycopy(elements, p, a, 0, r);
        System.arraycopy(elements, 0, a, r, p);
        elements = a;
        head = 0;
        tail = n;
    }
}
