package freecoding.exercise.hackersrank.countingvalleys;

import static org.junit.Assert.assertEquals;

public class Solution {
    public static int countingValleys(int steps, String path) {
        String[] split = path.split("");
        int sealevel = 0;
        int valleys = 0;
        boolean valleyStart = false;
        for (int i = 0; i < split.length; i++) {
            if (split[i].equals("D")) {
                sealevel--;
                if(sealevel == -1) valleyStart = true;
            } else {
                sealevel++;
                if(sealevel == 0) {
                    valleyStart = false;
                    valleys ++;
                }
            }

        }

        return valleys;
    }

    public static void main(String[] args) {
        assertEquals(1, countingValleys(8, "UDDDUDUU"));
        assertEquals(1, countingValleys(8, "DDUUUUDD"));
    }
}
