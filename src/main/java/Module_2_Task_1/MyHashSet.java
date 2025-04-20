package Module_2_Task_1;


public class MyHashSet<T> {
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