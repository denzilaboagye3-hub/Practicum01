import javax.swing.JFileChooser;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ProductREader {
    public static void main(String[] args) {

        JFileChooser chooser = new JFileChooser();

        int result = chooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            Path file = chooser.getSelectedFile().toPath();

            System.out.println("Selected file: " + file.getFileName());

            System.out.printf("%-10s %-15s %-30s %-10s%n",
                    "ID#", "Name", "Description", "Cost");

            System.out.println("====================================================================");

            try (BufferedReader reader = Files.newBufferedReader(file)) {

                String line;

                while ((line = reader.readLine()) != null) {

                    String[] fields = line.split(",");

                    String id = fields[0].trim();
                    String name = fields[1].trim();
                    String description = fields[2].trim();
                    String cost = fields[3].trim();

                    System.out.printf("%-10s %-15s %-30s %-10s%n",
                            id, name, description, cost);
                }

            } catch (IOException e) {
                System.out.println("Error reading the file.");
                e.printStackTrace();
            }
        }
    }
}