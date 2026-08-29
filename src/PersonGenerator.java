import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class PersonGenerator {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<String> personRecords = new ArrayList<>();
        boolean done = false;
        while (!done) {

            String id = SafeInput.getNonZeroLenString(in, "Enter the person's ID");
            String firstName = SafeInput.getNonZeroLenString(in, "Enter the first name");
            String lastName = SafeInput.getNonZeroLenString(in, "Enter the last name");
            String title = SafeInput.getNonZeroLenString(in, "Enter the title");

            int yearOfBirth = SafeInput.getRangedInt(
                    in,
                    "Enter the year of birth",
                    1,
                    2026
            );
            String record = id + ", " +
                    firstName + ", " +
                    lastName + ", " +
                    title + ", " +
                    yearOfBirth;

            personRecords.add(record);

            System.out.println("\nRecord added:");
            System.out.println(record);

            boolean addAnother = SafeInput.getYNConfirm(
                    in,
                    "Would you like to enter another person?"
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

            for (String record : personRecords) {
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