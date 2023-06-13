package src.exeptions;

public class IllegalValueOfYException extends Exception{
    public IllegalValueOfYException() {
        super("Максимальное значение координаты Y: 746");
    }
}