package sorts;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class TestAnna {

    @Test
    void test1() {
        int answer = Anna.sherlockAndAnagrams("ifailuhkqq");
        assertEquals(answer,3);
    }

}
