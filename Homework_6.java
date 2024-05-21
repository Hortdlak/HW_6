public class Homework_6 {

    public static void main(String[] args) {
        LaptopCollection collection = new LaptopCollection();
        LaptopFilter filter = new LaptopFilter(collection);
        filter.filterLaptops();
    }
}