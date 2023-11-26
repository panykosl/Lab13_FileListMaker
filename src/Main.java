import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import static java.nio.file.StandardOpenOption.CREATE;
public class Main {
    static ArrayList<String> list = new ArrayList<>();
    static boolean needsSave = false;
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        final String menu = "\nA-Add D-Delete V-View O-Open S-Save C-Clear Q-Quit";
        boolean done = false;
        String response;
        String cmd = "";

        do{
            displayList();
            cmd = SafeInput.getRegExString(in, menu, "[AaDdVvQqOoSsCc]");
            cmd = cmd.toUpperCase();

            switch(cmd)
            {
                case "A":
                    String item;
                    item = SafeInput.getNonZeroLenString(in,"What item do you want to add?");
                    list.add(item);
                    needsSave = true;
                    break;

                case "D":
                    int delete;
                    int high;
                    int low;
                    low = 1;
                    high = list.size();
                    delete = SafeInput.getRangedInt(in,"What item do you want to delete?", low, (high));
                    delete = delete - 1;
                    list.remove(delete);
                    needsSave = true;
                    break;

                case "V":
                    displayList();
                    break;

                case "Q":
                    String saveConfirm;
                    saveConfirm = SafeInput.getYNConfirm(in, "Do you want to save your list?");
                    if (saveConfirm.equalsIgnoreCase("y")) {
                        saveList();
                    } else if (saveConfirm.equalsIgnoreCase("n")) {
                        response = SafeInput.getYNConfirm(in, "Are you sure you want to quit?");
                        if (response.equalsIgnoreCase("y")) {
                            done = true;
                        } else if (response.equalsIgnoreCase("n")) {
                            done = false;
                        }
                    }
                    break;
                case "O":
                    openFile();
                    break;
                case "S":
                    saveList();
                    break;
                case "C":
                    clearList();
                    break;
            }
        }while(!done);
    }
    private static void openFile() {
        JFileChooser chooser = new JFileChooser();
        Scanner inFile;
        String line;
        Path target = new File(System.getProperty("user.dir")).toPath();
        target = target.resolve("src");
        chooser.setCurrentDirectory(target.toFile());

        if (!needsSave) {
            try
            {
                if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
                {
                    target = chooser.getSelectedFile().toPath();
                    inFile = new Scanner(target);
                    list.clear();
                    while(inFile.hasNextLine())
                    {
                        list.add(inFile.nextLine());
                    }
                    inFile.close();
                }
                else
                {
                    System.out.println("Sorry, you must select a file! Terminating!");
                    System.exit(0);
                }
            }
            catch (FileNotFoundException e)
            {
                System.out.println("File Not Found Error");
                e.printStackTrace();
            }
            catch (IOException e)
            {
                System.out.println("IOException Error");
                e.printStackTrace();
            }
        }
        else {
            String userConfirm = SafeInput.getYNConfirm(in, "Your list has not been saved yet. Opening the file will terminate your current list. Do you want to save your list first?");

            if (userConfirm.equalsIgnoreCase("y")) {
                saveList();
            } else if (userConfirm.equalsIgnoreCase("n")) {
                try
                {
                    if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
                    {
                        target = chooser.getSelectedFile().toPath();
                        inFile = new Scanner(target);
                        list.clear();
                        while(inFile.hasNextLine())
                        {
                            list.add(inFile.nextLine());
                        }
                        inFile.close();

                    }
                    else
                    {
                        System.out.println("Sorry, you must select a file! Terminating!");
                        System.exit(0);
                    }
                }
                catch (FileNotFoundException e)
                {
                    System.out.println("File Not Found Error");
                    e.printStackTrace();
                }
                catch (IOException e)
                {
                    System.out.println("IOException Error");
                    e.printStackTrace();
                }
            }

        }

    }
    private static void saveList() {
        try {
            String fileName = SafeInput.getNonZeroLenString(in, "Name the file that you want to save your list in");
            fileName = fileName + ".txt";
            File myFile = new File(fileName);
            if (myFile.createNewFile()) {
                System.out.println("File created: " + myFile.getName());
                System.out.println("Saving list in file " + myFile.getName());
                BufferedWriter writer =
                        new BufferedWriter(new FileWriter(fileName));

                for(String rec : list)
                {
                    writer.write(rec, 0, rec.length());
                    writer.newLine();
                }
                writer.close();
                System.out.println("Data file written!");
                needsSave = false;
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    private static void clearList() {
        if (!needsSave) {
            list.clear();
            System.out.println("Your list has been cleared");
        }
        else {
            String userConfirm = SafeInput.getYNConfirm(in, "Your list has not been saved yet. Clearing the list will terminate everything in your current list. Do you want to save your list first?");

            if (userConfirm.equalsIgnoreCase("y")) {
                saveList();
            } else if (userConfirm.equalsIgnoreCase("n")) {
                list.clear();
                System.out.println("Your list has been cleared");
            }
        }
    }
    private static void displayList()
    {
        if(list.size() != 0) {

            for (int i = 0; i < list.size(); i++) {
                System.out.printf("\n%-3d%-35s", i + 1, list.get(i));
            }
        }
        else
            System.out.println("List is empty");
    }
}