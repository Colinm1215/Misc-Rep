import java.util.Random;

public class ArrayUtil {
    private static Random random = new Random();
    public static Integer[] getRandIntegerArray(int low, int high, int size) {
        Integer[] arr = new Integer[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt((high+1) - low) + low;
        }
        return arr;
    }

    public static void printArray(Object[] arr) {
        for (Object i : arr) {
            System.out.print(i + " ");
        }
        System.out.print("\n");
    }

    public static void swap(int i, int j, char[] arr) {
        char i1 = arr[i];
        arr[i] = arr[j];
        arr[j] = i1;
    }

    public static void swap(int i, int j, Object[] arr) {
        Object i1 = arr[i];
        arr[i] = arr[j];
        arr[j] = i1;
    }

    public static char getRandChar(char start, char end){
        int endChar = (int)end;
        int startChar = (int)start;
        return (char)((int)(Math.random()*(end-start+1))+start);
    }

    public static char getRandChar(){
        return (char)((int)(Math.random()*26)+((int)'a'));
    }

    public static String getRandStr(int length) {
        StringBuilder str = new StringBuilder();
        for (char c : getRandChars(length)) str.append(c);
        return str.toString();
    }

    public static String[] getRandStrs(int num, int length) {
        String[] strs = new String[num];
        for (int i = 0; i < num; i++) {
            strs[i] = getRandStr(length);
        }
        return strs;
    }

    public static char[] getRandChars(int length) {
        char[] chars = new char[length];
        for (int i = 0; i < length; i++) {
            chars[i] = getRandChar();
        }
        return chars;
    }
}
