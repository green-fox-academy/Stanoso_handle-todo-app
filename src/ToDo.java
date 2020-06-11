import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.List;

public class ToDo {

    String toDoNote = "";

    public void addToDoNote(String[] argument) {
        try {
            if (argument.length == 1) {
                System.out.println("Unable to add: no taks provided");
            } else {
                Path filePath = Paths.get("sourceFiles/toDoNotes.txt");
                this.toDoNote = argument[1];
                Files.write(filePath, Collections.singleton(toDoNote + ";f"), StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            System.out.println("File not found.");
        }
    }

    public void listTasks() {
        try {
            Path filePath = Paths.get("sourceFiles/toDoNotes.txt");
            List<String> lines = Files.readAllLines(filePath);
            if (lines.isEmpty()) {
                System.out.println("No todos for today");
            } else {
                for (int i = 0; i < lines.size(); i++) {
                    if (lines.get(i).substring(lines.get(i).length() - 1).equals("f")) {
                        System.out.println((i + 1) + " - [ ] "  + lines.get(i).substring(0, lines.get(i).length() - 2));
                    } else {
                        System.out.println( (i + 1) + " - [X] " + lines.get(i).substring(0, lines.get(i).length() - 2));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("File not found.");
        }
    }

    public void removeToDoNote(String[] argument) {
        try {
            Path filePath = Paths.get("sourceFiles/toDoNotes.txt");
            List<String> lines = Files.readAllLines(filePath);
            boolean isReallyInt = isInteger(argument[1]);
            if (lines.isEmpty()) {
                System.out.println("Nothing to remove");
            } else if (!isReallyInt) {
                System.out.println("Unable to remove: index is not a number");
            } else if (lines.size() < Integer.parseInt(argument[1]) || Integer.parseInt(argument[1]) < 0) {
                System.out.println("Unable to remove: index is out of bound");
            } else {
                lines.remove(Integer.parseInt(argument[1])-1);
                Files.write(filePath, lines);
            }
        } catch (IOException e) {
            System.out.println("File not found.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Unable to remove: no index provided");
        }
    }

    public void completeTask(String[] argument) {
        try {
            Path filePath = Paths.get("sourceFiles/toDoNotes.txt");
            List<String> lines = Files.readAllLines(filePath);
            boolean isReallyInt = isInteger(argument[1]);
            if (lines.isEmpty()) {
                System.out.println("Nothing to check");
            } else if (!isReallyInt) {
                System.out.println("Unable to check: index is not a number");
            } else if (lines.size() < Integer.parseInt(argument[1]) || Integer.parseInt(argument[1]) < 0) {
                System.out.println("Unable to check: index is out of bound");
            } else {
                String complete = lines.get(Integer.parseInt(argument[1])-1);
                complete = complete.substring(0,complete.length()-1)+"t";
                lines.set(Integer.parseInt(argument[1])-1,complete);
                Files.write(filePath, lines);
            }
        } catch (IOException e) {
            System.out.println("File not found.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Unable to check: no index provided");
        }
    }

    public static boolean isInteger(String s) {
        boolean isValidInteger = false;
        try {
            Integer.parseInt(s);
            isValidInteger = true;
        } catch (NumberFormatException ex) {
        }
        return isValidInteger;
    }

}



