package Google.QuestionBank;

import java.io.IOException;
import java.io.Reader;
import java.util.*;

public class DecodeTree {
    class DecodeTreeNode {
        public final Character letter;
        public final String decodedString;
        public final DecodeTreeNode[] children;

        public DecodeTreeNode(Character letter, String decodedString, DecodeTreeNode[] children) {
            this.letter = letter;
            this.decodedString = decodedString;
            this.children = children;
        }
    }

    class DecodeTreeStream implements Iterator<Character> {
        private final Reader input;
        private final DecodeTreeNode root;
        private String nextOutput = "";
        private boolean hitFailure = false;

        public DecodeTreeStream(Reader input, DecodeTreeNode root) {
            this.input = input;
            this.root = root;
        }

        @Override
        public boolean hasNext() {
            if (!nextOutput.isEmpty())
                return true;
            if (hitFailure) {
                return false;
            }

            try {
                nextOutput = findNextOutput(input, root);
            } catch (IOException e) {
                hitFailure = true;
                return false;
            }
            return !nextOutput.isEmpty();
        }

        private String findNextOutput(Reader input, DecodeTreeNode next) throws IOException {
            char letter = (char) input.read();

            for (DecodeTreeNode child : next.children) {
                if (child.letter.equals(letter)) {
                    if (!child.decodedString.isEmpty()) return child.decodedString;
                } else {
                    return findNextOutput(input, child);
                }
            }

            // no valid output, bad input
            return "";
        }

        @Override
        public Character next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Character nextChar = nextOutput.charAt(0);
            nextOutput = nextOutput.substring(1);
            return nextChar;
        }
    }
}
