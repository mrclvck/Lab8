package src.commands;

public class ArgsChecker {
    public static void argsChecker(int amountOfArgs) {
        if (Invoker.getSplit().length - 1 != amountOfArgs) {
            throw new NullPointerException();
        }
    }
}
