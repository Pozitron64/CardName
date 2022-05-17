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
//        if ((args.length < 1)||(args.length > 1)) {
//            throw new IllegalArgumentException("Incorrect number of arguments");
//        }
//        pathFail = args[0];

        doPost();
        List<StringBuffer> file = Main.read(new File(pathFail));
        List<Employee> employees = Main.parse(file);
        print(employees);
    }

    public static void doPost(){
        posts.put(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"), new Post(UUID.fromString("854ef89d-6c27-4635-926d-894d76a81707"),"Programmer"));
        posts.put(UUID.fromString("762d15a5-3bc9-43ef-ae96-02a680a557d0"), new Post(UUID.fromString("762d15a5-3bc9-43ef-ae96-02a680a557d0"),"Middle"));
    }

    public static List<StringBuffer> read(File file){
        List<StringBuffer> buffer = new ArrayList<StringBuffer>();
        StringBuffer card = new StringBuffer();
        StringBuffer line = new StringBuffer();
        try {
            Scanner scanner = new Scanner(file);
            while(scanner.hasNext()){
                line.append(scanner.nextLine());
                if(line.compareTo(new StringBuffer("")) == 0){
                    buffer.add(new StringBuffer(card));
                    card.delete(0,card.length());
                }
                else{
                    card.append(line.append(" "));
                }
                line.delete(0,line.length());
            }
            buffer.add(new StringBuffer(card));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    public static List<Employee> parse(List<StringBuffer> employees){
        Pattern p = Pattern.compile("firstName:.+lastName:.+description:.*characteristics:.+postId: \\S+ ");
        List<Employee> employeeList = new ArrayList<>();
        for(StringBuffer employee : employees){
            Matcher m = p.matcher(employee);
            if(!m.matches()){
                continue;
            }

            List<String> characteristicsString = Arrays.stream(employee.substring(employee.indexOf(CHARACTERISTICS) + CHARACTERISTICS.length(),employee.indexOf(POSTID))
                    .toString().split(", ")).toList();

            employeeList.add(new Employee(
                    employee.substring(employee.indexOf(FIRSTNAME) + FIRSTNAME.length(),employee.indexOf(LASTNAME)).trim(),
                    employee.substring(employee.indexOf(LASTNAME) + LASTNAME.length(),employee.indexOf(DESCRIPTION)).trim(),
                    employee.substring(employee.indexOf(DESCRIPTION) + DESCRIPTION.length(),employee.indexOf(CHARACTERISTICS)).trim(),
                    characteristicsString,
                    posts.get(UUID.fromString(employee.substring(employee.indexOf(POSTID) + POSTID.length()).trim()))
            ));
        }
        return employeeList;
    }

    public static void print(List<Employee> employees){
        for (Employee employee : employees){
            System.out.println("First Name - " + employee.getFirstName());
            System.out.println("Last Name - " + employee.getLastName());
            System.out.println("Description - " + employee.getDescription());
            String characteristics = "Characteristics -";
            for(String characteristic : employee.getCharacteristics()){
                characteristics += characteristic + ", ";
            }
            System.out.println(characteristics.substring(0, characteristics.length()-3));
            System.out.println("Post - " + employee.getPost().getName());
            System.out.println();
        }
    }
}
