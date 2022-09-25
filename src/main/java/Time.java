import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Time {
    
    public static void main(String[] args) {
        LocalDate t = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("LLLL");
        String text = t.format(formatter);
        System.out.println(text);
    } 
    
}
