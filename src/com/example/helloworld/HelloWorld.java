package com.example.helloworld;
import java.util.*;

// Author: Trevor Glascock

public class HelloWorld {
    public static void main(String[] args) {
        List<Integer> listA = new ArrayList<Integer>(Arrays.asList(1, 3, 3, 6, 6, 7, 9));
        List<Integer> listB = new ArrayList<Integer>(Arrays.asList(2, 3, 5, 7));

        List<Integer> result = exclusiveOrFinal(listA,listB);
        System.out.println(result); // => [1,2,5,6,6,9]
    }

    /**
     * This is the final version of the exclusiveOr function, refactored for improved readability and efficiency.
     * It will create an Iterator/Pointer for each list, then use a recursive function to iterate through all the elements
     * of both lists until the final result is populated.
     * @param listA First provided List
     * @param listB Second provided List
     * @return List Returns the list that results from dualIteratingExclusiveLists()
     */
    public static List<Integer> exclusiveOrFinal(List<Integer> listA, List<Integer> listB) {
        Iterator<Integer> itA = listA.iterator();
        Iterator<Integer> itB = listB.iterator();
        return dualIteratingExclusiveList(itA, itB, itA.next(), itB.next(), new ArrayList<Integer>());
    }

    /**
     * This function provides the core logic for the whole program.
     * Using two iterators to traverse two different Lists, this function will return all elements exclusive to a List
     * @param itA the Iterator for listA
     * @param itB the Iterator for listB
     * @param valA Initially the 1st value of listA, it will store the value of the current node in listA
     * @param valB Initially the 1st value of listB, it will store the value of the current node in listB
     * @param result Initially an empty ArrayList that will be populated with each exclusive element, then returned
     * @return Returns the populated result ArrayList
     */
    private static List<Integer> dualIteratingExclusiveList(Iterator<Integer> itA, Iterator<Integer> itB, int valA, int valB, List<Integer> result){
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
        // One iterator has reached its final node, so we add the remaining elements of the other into the result
        else{
            addRemainingElements(itA,result);
            addRemainingElements(itB,result);
        }

        // if itA or itB have any remaining nodes to traverse, keep iterating until otherwise
        if(itA.hasNext()||itB.hasNext()) return dualIteratingExclusiveList(itA,itB,valA,valB,result);

        // If itA and itB have both finished iteration, result is fully populated
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
     * This helper function while traverse the nodes of an Iterator until the value at the current node no longer matches the starting value
     * @param it iterator to traverse to the next node
     * @param startingValue the value of the node that we start at
     * @param currentValue the value of the current node we are on
     * @return int Returns the first value that doesn't match the startingValue
     */
    private static int iterateUntilValueChanges(Iterator<Integer> it,int startingValue, int currentValue){
        if(currentValue==startingValue && it.hasNext())
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
     * @return void -- The return only exists as a base case for the recursive function
     */
    private static void addRemainingElements(Iterator<Integer> it, List<Integer> list){
        if(!it.hasNext()) return;
        list.add(it.next());
        addRemainingElements(it, list);
    }



    /************************************************************************************************
     * Below are the previous solutions for this problem, Only preserved for demonstration purposes *
     ***********************************************************************************************/

    /****************************
     *** Brute Force Approach ***
     ***************************/
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

    /******************************
     ****** HashMap Approach ******
     *****************************/
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

    /******************************
     *** Dual Iterator Approach ***
     *****************************/
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