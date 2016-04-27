package ru.medvedev.dictionary.records;

/**
 * Created by Сергей on 26.04.2016.
 */
public class GeneralRecord {
    RecordEng recordEng;
    RecordRus recordRus;

    public GeneralRecord(RecordRus recordRus, RecordEng recordEng) {
        this.recordRus = recordRus;
        this.recordEng = recordEng;
    }

    @Override
    public String toString() {
        return "GeneralRecord{" +
                "recordEng=" + recordEng +
                ", recordRus=" + recordRus +
                '}';
    }

    public RecordEng getRecordEng() {
        return recordEng;
    }

    public RecordRus getRecordRus() {
        return recordRus;
    }
}
