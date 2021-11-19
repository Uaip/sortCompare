package sortCompare;

public class quickSort1 extends sortCompare {
    // quickSort 1
    void Quick_sort1(int a[], int left, int right, int layer) {
        boolean isAsc = true;
        // sequence is already in ascending order
        for (int i = 0; i < a.length - 1; i++) {
            isAsc = true;
            if (a[i] > a[i + 1]) {
                isAsc = false;
                break;
            }
        }
        if (isAsc)
            return;
        // sequence is already in descending order
        for (int i = 0; i < a.length - 1; i++) {
            isAsc = false;
            if (a[i] < a[i + 1]) {
                isAsc = true;
                break;
            }
        }
        if (!isAsc)
            return;
        if (left >= right) {
            layerMax = layerMax > layer ? layerMax : layer;
            layer = 0;
            return;
        }
        int pivot = a[left];
        int L = left, R = right;
        while (L < R) {
            while (L < R && a[R] > pivot)
                R--;
            if (L < R)
                a[L++] = a[R];
            while (L < R && a[L] < pivot)
                L++;
            if (L < R)
                a[R--] = a[L];
            if (L == R)
                a[L] = pivot; // pivot's location is array[L]
        }
        Quick_sort1(a, left, R - 1, layer + 1);
        layer = 0;
        Quick_sort1(a, L + 1, right, layer + 1);
    }
}
