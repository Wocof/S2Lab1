package storage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StorageFile {

    public void saveToFile(String filePath, StorageData storageData) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(storageData);
        }
    }

    public StorageData loadFromFile(String filePath) throws IOException, ClassNotFoundException {

        Path path = Paths.get(filePath);

        if (!Files.exists(path)) {
            throw new FileNotFoundException("Файл не найден: " + filePath);
        }
        if (!Files.isReadable(path)) {
            throw new IOException("Недостаточно прав для чтения файла: " + filePath);
        }

        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(path))) {
            Object obj = ois.readObject();
            if (obj instanceof StorageData) {
                return (StorageData) obj;
            } else {
                throw new IOException("Файл содержит неверный тип данных");
            }
        }
    }

}

