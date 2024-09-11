package task3;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Task3 {
    public static void main(String[] args) {
        JSONObject valuesJson = readJsonFile("filePath");
        JSONObject testsJson = readJsonFile("filePath");

        JSONArray values = (JSONArray) valuesJson.get("values");
        JSONArray tests = (JSONArray) testsJson.get("tests");

        makeReport(tests, values);

        writeJsonFile(testsJson, "filePath");
    }

    private static JSONObject readJsonFile(String file) {
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(file)) {
            return (JSONObject) parser.parse(reader);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void writeJsonFile(JSONObject jsonObject, String filePath) {
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(jsonObject.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void makeReport(JSONArray tests, JSONArray values) {
        for (Object testObj : tests) {
            JSONObject test = (JSONObject) testObj;
            long testId = (long) test.get("id");
            String testValue = searchValue(testId, values);
            if (testValue != null) {
                test.put("value", testValue);
            }

            if (test.containsKey("values")) {
                JSONArray innerTests = (JSONArray) test.get("values");
                makeReport(innerTests, values);
            }
        }
    }

    private static String searchValue(long testId, JSONArray values) {
        for (Object valueObj : values) {
            JSONObject value = (JSONObject) valueObj;
            long valueId = (long) value.get("id");
            if (valueId == testId) {
                return (String) value.get("value");
            }
        }
        return null;
    }
    }

