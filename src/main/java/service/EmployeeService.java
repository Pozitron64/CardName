package service;

import action.Searching;
import lombok.RequiredArgsConstructor;
import model.Employee;
import model.Post;
import model.SearchParams;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.*;

@RequiredArgsConstructor
public class EmployeeService {

    private final PostService postService;
    private final Searching searching;
    private List<Employee> employees = new ArrayList<>();

    public EmployeeService(PostService postService) {
        this.postService = postService;
        this.searching = new Searching();
    }

    public Employee parseFromJson(JSONObject employee) {
        Object firstName = employee.get("firstName");
        Object lastName = employee.get("lastName");
        Object description = employee.get("description");
        JSONArray characteristics = (JSONArray) employee.get("characteristics");
        Object postId = employee.get("postID");
        Post post = postService.findPostByUUID(UUID.fromString(String.valueOf(postId)));
        String stringDescription = "";
        if (description != null) {
            stringDescription = description.toString();
        }
        Employee parsedEmployee = Employee.builder()
                .firstName(firstName.toString())
                .lastName(lastName.toString())
                .characteristics(new ArrayList<>(characteristics))
                .description(stringDescription)
                .post(post)
                .build();
        addEmployee(parsedEmployee);
        return parsedEmployee;
    }

    public List<Employee> getEmployees(SearchParams params) {
        return searching.search(employees, params);
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }
}