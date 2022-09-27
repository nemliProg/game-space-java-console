import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

public class Gamer {
    private final String code;
    private String fName;
    private String lName;
    private int postN;
    private int duration;
    private String game;
    private double price;

    public Gamer(String fName, String lName, int postN,int duration, String game, double price) {
        
        this.code = UUID.randomUUID().toString();
        this.fName = fName;
        this.lName = lName;
        this.postN = postN;
        this.duration = duration;
        this.game = game;
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public int getPostN() {
        return postN;
    }

    public void setPostN(int postN) {
        this.postN = postN;
    }

    public int getDuration() {
        return duration;
    }
    
    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public class session {
    
        private LocalTime beginTime;

        public session(ArrayList<Gamer> wl,ArrayList<Post> p) {
            LocalTime t = LocalTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            String text = t.format(formatter);
            LocalTime parsedTime = LocalTime.parse(text, formatter);
            Timer timer = new Timer();

            TimerTask task = new TimerTask() {

                @Override
                public void run(){
                    int test = 0;
                    for (Gamer g : wl) {
                        if (g.getPostN() == postN) {
                            Gamer.session s = g.new session(wl,p);
                            p.get(postN).setAvailable(false);
                            System.out.println("/nPost n°"+(postN+1)+" booked from waiting List succesfully."+" - " + "\n");
                            test++;
                            wl.remove(g);
                            break;
                        }
                    }
                    if (test == 0) {
                        p.get(postN).setAvailable(true);
                    }
                }

            };

            timer.schedule(task, duration);

            this.beginTime = parsedTime;
        }


        public LocalTime getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(LocalTime beginTime) {
            this.beginTime = beginTime;
        }

    }
}
