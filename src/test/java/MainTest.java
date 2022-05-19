import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

class MainTest {

    @Test
    void checkCorrectReadFile() {
        File file = new File("src\\main\\resources\\fileAuthentication");
        List<StringBuilder> actual = Main.read(file);
        Assertions.assertEquals(DataForTest.stringsFromFile.get(1).toString() + DataForTest.stringsFromFile.get(0).toString(),
                actual.get(1).toString() + actual.get(0).toString());
    }

    @Test
    void correctParse() {
        Main.doPost();
        Employee expected = new Employee("Геннадий",
                "Кузьмин",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras sitamet dictum felis, eu fringilla eros. Sed et gravida neque. Nullam at egestas erat. Mauris vitae convallis nulla. Aenean condimentum lectus magna. Suspendisse viverra quam non ante pellentesque, a euismod nunc dapibus. Duis sed congue erat",
                new ArrayList<>(List.of(" honest, introvert, like criticism, love of Learning, pragmatism".split(", "))),
                new Post(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"),"Programmer"));

        List<Employee> actual = Main.parse(DataForTest.stringsFromFile);
        Assertions.assertTrue(expected.equals(actual.get(0)));
    }
}
