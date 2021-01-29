public class Settings {
    public static int time;
    public String examPwd;
    public static String subject;
    public static String subcode;
    public static String ext;
    public static int Qcount;
    public static String QAns;
    public String year;
    
    static {
        Settings.time = 45;
        Settings.subject = "Technical Quiz";
        Settings.subcode = "CS604";
        Settings.ext = ".PNG";
        Settings.Qcount = 25;
        Settings.QAns = "4444444444444444444444444";
    }
    
    public Settings() {
        this.examPwd = "CyberPunk";
        this.year = "Teckzite'19";
    }
}
