public class ArrayUtil {

    public static int[][] getIdentity(int size) {
        int[][] array2D = new int[size][size];
        for (int r = 0; r < size; r++) {
            array2D[r][r] = 1;
        }
        return array2D;
    }

    public static void printArray2D(int[][] array2D) {
        for (int[] row : array2D) {
            for (int num : row) {
                System.out.print(num + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        printArray2D(getIdentity(4));
    }
}
