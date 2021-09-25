package com.example.helloworld;
import java.security.KeyStore;
import java.util.*;

public class HelloWorld {
    public static void main(String[] args) {
        List<Integer> listA = new ArrayList<Integer>(Arrays.asList(1, 3, 3, 6, 6, 7, 9));
        List<Integer> listB = new ArrayList<Integer>(Arrays.asList(2, 3, 5, 7));

        List<Integer> result = exclusiveOr2(listA,listB);
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

}