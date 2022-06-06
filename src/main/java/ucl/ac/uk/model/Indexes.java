package ucl.ac.uk.model;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.ArrayList;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

public class Indexes {

  private ArrayList<String> indexes;

  // Read indexes
  public void readIndexes() {
    try (CSVReader reader = new CSVReader(new FileReader("./data/indexes.csv"))) {
      try {
        indexes = new ArrayList<>(Arrays.asList(reader.readNext()));
      } catch (NullPointerException e) {
        indexes = new ArrayList<>();
      }
    } catch (IOException | CsvValidationException e) {
      System.out.println("File read failed: " + e.getMessage());
    }
  }

  // Get indexes
  public ArrayList<String> getIndexes() {
    return indexes;
  }

  // Create index
  public boolean addIndex(String name) {
    try {
      File file = new File("./data/" + name + ".csv");
      if (!file.createNewFile()) {
        System.out.println("File already exists");
        return false;
      }
      indexes.add(name);
      writeFile();
      return true;
    } catch (IOException e) {
      System.out.println("Error creating file object: " + e.getMessage());
      return false;
    }
  }

  // Delete index
  public boolean deleteIndex(String name) {
    if (name.equals("indexes")) {
      System.out.println("Cannot delete indexes");
      return false;
    }
    File file = new File("./data/" + name + ".csv");
    if (!file.delete()) {
      System.out.println("Deletion failed");
      return false;
    }
    System.out.println("File removed");
    indexes.remove(name);
    writeFile();
    return true;
  }

  // Search for matching indexes
  public ArrayList<String> searchIndex(String search) {
    ArrayList<String> matchingIndexes = new ArrayList<>();
    for (String index : getIndexes()) {
      if (index.toLowerCase().contains(search)) {
        matchingIndexes.add(index);
      }
    }
    return matchingIndexes;
  }

  // Write to file
  private void writeFile() {
    if (indexes.isEmpty()) {
      try (PrintWriter writer = new PrintWriter(new FileWriter("./data/indexes.csv"))) {
        writer.print("");
        writer.close();
      } catch (IOException e) {
        System.out.println("File write failed: " + e.getMessage());
      }
    } else {
      try (CSVWriter writer = new CSVWriter(new FileWriter("./data/indexes.csv"))) {
        writer.writeNext(indexes.toArray(new String[0]));
        writer.close();
      } catch (IOException e) {
        System.out.println("File write failed: " + e.getMessage());
      }
    }
  }

}
