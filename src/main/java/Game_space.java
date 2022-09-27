import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import java.io.IOException;
import static java.lang.System.exit;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Game_space {

    private static ArrayList<MonthRevenue> mrl = new ArrayList<>();
    
    
    private static Path getDefaultPath(){
        String home = System.getProperty("user.home");
        return Paths.get(home).resolve("MonthRevenue.json");
    }
    
    static void save(){
        save(getDefaultPath());
    }
    
    static void save(Path path){
        JsonArray ja = new JsonArray();
        for(MonthRevenue mr: mrl){
            ja.add(mr.toJsonObject());
        }
        String jsonText = Jsoner.serialize(ja);
        try {
            Files.write(path,jsonText.getBytes(),StandardOpenOption.CREATE);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    static void load(){
        load(getDefaultPath());
    }
    
    static void load(Path path){
        String jsonText = null;
        JsonArray ja = null;
        try {
            jsonText = new String(Files.readAllBytes(path));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        
        try {
            ja = (JsonArray) Jsoner.deserialize(jsonText);
        } catch (JsonException ex) {
            throw new RuntimeException(ex);
        }
        
        for (Object o : ja) {
            JsonObject jo = (JsonObject) o;
            MonthRevenue mr = MonthRevenue.fromJsonObject(jo);
            Game_space.mrl.add(mr);
        }
    }
    
    
    public static void main(String[] args) {
        
        String thisMounth = LocalDate.now().format(DateTimeFormatter.ofPattern("LLLL"));
        
        
        Game_space.load();
        System.out.println(thisMounth);
        if (!Game_space.mrl.get(Game_space.mrl.size()-1).getName().equals(thisMounth)) {
            Game_space.mrl.add(new MonthRevenue(thisMounth,0));
        }
        
//        Total revenue de mois

//          Game_space.load();
//          Game_space.save();
//          for(MonthRevenue monR : Game_space.mrl){
//              System.out.println(monR.getName() +" : " + monR.getRevenue());
//          }
          
        
       
        
        
//        Total revenue de la journee
        double td = 0;
        
//        JsonObject jo = new JsonObject();
        
        
        
        
        
//        Clients or Gamers
        ArrayList<Gamer> clients = new ArrayList<>();
        
//      Waiting List
        ArrayList<Gamer> waitingList = new ArrayList<>();
        
//      Posts
        ArrayList<Post> posts = new ArrayList<>();
        posts.add(new Post("ps5","samsung",true));
        posts.add(new Post("ps5","samsung",true));
        posts.add(new Post("ps5","dell",true));
        posts.add(new Post("xbox","dell",true));
        posts.add(new Post("xbox","asus",true));
        posts.add(new Post("xbox","asus",true));
        posts.add(new Post("xbox","hp",true));
        posts.add(new Post("ns","dell",true));
        posts.add(new Post("ns","asus",true));
        
//      Games
        ArrayList<String> games = new ArrayList<>();
        games.add("FIFA");
        games.add("PES");
        games.add("Counter-Strike");
        games.add("Assassin's Creed");

//      Durations        
        HashMap<String,Integer> durations = new HashMap<>();
        durations.put("Half Hour",1800000 );
        durations.put("1 Hour",3600000 );
        durations.put("2 Hours",7200000 );
        durations.put("5 Hours",18000000 );
        durations.put("All Day Long",32400000 );
        
        ArrayList<String> schedulesAvailable = new ArrayList<>();
        schedulesAvailable.add("Half Hour");
        schedulesAvailable.add("1 Hour");
        schedulesAvailable.add("2 Hours");
        schedulesAvailable.add("5 Hours");
        schedulesAvailable.add("All Day Long");
//      
     
        
        
        
        Scanner scanner = new Scanner(System.in);

        int option = 1;
        while (option != 5){
            printMenu(options);
            try {
                option = scanner.nextInt();
                switch (option){
                    case 1 -> {
                        String lname,fname;
                        int duration,postNum,game;
//                        Game
                        gamesMenu(games);
                        game = (scanner.nextInt()-1);
                        System.out.println("you choosed : "+ games.get(game));
                        
//                        Duration
                        durationsMenu(schedulesAvailable);
                        duration = (scanner.nextInt()-1);
                        System.out.println("you choosed : "+ schedulesAvailable.get(duration)+ " - " + durations.get(schedulesAvailable.get(duration)));
//                        First & Last name
                        System.out.print("First Name :");
                        scanner.nextLine();
                        fname = scanner.nextLine();
                        System.out.print("Last Name :");
                        lname = scanner.nextLine();
                        
//                        Post
                        Post.postMenu(posts);
                        postNum = (scanner.nextInt()-1);
                        
                        Gamer g = new Gamer(lname,fname,postNum,durations.get(schedulesAvailable.get(duration)),games.get(game),getPrice(schedulesAvailable.get(duration)));
                        Game_space.mrl.get(Game_space.mrl.size()-1).setRevenue(Game_space.mrl.get(Game_space.mrl.size()-1).getRevenue()+getPrice(schedulesAvailable.get(duration)));
                        Game_space.save();
                        if(posts.get(postNum).getAvailable() == true){
                            Post.bookPost(postNum,posts);
                            td += 133.2;
                            clients.add(g);
                            Gamer.session s = g.new session(waitingList,posts);
                            
                        }else{
                            if(waitingList.size() >= 8){
                               System.out.println("        Sorry the waiting List is Full.");
                               System.out.println("        see you Soon.");
                               g = null;
                            }else{
                               clients.add(g);
                               waitingList.add(g);
                            }

                        }
                    }
                    case 2 ->{ 
                        
                    }
                    case 3 ->{ 
                        System.out.println("");
                    }
                    case 4 ->{ 
                        waitingList(waitingList);
                        
                    }
                    default -> exit(0);
                }
            }
            catch (Exception ex){
                System.out.println("Please enter an integer value between 1 and " + options.length);
                scanner.next();
            }
        }
    }
    
    public static void printMenu(String[] options){
        for (String option : options){
            System.out.println(option);
        }
        System.out.print("Choose your option : ");
    }
    public static final String[] options = {
        "1- nouveau client",
        "2- la somme totale de la journée",
        "3- Totale de revenus du mois",
        "4- Waiting List",
        "other- Exit"
    };
    
    public static void gamesMenu(ArrayList<String> games){
        System.out.println("Choose Game :");
        System.out.println("    Games Available:");
        for (int i = 0; i < games.size(); i++) {
            System.out.println( i+1 + "- "+games.get(i));
        }
        System.out.print("\nChoose game : ");
    }
    
    public static int getPrice(String d){
        int p;
        p = switch (d) {
            case "Half Hour" -> 5;
            case "1 Hour" -> 10;
            case "2 Hours" -> 18;
            case "5 Hours" -> 40;
            case "All Day Long" -> 65;
            default -> 0;
        };
        return p;
    }
    
    public static void durationsMenu(ArrayList<String> sa){
        LocalTime now = LocalTime.now();
        LocalTime midi = LocalTime.parse("12:00");
        LocalTime L3chiya = LocalTime.parse("20:00");
        
        System.out.println(now.toString() +" - "+ midi.toString() + " = "+ Duration.between(now, midi).toMinutesPart());
        
        
        System.out.println("choose Duration");
        System.out.println("    Duration(s) Available :");
        
        int dur = Duration.between(now, midi).toMinutesPart();
        
        double hours = dur/60;
        if(now.isAfter(midi) && now.isBefore(LocalTime.parse("14:00"))){
            System.out.println("Sad");
        }else if(dur > 0){
            
            if(dur < 30){
                System.out.println("Ansado db");
            }else if(dur > 30 && hours < 1){
                System.out.println( 1 + "- "+sa.get(0));
            }else if(dur > 60 && hours < 2){
                for (int i = 1; i < sa.size()-2; i++) {
                    System.out.println( i + "- "+sa.get(i-1));
                }
            }else if(dur > 120 && hours < 3){
                for (int i = 1; i < sa.size()-1; i++) {
                    System.out.println( i + "- "+sa.get(i-1));
                }
            }else if(dur > 300){
                for (int i = 1; i < sa.size(); i++) {
                    System.out.println( i + "- "+sa.get(i-1));
                }
            }
            
        }else{
            dur = Duration.between(now, L3chiya).toMinutesPart();
            
        }
        
        
        
        
//        for (int i = 1; i < sa.size()+1; i++) {
//            System.out.println( i + "- "+sa.get(i-1));
//        }
        System.out.print("\nChoose duration : ");
    }
    
    public static void waitingList(ArrayList<Gamer> wl){
        System.out.println("Waiting List :");
        if (wl.size() >= 1 ) {
           for (int i = 0; i < wl.size(); i++) {
            System.out.println(" "+ (i+1) +"- First Name : "+ wl.get(i).getfName() +"; Last Name : "+ wl.get(i).getlName() +"; Post Number : "+ (wl.get(i).getPostN()+1));
           } 
        }else{
            System.out.println("\n\nThe waiting List is empty.");
        }
        System.out.print("\n\n");
    }
}
