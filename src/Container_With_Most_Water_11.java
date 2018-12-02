/*
 * Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). 
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.

Note: You may not slant the container.
 */

public class Container_With_Most_Water_11 {
	public int maxArea(int[] height) {
        int lpoint = 0, rpoint = height.length - 1;
        int area = 0;
        while (lpoint < rpoint) {
            area = Math.max(area, Math.min(height[lpoint], height[rpoint]) *
                    (rpoint - lpoint));
            if (height[lpoint] > height[rpoint])
                rpoint--;
            else
                lpoint++;
        }
        return area;
    }
}
