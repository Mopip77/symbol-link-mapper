package tech.mopip77.symbollinkmapper.enums;

public enum SymbolLinkType {
    DirectLink(0, "direct_link"),
    RecursiveLink(1, "recursive_link");

    private Integer number;
    private String type;

    public static SymbolLinkType getByNumber(int number) {
        if (number == 0)
            return SymbolLinkType.DirectLink;
        else if (number == 1)
            return SymbolLinkType.RecursiveLink;
        else
            return null;
    }

    SymbolLinkType(Integer number, String type) {
        this.number = number;
        this.type = type;
    }

    public static boolean isInRange(int type) {
        return type >= 0 && type <= 1;
    }

    public Integer getNumber() {
        return number;
    }

    public String getType() {
        return type;
    }
}
