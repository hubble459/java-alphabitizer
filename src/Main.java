import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        new Main().run();
    }

    private void run() throws IOException {
        showFiles();

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

        Object[] file;
        file = fileReader(filePath);
        Arrays.sort(file);
        fileWriter(file, filePath);

        System.out.println();
        System.out.println("Done!");
    }

    public Object[] fileReader(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        Object[] file = reader.lines().toArray();
        reader.close();
        return file;
    }

    public void fileWriter(Object[] file, String filePath) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        for (int i = 0; i < file.length; i++) {
            writer.write((String) file[i]);
            if (i != file.length - 1) writer.newLine();
        }
        writer.flush();
        writer.close();
    }

    public void showFiles() throws IOException {
        String dirName = System.getProperty("user.dir") + "\\src\\";
        System.out.println("+==+==+==+ files +==+==+==+");
        Files.list(new File(dirName).toPath())
            .limit(25)
            .forEach(path -> {
                if (path.getFileName().toString().length() < 6) {
                    System.out.println("+ " + path.getFileName() + "\u0009\u0009\u0009\u0009\u0009  " + "+");
                } else if (path.getFileName().toString().length() < 10) {
                    System.out.println("+ " + path.getFileName() + "\u0009\u0009\u0009\u0009  " + "+");
                } else if (path.getFileName().toString().length() < 14) {
                    System.out.println("+ " + path.getFileName() + "\u0009\u0009\u0009  " + "+");
                } else if (path.getFileName().toString().length() < 18) {
                    System.out.println("+ " + path.getFileName() + "\u0009\u0009  " + "+");
                } else if (path.getFileName().toString().length() < 22) {
                    System.out.println("+ " + path.getFileName() + "\u0009  " + "+");
                } else if (path.getFileName().toString().length() < 23) {
                    System.out.println("+ " + path.getFileName() + "  " + "+");
                } else if (path.getFileName().toString().length() < 24) {
                    System.out.println("+ " + path.getFileName() + " " + "+");
                } else if (path.getFileName().toString().length() < 25) {
                    System.out.println("+ " + path.getFileName() + "+");
                } else {
                    System.out.println("+ " + path.getFileName());
                }
            });
        System.out.println("+==+==+==+=+==+==+==+==+==+");
    }
}
