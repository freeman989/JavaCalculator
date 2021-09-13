package Console;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Yuriy Vins
 */

public class Calculator {

    public static String[] numbers = new String[]{
            "1","2","3","4","5","6","7","8","9","10",
            "I","II","III","IV","V","VI","VII","VIII","IX","X"
    };
    public static char[] symbols = new char[]{
            '0','1','2','3','4','5','6','7','8','9','I','V','X'
    };

    public static void main(String[] args) throws MyException.InvalidOperationException, MyException.IncorrectRomanNumbersException, MyException.InvalidMathOperationFormat, MyException.InvalidSymbolException {
        int start=0;
        String number1="", number2="";
        String roman_result="";
        int result = 0;
        int operator_count = 0;
        char operator = ' ';
        List operators = new ArrayList();
        boolean section = false;
        boolean roman_number1 = false;
        boolean roman_number2 = false;
        boolean isCorrectNumber1 = false;
        boolean isCorrectNumber2 = false;
        boolean isCorrectSymbol = false;
        //--Main loop.
        do {
            for(; start<1; start++)
                System.out.println("Вас приветствует консольный Java-калькулятор!");

            Scanner in = new Scanner(System.in);
            System.out.println("Введите выражение в одной строке :");
            String str = in.nextLine();

            //--- Поиск символа неявляющимся числом.
            for(int i=0; i<str.length(); i++){
                for(int j=0; j< symbols.length; j++){
                    if(str.charAt(i) == symbols[j]){
                        isCorrectSymbol = true;
                    }
                }
                if(isCorrectSymbol == false){
                    operator = str.charAt(i);
                    section = true;
                    operator_count++;
                    operators.add(operator);
                }
                else if(!section){
                    number1 += Character.toString(str.charAt(i));
                }
                else{
                    number2 += Character.toString(str.charAt(i));
                }
                isCorrectSymbol = false;
            }

            //--- Проверка на ввод правильных символов.
            for(int i=0; i<numbers.length; i++) {
                if (number1.equals(numbers[i]))
                    isCorrectNumber1 = true;
            }
            for(int i=0; i<numbers.length; i++) {
                if (number2.equals(numbers[i]))
                    isCorrectNumber2 = true;
            }

            if(operator_count > 1){
                if(operators.get(0).equals('+') || operators.get(0).equals('-') || operators.get(0).equals('*') || operators.get(0).equals('/'))
                    if(operators.get(1).equals('+') || operators.get(1).equals('-') || operators.get(1).equals('*') || operators.get(1).equals('/'))
                        throw new MyException.InvalidMathOperationFormat();
            }

            //--- Проверка. Римские цифры или арабские.
            switch (number1){
                case "I" : number1 = "1"; roman_number1 = true; break;
                case "II" : number1 = "2"; roman_number1 = true; break;
                case "III" : number1 = "3"; roman_number1 = true; break;
                case "IV" : number1 = "4"; roman_number1 = true; break;
                case "V" : number1 = "5"; roman_number1 = true; break;
                case "VI" : number1 = "6"; roman_number1 = true; break;
                case "VII" : number1 = "7"; roman_number1 = true; break;
                case "VIII" : number1 = "8"; roman_number1 = true; break;
                case "IX" : number1 = "9"; roman_number1 = true; break;
                case "X" : number1 = "10"; roman_number1 = true; break;
            }
            switch (number2){
                case "I" : number2 = "1"; roman_number2 = true; break;
                case "II" : number2 = "2"; roman_number2 = true; break;
                case "III" : number2 = "3"; roman_number2 = true; break;
                case "IV" : number2 = "4"; roman_number2 = true; break;
                case "V" : number2 = "5"; roman_number2 = true; break;
                case "VI" : number2 = "6"; roman_number2 = true; break;
                case "VII" : number2 = "7"; roman_number2 = true; break;
                case "VIII" : number2 = "8"; roman_number2 = true; break;
                case "IX" : number2 = "9"; roman_number2 = true; break;
                case "X" : number2 = "10"; roman_number2 = true; break;
            }
            if(number2 == ""){
                throw new MyException.InvalidOperationException();
            }
            //--- Проверка на то чтобы числа были от одного до 1-го до 10-ти.
            try{
                if(Integer.parseInt(number1) > 10 || Integer.parseInt(number2) > 10)
                    System.out.println("Числа должны быть не больше 10-ти!");
            }
            catch(NumberFormatException e){
                System.out.println("Если это римские числа, то они должны быть от I до X-ти!");
            }

            if(!isCorrectNumber1 || !isCorrectNumber2)
                throw new MyException.InvalidSymbolException();

            //--- Проверка, чтобы оба операнда были либо арабскими, либо римскими.
            if((roman_number1 && !roman_number2) || (!roman_number1 && roman_number2)){
                try {
                    throw new IOException();
                }
                catch (IOException e) {
                    System.out.println(e+" Используются одновременно разные системы счисления!");
                    break;
                }
            }

            switch (operator){
                case '+' : result = Integer.parseInt(number1) + Integer.parseInt(number2); break;
                case '-' : result = Integer.parseInt(number1) - Integer.parseInt(number2); break;
                case '*' : result = Integer.parseInt(number1) * Integer.parseInt(number2); break;
                case '/' : result = Integer.parseInt(number1) / Integer.parseInt(number2); break;
                default: throw new MyException.InvalidOperationException();
            }
            //---Преобразование результата операции в римские цифры.
            if(roman_number1 && roman_number2){

                roman_result = RomanConverter.Converter(result,roman_result);

                if(roman_result.equals(" ")){
                    throw new MyException.IncorrectRomanNumbersException();
                }
                else
                    System.out.println("result = "+roman_result);
            }
            else
                System.out.println("result = "+result);

            //--- Продолжить работу с калькулятором или нет?
            System.out.println(" ");
            System.out.println("Продолжим?");
            System.out.println("'Y' - Да!   'N' - Нет!");

            str = in.nextLine();

            while (!str.equals("Y") && !str.equals("N") && !str.equals("y") && !str.equals("n")) {
                System.out.println("Пожалуйста, введите или 'Y' или 'N' .");
                str = in.nextLine();
            }
            if(str.equals("N") || str.equals("n")){
                System.out.println("Пока, пока..)");
                return;
            }
            else if(str.equals("Y") || str.equals("y")) {
                //--- Обнуление переменных.
                number1 = "";
                number2 = "";
                roman_result = "";
                result = 0;
                operator_count = 0;
                operator = ' ';
                section = false;
                roman_number1 = false;
                roman_number2 = false;
                isCorrectNumber1 = false;
                isCorrectNumber2 = false;
                isCorrectSymbol = false;
            }
        }
        while (true);
    }
}
