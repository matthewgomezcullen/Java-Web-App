package ucl.ac.uk.model;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import ucl.ac.uk.records.Note;

public class Notes {

  private ArrayList<Note> notes;

  public void readNotes(String index) {
    notes = new ArrayList<>();
    try (CSVReader reader = new CSVReader(new FileReader("./data/" + index + ".csv"))) {
      try {
        List<String[]> data = reader.readAll();
        for (String[] attributes : data) {
          Note note = createNote(attributes[0], attributes[1], attributes[2], attributes[3]);
          notes.add(note);
        }
      } catch (NullPointerException e) {
        System.out.println("No notes");
      } catch (CsvException e) {
        System.out.println("Invalid format: " + e.getMessage());
      }
    } catch (IOException e) {
      System.out.println("File read failed: " + e.getMessage());
    }
  }

  // Get notes
  public ArrayList<Note> getNotes() {
    return notes;
  }

  // Create note
  public void addNote(String index, String title, String details, String image, String youtube) {
    Note note = createNote(title, details, image, youtube);
    notes.add(note);
    writeFile(index);
  }

  // Delete note
  public void deleteNote(String index, String title) {
    Note noteToDelete = new Note("dummy", "dummy", "dummy", "dummy");
    boolean found = false;
    for (Note note : notes) {
      if (note.title().equals(title)) {
        noteToDelete = note;
        found = true;
      }
    }
    if (found) {
      notes.remove(noteToDelete);
      writeFile(index);
    }
  }

  // Search for matching notes
  public ArrayList<Note> searchNote(String search) {
    ArrayList<Note> matchingNotes = new ArrayList<>();
    for (Note note : notes) {
      if (note.title().toLowerCase().contains(search) || note.details().toLowerCase().contains(search)) {
        matchingNotes.add(note);
      }
    }
    return matchingNotes;
  }

  // Create note record
  private Note createNote(String title, String details, String image, String youtube) {
    return new Note(title, details, image, youtube);
  }

  // Convert note record
  private ArrayList<String> convertNote(Note note) {
    ArrayList<String> attributes = new ArrayList<>();
    attributes.add(note.title());
    attributes.add(note.details());
    attributes.add(note.image());
    attributes.add(note.youtube());
    return attributes;
  }

  private void writeFile(String index) {
    if (notes.isEmpty()) {
      try (PrintWriter writer = new PrintWriter(new FileWriter("./data/" + index + ".csv"))) {
        writer.print("");
      } catch (IOException e) {
        System.out.println("File write failed: " + e.getMessage());
      }
    } else {
      try (CSVWriter writer = new CSVWriter(new FileWriter("./data/" + index + ".csv"))) {
        for (Note note : notes) {
          writer.writeNext(convertNote(note).toArray(new String[0]));
        }
      } catch (IOException e) {
        System.out.println("File write failed: " + e.getMessage());
      }
    }
  }

}
