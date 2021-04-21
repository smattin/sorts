package sorts;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestSwaps {

    @Test
    void test1() throws InterruptedException {
        int [] arr = {2,3,4,1,5};
        int answer = Swaps.minimum(arr);
        assertEquals(answer,3);
    }

    @Test
    void test2() throws InterruptedException {
        int [] arr = {4,3,1,2};
        int answer = Swaps.minimum(arr);
        assertEquals(answer,3);
    }

    @Test
    void test3() throws InterruptedException {
        int [] arr = {1,3,5,2,4,6,7};
        int answer = Swaps.minimum(arr);
        assertEquals(answer,3);
    }

    @Test
    void test4() throws InterruptedException {
        int [] arr = {2,3,4,5,6,7,1};
        int answer = Swaps.minimum(arr);
        assertEquals(answer,6);
    }

    @Test
    void test5() throws InterruptedException {
        int [] arr = {1,3,2,4,5,6,7};
        int answer = Swaps.minimum(arr);
        assertEquals(answer,1);
    }

    @Test
    void test6() throws InterruptedException {
        int [] arr = {1,2,4,3,5,6,7};
        int answer = Swaps.minimum(arr);
        assertEquals(answer,1);
    }

    @Test
    void test7() throws InterruptedException {
        int [] arr = {1,2,3,4,5,6,7,8,9,10,11,12,13};
        int answer = Swaps.minimum(arr);
        assertEquals(answer,0);
    }

    @Test
    void test8() throws InterruptedException {
        int [] arr = {1,2,3,4,5,6,7,8,9,10,11,13,12};
        int answer = Swaps.minimum(arr);
        assertEquals(answer,1);
    }

    @Test
    void test9() throws InterruptedException {
        int [] arr = {1,2,3,4,5,6,7,8,9,10,12,11};
        int answer = Swaps.minimum(arr);
        assertEquals(answer,1);
    }
}
