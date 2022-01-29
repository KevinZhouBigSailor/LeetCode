package Facebook;

import java.util.Stack;

public class SimplifyPath_71 {
    public String simplifyPath(String path) {
        StringBuilder sb = new StringBuilder("/");
        Stack<String> stack = new Stack<String>();
        for (String s : path.split("/")) {
            if (s.equals("..")) {
                if (!stack.isEmpty())
                    stack.pop();
            } else if (!s.equals("") && !s.equals("."))
                stack.add(s);
        }
        for (String s : stack) {
            sb.append(s + "/");
        }
        if (!stack.isEmpty()) sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    public static void main(String[] args) {
        SimplifyPath_71 instance = new SimplifyPath_71();
        System.out.println(instance.simplifyPath("/home//foo/"));
    }

    /**
     * Initialize a stack, S that we will be using for our implementation.
     * Split the input string using / as the delimiter. This step is really important because no matter what, the given input is a valid path and we simply have to shorten it. So, that means that whatever we have between two / characters is either a directory name or a special character and we have to process them accordingly.
     * Once we are done splitting the input path, we will process one component at a time.
     * If the current component is a . or an empty string, we will do nothing and simply continue. Well if you think about it, the split string array for the string /a//b would be [a,,b]. yes, that's an empty string in between a and b. Again, from the perspective of the overall path, it doesn't mean anything.
     * If we encounter a double-dot .., we have to do some processing. This simply means go one level up in the current directory path. So, we will pop an entry from our stack if it's not empty.
     * Finally, if the component we are processing right now is not one of the special characters, then we will simply add it to our stack because it's a legitimate directory name.
     * Once we are done processing all the components, we simply have to connect all the directory names in our stack together using / as the delimiter and we will have our shortest path that leads us to the same directory as the one provided as an input.
     */
    class Solution {
        public String simplifyPath(String path) {

            // Initialize a stack
            Stack<String> stack = new Stack<String>();
            String[] components = path.split("/");

            // Split the input string on "/" as the delimiter
            // and process each portion one by one
            for (String directory : components) {

                // A no-op for a "." or an empty string
                if (directory.equals(".") || directory.isEmpty()) {
                    continue;
                } else if (directory.equals("..")) {

                    // If the current component is a "..", then
                    // we pop an entry from the stack if it's non-empty
                    if (!stack.isEmpty()) {
                        stack.pop();
                    }
                } else {

                    // Finally, a legitimate directory name, so we add it
                    // to our stack
                    stack.add(directory);
                }
            }

            // Stich together all the directory names together
            StringBuilder result = new StringBuilder();
            for (String dir : stack) {
                result.append("/");
                result.append(dir);
            }

            return result.length() > 0 ? result.toString() : "/";
        }
    }

}
