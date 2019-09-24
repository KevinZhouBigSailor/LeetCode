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

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        FindPeakElement ins = new FindPeakElement();
        ins.findPeakElement(nums);
    }
}
