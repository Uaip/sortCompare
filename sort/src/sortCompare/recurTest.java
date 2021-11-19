package sortCompare;

public class recurTest {
    // 递归求N！，加上日志打印（递归的调试）
    static boolean reach = false;

    public static void main(String[] args) {
        int n = 10;
        int ret = factor(n);
        System.out.println("ret = " + ret);
    }

    public static int factor(int n) {
        System.out.println("begin, n = " + n);
        if (n == 1) {
            System.out.println("end n=1 ret=factor(1)=1");
            return 1;
        }
        int ret = n * factor(n - 1);
        System.out.println("end n = " + n + "ret = " + ret);
        return ret;
    }
}
