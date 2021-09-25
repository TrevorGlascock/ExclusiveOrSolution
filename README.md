# Problem:
#### Given the following Lists:
  1.  **[ 1 , 3 , 3 , 6 , 6 , 7 , 9 ]**
  2.  **[ 2 , 3 , 5 , 7 ]**
#### Write a function that returns this List:
* **[ 1, 2 , 5 , 6 , 6 , 9 ]**

---


# **Solutions:**

# [Final Refactored Solution (exclusiveOrFinal)](https://github.com/TrevorGlascock/ExclusiveOrSolution/blob/main/src/com/example/helloworld/HelloWorld.java#L16)

### This is algorithmically identical to Iterator solution, but refactored for readability.
* All previous functionality remains intact
* All functions are properly documented
* Reduced code complexity through use of helper functions and recursion
* Code is made DRYer by use of encapsulation
* Still has linear Time complexity
* Still has constant Space complexity
* Refer to the documentation of each helper function for a breakdown of their utility:
  * **[dualIteratingExclusiveList](https://github.com/TrevorGlascock/ExclusiveOrSolution/blob/main/src/com/example/helloworld/HelloWorld.java#L32-L70)** -- Holds the core recursive logic that iterates through both Lists simultaneously and performs all the necessary conditional logic at each iteration until both Iterators are fully traversed.
    * **[addValueToList](https://github.com/TrevorGlascock/ExclusiveOrSolution/blob/main/src/com/example/helloworld/HelloWorld.java#L72-L83)** -- Adds the current value to the result List, then increases its respective iterator.
    * **[iterateUntilValueChanges](https://github.com/TrevorGlascock/ExclusiveOrSolution/blob/main/src/com/example/helloworld/HelloWorld.java#L84-L109)** -- Whenever the value of both Iterators are the same, we must NOT add it the result, but instead iterate through each of the Lists until we find a value that is different from the current value. 
    * **[addRemainingElements](https://github.com/TrevorGlascock/ExclusiveOrSolution/blob/main/src/com/example/helloworld/HelloWorld.java#L110-L122)** -- After one of the Lists becomes fully traversed, we must add all the remaining elements of the other List to the result List.

---

# [Pointer/Iterator Solution (exclusiveOr3)](https://github.com/TrevorGlascock/ExclusiveOrSolution/blob/main/src/com/example/helloworld/HelloWorld.java#L180-L224)
 
### This was the hardest solution to fully wrap my head around, but is the most efficient. 
* If we can assume that all values of the input are presorted:
    * We can create two pointers (read: iterators)
    * Each pointer will iterate through their respective input Lists as long as both pointers have not reached the end of their list
    * If the two pointers have the same value, then we will skip over those values
        * We will repeat this until both pointers have landed on different values
    * If they don't have the same value, then we will only look at the lesser value between the two pointers, and we will add this value to our result List. 
         * We will repeat this until we reach a value that is greater than the value of the other pointer  
    * And if one of the pointers has reached the end of its List, we will add all remaining values from the List that is left into the result. 


* Has a worst-case Time complexity of O(n+m)
  * Linear Time complexity 
* Has a constant worst-case Space complexity of O(6)
  * Constant Space complexity
* Fixes the bug introduced by the previous solution
* Eliminates the need for multiple levels of iteration
* Eliminates the need for storing a HashMap

---

# [HashMap Solution (exclusiveOr2)](https://github.com/TrevorGlascock/ExclusiveOrSolution/blob/main/src/com/example/helloworld/HelloWorld.java#L149-L178)

### This is the next solution that came to my mind, here's my thought process:
1. We can use HashMap as a histogram to store the # of occurrences of a specific number in the first list.
2. We can then check the numbers of the second list, and any that don't match can be added to the histogram, but any that DO match must be set to 0 on our histogram.
3. Now we can iterate through the histogram, and push each number x times into the result List, where X is the # of occurrence


* Has worst-case Time complexity of O( (2n+ 2m) (n+m)log(n+m) )
  * Log-Linear Time complexity
* Has worst-case Space complexity of O( n+m )
  * Linear Space complexity
* Introduces a new bug that does not apply to input case, but to edge case:
   * If the second List has repeated numbers (ex: [2, 3, 5, 5, 7]), the repeated number (5) will not be included at all in the result List.

---

# [Brute Force Solution (exclusiveOr)](https://github.com/TrevorGlascock/ExclusiveOrSolution/blob/main/src/com/example/helloworld/HelloWorld.java#L128-L147)

### This is the most naturally intuitive solution.
* Although it appears to not have any nested for loops, the ArrayList.contains method is actually iterative in its implementation, requiring n operations to perform, unlike the instant lookup times of a HashMap.
* Has worst-case Time complexity of O(2n*m+(n+m)log(n+m)) 
  * Polynomial Time complexity
* Has worst-case Space complexity of O(n+m) 
  * Linear Space complexity
