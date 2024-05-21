import java.util.Objects;

public class Laptop {
    private String brand;
    private int ram; 
    private int storage; 
    private String os; 
    private String color;

    public Laptop(String brand, int ram, int storage, String os, String color) {
        this.brand = brand;
        this.ram = ram;
        this.storage = storage;
        this.os = os;
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public int getRam() {
        return ram;
    }

    public int getStorage() {
        return storage;
    }

    public String getOs() {
        return os;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Ноутбук{" +
                "Бренд='" + brand + '\'' +
                ", ОЗУ=" + ram +
                ", Объем ЖД=" + storage +
                ", Операционная система='" + os + '\'' +
                ", Цвет='" + color + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;
        
        Laptop laptop = (Laptop) o;

        boolean resault = ram == laptop.ram && storage == laptop.storage 
        && Objects.equals(brand, laptop.brand) && Objects.equals(os, laptop.os) && Objects.equals(color, laptop.color);

        return resault;
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, ram, storage, os, color);
    }
}