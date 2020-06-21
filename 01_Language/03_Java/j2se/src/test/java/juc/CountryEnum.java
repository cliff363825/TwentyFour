package juc;

public enum CountryEnum {
    ONE(1, "齐"),
    TWO(2, "楚"),
    THREE(3, "燕"),
    FOUR(4, "赵"),
    FIVE(5, "魏"),
    SIX(6, "韩");

    private final int id;
    private final String name;

    CountryEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static CountryEnum getCountryEnumById(int id) {
        for (CountryEnum countryEnum : CountryEnum.values()) {
            if (countryEnum.getId() == id) {
                return countryEnum;
            }
        }
        return null;
    }
}
