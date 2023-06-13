package src.commands;

import src.commands.AllCommands.*;

import java.util.HashMap;

public class Invoker {

    private static String[] split;

    private static final HashMap<String, Command> commandHashMap = new HashMap<>();

    static {
        commandHashMap.put("add", new Add());
        commandHashMap.put("update", new Update());
        commandHashMap.put("remove_by_id", new RemoveById());
        commandHashMap.put("clear", new Clear());
        commandHashMap.put("execute_script", new ExecuteScript());
        commandHashMap.put("count_greater_than_type", new CountGreaterThanType());
        commandHashMap.put("remove_all_by_type", new RemoveAllByType());
    }

    public static String[] getSplit() {
        return split;
    }

    public static void setSplit(String[] split) {
        Invoker.split = split;
    }

    public static HashMap<String, Command> getCommandHashMap() {
        return commandHashMap;
    }
}
