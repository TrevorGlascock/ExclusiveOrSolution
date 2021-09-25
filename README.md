# Pointer/Iterator Solution (exclusiveOr3)
 
### This was the hardest solution to fully wrap my head around, but is the most efficient. 
* If we can assume that all values of the input are presorted:
    * We can create two pointers (read: iterators)
    * Each pointer will iterate through their respective input Lists as long as both pointers have not reached the end of their list
    * If the two pointers have the same value, then we will skip over those values
        * We will repeat this until both pointers have landed on different values
    * If they don't have the same value, then we will only look at the lesser value between the two pointers, and we will add this value to our result List. 
         * We will repeat this until we reach a value that is greater than the value of the other pointer  
    * And if one of the pointers has reached the end of its List, we will add all remaining values from the List that is left into the result. 


* Has a linear worst-case Time complexity of O(n+m)
* Has a constant worst-case Space complexity of O(6)
* Fixes the bug introduced by the previous solution
* Eliminates the need for multiple levels of iteration
* Eliminates the need for storing a HashMap

---

# HashMap Solution (exclusiveOr2())

### This is the next solution that came to my mind, here's my thought process:
1. We can use HashMap as a histogram to store the # of occurrences of a specific number in the first list.
2. We can then check the numbers of the second list, and any that don't match can be added to the histogram, but any that DO match must be set to 0 on our histogram.
3. Now we can iterate through the histogram, and push each number x times into the result List, where X is the # of occurrence


* Has worst-case Time complexity of O( (2n+ 2m) (n+m)log(n+m) )
* Has worst-case Space complexity of O( n+m )
* Introduces a new bug that does not apply to input case, but to edge case:
   * If the second List has repeated numbers (ex: [2, 3, 5, 5, 7]), the repeated number (5) will not be included at all in the result List.

---

# Brute Force Solution (exclusiveOr())

### This is the most naturally intuitive solution.

* Has worst-case Time complexity of O(n*m+(n+m)log(n+m))
* Has worst-case Space complexity of O(n+m)
