package Module_2_Task_1;


public class Solution {

    public static void main(String[] args) {

        System.out.println("___________________MY_HASH_SET_________________________________");
        MyHashSet <String> mySet = new MyHashSet<>();
        mySet.add("First");
        mySet.add("Second");
        mySet.add("Third");
        mySet.add("Fourth");
        mySet.add("Fifth");
        System.out.println(mySet);

        System.out.println("mySet contains \"Fourth\" -- " +mySet.contains("Fourth"));
        System.out.println("method remove");
        mySet.remove("Fourth");
        System.out.print("mySet contains \"Fourth\" -- " +mySet.contains("Fourth")+" ");
        System.out.println(mySet);

        for (int i =0 ; i<= 20; i++){
            mySet.add(String.valueOf(i));
        }
        System.out.println(mySet);
        System.out.println(mySet.size());
        mySet.clear();
        System.out.println(mySet);

        System.out.println("___________________MY_ARRAY_LIST_______________________________");

        MyArrayList<String> myArrayList = new MyArrayList<>();
        for (int i = 0; i <= 10; i++) {
            myArrayList.add(String.valueOf(i));
        }
        System.out.println(myArrayList);

        System.out.println("method add 99, add 1000 to index 1");
        myArrayList.add(String.valueOf(99));
        myArrayList.add(1, String.valueOf(1000));
        System.out.println(myArrayList);

        System.out.println("method addAll");
        String[] s = new String[]{"hello", "world", "99"};
        String[] s1 = new String[]{"Start","Jony"};
        myArrayList.addAll(s);
        myArrayList.addAll(0, s1);
        System.out.println(myArrayList);

        System.out.println("method get(1), remove(0)");
        System.out.println(myArrayList.get(1));
        System.out.println(myArrayList.remove(0));
        System.out.println(myArrayList);





    }
}

 class MyHashSet<T> {
    private static final int DEFAULT_CAPACITY = 16;
    private MyArrayList<T>[] buckets;
    private int size;

    public MyHashSet() {
        buckets = new MyArrayList[DEFAULT_CAPACITY];
        for (int i = 0; i < DEFAULT_CAPACITY; i++) {
            buckets[i] = new MyArrayList<>();
        }
        size = 0;
    }

    public void add(T element) {
        if (element == null) {
            throw new NullPointerException("Element cannot be null");
        }

        if (size >= buckets.length * 0.75) {
            resize();
        }

        int bucketIndex = getBucketIndex(element);
        MyArrayList<T> bucket = buckets[bucketIndex];

        if (!bucket.contains(element)) {
            bucket.add(element);
            size++;
        }
    }

    public void remove(T element) {
        if (element == null) {
            throw new NullPointerException("Element cannot be null");
        }

        int bucketIndex = getBucketIndex(element);
        MyArrayList<T> bucket = buckets[bucketIndex];

        if (bucket.remove(element)) {
            size--;
        }
    }

    public boolean contains(T element) {
        if (element == null || size == 0) {
            return false;
        }
        int bucketIndex = getBucketIndex(element);
        return buckets[bucketIndex].contains(element);
    }

    public int size() {
        return size;
    }

    public void clear() {
        for (MyArrayList<T> bucket : buckets) {
            bucket.clear();
        }
        size = 0;
    }

    private int getBucketIndex(T element) {
        int hashCode = Math.abs(element.hashCode());
        return hashCode % buckets.length;
    }

    private void resize() {
        MyArrayList<T>[] oldBuckets = buckets;
        int newCapacity = oldBuckets.length * 2;
        buckets = new MyArrayList[newCapacity];

        for (int i = 0; i < newCapacity; i++) {
            buckets[i] = new MyArrayList<>();
        }

        size = 0;

        for (int i = 0; i < oldBuckets.length; i++) {
            MyArrayList<T> oldBucket = oldBuckets[i];
            for (int j = 0; j < oldBucket.size(); j++) {
                T element = oldBucket.get(j);
                add(element);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        boolean first = true;

        for (int i = 0; i < buckets.length; i++) {
            MyArrayList<T> bucket = buckets[i];
            for (int j = 0; j < bucket.size(); j++) {
                T element = bucket.get(j);
                if (!first) {
                    sb.append(", ");
                }
                sb.append(element);
                first = false;
            }
        }
        sb.append("]");
        return sb.toString();
    }
}

 class MyArrayList<T> {

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


