package Chime;

import java.util.Arrays;
import java.util.Stack;

public class HighestBuildingCanSee {
    public int[] highestBuildings(int[] buildings) {
        Stack<Integer> leftStack = new Stack();
        Stack<Integer> rightStack = new Stack();
        int len = buildings.length;
        int[] res = new int[len];
        int[] leftMax = new int[len];
        int[] rightMax = new int[len];

        for (int i = 1; i < buildings.length; i++) {
            if (leftStack.isEmpty()) {
                leftStack.push(buildings[i - 1]);
            }
            if (buildings[i] <= buildings[i - 1]) {
                leftMax[i] = buildings[i - 1];
            } else {
                while (leftStack.size() > 1 && buildings[i] > leftStack.peek()) {
                    leftStack.pop();
                }
                leftMax[i] = leftStack.peek();
                // reset stack with current max because current building can block them
                while (!leftStack.isEmpty() && buildings[i] > leftStack.peek()) {
                    leftStack.pop();
                }
                leftStack.push(buildings[i]);
            }
        }

        for (int i = buildings.length - 1; i > 0; i--) {
            if (rightStack.isEmpty()) {
                rightStack.push(buildings[i]);
            }
            if (buildings[i] >= buildings[i - 1]) {
                rightMax[i - 1] = buildings[i];
            } else {
                while (rightStack.size() > 1 && buildings[i - 1] > rightStack.peek()) {
                    rightStack.pop();
                }
                rightMax[i - 1] = rightStack.peek();
                // reset stack with current max because current building can block them
                while (!rightStack.isEmpty() && buildings[i - 1] > rightStack.peek()) {
                    rightStack.pop();
                }
                rightStack.push(buildings[i - 1]);
            }
        }

        System.out.println("Left:   " + Arrays.toString(leftMax));
        System.out.println("Right:  " + Arrays.toString(rightMax));

        for (int i = 0; i < buildings.length; i++) {
            res[i] = Math.max(leftMax[i], rightMax[i]);
        }

        return res;
    }

    public static void main(String[] args) {
        HighestBuildingCanSee instance = new HighestBuildingCanSee();
        int[] buildings = {9, 1, 3, 7, 5, 6, 4, 3, 2, 1};
        System.out.println("Origin: " + Arrays.toString(buildings));
        System.out.println("Output: " +
                Arrays.toString(
                        instance.highestBuildings(buildings)));
    }

    class Building {
        int index;
        int leftMax;
        int rightMax;

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public int getLeftMax() {
            return leftMax;
        }

        public void setLeftMax(int leftMax) {
            this.leftMax = leftMax;
        }

        public int getRightMax() {
            return rightMax;
        }

        public void setRightMax(int rightMax) {
            this.rightMax = rightMax;
        }
    }
}
