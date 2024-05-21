public enum FilterCriteria {
    BRAND("Бренд"),
    RAM("ОЗУ"),
    STORAGE("Объем ЖД"),
    OS("Операционная система"),
    COLOR("Цвет");

    private final String description;

    FilterCriteria(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
