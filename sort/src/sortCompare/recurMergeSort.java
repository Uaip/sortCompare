package sortCompare;

public class recurMergeSort extends sortCompare {
    // recur MergeSort
    void RecurMergeSort(int a[], int left, int right, int layer) {
        if (left >= right) {
            layerMax = layerMax > layer ? layerMax : layer;
            layer = 0;
            return;
        }
        if (left < right) {
            int i = (left + right) / 2;
            RecurMergeSort(a, left, i, layer + 1);
            layer = 0;
            RecurMergeSort(a, i + 1, right, layer + 1);
            mergeSort1(a, left, i, right);
        }
    }

    void mergeSort1(int a[], int left, int mid, int right) {
        int[] temp = new int[a.length];
        int r1 = mid + 1;
        int tIndex = left;
        int cIndex = left;
        while (left <= mid && r1 <= right) {
            if (a[left] < a[r1])
                temp[tIndex++] = a[left++];
            else
                temp[tIndex++] = a[r1++];
        }
        while (left <= mid)
            temp[tIndex++] = a[left++];
        while (r1 <= right)
            temp[tIndex++] = a[r1++];
        while (cIndex <= right) {
            a[cIndex] = temp[cIndex];
            cIndex++;
        }
    }
}
