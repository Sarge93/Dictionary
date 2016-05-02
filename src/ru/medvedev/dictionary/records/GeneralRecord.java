package ru.medvedev.dictionary.records;

/**
 * Created by Сергей on 26.04.2016.
 */
public class GeneralRecord {
    private Record recordEng;
    private Record recordRus;
    private PropertiesRecord propertiesRecord;

    public GeneralRecord(Record recordEng, Record recordRus, PropertiesRecord propertiesRecord) {
        this.recordEng = recordEng;
        this.recordRus = recordRus;
        this.propertiesRecord = propertiesRecord;
    }

    @Override
    public String toString() {
        return "GeneralRecord{" +
                "recordEng=" + recordEng +
                ", recordRus=" + recordRus +
                ", propertiesRecord=" + propertiesRecord +
                '}';
    }

    public Record getRecordEng() {
        return recordEng;
    }

    public Record getRecordRus() {
        return recordRus;
    }

    public PropertiesRecord getPropertiesRecord() {
        return propertiesRecord;
    }
}
