package sortCompare;

public class nonRecurMergeSort extends sortCompare {
    void mergeSort2(int arr[], int tmp[], int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int idt = left;

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                tmp[idt++] = arr[i++];
            } else {
                tmp[idt++] = arr[j++];
            }
        }
        // merge remaining sequence
        while (i <= mid) {
            tmp[idt++] = arr[i++];
        }

        while (j <= right) {
            tmp[idt++] = arr[j++];
        }
    }

    void MergePass(int arr[], int tmp[], int step, int n) {
        int i = 0;
        while (n - i >= 2 * step) {
            mergeSort2(arr, tmp, i, i + step - 1, i + 2 * step - 1);
            i = i + 2 * step;
        }
        if (n - i > step)
            mergeSort2(arr, tmp, i, i + step - 1, n - 1);
        else
            for (int j = i; j < n; j++)
                tmp[j] = arr[j];

    }

    void NonRecurMergeSort(int arr[], int n) {
        int step = 1;
        int tmp[] = new int[n]; // allocate additional space

        while (step < n) {
            MergePass(arr, tmp, step, n);
            step *= 2;
            //
            MergePass(tmp, arr, step, n);
            step *= 2;
        }
    }
}
