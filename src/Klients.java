public class Klients {
    private String stils;
    private String topings;
    private int izmers;
    private String piegade;

    public Klients(String veids, String stils, String topings, int izmers, String piegade) {
        this.stils = stils;
        this.topings = topings;
        this.izmers = izmers;
        this.piegade = piegade;
    }

    public String getStyle() { return stils; }
    public String getToping() { return topings; }
    public int getSize() { return izmers; }
    public String getDeliveryMethod() { return piegade; }
}