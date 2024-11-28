package ll;

public class Calc {
    public static int run(String expression){
        expression = expression.replaceAll("\\s+", "");

        if(!checkClosingParentheses(expression)) {
            throw new IllegalArgumentException("괄호 쌍이 맞지 않습니다.");
        }

        return calReclusive(expression);
    }

    private static int calReclusive(String expression){
        int answer;

        int left;
        int right;
        char operator;


        char[] operators = {'+', '-', '*', '/'};
        int operatorIndex = -1;

        for(char op : operators){
            System.out.println(expression.indexOf(op));
            if(expression.indexOf(op) != -1){
                operatorIndex = expression.indexOf(op);
                break;
            }
        }

        left = Integer.parseInt(expression.substring(0, operatorIndex));
        operator = expression.charAt(operatorIndex);
        right = Integer.parseInt(expression.substring(operatorIndex + 1));

        answer = operatorCalculation(right, left, operator);

        return answer;
    }

    private static int operatorCalculation(int left, int right, char operator){
        int answer;

        switch (operator) {
            case '+' -> answer = right + left;
            case '-' -> answer = right - left;
            case '*' -> answer = right * left;
            case '/' -> answer = right / left;
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

