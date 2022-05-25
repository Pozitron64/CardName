package action;

import model.Employee;
import model.SearchParams;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Searching {
    public List<Employee> search(List<Employee> employees, SearchParams params) {
        Predicate<Employee> predicateFilter = Objects::nonNull;
        if(params.getFirstName()!=null){
            predicateFilter = employee -> employee.getFirstName().equals(params.getFirstName());
        }
        if(params.getLastName()!=null){
            predicateFilter = employee ->  employee.getLastName().equals(params.getLastName());
        }
        if(params.getPostId()!=null){
            predicateFilter = employee -> employee.getPost().getId().equals(params.getPostId());
        }

        return employees.stream()
                .filter(predicateFilter)
                .sorted(Comparator.comparing(Employee::getFirstName)
                        .thenComparing(Employee::getLastName))
                .collect(Collectors.toList());
    }
}
