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
}
