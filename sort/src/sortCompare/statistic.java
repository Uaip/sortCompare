package sortCompare;

import java.util.ArrayList;

public class statistic{
    int No; //sequence No
    int n; //length of current sequence
    int groupNo; //group no 
    int DD; //degree of disorder 
    int ADD; //average dd
    //mergesort info
    int MergeRecurLayer;
    String MergeRecurTime;
    String MergeCirculeTime; //non-recursive time
    //2 quickSort info
    int QuickRecurLayer1;
    int QuickRecurLayer2;
    String quickSortTime1;
    String quickSortTime2;
    ArrayList<Integer> list = new ArrayList<>(); //use arraylist to store sequence
}
