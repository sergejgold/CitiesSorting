import java.util.List;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        boolean flag = true;
        Utils.deleteAllFields();
        List<City> listCities = Utils.getList();
        while (flag) {
            System.out.println("Выберите действие: ");
            System.out.println("1) Список городов.");
            System.out.println("2) Количество городов в разрезе регионов.");
            System.out.println("3) Сортировка по наименованию без учёта регистра.");
            System.out.println("4) Сортировка по федеральному округу и наименованию.");
            System.out.println("5) Город с наибольшим количеством жителей.");
            System.out.println("6) Выход.");
            Scanner inputDigit = new Scanner(System.in);
            switch (inputDigit.next()) {
                case "1":
                    System.out.println();
                    Utils.addToList();
                    System.out.println();
                    break;
                case "2":
                    System.out.println();
                    Utils.findCitiesForRegion(listCities);
                    System.out.println();
                    break;
                case "3":
                    System.out.println();
                    Utils.sortByName(listCities);
                    printCities(listCities);
                    System.out.println();
                    break;
                case "4":
                    System.out.println();
                    Utils.sortByDistrictAndName(listCities);
                    printCities(listCities);
                    System.out.println();
                    break;
                case "5":
                    System.out.println();
                    Utils.cityWithMaxPopulation(listCities);
                    System.out.println();
                    break;
                case "6":
                    flag = false;
                    System.out.println();
                    Utils.deleteAllFields();
                    System.out.println();
                    break;
                default:
                    System.out.println("#################################");
                    System.out.println("---> Введите число с 1 до 6: <---");
                    System.out.println("#################################");
            }
        }
    }

    public static void printCities(List<City> cities) {
        cities.forEach(System.out::println);
    }
}
