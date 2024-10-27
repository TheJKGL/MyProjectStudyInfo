package malakhov.test;

public class InterviewTestAssignment3 {
    public static void main(String[] args) {
        //OK
        //OOKK
        String inputString = "OK";
//        char[] chars = inputString.toCharArray();
//        char[] result = new char[2 * inputString.length()];
//        for (int i = 0; i < inputString.length() - 1; i++) {
//            char a = chars[i];//K
//            result[i * 2] = a;
//            result[i * 2 + 1] = a;
//            //OO
//        }
//        new String(result);
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = inputString.toCharArray();
        for (int i = 0; i < inputString.length() - 1; i++) {
            stringBuilder.append(chars[i]).append(chars[i]);
        }
    }
}
