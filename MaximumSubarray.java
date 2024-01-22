public class MaximumSubarray {

    // Class variables
    private int[] array;
    private int low;
    private int high;

    public static void main(String[] args) {
        // Example usage
        int[] inputArray = {-2, -5, 6, -2, -3, 1, 5, -6};
        MaximumSubarray maximumSubarrayFinder = new MaximumSubarray(inputArray);
        int[] result = maximumSubarrayFinder.findMaximumSubarray();

        // Output the result
        System.out.println("Maximum Subarray: [" + result[0] + ", " + result[1] + "] with sum: " + result[2]);
    }


    // Constructor
    public MaximumSubarray(int[] array) {
        this.array = array;
        this.low = 0;
        this.high = array.length - 1;
    }

    // Main method to find maximum subarray
    public int[] findMaximumSubarray() {
        // Base case: Only one element
        if (low == high) {
            return new int[]{low, high, array[low]};
        } else {
            // Recursive case: Divide the array
            int mid = (low + high) / 2;

            // Find maximum subarray in the left, right, and crossing subarrays
            int[] leftSubarray = findMaximumSubarray(low, mid);
            int[] rightSubarray = findMaximumSubarray(mid + 1, high);
            int[] crossingSubarray = findMaxCrossingSubarray(low, mid, high);

            // Compare and return the maximum subarray among left, right, and crossing
            if (leftSubarray[2] >= rightSubarray[2] && leftSubarray[2] >= crossingSubarray[2]) {
                return leftSubarray;
            } else if (rightSubarray[2] >= leftSubarray[2] && rightSubarray[2] >= crossingSubarray[2]) {
                return rightSubarray;
            } else {
                return crossingSubarray;
            }
        }
    }

    // Helper method to find maximum subarray within a specified range
    private int[] findMaximumSubarray(int low, int high) {
        // Base case: Only one element
        if (low == high) {
            return new int[]{low, high, array[low]};
        } else {
            int mid = (low + high) / 2;

            // Find maximum subarray in the left and right halves
            int[] leftSubarray = findMaximumSubarray(low, mid);
            int[] rightSubarray = findMaximumSubarray(mid + 1, high);

            // Find the maximum subarray crossing the midpoint
            int[] crossingSubarray = findMaxCrossingSubarray(low, mid, high);

            // Compare and return the maximum subarray among left, right, and crossing
            if (leftSubarray[2] >= rightSubarray[2] && leftSubarray[2] >= crossingSubarray[2]) {
                return leftSubarray;
            } else if (rightSubarray[2] >= leftSubarray[2] && rightSubarray[2] >= crossingSubarray[2]) {
                return rightSubarray;
            } else {
                return crossingSubarray;
            }
        }
    }

    // Helper method to find maximum crossing subarray
    private int[] findMaxCrossingSubarray(int low, int mid, int high) {
        // Initialize variables to store indices and sums
        int leftSum = Integer.MIN_VALUE;
        int sum = 0;
        int maxLeft = 0;

        // Find maximum subarray in the left of the midpoint
        for (int i = mid; i >= low; i--) {
            sum += array[i];
            if (sum > leftSum) {
                leftSum = sum;
                maxLeft = i;
            }
        }

        // Reset sum for the right subarray
        sum = 0;
        int rightSum = Integer.MIN_VALUE;
        int maxRight = 0;

        // Find maximum subarray in the right of the midpoint
        for (int j = mid + 1; j <= high; j++) {
            sum += array[j];
            if (sum > rightSum) {
                rightSum = sum;
                maxRight = j;
            }
        }

        // Return the result: [maxLeft, maxRight, sum of left and right subarrays]
        return new int[]{maxLeft, maxRight, leftSum + rightSum};
    }

    // ... (rest of the code)



}
