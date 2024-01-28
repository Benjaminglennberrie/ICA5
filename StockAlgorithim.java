public class StockAlgorithim {

    public static int[] findMaxSubarray(int[] arr) {
        int n = arr.length;

        int start = 0;   // Initialize the starting index of the subarray
        int end = 0;     // Initialize the ending index of the subarray
        int currentStart = 0; // Temporary start index for the current subarray
        int maxSum = arr[0];  // Initialize the maximum sum
        int currentSum = arr[0]; // Initialize the current sum

        for (int i = 1; i < n; i++) {
            // If the current element is greater than the current sum plus the element itself,
            // update the temporary start index to the current index
            if (arr[i] > currentSum + arr[i]) {
                currentStart = i;
                currentSum = arr[i];
            } else {
                currentSum += arr[i];
            }

            // If the current sum is greater than the maximum sum, update start and end indices
            if (currentSum > maxSum) {
                start = currentStart;
                end = i;
                maxSum = currentSum;
            }
        }

        return new int[]{start, end, maxSum};
    }

    public static void main(String[] args) {
        int[] stockPrices = {7, -3, 4, -1, 2, 1, -5, 4};

        int[] result = findMaxSubarray(stockPrices);

        System.out.println("Buy on day " + result[0] + ", sell on day " + result[1]);
        System.out.println("Maximum profit: " + result[2]);
    }
}
