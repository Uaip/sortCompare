package sortCompare;

import java.nio.file.attribute.GroupPrincipal;
import java.util.ArrayList;
import java.util.Random;
public class sortCompare {
    static int No = 1;
    static int length = 1000;// the start length of group list
    static int N = 30000; // the maximum value of the list
    static int delta = 10000; //increased length of each group
    static int M = 5; // m is the number of sequence of one group
    static int groupNo = 1;
    static int nsToms = 1000000; //
    static int layerMax = 0; // global variable to store the max layer
    public static void main(String[] args) {
        statistic group1[] = new statistic[M];
        statistic group2[] = new statistic[M];
        statistic group3[] = new statistic[M];
        statistic group4[] = new statistic[M];
        statistic group5[] = new statistic[M];
        statistic group6[] = new statistic[M];
        sortCompare test = new sortCompare();

        // allocate memory for the array
        test.Instantiation(group1);
        test.Instantiation(group2);
        test.Instantiation(group3);
        test.Instantiation(group4);
        test.Instantiation(group5);
        test.Instantiation(group6);

        // generating the limited sequence
        long start = System.nanoTime();
        test.makeList(group1);
        test.makeList(group2);
        test.makeList(group3);
        test.makeList(group4);
        test.makeList(group5);
        test.makeList(group6);
        long end = System.nanoTime();
        System.out.println("time used:\t" + String.format("%.4f", (double) (end - start) / nsToms));
        ArrayList<statistic[]> group = new ArrayList<>();
        group.add(group1);
        group.add(group2);
        group.add(group3);
        group.add(group4);
        group.add(group5);
        group.add(group6);
        test.TitlePrint();
        for (int i = 0; i < group.size(); i++) {
            test.quickSort2_Test(group.get(i));
            test.recurMergeTest(group.get(i));
            test.NonRecurMergeSortTest(group.get(i));
            test.quickSort1_Test(group.get(i));

            test.InfoOutput(group.get(i));
        }
        int index = 0;
        for(int i = 0;i < group.size();i++) {
            int length = 0;
            double avgDD = 0;
            double avgADD = 0;
            double avgRecTime = 0;
            double avgNonRecTime = 0;
            double avgQK1Time = 0;
            double avgQK2Time = 0;
            statistic temp[] = group.get(i);
            for(int j = 0;j < temp.length;j ++) {
                length = temp[j].n;
                avgDD += temp[j].DD;
                avgADD += temp[j].ADD;
                StringBuilder a1 = new StringBuilder(temp[j].MergeRecurTime);
                a1.delete(a1.length() -3, a1.length());
                avgRecTime += Double.parseDouble(a1.toString());

                a1 = new StringBuilder(temp[j].MergeCirculeTime);
                a1.delete(a1.length() -3, a1.length());
                avgNonRecTime += Double.parseDouble(a1.toString());

                a1 = new StringBuilder(temp[j].quickSortTime1);
                a1.delete(a1.length() -3, a1.length());
                avgQK1Time += Double.parseDouble(a1.toString());

                a1 = new StringBuilder(temp[j].quickSortTime2);
                a1.delete(a1.length() -3, a1.length());
                avgQK2Time += Double.parseDouble(a1.toString());
            }
            System.out.println(length + "\t" + (index++) + "\t\t" + avgDD / 5 + "\t\t" + avgADD /5 + "\t\t" + avgRecTime/5 +"\t" +  avgNonRecTime/5 +"\t" + avgQK1Time/ 5 +"\t"+ avgQK2Time / 5);
        }
    }

    private void TitlePrint() {
        System.out.println(String.format("%-5s%-5s%-10s%-8s%-6s%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n", "No", "n",
                "groupID", "DD", "ADD", "RevurMergeLayer", "RecurMerge Time", "NonRecurMerge Time", "Quick_Sort1 Time",
                "Quick_Sort2 Time", "Quick_Sort1 Layer", "Quick_Sort2 Layer"));
    }

    void quickSort2_Test(statistic[] group) {
        for (int i = 0; i < M; i++) {
            int temp[] = new int[group[i].list.size()]; // Use temporary array to store the list
            for (int j = 0; j < temp.length; j++)
                temp[j] = group[i].list.get(j);
            quickSort2 qk2 = new quickSort2();
            long start = System.nanoTime();
            qk2.Quick_sort2(temp, 0, temp.length - 1, 0);
            long end = System.nanoTime();
            group[i].quickSortTime2 = String.format("%.4f", (double) (end - start) / nsToms);
            group[i].QuickRecurLayer2 = layerMax;
            layerMax = 0;
        }
    }

    void quickSort1_Test(statistic[] group) {
        for (int i = 0; i < M; i++) {
            int temp[] = new int[group[i].list.size()]; // Use temporary array to store the list
            for (int j = 0; j < temp.length; j++)
                temp[j] = group[i].list.get(j);
            quickSort1 qk1 = new quickSort1();
            long start = System.nanoTime();
            qk1.Quick_sort1(temp, 0, temp.length - 1, 0);
            long end = System.nanoTime();
            group[i].quickSortTime1 = String.format("%.4f", (double) (end - start) / nsToms);
            group[i].QuickRecurLayer1 = layerMax;
            layerMax = 0;
        }
    }

    void NonRecurMergeSortTest(statistic[] group) {
        for (int i = 0; i < M; i++) {
            int temp[] = new int[group[i].list.size()]; // Use temporary array to store the list
            for (int j = 0; j < temp.length; j++)
                temp[j] = group[i].list.get(j);
            // get sorting information
            // non-recursice Mergesort
            nonRecurMergeSort nrms = new nonRecurMergeSort();
            long start = System.nanoTime();
            nrms.NonRecurMergeSort(temp, temp.length);
            long end = System.nanoTime();
            group[i].MergeCirculeTime = String.format("%.4f", (double) (end - start) / nsToms);
        }
    }

