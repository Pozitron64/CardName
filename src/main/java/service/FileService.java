package service;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class FileService {

    public JSONArray readJsonArray(File file)  {
        JSONArray jsonArray = null;
        try (FileReader fileReader = new FileReader(file)){
            JSONParser parser = new JSONParser();
            jsonArray = (JSONArray) parser.parse(fileReader);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }
}