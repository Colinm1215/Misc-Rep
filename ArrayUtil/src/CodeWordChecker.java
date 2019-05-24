public class CodeWordChecker implements StringChecker{
    int min, max; // minimum and maximum string lengths
    String restricted; // string that cannot occur

    CodeWordChecker(int min, int max, String restrict) {
        restricted = restrict;
        this.min = min;
        this.max = max;
    }

    CodeWordChecker(String restrict) {
        restricted = restrict;
        min = 6;
        max = 20;
    }

    public boolean isValid(String str) {
        return !(str.contains(restricted) || str.length() > max || str.length() < min);
    }
}
