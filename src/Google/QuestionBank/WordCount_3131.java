package Google.QuestionBank;

import java.util.*;

public class WordCount_3131 {
    /*------------------- Known API -------------------*/
    interface CWFuture {
        int Join();

        boolean isDone();
    }

    public CWFuture CountWords(int machine_num, int doc_num) {
        return null;
    }
    /*------------------- Known API -------------------*/

    class CWFutureMachine {
        int machine_num;
        CWFuture future;

        public CWFutureMachine(int machine_num, CWFuture future) {
            this.machine_num = machine_num;
            this.future = future;
        }
    }

    // Parallel w/o calling isDone()
    public int CountWords(int num_docs) {
        List<CWFuture> list = new ArrayList<>();
        for (int i = 0; i < num_docs; i++) {
            list.add(CountWords(i, i));
        }
        int result = 0;
        for (CWFuture future : list) {
            result += future.Join();
        }
        return result;
    }

    // Parallel solution w/ checking isDone() and using queue
    public int CountWords_2(int num_docs) {
        Queue<CWFuture> queue = new LinkedList<>();
        for (int i = 0; i < num_docs; i++) {
            queue.add(CountWords(i, i));
        }
        int result = 0;
        while (!queue.isEmpty()) {
            CWFuture future = queue.poll();
            if (future.isDone()) {
                result += future.Join();
            } else {
                queue.add(future);
            }
        }
        return result;
    }

    public int CountWords_Followup_2(int num_docs) {
        Map<Integer, Integer> counts = new HashMap<>(num_docs);
        Queue<CWFutureMachine> queue = new LinkedList<>();
        final int simultaneous_per_node = 2;

        int result = 0;

        for (int i = 0; i < num_docs; i++) {
            for (int j = 0; j < simultaneous_per_node; j++) {
                int machine_num = i * simultaneous_per_node + j;
                queue.add(new CWFutureMachine(machine_num, CountWords(machine_num, i)));
            }
        }

        while (!queue.isEmpty()) {
            CWFutureMachine futureMachine = queue.poll();
            int machine_num = futureMachine.machine_num;
            int doc_num = machine_num % simultaneous_per_node;
            // if (!counts.containsKey(doc_num)) continue;
            CWFuture future = futureMachine.future;
            if (future.isDone()) {
                int count = future.Join();
                if (counts.getOrDefault(machine_num, -1).equals(count)) {
                    result += count;
                } else {
                    counts.put(machine_num, count);
                    queue.add(new CWFutureMachine(machine_num, CountWords(machine_num, doc_num)));
                }
            } else {
                queue.add(futureMachine);
            }
        }

        return result;
    }
}
