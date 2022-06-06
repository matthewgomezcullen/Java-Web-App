package ucl.ac.uk.model;

import ucl.ac.uk.records.Note;

import java.util.ArrayList;

public class Model {

  private Indexes indexes = new Indexes();
  private Notes notes = new Notes();

  // Read indexes
  public void readIndexes() {
    indexes.readIndexes();
  }

  // Get indexes
  public ArrayList<String> getIndexes() {
    return indexes.getIndexes();
  }

  // Create index
  public void addIndex(String name) {
    indexes.addIndex(name);
  }

  // Delete index
  public void deleteIndex(String name) {
    indexes.deleteIndex(name);
  }

  // Search for matching indexes
  public ArrayList<String> searchIndex(String index) {
    return indexes.searchIndex(index.toLowerCase());
  }

  // Read notes
  public void readNotes(String index) {
    notes.readNotes(index);
  }

  // Get notes
  public ArrayList<Note> getNotes() {
    return notes.getNotes();
  }

  // Create note
  public void addNote(String index, String title, String details, String image, String youtube) {
    notes.addNote(index, title, details, image, youtube);
  }

  // Delete note
  public void deleteNote(String index, String title) {
    notes.deleteNote(index, title);
  }

  // Search for note
  public ArrayList<Note> searchNote(String title) {
    return notes.searchNote(title.toLowerCase());
  }

  // // Search for note
  // public List<String> searchFor(String keyword) {
  // return List.of("Search keyword is: " + keyword, "result1", "result2",
  // "result3");
  // }
}
