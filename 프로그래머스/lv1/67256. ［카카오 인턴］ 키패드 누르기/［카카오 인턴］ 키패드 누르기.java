class Solution {
    public String solution(int[] numbers, String hand) {
        String answer = "";
        int leftX = 0;
        int leftY = 3;
        int rightX  = 2;
        int rightY = 3;
        for (int i:numbers) {
            switch (i) {
                case 1: case 4: case 7:
                    answer += "L";
                    leftX = 0;
                    leftY = i / 3;
                    break;
                case 3: case 6: case 9:
                    answer += "R";
                    rightX = 2;
                    rightY = i / 3 - 1;
                    break;
                case 2: case 5: case 8: case 0:
                    if (distance(i, leftX, leftY) == distance(i, rightX, rightY)) {
                        if (hand.equals("right")) {
                            answer += "R";
                            rightX = 1;
                            if (i == 0) rightY = 3;
                            else rightY = i / 3;
                        } else {
                            answer += "L";
                            leftX = 1;
                            if (i == 0) leftY = 3;
                            else leftY = i / 3;
                        }
                    } else if (distance(i, leftX, leftY) > distance(i, rightX, rightY)) {
                        answer += "R";
                        rightX = 1;
                        if (i == 0) rightY = 3;
                        else rightY = i / 3;
                    } else {
                        answer += "L";
                        leftX = 1;
                        if (i == 0) leftY = 3;
                        else leftY = i / 3;
                    }
                    break;
            }
        }
        return answer;
    }
    public int distance(int n, int X, int Y) {
        int d = 0;
        switch (n) {
            case 2:
                d = Math.abs(1 - X) + Math.abs(- Y);
                break;
            case 5:
                d = Math.abs(1 - X) + Math.abs(1 - Y);
                break;
            case 8:
                d = Math.abs(1 - X) + Math.abs(2 - Y);
                break;
            case 0:
                d = Math.abs(1 - X) + Math.abs(3 - Y);
                break;
        }
        return d;
    }
}