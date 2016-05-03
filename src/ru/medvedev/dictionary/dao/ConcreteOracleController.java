package ru.medvedev.dictionary.dao;

import oracle.jdbc.proxy.annotation.Pre;
import ru.medvedev.dictionary.records.GeneralRecord;
import ru.medvedev.dictionary.records.PropertiesRecord;
import ru.medvedev.dictionary.records.Record;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Сергей on 02.05.2016.
 */
public class ConcreteOracleController extends OracleController<GeneralRecord, String> {

    private static final String SELECTALL = "SELECT * FROM ENGLISHWORDS " +
            "INNER JOIN CONNECTWORDS ON ENGLISHWORDS.ID = CONNECTWORDS.ID_ENG " +
            "INNER JOIN RUSSIANWORDS ON CONNECTWORDS.ID_RUS = RUSSIANWORDS.ID " +
            "INNER JOIN PROPERTIES ON CONNECTWORDS.ID_PROP = PROPERTIES.ID";
    private static final String UPDATE = "UPDATE PROPERTIES SET ? = ? WHERE id = (" +
            "SELECT ID_PROP FROM CONNECTWORDS " +
            "INNER JOIN ENGLISHWORDS ON CONNECTWORDS.ID_ENG = ENGLISHWORDS.ID " +
            "WHERE ENGLISHWORDS.WORD_ENG = ?))";
    private static final String DELETE = "DELETE FROM ENGLISHWORDS WHERE ID = (SELECT ID FROM ENGLISHWORDS WHERE WORD_ENG = ?)";
    private static final String INSERT = "INSERT INTO ";
    private static final String VALUES = " VALUES (";
    private static final String SELECTDUAL = "SELECT ? AS CURRVAL FROM dual";
    private static final String WHERE = " WHERE ENGLISHWORDS.WORD_ENG = ?";
    private static final String ID_ENG = "ID_ENG";
    private static final String ID_RUS = "ID_RUS";
    private static final String ID_PROP = "ID_PROP";
    private static final String ENGWORD = "WORD_ENG";
    private static final String RUSWORD = "WORD_RUS";
    private static final String PARTOFSPEECH = "PARTOFSPEECH";
    private static final String SENSE = "SENSE";
    private static final String GENDER = "GENDER";
    private static final String ENGLISHWORDS = "ENGLISHWORDS";
    private static final String RUSSIANWORDS = "RUSSIANWORDS";
    private static final String PROPERTIES = "PROPERTIES";
    private static final String CONNECTWORDS = "CONNECTWORDS";
    private static final String EW_SEQ = "EW_SEQ.NEXTVAL";
    private static final String RW_SEQ = "RW_SEQ2.NEXTVAL";
    private static final String PROP_SEQ = "PROP_SEQ.NEXTVAL";
    private static final String RUSCURR = "EW_SEQ.CURRVAL";
    private static final String ENGCURR = "RW_SEQ2.CURRVAL";
    private static final String PROPCURR = "PROP_SEQ.CURRVAL";
    private static final String CURRVAL = "CURRVAL";


    private List<GeneralRecord> getResultSet(String sql, String w) {
        List<GeneralRecord> records = new ArrayList<>();
        try(PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            if (sql.contains("WHERE")) preparedStatement.setString(1, w);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id_eng = resultSet.getInt(ID_ENG);
                int id_rus = resultSet.getInt(ID_RUS);
                int id_prop = resultSet.getInt(ID_PROP);
                String wordEng = resultSet.getString(ENGWORD);
                String wordRus = resultSet.getString(RUSWORD);
                String partOfSpeech = resultSet.getString(PARTOFSPEECH);
                String sense = resultSet.getString(SENSE);
                String gender = resultSet.getString(GENDER);
                Record recordEng = new Record(id_eng,wordEng);
                Record recordRus = new Record(id_rus,wordRus);
                PropertiesRecord propertiesRecord = new PropertiesRecord(id_prop, partOfSpeech, sense, gender);
                GeneralRecord generalRecord = new GeneralRecord(recordEng, recordRus, propertiesRecord);
                records.add(generalRecord);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }

    private void insert(String table, String ... val) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(INSERT+table+VALUES+val[0]+", ");
        for (int i = 1; i < val.length; i++) {
           if (i != val.length - 1) stringBuilder.append("?, ");
           else stringBuilder.append("?");
        }
        stringBuilder.append(")");
        System.out.println(stringBuilder.toString());
        try(PreparedStatement preparedStatement = getConnection().prepareStatement(stringBuilder.toString(),  new String[]{"ID_ENG"})) {
            for (int i = 1; i < val.length; i++) {
                preparedStatement.setString(i,val[i]);
            }
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            System.out.println("ID=" + rs.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println("OK");
    }

    @Override
    public List<GeneralRecord> getAll() {
        List<GeneralRecord> records = getResultSet(SELECTALL,null);
        return records;
    }

    @Override
    public boolean update(String w, String field, String value) {
        try(PreparedStatement preparedStatement = getConnection().prepareStatement(UPDATE)) {
            preparedStatement.setString(1, field);
            preparedStatement.setString(2, value);
            preparedStatement.setString(3, w);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return  true;
    }

    @Override
    public GeneralRecord getEntityByName(String w) {
        List<GeneralRecord> resultSet = getResultSet(SELECTALL + WHERE, w);
        return resultSet.get(0);
    }

    @Override
    public boolean delete(String w) {
        try(PreparedStatement preparedStatement = getConnection().prepareStatement(DELETE)) {
            preparedStatement.setString(1,w);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean create(GeneralRecord entity) {
        insert(ENGLISHWORDS,EW_SEQ,entity.getRecordEng().getWord());
        insert(RUSSIANWORDS,RW_SEQ,entity.getRecordRus().getWord());
        insert(PROPERTIES,PROP_SEQ,entity.getPropertiesRecord().getPartOfSpeech(),entity.getPropertiesRecord().getSense(),entity.getPropertiesRecord().getGender());
        insert(CONNECTWORDS, Integer.toString(getLastId(ENGCURR)),Integer.toString(getLastId(RUSCURR)),Integer.toString(getLastId(PROPCURR)));
        return true;
    }

    private int getLastId(String seq) {
        int result = -1;
        try(PreparedStatement preparedStatement = getConnection().prepareStatement(SELECTDUAL)) {
            preparedStatement.setString(1,seq);
            System.out.println(SELECTDUAL + "    " + seq);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getInt(CURRVAL);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return result;
    }

    public int getNextId(String seq) {
        int myid = -1;
        try (PreparedStatement pst = getConnection().prepareStatement(SELECTDUAL)) {
            pst.getGeneratedKeys();
            pst.setString(1,seq);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                myid = rs.getInt("CURRVAL");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return myid;
    }

}
