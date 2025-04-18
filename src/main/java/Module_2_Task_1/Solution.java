package Module_2_Task_1;


import java.util.HashSet;

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

