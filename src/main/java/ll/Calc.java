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
        // 단일 숫자
        if (!containsOperator(expression)) {
            return Integer.parseInt(expression);
        }

        // 먼저 곱셈/나눗셈 처리
        //int operatorIndex = findMultiDivOperator(expression);
        int operatorIndex = findOperator(expression, 0);

        if (operatorIndex == -1) {
            // 곱셈/나눗셈이 없으면 덧셈/뺄셈 처리
            operatorIndex = findAddMinusOperator(expression);
        }

        // 왼쪽 피연산자와 연산자 분리
        int left = Integer.parseInt(expression.substring(0, operatorIndex));
        char operator = expression.charAt(operatorIndex);

        // 오른쪽 피연산자 구하기
        int rightEndIndex = findOperator(expression, operatorIndex + 1);
        int right = Integer.parseInt(expression.substring(operatorIndex + 1, rightEndIndex));

        // 연산 수행
        int answer = operatorCalculation(left, right, operator);

        if(rightEndIndex < expression.length()) {
            String nextCalExpression = answer + expression.substring(rightEndIndex);
            return calReclusive(nextCalExpression);
        }

        System.out.println(answer);
        return answer;
    }

    private static int findOperator(String expression, int startIndex){
        boolean isMinus = true;  // 음수 판별

        for (int i = startIndex; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            // 연산자 중 +, -, *, / 확인
            if (ch == '+' || ch == '*' || ch == '/') {
                return i;  // 연산자 처리
            }

            // -가 연산자인지 확인
            if (ch == '-'){
                isMinus = i == 0 ||
                        expression.charAt(i - 1) == '+' ||
                        expression.charAt(i - 1) == '-' ||
                        expression.charAt(i - 1) == '*' ||
                        expression.charAt(i - 1) == '/';
            }

            // 연산자 일경우 반환
            if (!isMinus) {
                return i;
            }

        }

        return expression.length();
    }

    // 곱셈/나눗셈 연산자 우선 찾기
    private static int findMultiDivOperator(String expression) {
        for (int i = 1; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            if (ch == '*' || ch == '/') {
                return i;
            }
        }
        return -1;
    }

    // 일반 연산자 찾기
    private static int findAddMinusOperator(String expression) {
        for (int i = 1; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            if (ch == '+' || ch == '-') {
                return i;
            }
        }
        return -1;
    }

    /**
     * 연산자 포함 문자열인지 판별하는 수식
     * @param expression 문자열
     * @return 연산자 포함하면 true, 없으면 false
     *
     * @since 2024.11.28
     * @author han
     */
    private static boolean containsOperator(String expression) {
        return expression.contains("+") ||
                expression.contains("-") ||
                expression.contains("*") ||
                expression.contains("/");
    }


    /**
     * 연산 수행하는 수식
     * @param left 왼쪽 값
     * @param right 오른쪽 값
     * @param operator 연삱
     * @return 계산 값
     * @throws IllegalArgumentException 연산자 불일치 하면 "지원하지 않는 연산자입니다." 출력
     *
     * @since 2024.11.18
     * @author han
     */
    private static int operatorCalculation(int left, int right, char operator){
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