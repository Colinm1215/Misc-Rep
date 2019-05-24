import java.util.Scanner;

public class StrSort {

    static Scanner reader = new Scanner(System.in);  // Reading from System.in
    public static void sort(String[] items) {
        int n = items.length;
        int i = 0;
        while (i < 2) {
            sortHelper(items, n-1);
//            while (true) {
//                Object i = reader.next();
//                if (i.equals("go")) break;
//            }
//            ArrayUtil.printArray(items);
            n--;
            i++;
        }
    }

    public static void sortHelper(String[] items, int last) {
        int m = last;
        for (int k = 0; k < last; k++) {
            if (items[k].compareTo(items[m]) > 0) m = k;
        }
        String temp = items[m];
        items[m] = items[last];
        items[last] = temp;
    }

    public static void main(String[] args) {
        String[] rand = {"Dan", "Alice", "Claire", "Evan", "Boris"};
        ArrayUtil.printArray(rand);
        sort(rand);
        ArrayUtil.printArray(rand);
    }
}