    void recurMergeTest(statistic[] group) {
        for (int i = 0; i < M; i++) {
            // list to array for sort
            int temp[] = new int[group[i].list.size()];
            for (int j = 0; j < temp.length; j++)
                temp[j] = group[i].list.get(j);
            // get sorting information
            // recursice Mergesort
            recurMergeSort rm = new recurMergeSort();
            long start = System.nanoTime();
            rm.RecurMergeSort(temp, 0, temp.length - 1, 0);
            long end = System.nanoTime();
            group[i].MergeRecurTime = String.format("%.4f", (double) (end - start) / nsToms);
            group[i].MergeRecurLayer = layerMax;
            layerMax = 0;
        }
    }

    private void InfoOutput(statistic[] group) {
        for (int i = 0; i < group.length; i++) {
            System.out.print(String.format("%-3d%-10d%-5d%-10d%-10d%-20d%-20s%-20s", group[i].No, group[i].n,
                    group[i].groupNo, group[i].DD, group[i].ADD, group[i].MergeRecurLayer,
                    group[i].MergeRecurTime + " ms", group[i].MergeCirculeTime + " ms"));
            System.out.println(String.format("%-20s%-20s%-20d%-20d\n", group[i].quickSortTime1 + " ms",
                    group[i].quickSortTime2 + " ms", group[i].QuickRecurLayer1, group[i].QuickRecurLayer2));
        }
    }

    private void Instantiation(statistic[] group) { //
        for (int i = 0; i < group.length; i++)
            group[i] = new statistic();
    }

    void shuffleArr(int[] arr) {
        // shuffle the array sequence
        Random rm = new Random();
        for (int i = 0; i < arr.length; i++) {
            int j = rm.nextInt(arr.length);
            swap(arr, i, j);
        }
    }

    void swap(int arr[], int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    void makeList(statistic[] group) {
        int curArr[][] = new int[M][length];
        for (int i = 0; i < length; i++) {
            // the list of one group has same numbers and different orders
            int randNum = (int) (Math.random() * 1) + 1;
            for (int j = 0; j < curArr.length; j++) {
                curArr[j][i] = randNum;
            }
        }
        /**
         * step 3 
         * ensure sequences'dd are same
         */
        /*for(int i = 0;i < M;i++) {
            group[i].No = No++;
            group[i].n = curArr[0].length;
            group[i].groupNo = groupNo;
            group[i].DD = getDD(curArr[0]);
            group[i].ADD = getADD(curArr[0]);
            for (int index = 0; index < curArr[0].length; index++) {
                group[i].list.add(curArr[i][index]);
            } 
        }*/
        int DD[] = new int[M];
        int ADD[] = new int[M];
        boolean isEqual = true;
        while (isEqual) { // shuffle the M sequences to ensure different DD and ADD
            isEqual = false;
            for (int i = 0; i < M; i++) {
                shuffleArr(curArr[i]);
                DD[i] = getDD(curArr[i]);
                
                /**
                 * step 6 code
                 **/
                 while(Math.abs(DD[i] - 100000) > 1000) {
                    shuffleArr(curArr[i]);
                    DD[i] = getDD(curArr[i]);
                    ADD[i] = getADD(curArr[i]);
                }
            }
            for (int i = 0; i < M ; i++) {
                for(int j = i; j < M;j++)
                if(i != j){
                    if (DD[i] == DD[j] || ADD[i] == ADD[j]) {
                        isEqual = true;
                        break;
                    }
                }
            }
            if (!isEqual)
                break;
        }
        // select minimum DD and ADD index of remaining sequence and store the into
        // struct group
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M - i - 1; j++) {
                if (DD[j] > DD[j + 1]) {
                    swap(DD, j, j + 1);
                    swap(ADD, j, j + 1);
                    int temp[] = new int[length];
                    for (int index = 0; index < length; index++) {
                        temp[index] = curArr[j][index];
                    }
                    for (int index = 0; index < length; index++) {
                        curArr[j][index] = curArr[j + 1][index];
                    }
                    for (int index = 0; index < length; index++) {
                        curArr[j + 1][index] = temp[index];
                    }
                }
            }
        }
        for (int i = 0; i < M; i++) {
            group[i].No = No++;
            group[i].n = curArr[0].length;
            group[i].groupNo = groupNo;
            group[i].DD = DD[i];
            group[i].ADD = ADD[i];
            for (int index = 0; index < curArr[0].length; index++) {
                group[i].list.add(curArr[i][index]);
            }
        }
        groupNo++;
        length += delta;
    }

    int getDD(int[] Arr) {
        int DD = 0;
        for (int i = 0; i < Arr.length; i++) {
            int DDpart = 0;
            for (int j = i + 1; j < Arr.length; j++) { // i < j <= n - 1 && a[j] < a[i]
                if (Arr[j] < Arr[i])
                    DDpart++;
            }
            DD += DDpart;
        }
        return DD;
    }

    int getADD(int[] Arr) {
        int ADD = 0;
        for (int i = 0; i < Arr.length; i++) {
            int ADDpart = 0;
            for (int j = i + 1; j < Arr.length; j++) {
                if (Arr[j] < Arr[i])
                    ADDpart++;
            }
            ADD += ADDpart;
        }
        return ADD / Arr.length;
    }
}
