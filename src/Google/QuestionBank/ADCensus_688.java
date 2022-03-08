package Google.QuestionBank;

import java.util.*;

public class ADCensus_688 {
    class Node {

    }

    class Message {
        public List<String> getModelList() {
            return new ArrayList<String>();
        }
    }

    interface ProtocolHelper {
        Node[] getNeighbors();

        void send(Node dest, Message m);

        void done();// Called by the initiator when traversal is over
    }

    interface ProtocolEvents {
        void onInitiate(); // Called only for initiator to start the process

        void onReceive(Node src, Message m);
    }

    class ProtocolImplementation implements ProtocolEvents {
        private final String myModel = "---";
        private ProtocolHelper helper;
        private boolean isInitiator = false;
        private Set<Node> nodesToProcess;
        private Node parent;

        public ProtocolImplementation(ProtocolHelper helper) {
            this.helper = helper;
            // It is important to retain the order of the elements in the set.
            this.nodesToProcess = new LinkedHashSet<>(Arrays.asList(helper.getNeighbors()));
        }

        @Override
        public void onInitiate() {
            isInitiator = true;
            List<String> modelList = new ArrayList<>();
            modelList.add(myModel);
            if (nodesToProcess.isEmpty()) {
                helper.done();
            } else {
                helper.send(nodesToProcess.iterator().next(), modelList);
            }
        }

        @Override
        public void onReceive(Node src, Message m) {
            List<String> modelList = m.getModelList();
            if (parent == null && !isInitiator) {
                // 1. If it is the first time you receive this trolley, please do the following
                parent = src;
                modelList.add(myModel);
            } else if (!nodesToProcess.isEmpty() && src != nodesToProcess.iterator().next()) {
                // 2. If you have received the trolley back not from the neighbor you have sent it to last time, just
                // cross out the neighbor from your list and send the trolley back
                nodesToProcess.remove(src);
                helper.send(src, m);
                return;
            }
            // 3. Cross out from your list the neighbor you've received the trolley from
            nodesToProcess.remove(src);
            // 4. If there are still neighbors on your list, send the trolley to the first one otherwise, send the trolley
            // back to the parent
            if (!nodesToProcess.isEmpty()) {
                helper.send(nodesToProcess.iterator().next(), m);
            } else {
                if (!isInitiator) {
                    helper.send(parent, m);
                } else {
                    helper.done();
                }
            }
        }
    }
}
