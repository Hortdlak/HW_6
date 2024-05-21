import java.util.HashSet;
import java.util.Set;

public class LaptopCollection {
    private Set<Laptop> laptops;

    public LaptopCollection() {
        laptops = new HashSet<>();
        laptops.add(new Laptop("Dell", 16, 512, "Windows 10", "Black"));
        laptops.add(new Laptop("HP", 8, 256, "Windows 10", "Silver"));
        laptops.add(new Laptop("Apple", 8, 512, "macOS", "Gray"));
        laptops.add(new Laptop("Asus", 16, 1024, "Windows 10", "White"));
        laptops.add(new Laptop("Lenovo", 4, 500, "Windows 7", "Black"));
    }

    public Set<Laptop> getLaptops() {
        return laptops;
    }

    public void addLaptop(Laptop laptop) {
        laptops.add(laptop);
    }

    public Set<String> getAvailableBrands() {
        Set<String> brandSet = new HashSet<>();
        for (Laptop laptop : laptops) {
            brandSet.add(laptop.getBrand().toLowerCase());
        }
        return brandSet;
    }

    public Set<String> getAvailableOperatingSystems() {
        Set<String> osSet = new HashSet<>();
        for (Laptop laptop : laptops) {
            osSet.add(laptop.getOs().toLowerCase());
        }
        return osSet;
    }

    public Set<String> getAvailableColors() {
        Set<String> colorSet = new HashSet<>();
        for (Laptop laptop : laptops) {
            colorSet.add(laptop.getColor().toLowerCase());
        }
        return colorSet;
    }
}
