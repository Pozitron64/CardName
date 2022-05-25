package model;

import lombok.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@AllArgsConstructor
public class Employee {
    private String firstName;
    private String lastName;
    private String description;
    private List<String> characteristics;
    private Post post;

    @Override
    public String toString(){
        String out = "";
        characteristics.stream().sorted().collect(Collectors.toList());

        out += "First Name - " + firstName + "\n";
        out += "Last Name - " + lastName + "\n";
        if(description != ""){
            out += "Description - " + description + "\n";
        }
        out += "Characteristics - " + String.join(", ",characteristics) + "\n";
        out += "Post - " + post.getName() + "\n";
        return out;
    }
}
