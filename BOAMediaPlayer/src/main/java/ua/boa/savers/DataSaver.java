package ua.boa.savers;

import java.io.*;

public class DataSaver {
    private Configuration configuration;
    private final String fileName;

    public DataSaver(String fileName) {
        configuration = new Configuration();
        this.fileName = fileName;
        File file = new File(fileName);
        if (file.exists()) {
            try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
                try (ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                    configuration = (Configuration) objectInputStream.readObject();
                }
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                if (file.createNewFile()) save();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void save() {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
                objectOutputStream.writeObject(configuration);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void addHistory(String path) {
        int index = configuration.history.indexOf(path);
        if (index == -1) {
            if (configuration.history.size() >= 20) configuration.history.remove(configuration.history.size() - 1);
            configuration.history.add(0, path);
            return;
        }
        if (index == 0) return;
        configuration.history.remove(path);
        configuration.history.add(0, path);
    }
}
