import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Utils {

    public static List<City> addToList() {
        List<City> cities = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File("Cities.txt"));
            while (scanner.hasNextLine()) {
                cities.add(parsingToObject(scanner.nextLine()));
            }
            scanner.close();
            for (int i = 0; i < cities.size(); i++) {
                System.out.println(cities.get(i));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return cities;
    }
    private static City parsingToObject(String line) {
        Scanner scanner = new Scanner(line);
        scanner.useDelimiter(";");
        scanner.skip("\\d*");
        String name = scanner.next();
        String region = scanner.next();
        String district = scanner.next();
        int population = scanner.nextInt();
        int foundation = scanner.nextInt();
        makeConnectAndTransfer(name, region, district, population, foundation);
        scanner.close();
        return new City(name, region, district, population, foundation);
    }

    public static void makeConnectAndTransfer(String s1, String s2, String s3, int i4, int i5) {
        try {
            String myDriver = "com.mysql.cj.jdbc.Driver";
            String myUrl = "jdbc:mysql://localhost:3306/sys?useSSL=false";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "GtV7t31z");
            String query = "insert into Cities (name, region, district, population, foundation)"
                    + " values (?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, s1);
            preparedStmt.setString(2, s2);
            preparedStmt.setString(3, s3);
            preparedStmt.setInt(4, i4);
            preparedStmt.setInt(5, i5);
            preparedStmt.execute();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<City> getList() {
        List<City> cities = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File("Cities.txt"));
            while (scanner.hasNextLine()) {
                cities.add(parsingToObject(scanner.nextLine()));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return cities;
    }

    public static void deleteAllFields() {
        try {
            String myDriver = "com.mysql.cj.jdbc.Driver";
            String myUrl = "jdbc:mysql://localhost:3306/sys?useSSL=false";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "GtV7t31z");
            String query = "truncate Cities";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.execute();
            conn.close();
        } catch (SQLException |
                ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    // Module 2
    public static void sortByName(List<City> cities) {
        cities.sort(new Comparator<City>() {
            @Override
            public int compare(City o1, City o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
    }

    public static void sortByDistrictAndName(List<City> cities) {
        cities.sort(Comparator.comparing(City::getDistrict).thenComparing(City::getName));
    }

    // module 3
    public static void cityWithMaxPopulation(List<City> cities) {
        City[] arrayCities = new City[cities.size()];
        cities.toArray(arrayCities);
        City current = arrayCities[0];
        int index = 0;

        for (int i = 1; i < arrayCities.length; i++) {
            if (arrayCities[i].getPopulation() > current.getPopulation()) {
                current = arrayCities[i];
                index = i;
            }
        }
        System.out.println("[" + index + "]" + " = " + arrayCities[index].getPopulation());
    }

    // module 4
        public static void findCitiesForRegion(List<City> cities) {
            System.out.println(cities.stream().collect(Collectors.groupingBy(City::getRegion, Collectors.counting())));
        }
}
