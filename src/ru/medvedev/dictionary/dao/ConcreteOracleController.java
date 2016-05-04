package ru.medvedev.dictionary.dao;

import oracle.jdbc.proxy.annotation.Pre;
import ru.medvedev.dictionary.records.GeneralRecord;
import ru.medvedev.dictionary.records.PropertiesRecord;
import ru.medvedev.dictionary.records.Record;

import java.sql.*;
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
    private static final String WHEREWORD = " WHERE ENGLISHWORDS.WORD_ENG = ?";
    private static final String ID_ENG = "ID_ENG";
    private static final String ID_RUS = "ID_RUS";
    private static final String ID_PROP = "ID_PROP";
    private static final String ENGWORD = "WORD_ENG";
    private static final String RUSWORD = "WORD_RUS";
    private static final String PARTOFSPEECH = "PARTOFSPEECH";
    private static final String SENSE = "SENSE";
    private static final String GENDER = "GENDER";
    private static final String WHERE = "WHERE";
    private static final String INSERTROW = "{call insertrow(?,?,?,?,?)}";


    private List<GeneralRecord> getResultSet(String sql, String w) {
        List<GeneralRecord> records = new ArrayList<>();
        try(PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            if (sql.contains(WHERE)) preparedStatement.setString(1, w);
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
        List<GeneralRecord> resultSet = getResultSet(SELECTALL + WHEREWORD, w);
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
        try(CallableStatement stmt = getConnection().prepareCall(INSERTROW)){
            stmt.setString(1, entity.getRecordEng().getWord());
            stmt.setString(2, entity.getRecordRus().getWord());
            stmt.setString(3, entity.getPropertiesRecord().getPartOfSpeech());
            stmt.setString(4, entity.getPropertiesRecord().getSense());
            stmt.setString(5, entity.getPropertiesRecord().getGender());
            stmt.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return true;
    }
}
