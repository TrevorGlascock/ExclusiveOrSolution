package com.example.helloworld;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import static com.example.helloworld.HelloWorld.exclusiveOrFinal;

class HelloWorldTest {

    @Test
    @DisplayName("Should return the expected solution when provided with the 2 original lists.")
    void main() {
        List<Integer> listA = new ArrayList<Integer>(Arrays.asList(1, 3, 3, 6, 6, 7, 9));
        List<Integer> listB = new ArrayList<Integer>(Arrays.asList(2, 3, 5, 7));

        List<Integer> expected = new ArrayList<Integer>(Arrays.asList(1, 2, 5, 6, 6, 9));
        List<Integer> actual = exclusiveOrFinal(listA, listB);

        assertEquals(expected,actual);
    }

}