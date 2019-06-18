package gg.stelch.core.Varables;

public enum ranks {
    MEMBER("&e"), ADMIN("&c"), OWNER("&9");
    private String rank;
    public String getRank() {return this.rank;}
    private ranks(String rank){this.rank = rank.toUpperCase();}
}