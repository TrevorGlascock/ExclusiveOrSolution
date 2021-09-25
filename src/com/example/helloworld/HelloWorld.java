package com.example.helloworld;
import java.security.KeyStore;
import java.util.*;

public class HelloWorld {
    public static void main(String[] args) {
        List<Integer> listA = new ArrayList<Integer>(Arrays.asList(1, 3, 3, 6, 6, 7, 9));
        List<Integer> listB = new ArrayList<Integer>(Arrays.asList(2, 3, 5, 7));

        List<Integer> result = exclusiveOr3(listA,listB);
        System.out.println(result); // => [1,2,5,6,6,9]
    }

    public static List<Integer> exclusiveOr(List<Integer> listA, List<Integer> listB){
        List<Integer> result = new ArrayList<Integer>();
        for(int i=0;i<listA.size();i++){
            if(!listB.contains(listA.get(i))) {
                result.add(listA.get(i));
            }
        }

        for(int i=0;i<listB.size();i++) {
            if (!listA.contains(listB.get(i))) {
                result.add(listB.get(i));
            }
        }

        Collections.sort(result);
        return result;
    }

    public static List<Integer> exclusiveOr2(List<Integer> listA, List<Integer> listB){
        Map<Integer,Integer> xorHistogram = new HashMap<Integer,Integer>();

        for(int num:listA){
            if(!xorHistogram.containsKey(num))
                xorHistogram.put(num,1);
            else xorHistogram.replace(num, xorHistogram.get(num)+1);
        }

        for(int num : listB){
            if(!xorHistogram.containsKey(num))
                xorHistogram.put(num,1);
            else xorHistogram.replace(num, 0);
        }

        List<Integer> result = new ArrayList<>();

        for( int key : xorHistogram.keySet()){
            int value = xorHistogram.get(key);
            while(value>0) {
                result.add(key);
                value--;
            }
        }

        return result;
    }

    /*
    listA = [1, 3, 3, 6, 6, 7, 9];
    listB = [2, 3, 5, 7];
     */

    public static List<Integer> exclusiveOr3(List<Integer> listA, List<Integer> listB) {
        List<Integer> result = new ArrayList<>();

        Iterator<Integer> itA = listA.iterator();
        Iterator<Integer> itB = listB.iterator();

        int valA = itA.next();
        int valB = itB.next();

        while(itA.hasNext() || itB.hasNext()){
            // valA is smaller AND itA has not terminated
            if(valA<valB && itA.hasNext()) {
                result.add(valA);
                valA = itA.next();
            }

            // valB is smaller, AND itB has not terminated
            else if(valB<valA && itB.hasNext()){
                result.add(valB);
                valB = itB.next();
            }

            // valA and valB are equal, AND they both have not terminated
            else if(valA==valB && itA.hasNext() && itB.hasNext()){
                int temp = valA;
                while(valA==temp)
                    valA=itA.next();

                temp = valB;
                while(valB==temp)
                    valB=itB.next();
            }

            // itA or itB has reached the end
            else{
                while(itA.hasNext()) result.add(itA.next());
                while(itB.hasNext()) result.add(itB.next());
            }
        }


        return result;
    }

}