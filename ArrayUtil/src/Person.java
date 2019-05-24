import java.util.ArrayList;

public class Person {
    private String name;
    ArrayList<String> prefs = new ArrayList<>();
    private boolean isFree = true;
    ArrayList<String> proposedTo = new ArrayList<>();
    private String engagedTo;
    Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getPrefs() {
        return prefs;
    }

    public void setPrefs(ArrayList<Person> partners) {
        prefs.clear();
        for (int i = 0; i < partners.size(); i++) {
            String name = partners.get((int)(Math.random()*partners.size())).name;
            while (prefs.contains(name)) {
                name = partners.get((int)(Math.random()*partners.size())).name;
            }
            prefs.add(name);
        }
    }

    public boolean isFree() {
        return isFree;
    }

    private void setFree(boolean free) {
        isFree = free;
    }

    public ArrayList<String> getProposedTo() {
        return proposedTo;
    }

    public void addProposedTo(String name) {
        this.proposedTo.add(name);
    }

    public String getEngagedTo() {
        return engagedTo;
    }

    public void setEngagedTo(String engagedTo) {
        setFree(false);
        this.engagedTo = engagedTo;
    }

    public void becomeFree() {
        setEngagedTo("");
        setFree(true);
    }

    public boolean proposedToAll() {
        return proposedTo.size() >= prefs.size();
    }

    public String toString() {
        return name + " engaged to " + engagedTo + " at pref level " + prefs.indexOf(engagedTo);
    }
}
