package search;

public class RecursiveChop implements Chop {
    public int chop(int target, int[] array) {
        if (array[0] == target) {
            return 0;
        } else if (array[array.length-1] == target) {
            return array.length-1;
        }
        return slice(target, array, 0, array.length-1);
    }
    
    private int slice(int target, int[] array, int bottom, int top) {
        if (bottom > top) {
            return -1;
        }
        int half = (top + bottom) / 2;
        if (array[half] < target) {
            return slice(target, array, half + 1, top);
        } else if (array[half] > target) {
            return slice(target, array, bottom, half - 1);
        } else {
            return half;
        }
    }
}
