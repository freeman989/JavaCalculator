package Console;

public class MyException{

    static class InvalidSymbolException extends Exception {
        public InvalidSymbolException(){
            super("Введены неправильные символы!");
        }
    }
    static class InvalidOperationException extends Exception {
        public InvalidOperationException(){
            super("Строка не является математической операцией!");
        }
    }
    static class IncorrectRomanNumbersException extends Exception {
        public IncorrectRomanNumbersException(){
            super("В римской системе нет отрицательных чисел!");
        }
    }
    static class InvalidMathOperationFormat extends Exception{
        public InvalidMathOperationFormat(){
            super("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *).");
        }
    }
}
