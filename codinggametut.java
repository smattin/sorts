import java.util.Arrays;

class codinggametut {

    static boolean exists(int[] ints, int k) {
        // input is already sorted
        return (Arrays.binarySearch(ints,k) >= 0);
	}

    public static void main(String[] args) {
        int[] ints = {-9, 14, 37, 102};
        assert(codinggametut.exists(ints, 102)); // true
        assert(!codinggametut.exists(ints, 101)); // false
    }
}

