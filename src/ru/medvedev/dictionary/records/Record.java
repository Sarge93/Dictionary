package ru.medvedev.dictionary.records;

/**
 * Created by Сергей on 02.05.2016.
 */
public class Record {
    private int id;
    private String word;

    public Record(int id, String word) {
        this.id = id;
        this.word = word;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", word='" + word + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getWord() {
        return word;
    }
}
