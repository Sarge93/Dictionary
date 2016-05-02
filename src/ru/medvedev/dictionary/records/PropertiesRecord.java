package ru.medvedev.dictionary.records;

/**
 * Created by Сергей on 02.05.2016.
 */
public class PropertiesRecord {
    private int id;
    private String partOfSpeech;
    private String sense;
    private String gender;

    public PropertiesRecord(int id, String partOfSpeech) {
        this.id = id;
        this.partOfSpeech = partOfSpeech;
    }

    public PropertiesRecord(int id, String partOfSpeech, String sense, String gender) {
        this.id = id;
        this.partOfSpeech = partOfSpeech;
        this.sense = sense;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public String getSense() {
        return sense;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "PropertiesRecord{" +
                "id=" + id +
                ", partOfSpeech='" + partOfSpeech + '\'' +
                ", sense='" + sense + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
