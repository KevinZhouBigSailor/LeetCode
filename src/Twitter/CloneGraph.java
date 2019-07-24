package Twitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CloneGraph {
    public Node cloneGraph(Node node) {
        if (node == null)
            return null;
        Map<Node, Node> map = new HashMap<>();
        return dfs(node, map);
    }

    private Node dfs(Node node, Map<Node, Node> map) {
        if (map.containsKey(node))
            return map.get(node);
        Node copy = new Node(node.val, new ArrayList<>());
        map.put(node, copy);
        for (Node n : node.neighbors) {
            copy.neighbors.add(dfs(n, map));
        }
        return copy;
    }
}
