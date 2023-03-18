class Solution {
    /*
        Approach: For every coming token, if it is number, then simply add it to stack but if any
        operator will come, then pop the first 2 tokens from the stack, perform the operation and 
        push the result of the operation in the stack again. At the last only one token will remain in the stack, that will be the answer. 
        @rhythm_varshney

    */
    public int stepCalculation(int value1, int value2, String ch){
        return switch (ch) {
            case "+" -> value1 + value2;
            case "-" -> value1 - value2;
            case "*" -> value1 * value2;
            case "/" -> value1 / value2;
            default -> throw new UnsupportedOperationException();
        };
    }
    public int evalRPN(String[] tokens) {
        Stack<Integer> st=new Stack<>();
       for(String token: tokens){
            try{
                st.push(Integer.parseInt(token));
            }catch(Exception e){
                int val2=st.pop();
               int val1=st.pop();
               int result= stepCalculation(val1,val2,token);
               st.push(result);
            }
       }
        return st.peek();
    }
}