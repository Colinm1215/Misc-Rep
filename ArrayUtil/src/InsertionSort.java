public class InsertionSort {
    /*
        O(n) ~ n^2
            if you increase the number of elements by a factor of n, work increases by n^2
     */
    public static void sort(Integer[] arr) {
        for (int i = 1; i < 5; i++) {
            int j = i;
            while (j > 0 && arr[j] < arr[j - 1]) {
                ArrayUtil.swap(j, j - 1, arr);
                j--;
            }
        }
    }
}
