package sortCompare;

import java.util.LinkedList;
import java.util.List;

public class test {
    public static void main(String[] args) {
        int temp1[] = new int[100000];
        for (int i = 0; i < temp1.length; i++) {
            temp1[i] = (int) (Math.random() * 1000 + 1);
        }
        int temp2[] = new int[100000];
        for(int i = 0;i < temp1.length;i++) {
            temp2[i] = temp1[i];
        }
        long s1 = System.currentTimeMillis();
        nonRecurMergeSort tM =  new nonRecurMergeSort();
        tM.NonRecurMergeSort(temp1, temp1.length);
        long s2 = System.currentTimeMillis();
        quickSort2 qk1 = new quickSort2();
        qk1.Quick_sort2(temp2, 0, temp2.length - 1, 0);
        long s3 = System.currentTimeMillis();
        System.out.println(s2 - s1 + "ms");
        System.out.println(s3 - s2 + "ms");
        System.out.println();
        System.out.println(System.getProperty("file.encoding"));
    }

    static void bubbleSort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    swap(a, j, j + 1);
                }
            }
        }
    }

    static void swap(int a[], int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}

class struct {
    int i;
    int j;
    List<Integer> list = new LinkedList<>();
}