import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class DataHandler {
    private static Path file = Paths.get("SavedData.euler");

    public static void serialize(Object object) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(file.toFile())) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(object);
        }

    }

    public static ArrayList<String> deserializeLottery() throws IOException, ClassNotFoundException {
        if (Files.exists(file)) {
            try (FileInputStream fis = new FileInputStream(file.toFile())) {
                ObjectInputStream ois = new ObjectInputStream(fis);
                return ((Lottery) ois.readObject()).getAlreadySelectedMembers();
            }
        }
        return null;
    }
}
