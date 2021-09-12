package Console;
import java.io.IOException;
import java.lang.NumberFormatException;
import java.util.Scanner;

/*public class InvalidSymbolException extends Exception {

    public static void  Message(String message){
        System.out.println(message);
    }
}*/

public class Calculator {

    public static String[] symbols = new String[]{
            "1","2","3","4","5","6","7","8","9","10",
            "I","II","III","IV","V","VI","VII","VIII","IX","X"
    };

    public static void main(String[] args){
        int start=0;
        String number1="", number2="";
        String roman_result="";
        int result = 0;
        char operator = ' ';
        boolean section = false;
        boolean roman_number1 = false;
        boolean roman_number2 = false;
        //--Main loop.
        do {
            for(; start<1; start++)
                System.out.println("Вас приветствует консольный Java-калькулятор!");

            Scanner in = new Scanner(System.in);
            System.out.println("Введите выражение в одной строке :");
            String str = in.nextLine();

            //--- Проверка на ввод правильных символов.
            /*try {
                 for(int i=0; i<symbols.length; i++) {
                     if (!str.equals(symbols[i]))
                         throw new NumberFormatException();
                    }
                }
                catch (NumberFormatException e) {
                    System.out.println(e+" Введены неправильные символы! Так дело не пойдет!");
                    break;
                }*/

            for(int i=0; i<str.length(); i++){
                if(str.charAt(i) == '+' || str.charAt(i) == '-' || str.charAt(i) == '*' || str.charAt(i) == '/'){
                  operator = str.charAt(i);
                  section = true;
                }
                else if(!section){
                    number1 += Character.toString(str.charAt(i));
                }
                else{
                    number2 += Character.toString(str.charAt(i));
                }
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
                default: System.out.println(" ");
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
                default: System.out.println(" ");
            }
            //--- Проверка, чтобы оба операнда были либо арабскими, либо римскими.
            if((roman_number1 && !roman_number2) || (!roman_number1 && roman_number2)){
                try {
                    throw new IOException();
                }
                catch (IOException e) {
                    System.out.println(e+"  Числа должны быть однотипными! Либо только арабскими, либо только римскими!");
                    break;
                }
            }

            switch (operator){
                case '+' : result = Integer.parseInt(number1) + Integer.parseInt(number2); break;
                case '-' : result = Integer.parseInt(number1) - Integer.parseInt(number2); break;
                case '*' : result = Integer.parseInt(number1) * Integer.parseInt(number2); break;
                case '/' : result = Integer.parseInt(number1) / Integer.parseInt(number2); break;
                default: System.out.println("Неверная операция!");
            }
            //---Преобразование результата операции в римские цифры.
            if(roman_number1 && roman_number2){

                roman_result = RomanConverter.Converter(result,roman_result);

                if(roman_result.equals(" ")){
                    try{
                        throw new NumberFormatException();
                    }
                    catch (NumberFormatException e){
                        System.out.println("Результат операции римских чисел меньше 1-го!");
                    }
                    //InvalidSymbolException("Результат операции римских чисел меньше 1-го!")
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

            while (!str.equals("Y") && !str.equals("N")) {
                System.out.println("Пожалуйста, введите или 'Y' или 'N' .");
                str = in.nextLine();
            }
            if(str.equals("N")){
                System.out.println("Пока, пока..)");
                return;
            }
            else if(str.equals("Y")) {
                //--- Обнуление переменных.
                number1 = "";
                number2 = "";
                roman_result = "";
                result = 0;
                operator = ' ';
                section = false;
                roman_number1 = false;
                roman_number2 = false;
            }
        }
        while (true);
    }
}
