package ru.medvedev.dictionary.records;

/**
 * Created by Сергей on 26.04.2016.
 */
public class RecordRus {
    private int id;
    private String word;
    private String gender;

    public RecordRus(int id, String word, String gender) {
        this.id = id;
        this.word = word;
        this.gender = gender;
    }

    public RecordRus(int id, String word) {
        this.id = id;
        this.word = word;
    }

    @Override
    public String toString() {
        return "RecordRus{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getWord() {
        return word;
    }

    public String getGender() {
        return gender;
    }
}
