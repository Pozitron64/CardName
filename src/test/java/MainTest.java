import model.SearchParams;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import service.EmployeeService;
import service.FileService;
import service.PostService;

import java.io.File;

public class MainTest {

    @Test
    public void checkParseArgs() {
        SearchParams searchParam = new SearchParams();
        String[] args = {"-file","src/main/resources/authentification.json","-firstname","Генадий","-lastname","Кузьмин"};
        Main.parseArgs(args, searchParam);
        Assert.assertTrue(("src/main/resources/authentification.json" == searchParam.getFilePath())
                ||("Генадий" == searchParam.getFirstName())
                ||("Кузьмин" == searchParam.getLastName())
                ||(searchParam.getPostId() == null));
    }

    @Test
    public void checkSearching(){
        String[] testArgs = {"-file","src/main/resources/authentification.json","-firstname","Генадий"};

        SearchParams searchParam = new SearchParams();
        PostService postService = new PostService();
        EmployeeService employeeService = new EmployeeService(postService);
        FileService fileService = new FileService();

        Main.parseArgs(testArgs, searchParam);
        postService.doPost();

        File file = new File(searchParam.getFilePath());
        JSONArray array = fileService.readJsonArray(file);
        array.forEach(e -> {
            employeeService.parseFromJson((JSONObject) e);
        });

        Assert.assertArrayEquals(employeeService.getEmployees(searchParam).toArray(),DataForTest.employee1.toArray());
    }

}
