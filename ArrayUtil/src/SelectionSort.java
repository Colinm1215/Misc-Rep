public class SelectionSort {
    /*
        O(n) ~ n^2
            if you increase the number of elements by a factor of n, work increases by n^2
     */
    public static void sort(Integer[] arr) {
        for (int i = 0; i < arr.length; i++) {
            ArrayUtil.swap(i, minIndexFrom(i, arr), arr);
        }
    }

    private static int minIndexFrom(int start, Integer[] arr) {
        int index = start;
        for (int i = start+1; i < arr.length; i++) {
            if (arr[i] < arr[index]) {
                index = i;
            }
        }
        return index;
    }
}
