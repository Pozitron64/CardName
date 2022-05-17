import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class MainTest {
    List<StringBuffer> stringsFromFile = List.of(
            new StringBuffer("firstName: Геннадий " +
                    "lastName: Кузьмин " +
                    "description: Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras sit amet dictum felis, eu fringilla eros. Sed et gravida neque. Nullam at egestas erat. Mauris vitae convallis nulla. Aenean condimentum lectus magna. Suspendisse viverra quam non ante pellentesque, a euismod nunc dapibus. Duis sed congue erat " +
                    "characteristics: honest, introvert, like criticism, love of Learning, pragmatism " +
                    "postId: 854ef89d-6c27-4635-926d-894d76a81707 "),
            new StringBuffer("firstName: Иван " +
                    "lastName: Иванов " +
                    "description: " +
                    "characteristics: some characteristic " +
                    "postId: 762d15a5-3bc9-43ef-ae96-02a680a557d0 "));

    @Test
    void checkCorrectReadFile() {
        File file = new File("E:\\java_projects\\CardName\\src\\main\\resources\\fileAuthentication");
        List<StringBuffer> actual = Main.read(file);
        Assertions.assertEquals(stringsFromFile.get(1).toString() + stringsFromFile.get(0).toString(),
                actual.get(1).toString() + actual.get(0).toString());
    }

    @Test
    void correctParse() {
        Employee expected = new Employee("Геннадий",
                "Кузьмин",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras sitamet dictum felis, eu fringilla eros. Sed et gravida neque. Nullam at egestas erat. Mauris vitae convallis nulla. Aenean condimentum lectus magna. Suspendisse viverra quam non ante pellentesque, a euismod nunc dapibus. Duis sed congue erat",
                new ArrayList<>(List.of("introvert, like criticism, love of Learning, pragmatism, honest".split(", "))),
                new Post(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"),"Programmer"));
        Main.doPost();
        List<Employee> actual = Main.parse(stringsFromFile);
        Assertions.assertEquals(expected.getFirstName(),actual.get(0).getFirstName());
    }
}
