package search;

public class IterativeChop implements Chop {
    public int chop(int target, int[] array) {
        int top, bottom, half;

        bottom = 0;
        top = array.length - 1;
        
        if (array[bottom] == target) {
            return bottom;
        } else if (array[top] == target) {
            return top;
        }
        
        while (bottom <= top) {
            half = (top + bottom) / 2;
            if (array[half] < target) {
                bottom = half + 1;
            } else if (array[half] > target) {
                top = half - 1;
            } else {
                return half;
            }
        }

        return -1;
    }
}
