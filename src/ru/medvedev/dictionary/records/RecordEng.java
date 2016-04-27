package ru.medvedev.dictionary.records;

/**
 * Created by Сергей on 26.04.2016.
 */
public class RecordEng {
    private int id;
    private String word;
    private String partOfSpeech;
    private String sense;

    public RecordEng(int id, String word, String partOfSpeech, String sense) {
        this.id = id;
        this.word = word;
        this.partOfSpeech = partOfSpeech;
        this.sense = sense;
    }

    public RecordEng(int id, String word, String partOfSpeech) {
        this.id = id;
        this.word = word;
        this.partOfSpeech = partOfSpeech;
    }

    @Override
    public String toString() {
        return "RecordEng{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", partOfSpeech='" + partOfSpeech + '\'' +
                ", sense='" + sense + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getWord() {
        return word;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public String getSense() {
        return sense;
    }
}
