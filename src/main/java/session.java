//
//import java.time.LocalTime;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.Timer;
//import java.util.TimerTask;
//
//public class session {
//    
//    private LocalTime beginTime;
//    private int duration;
//    private final String GamerCode;
//
//    public session( int duration, String GamerCode,ArrayList<Gamer> wl,ArrayList<Gamer> cl) {
//        LocalTime t = LocalTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
//        String text = t.format(formatter);
//        LocalTime parsedTime = LocalTime.parse(text, formatter);
//        Timer timer = new Timer();
//        
//        TimerTask task = new TimerTask() {
//            
//            @Override
//            public void run(){
//                cl.contains();
//            }
//        
//        };
//        
//        timer.schedule(task, duration);
//        
//        this.beginTime = parsedTime;
//        this.duration = duration;
//        this.GamerCode = GamerCode;
//    }
//
//
//    public LocalTime getBeginTime() {
//        return beginTime;
//    }
//
//    public void setBeginTime(LocalTime beginTime) {
//        this.beginTime = beginTime;
//    }
//
//    public int getHours() {
//        return duration;
//    }
//
//    public void setHours(int duration) {
//        this.duration = duration;
//    }
//
//    public String getGamerCode() {
//        return GamerCode;
//    }
    
    
    
    
//    public static void main(String[] args){
//        session g1 = new session();
//        
//        LocalTime t = LocalTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
//        String text = t.format(formatter);
//        LocalTime parsedTime = LocalTime.parse(text, formatter);
//        g1.setBeginTime(parsedTime);
//        System.out.println(g1.getBeginTime());
        
//    }
//}
