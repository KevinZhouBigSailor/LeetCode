package sumoLogic;

/**
 * Created by zzhou on 4/4/2017.
 */
public class FindPeakElement {
    public int findPeakElement(int[] nums) {
        return search(nums, 0, nums.length - 1);
    }

    public int search(int[] nums, int l, int r) {
        if (l == r)
            return l;
        int mid = l + ((r - l) >> 1); //(l + r) >> 1;
        if (nums[mid] > nums[mid + 1])
            return search(nums, l, mid);
        return search(nums, mid + 1, r);
    }

    /*public int findPeakElement2D(int[][] nums) {
        int col = nums[0].length;
        int row = nums.length;
        int midCol = (col - 1) >> 1;
        int maxIdx = getMaxIndex(nums[][midCol]);

        if (midCol == 0 || nums[maxIdx][midCol] > nums[maxIdx][midCol-1]) && (midCol+1 == col) || nums[maxIdx][midCol] > nums[maxIdx][midCol+1]))
        {
            return nums[maxIdx][midCol];
        }
        else if(nums[maxIdx][midCol-1] > nums[maxIdx][midCol+1])   //LHS bigger
        return findPeakElement2D([nums[i][0:midCol] f|| i in range(0, len(nums))])
        else// RHS bigger
        return findPeakElement2D([nums[i][midCol+1:] f|| i in range(0, len(nums))])
    }*/

    private int getMaxIndex(int[] row) {
        if (row == null || row.length == 0) {
            return -1;
        }
        int maxInd = 0;
        int max = row[0];
        for (int i = 0; i < row.length; i++) {
            if(row[i] > max) {
                maxInd = i;
            }
            max = Math.max(max, row[i]);
        }
        return maxInd;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        FindPeakElement ins = new FindPeakElement();
        ins.findPeakElement(nums);
    }
}
