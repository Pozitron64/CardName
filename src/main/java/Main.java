import model.SearchParams;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import service.EmployeeService;
import service.FileService;
import service.PostService;

import java.io.File;
import java.util.*;

public class Main {
    private static SearchParams searchParam = new SearchParams();

    public static void main(String[] args) {
        String[] kon = {"-file","src/main/resources/authentification.json","-firstname","Генадий","-lastname","Кузьмин"};

        PostService postService = new PostService();
        EmployeeService employeeService = new EmployeeService(postService);
        FileService fileService = new FileService();

        parseArgs(kon, searchParam);
        postService.doPost();

        File file = new File(searchParam.getFilePath());
        JSONArray array = fileService.readJsonArray(file);
        array.forEach(e -> {
            employeeService.parseFromJson((JSONObject) e);
        });
        employeeService.getEmployees(searchParam).forEach(System.out::println);
    }

    public static void parseArgs(String[] args,SearchParams searchParam) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Введите путь к файлу аргументом консоли при запуске");
        }
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-file")) {
                searchParam.setFilePath(args[i + 1]);
            }
            if (args[i].equals("-firstname")) {
                searchParam.setFirstName(args[i + 1]);
            }
            if (args[i].equals("-lastname")) {
                searchParam.setLastName(args[i + 1]);
            }
            if (args[i].equals("-uuid")) {
                searchParam.setPostId(UUID.fromString(args[i + 1]));
            }
        }
    }
}
