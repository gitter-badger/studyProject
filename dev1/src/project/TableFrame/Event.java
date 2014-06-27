package project.TableFrame;

public class Event {
    String name;
    String description;

    public Event(String n, String d){
        name = n;
        description = d;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
