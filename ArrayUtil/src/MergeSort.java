public class MergeSort {
    /*
        Precondition: a and b are both sorted
     */

    public static char[] merge(char[] a, char[] b) {
        char[] merged = new char[a.length + b.length];
        int ai = 0;
        int bi = 0;
        int mi = 0;

        while (ai < a.length && bi < b.length) {
            if (a[ai] <= b[bi]) {
                merged[mi] = a[ai];
                ai++;
            }
            else {
                merged[mi] = b[bi];
                bi++;
            }
            mi++;
        }

        // copy elements from b
        for (int i = bi; i < b.length; i++){
            merged[mi] = b[i];
            mi++;
        }
        // copy elements from a
        for (int i = ai; i < a.length; i++){
            merged[mi] = a[i];
            mi++;
        }
        return merged;
    }


    public static Comparable[] merge(Comparable[] a, Comparable[] b) {
        Comparable[] merged = new Comparable[a.length + b.length];
        int ai = 0;
        int bi = 0;
        int mi = 0;

        while (ai < a.length && bi < b.length) {
            if (a[ai].compareTo(b[bi]) <= 0) {
                merged[mi] = a[ai];
                ai++;
            }
            else {
                merged[mi] = b[bi];
                bi++;
            }
            mi++;
        }

        // copy elements from b
        for (int i = bi; i < b.length; i++){
            merged[mi] = b[i];
            mi++;
        }
        // copy elements from a
        for (int i = ai; i < a.length; i++){
            merged[mi] = a[i];
            mi++;
        }
        return merged;
    }

    public static Comparable[] sort(Comparable[] arr) {
        if (arr.length <= 1) return arr;
        Comparable[] firstHalf = new Comparable[arr.length/2];
        Comparable[] secondHalf = new Comparable[arr.length - firstHalf.length];

        for (int i = 0; i < arr.length; i++) {
            if (i < firstHalf.length) firstHalf[i] = arr[i];
            else secondHalf[i-firstHalf.length] = arr[i];
        }

        return merge(sort(firstHalf), sort(secondHalf));
    }

    public static char[] sort(char[] arr) {
        if (arr.length <= 1) return arr;
        char[] firstHalf = new char[arr.length/2];
        char[] secondHalf = new char[arr.length - firstHalf.length];

        for (int i = 0; i < arr.length; i++) {
            if (i < firstHalf.length) firstHalf[i] = arr[i];
            else secondHalf[i-firstHalf.length] = arr[i];
        }

        return merge(sort(firstHalf), sort(secondHalf));
    }
}
