package com.example.helloworld;
import java.security.KeyStore;
import java.util.*;

public class HelloWorld {
    public static void main(String[] args) {
        List<Integer> listA = new ArrayList<Integer>(Arrays.asList(1, 3, 3, 6, 6, 7, 9));
        List<Integer> listB = new ArrayList<Integer>(Arrays.asList(2, 3, 5, 7));

        List<Integer> result = exclusiveOrFinal(listA,listB);
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

    /**
     * This is the final version of the exclusiveOr function, refactored for maximum readability and efficiency
     * @param listA First provided List
     * @param listB Second provided List
     * @return List Returns a List that contains all the elements of listA and listB, except for the elements they share
     */

    public static List<Integer> exclusiveOrFinal(List<Integer> listA, List<Integer> listB) {
        List<Integer> result = new ArrayList<>();

        Iterator<Integer> itA = listA.iterator();
        Iterator<Integer> itB = listB.iterator();

        int valA = itA.next();
        int valB = itB.next();

        while(itA.hasNext() || itB.hasNext()){
            // valA is smaller AND itA has not terminated
            if(valA<valB && itA.hasNext())
                valA = addValueToList(itA, valA, result);

            // valB is smaller, AND itB has not terminated
            else if(valB<valA && itB.hasNext())
                valB = addValueToList(itB, valB, result);


            // valA and valB are equal, AND they both have not terminated
            else if(valA==valB && itA.hasNext() && itB.hasNext()){
                valA = iterateUntilValueChanges(itA,valA);
                valB = iterateUntilValueChanges(itB,valB);
            }

            // itA or itB has reached the end
            else{
                addRemainingElements(itA,result);
                addRemainingElements(itB,result);
            }
        }

        return result;
    }

    /**
     * This helper function returns the next node of an iterator after adding a value to a List.
     * @param it iterator to traverse to the next node
     * @param value  stores the current value of iterator before traversal
     * @param insertTo is the list that we are adding the value to
     * @return int Returns the value of the next node that Iterator points to
     */
    private static int addValueToList(Iterator<Integer> it, int value, List insertTo){
        insertTo.add(value);
        return it.next();
    }

    /**
     * This helper function while traverse the nodes of a given Iterator until the value at the current node no longer matches the starting value
     * @param it iterator to traverse to the next node
     * @param startingValue the value of the node that we start at
     * @param currentValue the value of the current node we are on
     * @return int Returns the first value that doesn't match the startingValue
     */
    private static int iterateUntilValueChanges(Iterator<Integer> it,int startingValue, int currentValue){
        if(currentValue==startingValue)
            return iterateUntilValueChanges(it,startingValue,it.next());
        return currentValue;
    }

    /**
     * This overloaded version will take an iterator and a value that is both the startingValue and currentValue
     * @param it iterator to traverse to the next node
     * @param value is both the startingValue and the currentValue
     * @return returns the original function with the new parameters
     */
    private static int iterateUntilValueChanges(Iterator<Integer> it, int value){
        return iterateUntilValueChanges(it,value,value);
    }

    /**
     * This function adds all remaining nodes of the Iterator into the list
     * @param it iterator to traverse to all remaining elements
     * @param list the list that each element will be added to
     * @return void -- No need to return
     */
    private static void addRemainingElements(Iterator<Integer> it, List<Integer> list){
        while(it.hasNext()) list.add(it.next());
    }

}