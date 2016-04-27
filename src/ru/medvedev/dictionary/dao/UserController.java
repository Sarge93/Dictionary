package ru.medvedev.dictionary.dao;

import ru.medvedev.dictionary.records.GeneralRecord;
import ru.medvedev.dictionary.records.RecordEng;
import ru.medvedev.dictionary.records.RecordRus;

import java.io.StringWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Сергей on 25.04.2016.
 */
public class UserController extends AbstractController<GeneralRecord, String> {
    @Override
    public List<GeneralRecord> getAll() {
        ArrayList<GeneralRecord> records = new ArrayList<>();

        try(Statement statement = getConnection().createStatement()) {
            String q = "SELECT * FROM ENGLISHWORDS";
            ResultSet resultSet = statement.executeQuery(q);
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String word = resultSet.getString("WORD");
                String partOfSpeech = resultSet.getString("PARTOFSPEECH");
                String sense = resultSet.getString("SENSE");
                RecordEng recordEng = new RecordEng(id,word,partOfSpeech,sense);
                RecordRus recordRus = getDataFromAnotherTable(id);
                GeneralRecord generalRecord = new GeneralRecord(recordRus, recordEng);
                records.add(generalRecord);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }

    @Override
    public boolean update(String w, String field, String value) {
        try(Statement statement = getConnection().createStatement()) {
            if (field.equals("PARTOFSPEECH") || field.equals("SENSE")) {
                String q = "UPDATE ENGLISHWORDS SET " + field + " = '" + value + "' " + "WHERE word = '" + w + "'";
                statement.executeUpdate(q);
            } else if (field.equals("GENDER")) {
                int idRus = -1;
                String q = "SELECT id FROM ENGLISHWORDS WHERE word = '" + w + "'";
                ResultSet resultSet = statement.executeQuery(q);
                while (resultSet.next()) {
                    idRus = getDataFromAnotherTable(resultSet.getInt("ID")).getId();
                }
                q = "UPDATE RUSSIANWORDS SET " + field + " = '" + value + "' " + "WHERE id = " + idRus;
                statement.executeUpdate(q);
            } else {
                return false;
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return  true;
    }

    @Override
    public GeneralRecord getEntityByName(String w) {
        GeneralRecord generalRecord = null;
        try(Statement statement = getConnection().createStatement()) {
            String q = "SELECT * FROM ENGLISHWORDS WHERE word = '" + w + "'";
            ResultSet resultSet = statement.executeQuery(q);
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String word = resultSet.getString("WORD");
                String partOfSpeech = resultSet.getString("PARTOFSPEECH");
                String sense = resultSet.getString("SENSE");
                RecordEng recordEng = new RecordEng(id,word,partOfSpeech,sense);
                RecordRus recordRus = getDataFromAnotherTable(id);
                generalRecord = new GeneralRecord(recordRus, recordEng);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return generalRecord;
    }

    @Override
    public boolean delete(String w) {
        int idEng = -1;
        int idRus = -1;

        try(Statement statement = getConnection().createStatement()) {
            String q = "SELECT id FROM ENGLISHWORDS WHERE word = '" + w + "'";
            ResultSet resultSet = statement.executeQuery(q);
            while (resultSet.next()) {
                idEng = resultSet.getInt("ID");
                idRus = getDataFromAnotherTable(idEng).getId();
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        try(Statement statement = getConnection().createStatement()) {
            String q = "DELETE FROM ENG_RUS WHERE ID_ENG = " + idEng;
            statement.executeUpdate(q);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        try(Statement statement = getConnection().createStatement()) {
            String q = "DELETE FROM ENGLISHWORDS WHERE ID = " + idEng;
            statement.executeUpdate(q);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        try(Statement statement = getConnection().createStatement()) {
            String q = "DELETE FROM RUSSIANWORDS WHERE ID = " + idRus;
            statement.executeUpdate(q);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean create(GeneralRecord entity) {
        try(Statement statement = getConnection().createStatement()) {
            StringBuilder qToEng = new StringBuilder();

            qToEng.append("insert into englishwords VALUES (ew_seq.nextval, ");
            qToEng.append("'" + entity.getRecordEng().getWord() + "', ");
            qToEng.append("'" + entity.getRecordEng().getPartOfSpeech() + "', ");
            if (entity.getRecordEng().getSense() == null) {
                qToEng.append(entity.getRecordEng().getSense() + ")");
            } else {
                qToEng.append("'" + entity.getRecordEng().getSense() + "')");
            }

            StringBuilder qToRus = new StringBuilder();

            qToRus.append("insert into russianwords VALUES (rw_seq.nextval, ");
            qToRus.append("'" + entity.getRecordRus().getWord() + "', ");
            if (entity.getRecordRus().getGender() == null) {
                qToRus.append(entity.getRecordRus().getGender() + ")");
            } else {
                qToRus.append("'" + entity.getRecordRus().getGender() + "')");

            }

            statement.executeUpdate(qToEng.toString());
            statement.executeUpdate(qToRus.toString());

            statement.close();
        }
        catch (SQLException e) {
            return false;
        }

        try (Statement statement = getConnection().createStatement()) {
            int idR = getLastIdRus(), idE = getLastIdEng();
            statement.executeUpdate("insert into ENG_RUS VALUES (" + idE + ", " + idR + ")");
            statement.close();
        }
        catch (SQLException e) {
            return false;
        }
        return true;
    }

    private RecordRus getDataFromAnotherTable(int id) {
        RecordRus recordRus = null;
        int idRus = 0;
        try(Statement statement = getConnection().createStatement()) {
            String q = "SELECT * FROM ENG_RUS WHERE ID_ENG = " + id;
            ResultSet resultSet = statement.executeQuery(q);
            while (resultSet.next()) {
                idRus = resultSet.getInt("ID_RUS");break;
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try(Statement statement = getConnection().createStatement()) {
            String q = "SELECT * FROM RUSSIANWORDS WHERE id = " + idRus;
            ResultSet resultSet = statement.executeQuery(q);
            while (resultSet.next()) {
                String word = resultSet.getString("WORD");
                String gender = resultSet.getString("GENDER");
                recordRus = new RecordRus(idRus,word,gender);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  recordRus;
    }

    private int getLastIdRus() {
        int result = -1;

        try(Statement statement = getConnection().createStatement()) {
            String q = "SELECT rw_seq.currval FROM dual";
            ResultSet resultSet = statement.executeQuery(q);
            while (resultSet.next()) {
                result = resultSet.getInt("CURRVAL");
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private int getLastIdEng() {
        int result = -1;

        try(Statement statement = getConnection().createStatement()) {
            String q = "SELECT ew_seq.currval FROM dual";
            ResultSet resultSet = statement.executeQuery(q);
            while (resultSet.next()) {
                result = resultSet.getInt("CURRVAL");
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
