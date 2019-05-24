import java.lang.reflect.Array;
import java.util.ArrayList;

public class StableMatch {
    StableMatch(ArrayList<Person> men, ArrayList<Person> women){
        int menLeftToDo = men.size();
        while (menLeftToDo > 0) {
            for (Person man : men) {
                if (man.isFree()) {
                    Person woman = null;
                    for (String partner : man.prefs) {
                        if (!man.proposedTo.contains(partner)) {
                            for (Person w : women) {
                                if (w.getName().equals(partner)) {
                                    woman = w;
                                    man.addProposedTo(w.getName());
                                    break;
                                }
                            }
                            break;
                        }
                    }
                    if (woman != null) {
                        if (woman.isFree()) {
                            woman.setEngagedTo(man.getName());
                            man.setEngagedTo(woman.getName());
                        } else {
                            int engIndex = woman.prefs.indexOf(woman.getEngagedTo());
                            int curIndex = woman.prefs.indexOf(man.getName());
                            if (curIndex < engIndex) {
                                for (Person m : men) {
                                    if (m.getName().equals(woman.getEngagedTo())) {
                                        m.becomeFree();
                                        break;
                                    }
                                }
                                woman.setEngagedTo(man.getName());
                                man.setEngagedTo(woman.getName());
                            }
                        }
                    }
                }
            }
//            w = first woman on m’s list to whom m has not yet proposed
//            if w is free
//                    (m, w) become engaged
//            else some pair (m', w) already exists
//                if w prefers m to m'
//                    m' becomes free
//                    (m, w) become engaged
//                else
//                    (m', w) remain engaged
            menLeftToDo = men.size();
            for (Person m : men) {
                if (!m.isFree()) menLeftToDo--;
            }
        }
        for (Person m : men) {
            System.out.println(m.toString());
        }
        for (Person f : women) {
            System.out.println(f.toString());
        }
    }

    public static void main(String[] args) {
        ArrayList<Person> males = new ArrayList<>();
        males.add(new Person("Bob"));
        males.add(new Person("Joe"));
        males.add(new Person("Jeff"));
        males.add(new Person("Billy"));
        ArrayList<Person> females = new ArrayList<>();
        females.add(new Person("Emma"));
        females.add(new Person("Lauren"));
        females.add(new Person("Rhiannon"));
        females.add(new Person("Janet"));
        for (Person m : males) {
            m.setPrefs(females);
        }
        for (Person f : females) {
            f.setPrefs(males);
        }
        StableMatch match = new StableMatch(males, females);
    }
}
