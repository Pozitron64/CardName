import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static Map<UUID, Post> posts = new HashMap<>();
    private static final String FIRSTNAME = "firstName:";
    private static final String LASTNAME = "lastName:";
    private static final String DESCRIPTION = "description:";
    private static final String CHARACTERISTICS = "characteristics:";
    private static final String POSTID = "postId:";


    private static String pathFail = "E:\\java_projects\\CardName\\src\\main\\resources\\fileAuthentication";

    public static void main(String[] args) {
        if ((args.length < 1)||(args.length > 1)) {
            throw new IllegalArgumentException("Incorrect number of arguments");
        }
        pathFail = args[0];

        doPost();
        List<StringBuilder> file = Main.read(new File(pathFail));
        List<Employee> employees = Main.parse(file);
        print(employees);
    }

    public static void doPost(){
        posts.put(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"), new Post(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"),"Programmer"));
        posts.put(UUID.fromString("762d15a5-3bc9-43ef-ae96-02a680a557d0"), new Post(UUID.fromString("762d15a5-3bc9-43ef-ae96-02a680a557d0"),"Middle"));
    }

    public static List<StringBuilder> read(File file){
        List<StringBuilder> buffer = new ArrayList<>();
        StringBuilder card = new StringBuilder();
        StringBuilder line = new StringBuilder();
        try (Scanner scanner = new Scanner(file)){
            while(scanner.hasNext()){
                line.append(scanner.nextLine());
                if(line.isEmpty()){
                    buffer.add(new StringBuilder(card));
                    card.delete(0,card.length());
                }
//                line.compareTo(new StringBuilder("")) == 0
                else{
                    card.append(line.append(" "));
                }
                line.delete(0,line.length());
            }
            buffer.add(new StringBuilder(card));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    public static List<Employee> parse(List<StringBuilder> employees){
        Pattern p = Pattern.compile("firstName:.+lastName:.+description:.*characteristics:.+postId: \\S+ ");
        List<Employee> employeeList = new ArrayList<>();
        for(StringBuilder employee : employees){
            Matcher m = p.matcher(employee);
            if(!m.matches()){
                continue;
            }

            String firstNameString = employee.substring(employee.indexOf(FIRSTNAME) + FIRSTNAME.length(),employee.indexOf(LASTNAME)).trim();
            String secondNameString = employee.substring(employee.indexOf(LASTNAME) + LASTNAME.length(),employee.indexOf(DESCRIPTION)).trim();
            String descriptionString = employee.substring(employee.indexOf(DESCRIPTION) + DESCRIPTION.length(),employee.indexOf(CHARACTERISTICS)).trim();
            List<String> characteristicsString = Arrays.stream(employee.substring(employee.indexOf(CHARACTERISTICS) + CHARACTERISTICS.length(),employee.indexOf(POSTID))
                    .toString().split(", ")).toList();
            String postIdString = employee.substring(employee.indexOf(POSTID) + POSTID.length()).trim();

            employeeList.add(new Employee(
                    firstNameString,
                    secondNameString,
                    descriptionString,
                    characteristicsString,
                    posts.get(UUID.fromString(postIdString))
            ));
        }
        return employeeList;
    }

    public static void print(List<Employee> employees){
        for (Employee employee : employees){
            System.out.println("First Name - " + employee.getFirstName());
            System.out.println("Last Name - " + employee.getLastName());
            System.out.println("Description - " + employee.getDescription());
            System.out.println("Characteristics -" + String.join(", ",employee.getCharacteristics()));
            System.out.println("Post - " + employee.getPost().getName());
            System.out.println();
        }
    }
}
