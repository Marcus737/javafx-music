package cn.gxust.var;

public class MusicVar {

    private static int pn;
    private static int rn;
    private static String keyword;
    private static volatile boolean isSearching = false;

    public static void init(){
        pn = 0;
        rn = 20;
        keyword = "";
    }

    public static boolean isIsSearching() {
        return isSearching;
    }

    public static void setIsSearching(boolean isSearching) {
        MusicVar.isSearching = isSearching;
    }

    public static int getPn() {
        return pn;
    }

    public static void setPn(int pn) {
        MusicVar.pn = pn;
    }

    public static int getRn() {
        return rn;
    }

    public static void setRn(int rn) {
        MusicVar.rn = rn;
    }

    public static String getKeyword() {
        return keyword;
    }

    public static void setKeyword(String keyword) {
        MusicVar.keyword = keyword;
    }

    public static void next(){
        pn += 1;
    }
}
