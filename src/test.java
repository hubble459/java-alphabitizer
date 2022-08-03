import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class test {
    public static void main(String[] args) throws IOException {
        new test().run();
    }
    private void run() throws IOException {
        new Main().showFiles();

        Scanner sc = new Scanner(System.in);
        System.out.println("What is the filename?");
        System.out.print("/~: ");
        String fileName = sc.nextLine();
        while (!fileName.contains(".txt")) {
            System.out.println("This is not a text file! Try again.");
            System.out.print("/~: ");
            fileName = sc.nextLine();
        }
        String filePath = fileName.contains("\\") ? fileName : System.getProperty("user.dir") + "\\src\\" + fileName;

        Object[][] file;
        file = fileReader(filePath);
        file = dimensionalSort(file);
        fileWriter(file, filePath);

        System.out.println();
        System.out.println("Done!");
    }

    private Object[][] dimensionalSort(Object[][] file) {
        Object[] toSort = new String[file.length];
        Object[][] fileSorted = new Object[file.length][2];
        for (int i = 0; i < file.length; i++) {
            toSort[i] = file[i][1];
        }
        Arrays.sort(toSort);
        int j = 0;
        for (int i = 0; i < file.length; i++) {
            for (int k = 0; k < file.length; k++) {
                if (toSort[i] == file[k][1]) {
                    fileSorted[j][0] = file[k][0];
                    fileSorted[j++][1] = file[k][1];
                }
            }
        }
        return fileSorted;
    }

    private Object[][] fileReader(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        Object[] file = reader.lines().toArray();
        Object[][] fileSplit = new Object[file.length][2];
        String[] split;
        for (int i = 0; i < file.length; i++) {
            split = file[i].toString().split(" ");
            if (split.length != 2) {
                System.err.println("The text file is not written correctly");
                System.exit(0);
                return new Object[0][0];
            } else {
                fileSplit[i][0] = split[0];
                fileSplit[i][1] = split[1];
            }
        }
        reader.close();
        return fileSplit;
    }

    private void fileWriter(Object[][] file, String filePath) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        for (int i = 0; i < file.length; i++) {
            writer.write(file[i][0] + " " + file[i][1]);
            if (i != file.length - 1) writer.newLine();
        }
        writer.flush();
        writer.close();
    }
}
