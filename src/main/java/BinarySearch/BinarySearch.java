package BinarySearch;

public class BinarySearch {
    public static void main(String[] args) {

    }

    public int findValueArray(int[] array, int value) {
        int index = -1;
        int left = 0;
        int right = array.length;
        int mid = 0;

        while (right - left >= 1) {
            mid = (left + right) / 2;
            int halfValue = array[mid];
            if (value == halfValue) {
                index = mid;
                break;
            } else if (value > halfValue) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        if (index == -1) {
            index = -mid - 1;
        }
        return index;
    }
}
