import java.util.*;
import java.util.stream.Collectors;

public class LaptopFilter {
    private final LaptopCollection collection;

    public LaptopFilter(LaptopCollection collection) {
        this.collection = collection;
    }

    public void filterLaptops() {
        Map<FilterCriteria, Object> criteria = getUserCriteria();

        System.out.println("Критерии фильтрации: " + criteria);

        Set<Laptop> filteredLaptops = filterLaptopsByCriteria(collection.getLaptops(), criteria);
        if (filteredLaptops.isEmpty()) {
            System.out.println("Нет ноутбуков, соответствующих заданным критериям.");
        } else {
            System.out.println("Ноутбуки, соответствующие заданным критериям:");
            filteredLaptops.forEach(System.out::println);
        }
    }

    private Map<FilterCriteria, Object> getUserCriteria() {
        Map<FilterCriteria, Object> criteria = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
    
        try {
            boolean continueSelection = true;
    
            // Цикл для выбора критериев фильтрации
            while (continueSelection) {
                // Выводим пользователю список доступных критериев
                System.out.println("Введите цифру, соответствующую необходимому критерию:");
                for (FilterCriteria criterion : FilterCriteria.values()) {
                    System.out.printf("%d - %s%n", criterion.ordinal() + 1, criterion.getDescription());
                }
                System.out.println("6 - Завершить выбор критериев");
    
                // Получаем выбор пользователя
                int choice = getIntInput(scanner, 1, 6);
    
                // Если пользователь выбрал 6, прекращаем выбор критериев
                if (choice == 6) {
                    continueSelection = false;
                } else {
                    // Иначе обрабатываем выбранный критерий
                    FilterCriteria selectedCriterion = FilterCriteria.values()[choice - 1];
                    switch (selectedCriterion) {
                        case BRAND:
                            // Запрос бренда у пользователя и добавление в критерии
                            String brand = getStringInput(scanner, "бренд", collection.getAvailableBrands());
                            criteria.put(FilterCriteria.BRAND, brand);
                            break;
                        case RAM:
                            // Запрос минимального значения ОЗУ у пользователя и добавление в критерии
                            int ram = getIntInput(scanner, "минимальное значение ОЗУ (ГБ)");
                            criteria.put(FilterCriteria.RAM, ram);
                            break;
                        case STORAGE:
                            // Запрос минимального объема ЖД у пользователя и добавление в критерии
                            int storage = getIntInput(scanner, "минимальный объем ЖД (ГБ)");
                            criteria.put(FilterCriteria.STORAGE, storage);
                            break;
                        case OS:
                            // Запрос операционной системы у пользователя и добавление в критерии
                            String os = getStringInput(scanner, "операционную систему", collection.getAvailableOperatingSystems());
                            criteria.put(FilterCriteria.OS, os);
                            break;
                        case COLOR:
                            // Запрос цвета у пользователя и добавление в критерии
                            String color = getStringInput(scanner, "цвет", collection.getAvailableColors());
                            criteria.put(FilterCriteria.COLOR, color);
                            break;
                        default:
                            // Обработка неверного выбора
                            System.out.println("Неверный выбор, попробуйте снова.");
                    }
                }
            }
        } finally {
            // Закрытие сканера
            scanner.close();
        }
        return criteria;
    }

    private int getIntInput(Scanner scanner, String prompt) {
        System.out.print("Введите " + prompt + ": ");
        while (!scanner.hasNextInt()) {
            System.out.println("Введите корректное числовое значение.");
            scanner.next(); 
        }
        int value = scanner.nextInt();
        scanner.nextLine(); 
        return value;
    }

    private int getIntInput(Scanner scanner, int min, int max) {
        int value;
        while (true) {
            while (!scanner.hasNextInt()) {
                System.out.printf("Введите число от %d до %d.%n", min, max);
                scanner.next(); 
            }
            value = scanner.nextInt();
            scanner.nextLine(); 
            if (value >= min && value <= max) {
                break;
            } else {
                System.out.printf("Введите число от %d до %d.%n", min, max);
            }
        }
        return value;
    }

    private String getStringInput(Scanner scanner, String prompt, Set<String> validOptions) {
        System.out.println("Доступные " + prompt + "ы: " + validOptions);
        String input;
        while (true) {
            System.out.print("Введите " + prompt + ": ");
            input = scanner.nextLine().trim().toLowerCase();
            if (validOptions.contains(input)) {
                break;
            } else {
                System.out.println("Некорректный " + prompt + ". Попробуйте снова.");
            }
        }
        return input;
    }

    private Set<Laptop> filterLaptopsByCriteria(Set<Laptop> laptops, Map<FilterCriteria, Object> criteria) {
        return laptops.stream()
                .filter(laptop -> criteria.entrySet().stream()
                        .allMatch(entry -> {
                            switch (entry.getKey()) {
                                case BRAND:
                                    return laptop.getBrand().equalsIgnoreCase((String) entry.getValue());
                                case RAM:
                                    return laptop.getRam() >= (int) entry.getValue();
                                case STORAGE:
                                    return laptop.getStorage() >= (int) entry.getValue();
                                case OS:
                                    return laptop.getOs().equalsIgnoreCase((String) entry.getValue());
                                case COLOR:
                                    return laptop.getColor().equalsIgnoreCase((String) entry.getValue());
                                default:
                                    return true;
                            }
                        }))
                .collect(Collectors.toSet());
    }
}