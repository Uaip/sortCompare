package sortCompare;

import java.util.Random;

public class quickSort2 extends sortCompare {
    // This Function helps in calculating
    // random numbers between low(inclusive)
    // and high(inclusive)
    void random(int arr[], int low, int high) {
        Random rand = new Random();
        int pivot = rand.nextInt(high - low) + low;
        swap(arr, high, pivot);
    }

    /*
     * This function takes last element as pivot, places the pivot element at its
     * correct position in sorted array, and places all smaller (smaller than pivot)
     * to left of pivot and all greater elements to right of pivot
     */
    int partition(int arr[], int low, int high) {
        // pivot is chosen randomly
        random(arr, low, high);
        int pivot = arr[high];

        int i = (low - 1); // index of smaller element
        for (int j = low; j < high; j++) {
            // If current element is smaller than or
            // equal to pivot
            if (arr[j] < pivot) {
                i++;

                // swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    /*
     * The main function that implements QuickSort() arr[] --> Array to be sorted,
     * low --> Starting index, high --> Ending index
     */
    void Quick_sort2(int arr[], int low, int high, int layer) {
        boolean isAsc = true;
        // sequence is already in ascending order
        for (int i = 0; i < arr.length - 1; i++) {
            isAsc = true;
            if (arr[i] > arr[i + 1]) {
                isAsc = false;
                break;
            }
        }
        if (isAsc)
            return;
        // sequence is already in descending order
        for (int i = 0; i < arr.length - 1; i++) {
            isAsc = false;
            if (arr[i] < arr[i + 1]) {
                isAsc = true;
                break;
            }
        }
        if (!isAsc)
            return;
        if (low >= high) {
            layerMax = layerMax > layer ? layerMax : layer;
            layer = 0;
            return;
        }
        if (low < high) {
            /*
             * pi is partitioning index, arr[pi] is now at right place
             */
            int pi = partition(arr, low, high);

            // Recursively sort elements before
            // partition and after partition
            Quick_sort2(arr, low, pi - 1, layer + 1);
            layer = 0;
            Quick_sort2(arr, pi + 1, high, layer + 1);
        }
    }
}
