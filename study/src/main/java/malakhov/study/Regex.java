package malakhov.study;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    public static void main(String[] args) {

        String input="woRld";

        if( input.matches("[A-Za-z]+") ){
            System.out.println("String contains only letters");
        }

        //=====================================================================
        //Игнорируеться регистр и шаблон первая буква "w".
        Pattern pattern = Pattern.compile("[w]\\w+",Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);
        if(matcher.matches()){
            System.out.println("Correct");
        }else{
            System.out.println("False");
        }

        //=====================================================================
        input = "12345";
        if( input.matches("[0-9]+") ){
            System.out.println("String contains only digits");
        }

        //=====================================================================
        Pattern lettersPattern = Pattern.compile("[A-Za-z]+");
        Matcher lettersMatcher = lettersPattern.matcher(input);
        if( lettersMatcher.matches()){
            System.out.println("mathes");
        }

        //=====================================================================

        /*regex = “.*foo”  input =“xfooxxxxxxfoo”   ->
        I found the text "xfooxxxxxxfoo" starting at index 0 and ending at index 13
        regex = “.*?foo”  input =“xfooxxxxxxfoo”  ->
        I found the text "xfoo" starting at index 0 and ending at index 4
        I found the text "xxxxxxfoo" starting at index 4 and ending at index 13
        regex = “.*+foo”  input =“xfooxxxxxxfoo”  ->*/

        String regex1 = ".*foo";
        String input1 = "xfooxxxxxxfoo";
        Pattern pattern1 = Pattern.compile(regex1);
        Matcher matcher1 = pattern1.matcher( input1 );
        boolean found = false;
        while (matcher1.find()) {
            System.out.format("I found the text" + " \"%s\" starting at " + "index %d and ending at index %d", matcher.group(), matcher.start(), matcher.end());
            found = true;
        }
        if(!found){ System.out.println("No match found."); }







    }
}
