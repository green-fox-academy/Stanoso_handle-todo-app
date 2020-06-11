public class ToDoAppMain {

    public static void main(String[] args) {
        ToDo note = new ToDo();

        if (args.length == 0) {
            printArguments();
        } else if (args[0].equals("-a")) {
            note.addToDoNote(args);
        } else if (args[0].equals("-l")) {
            note.listTasks();
        } else if (args[0].equals("-r")) {
            note.removeToDoNote(args);
        }else if (args[0].equals("-c")) {
            note.completeTask(args);
        }else{
            System.out.println("Unsupported argument!");
            printArguments();
        }
    }

    public static void printArguments() {
        System.out.println("Command line arguments:");
        System.out.println("-l   Lists all the tasks");
        System.out.println("-a   Adds a new task");
        System.out.println("-r   Removes an task");
        System.out.println("-c   Completes an task");
    }

}
