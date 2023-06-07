package hellocucumber;

public class IsItFriday {

    private IsItFriday() {

    }

    public static String isItFriday(String today) {
        return "Friday".equals(today) ? "TGIF" : "Nope";
    }
}
