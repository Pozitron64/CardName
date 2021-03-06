import java.util.List;
import java.util.Objects;

public class Employee {

    private String firstName;

    private String lastName;

    private String description;

    private List<String> characteristics;

    private Post post;

    public Employee(String firstName, String lastName, String description, List<String> characteristics, Post post) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
        this.characteristics = characteristics;
        this.post = post;
    }

    public Employee(String firstName, String lastName, List<String> characteristics, Post post) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.characteristics = characteristics;
        this.post = post;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(List<String> characteristics) {
        this.characteristics = characteristics;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, description, characteristics, post);
    }
}
