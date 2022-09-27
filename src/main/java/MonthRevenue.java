
import com.github.cliftonlabs.json_simple.JsonObject;
import java.math.BigDecimal;


public class MonthRevenue {
    private String name;
    private double revenue;

    public MonthRevenue(String name, double revenue) {
        this.name = name;
        this.revenue = revenue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }
    
    public JsonObject toJsonObject(){
        JsonObject jo = new JsonObject();
        jo.put("Month",name);
        jo.put("Revenue", revenue);
        return jo;
    }
    
    public static MonthRevenue fromJsonObject(JsonObject jo){
        String name = (String) jo.get("Month");
        double revenue = ((BigDecimal) jo.get("Revenue")).doubleValue();
        return new MonthRevenue(name,revenue);
    }
    
    
}
