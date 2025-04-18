package Module_2_Task_1;

public class MyArrayList<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final float GROWTH_FACTOR = 1.5f;
    private Object[] array;
    private int size;

    public MyArrayList() {
        array = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public MyArrayList(int initialCapacity) {
        try {
            array = new Object[initialCapacity];
            size = 0;
        } catch (Exception e) {
            System.out.println("Illegal initial capacity");
        }
    }


    public int size() {
        return size;
    }

    public void add(T element) {

        ensureCapacity(size + 1);

        array[size] = element;
        size++;

    }

    public void add(int index, T element) {
        try {
            ensureCapacity(size + 1);
            System.arraycopy(array, index, array, index + 1, size - index);
            array[index] = element;
            size++;
        } catch (Exception e) {
            System.out.println("Illegal index");
        }
    }

    public void addAll(T[] elements) {
        ensureCapacity(size + elements.length);
        System.arraycopy(elements, 0, array, size, elements.length);
        size += elements.length;
    }


    public void addAll(int index, T[] elements) {
        try {
            ensureCapacity(size + elements.length);
            System.arraycopy(array, index, array, index + elements.length, size - index);
            System.arraycopy(elements, 0, array, index, elements.length);
            size += elements.length;
        } catch (Exception e) {
            System.out.println("Illegal index");
        }
    }

    private void ensureCapacity(int minCapacity) {

        if (minCapacity > array.length) {
            int newCapacity = Math.max((int) (array.length * GROWTH_FACTOR), minCapacity);
            Object[] newArray = new Object[newCapacity];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;

        }

    }


    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (T) array[index];
    }


    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        T oldValue = (T) array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
        return oldValue;
    }

    public boolean remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == array[i]) {
                remove(i);
            }
        }
        return true;
    }



    public boolean contains(T element) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    public void clear() {
       array = new Object[DEFAULT_CAPACITY];
        size = 0;

    }


    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[");
        for (int i = 0; i < size; i++) {
            str.append(array[i]);
            if (i == size - 1) {
                str.append("]");
            } else {
                str.append(", ");
            }
        }

        return str.toString();
    }

}