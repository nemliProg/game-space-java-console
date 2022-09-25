
import java.util.ArrayList;

public class Post {
    private String console;
    private String monitor;
    private boolean available;
    
    public Post(String console, String monitor,boolean available) {
        this.console = console;
        this.monitor = monitor;
        this.available = available;
    }
    public String getConsole() {
        return console;
    }
    public String getMonitor() {
        return monitor;
    }
    public boolean getAvailable() {
        return available;
    }
    public void setConsole(String console) {
        this.console = console;
    }
    public void setMonitor(String monitor) {
        this.monitor = monitor;
    }
    public void setAvailable(boolean number) {
        this.available = number;
    }
    public static void postMenu(ArrayList<Post> posts){
        System.out.println("Choose poste n°  :");
        System.out.println("    Posts Available:");
        for (int i=0;i < 9;i++){
            if(posts.get(i).getAvailable() == true){
                System.out.println("  "+(i+1)+"- "+ posts.get(i).getConsole()+ " / "+ posts.get(i).getMonitor());
            }else {
                System.out.println("  "+(i+1)+"- "+ posts.get(i).getConsole()+ " / "+ posts.get(i).getMonitor() + " ***");
            }
        }
        System.out.println("    *** : This Post not vacant, you will be added in the waiting list\n");
        System.out.print("choose your post : ");
    }
    public static void bookPost(int nbrPost,ArrayList<Post> posts){
        posts.get(nbrPost).setAvailable(false);
        System.out.println("/nPost n°"+(nbrPost+1)+" booked succesfully."+" - " + "\n");
        
    }  
}
