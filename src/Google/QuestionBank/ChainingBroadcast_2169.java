package Google.QuestionBank;

import java.util.*;

public class ChainingBroadcast_2169 {
    class RouterManager {
        private Map<String, Map<String, Double>> routerToNeighborAndDistance;
        private Map<String, List<String>> routerToNeighbors;

        public RouterManager(List<Router> routers, double wirelessRange) {
            for (Router source : routers) {
                for (Router dest : routers) {
                    if (!source.name.equals(dest.name)) {
                        double dist = calcDist(source, dest);
                        if (dist <= wirelessRange) {
                            routerToNeighbors.computeIfAbsent(source.name, key -> new ArrayList<>()).add(dest.name);
                            Map<String, Double> destDistance = routerToNeighborAndDistance.getOrDefault(source, new HashMap<>());
                            destDistance.put(dest.name, dist);
                            routerToNeighborAndDistance.put(source.name, destDistance);
                        }
                    }
                }
            }
        }

        public Boolean canReach(String source, String dest) {
            return routerToNeighbors.get(source).contains(dest);
        }

        private double calcDist(Router source, Router dest) {
            return Math.pow((source.x - dest.x), 2.0d) + Math.pow((source.y - dest.y), 2.0d);
        }
    }

    class Router {
        String name;
        double x, y;

        public Router(String name, double x, double y) {
            this.name = name;
            this.x = x;
            this.y = y;
        }
    }
}
