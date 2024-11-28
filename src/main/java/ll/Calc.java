package ll;

public class Calc {
    public static int run(String expression){
        expression = expression.replaceAll("\\s+", "");

        if(!checkClosingParentheses(expression)) {
            throw new IllegalArgumentException("괄호 쌍이 맞지 않습니다.");
        }

        return caluRecusive(expression);
    }

    private static int caluRecusive(String expression){
        int answer;

        int left = 0;
        int right = 0;
        char operator = ' ';

        for(char ch : expression.toCharArray()){
            if('0' <= ch && ch <= '9'){
               if (left == 0) left = ch - '0';
               else if (right == 0) right = ch - '0';
            } else if(  ch == '+' ||
                        ch == '-' ||
                        ch == '*' ||
                        ch == '/' ){
                operator = ch;
            }
        }

        answer = operatorCalculation(left, right, operator);

        return answer;
    }

    private static int operatorCalculation(int right, int left, char operator){
        int answer;

        switch (operator) {
            case '+' -> answer = left + right;
            case '-' -> answer = left - right;
            case '*' -> answer = left * right;
            case '/' -> answer = left / right;
            default -> throw new IllegalArgumentException("지원되지 않는 연산자입니다.");
        }

        return answer;
    }

    /**
     * 괄호쌍 검증하는 수식
     * @param expression 검증할 수식
     * @return 괄호쌍이 맞으면 true, 안맞으면 false
     *
     * @since 2024.11.28
     * @author han
     */
    private static boolean checkClosingParentheses(String expression){
        // 1. 괄호 수 검증
        int count = 0;
        for(char ch : expression.toCharArray()) {
            if(ch == '(') count++;
            if(ch == ')') count--;
            if(count < 0) return false;
        }

        // 2. 괄호 쌍이 맞을 시 반환
        return count == 0;
    }
}

