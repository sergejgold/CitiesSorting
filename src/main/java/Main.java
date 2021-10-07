import java.util.List;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws Exception {
        boolean flag = true;
        List<City> listCities = Utils.getList();
        String drop = "DROP TABLE IF EXISTS cities";
        String create = "create table if not exists Cities\n" +
                "(\n" +
                "    idCities int auto_increment\n" +
                "    primary key,\n" +
                "    name varchar(45) null,\n" +
                "    region varchar(45) null,\n" +
                "    district varchar(45) null,\n" +
                "    population int null,\n" +
                "    foundation int null\n" +
                "    )";
        Utils.loadScripts(drop);
        Utils.loadScripts(create);
        Utils.addToList();
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
                    Utils.deleteAllFields();
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
