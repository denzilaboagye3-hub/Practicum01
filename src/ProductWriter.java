import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductWriter {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        ArrayList<String> productRecords = new ArrayList<>();
        boolean done = false;
        while (!done) {

            String id = SafeInput.getNonZeroLenString(in, "Enter the product ID");
            String name = SafeInput.getNonZeroLenString(in, "Enter the product name");
            String description = SafeInput.getNonZeroLenString(in, "Enter the product description");

            double cost = SafeInput.getRangedDouble(
                    in,
                    "Enter the product cost",
                    0,
                    1000000
            );
            String record = id + ", " +
                    name + ", " +
                    description + ", " +
                    cost;

            productRecords.add(record);

            System.out.println("\nRecord added:");
            System.out.println(record);

            boolean addAnother = SafeInput.getYNConfirm(
                    in,
                    "Would you like to enter another product?"
            );

            if (!addAnother) {
                done = true;
            }
        }
        String fileName = SafeInput.getNonZeroLenString(
                in,
                "Enter the file name"
        );

        Path file = Paths.get(fileName);

        try (BufferedWriter writer = Files.newBufferedWriter(file)) {

            for (String record : productRecords) {
                writer.write(record);
                writer.newLine();
            }

            System.out.println("\nFile successfully saved as: " + fileName);

        } catch (IOException e) {
            System.out.println("Error writing the file.");
            e.printStackTrace();
        }

    }
}