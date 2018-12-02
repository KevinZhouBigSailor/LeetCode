import java.util.*;

/*
abc de
abcd e
 */
public class AmazonOA_2 {

    public List<String> reorder(int logFileSize, List<String> logLines) {
        //customComparator(logLines);
        nodeComparator(logLines);
        return logLines;
    }

    public void nodeComparator(List<String> logLines) {
        Collections.sort(logLines, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                LogNode nodeA = new LogNode(a.substring(0, a.indexOf(" ")), a.substring(a.indexOf(" ") + 1));
                LogNode nodeB = new LogNode(a.substring(0, b.indexOf(" ")), b.substring(b.indexOf(" ") + 1));
                boolean isNumericA = Character.isDigit(nodeA.content.charAt(0));
                boolean isNumericB = Character.isDigit(nodeB.content.charAt(0));
                if (isNumericA != isNumericB) {
                    return (isNumericA) ? 1 : -1;
                } else {
                    return nodeA.content.compareToIgnoreCase(nodeB.content);
                }
            }
        });
    }

    public void customComparator(List<String> logLines) {
        Collections.sort(logLines, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                String[] arrA = a.split("\\s+");
                String[] arrB = b.split("\\s+");
                for (int i = 1; i < Math.min(arrA.length, arrB.length); i++) {
                    if (arrA[i] != arrB[i]) {
                        boolean isNumericA = isNumeric(arrA[i]);
                        boolean isNumericB = isNumeric(arrB[i]);
                        if (isNumericA != isNumericB) {
                            return (isNumericA) ? 1 : -1;
                        } else {
                            return arrA[i].compareToIgnoreCase(arrB[i]);
                        }
                    }
                }
                return arrA.length == arrB.length ? arrA[0].compareToIgnoreCase(arrB[0]) : arrB.length - arrA.length;
            }
        });
    }

    public static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    class LogNode {
        public String id;
        public String content;

        LogNode(String id, String content) {
            this.id = id;
            this.content = content;
        }
    }

    public static void main(String[] args) {
        AmazonOA_2 instance = new AmazonOA_2();
        List<String> logLines = new ArrayList<>();
        logLines.add("fdsf 2423 3244");
        logLines.add("efe2 brso fjsd");
        logLines.add("asd1 awer jikd");
        logLines.add("fhie 1328 4232");
        List<String> res = new ArrayList<>();
        res = instance.reorder(4, logLines);
        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i));
        }
    }
}




