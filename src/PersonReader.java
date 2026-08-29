import javax.swing.JFileChooser;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class PersonReader {
    public static void main(String[] args) {

        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            Path file = chooser.getSelectedFile().toPath();

            System.out.println("Selected file: " + file.getFileName());
            System.out.printf("%-10s %-15s %-15s %-10s %-6s%n",
                    "ID#", "Firstname", "Lastname", "Title", "YOB");

            System.out.println("============================================================");

            try (BufferedReader reader = Files.newBufferedReader(file)) {

                String line;

                while ((line = reader.readLine()) != null) {

                    String[] fields = line.split(",");

                    String id = fields[0].trim();
                    String firstName = fields[1].trim();
                    String lastName = fields[2].trim();
                    String title = fields[3].trim();
                    String yearOfBirth = fields[4].trim();

                    System.out.printf("%-10s %-15s %-15s %-10s %-6s%n",
                            id, firstName, lastName, title, yearOfBirth);
                }

            } catch (IOException e) {
                System.out.println("Error reading the file.");
                e.printStackTrace();
            }
        }
    }
}