package ua.boa.savers;

import java.io.*;

public class VolumeSaver {
    private final String fileName;
    private int volume = 50;

    public VolumeSaver(String fileName) {
        this.fileName = fileName;
        try {
            File file = new File(fileName);
            if(!file.exists()) file.createNewFile();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = bufferedReader.readLine();
            if(line != null && !line.equals("")){
                try{
                    volume = Integer.parseInt(line);
                }catch (NumberFormatException ignored){}
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void save(int volume){
        this.volume = volume;
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));
            bufferedWriter.write(String.valueOf(volume));
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public int getVolume(){
        return volume;
    }
}
