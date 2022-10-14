package ua.boa;

import java.io.*;
import java.util.Objects;

public class DataSaver {
    private final String fileName;
    private String lastPath = null;
    private String lastUrl = null;
    public DataSaver(String fileName) {
        this.fileName = fileName;
        try {
            File file = new File(fileName);
            if(!file.exists()) file.createNewFile();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = bufferedReader.readLine();
            if(line != null && !line.equals("")) lastPath = line;
            line = bufferedReader.readLine();
            if(line != null && !line.equals("")) lastUrl = line;
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void writeToFile(){
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));
            bufferedWriter.write(lastPath+"\n");
            bufferedWriter.write(lastUrl+"\n");
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void save(String path, String type){
        if(Objects.equals(type, "url")) lastUrl = path;
        lastPath = path;
        writeToFile();
    }
    public String getLastPath(){
        return lastPath;
    }
    public String getLastUrl(){
        return lastUrl;
    }
}
