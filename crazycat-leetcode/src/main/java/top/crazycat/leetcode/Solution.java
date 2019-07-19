package top.crazycat.leetcode;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2019/7/16
 * description:
 */
public class Solution {

    //给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
    public int singleNumber(int[] nums) {
        int r = 0;
        for (int num : nums) {
            r = r ^ num;
        }
        return r;
    }

    //给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
    public int majorityElement(int[] nums) {
        int num = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                num = nums[i];
                count++;
            } else if (num == nums[i]) {
                count++;
            } else {
                count--;
            }
        }
        return num;
    }

    /**
     * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：
     * <p>
     * 每行的元素从左到右升序排列。
     * 每列的元素从上到下升序排列。
     * [
     * [1,   4,  7, 11, 15],
     * [2,   5,  8, 12, 19],
     * [3,   6,  9, 16, 22],
     * [10, 13, 14, 17, 24],
     * [18, 21, 23, 26, 30]
     * ]
     */
    public boolean searchMatrix(int[][] matrix, int target) {
//        for(int i=0;i<matrix.length;i++){
//            for(int j=0;j<matrix[i].length;j++){
//                if(matrix[i][j] == target){
//                    return true;
//                }
//                if(matrix[i][j]>target){
//                    break;
//                }
//            }
//        }
//        return false;
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return false;
        int row = matrix.length - 1;
        int col = 0;
        int count = 0;
        while (row >= 0 && col < matrix[0].length) {
            if (target < matrix[row][col]) row--;
            else if (target > matrix[row][col]) col++;
            else {
//                count++;//计数
//                row--;
//                col++;
                return true;
            }
        }
        return false;
    }

    /**
     * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
     * <p>
     * 说明:
     * <p>
     * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
     * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] rs = new int[m + n];
        int i = 0, j = 0, k = 0;
        while (k < m + n) {
            int t;
            if (i >= m) {
                t = nums2[j];
                j++;
            } else if (j >= n || nums1[i] < nums2[j]) {
                t = nums1[i];
                i++;
            } else {
                t = nums2[j];
                j++;
            }
            rs[k] = t;
            k++;
        }
        for (int a = 0; a < rs.length; a++) {
            nums1[a] = rs[a];
        }
    }

    /**
     * 你将获得 K 个鸡蛋，并可以使用一栋从 1 到 N  共有 N 层楼的建筑。
     *
     * 每个蛋的功能都是一样的，如果一个蛋碎了，你就不能再把它掉下去。
     *
     * 你知道存在楼层 F ，满足 0 <= F <= N 任何从高于 F 的楼层落下的鸡蛋都会碎，从 F 楼层或比它低的楼层落下的鸡蛋都不会破。
     *
     * 每次移动，你可以取一个鸡蛋（如果你有完整的鸡蛋）并把它从任一楼层 X 扔下（满足 1 <= X <= N）。
     *
     * 你的目标是确切地知道 F 的值是多少。
     *
     * 无论 F 的初始值如何，你确定 F 的值的最小移动次数是多少？
     */
    public static int superEggDrop(int K, int N) {
        int[][] c = new int[K+1][N+1];
        for (int i=1;i<c[0].length;i++){//初始化1个鸡蛋的数据
            c[1][i] = i;
        }
        for (int j=1;j<c.length;j++){
            c[j][1]=1;
        }
        for(int i =2;i<K+1;i++){
            int x = 1;
            for(int j=2;j<N+1;j++){
                for (;x<=j;x++){
                    if (c[i-1][x-1]>=c[i][j-x]){
                        break;
                    }
                }
                c[i][j] = c[i-1][x-1]+1;
            }
        }
        for (int i=0;i<c.length;i++){
            for(int j=0;j<c[0].length;j++){
                System.out.print(c[i][j]+"\t");
            }
            System.out.println();
        }
        return c[K][N];
    }

    public static void main(String[] args) {
        long s = System.currentTimeMillis();
        superEggDrop(20, 30);
        System.out.println(System.currentTimeMillis()-s);
    }

}
