package Module_2_Task_1;


public class Solution {

    public static void main(String[] args) {

        MyArrayList<String> myArrayList = new MyArrayList<>();
        for (int i = 0; i <= 10; i++) {
            myArrayList.add(String.valueOf(Math.round(Math.random() * i * 10)));
        }
        System.out.println(myArrayList);
        System.out.println("method add ");
        myArrayList.add(String.valueOf(99));
        myArrayList.add(1, String.valueOf(1000));

        System.out.println(myArrayList);
        System.out.println("method addAll");
        String[] s = new String[]{"hello", "world", "99"};
        String[] s1 = new String[]{"Start"};
        myArrayList.addAll(s);
        myArrayList.addAll(0, s1);
        System.out.println(myArrayList);
        System.out.println("method get(1), remove(0)");
        System.out.println(myArrayList.get(1));
        System.out.println(myArrayList.remove(0));
        System.out.println(myArrayList);
        System.out.println(myArrayList.contains("39"));
    }
}

