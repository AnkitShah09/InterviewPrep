import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/*
    Reference: https://medium.com/@veenaraofr/java8-stream-api-commonly-asked-questions-about-employee-highest-salary-99c21cec4d98
*/
class EmployeeStreamAPIQuestions {
    public static void main(String[] args) {
        List<Employee> empList = new ArrayList<>();
        empList.add(new Employee(1, "abc", 28, 123, "F", "HR", "Blore", 2020));
        empList.add(new Employee(2, "xyz", 29, 120, "F", "HR", "Hyderabad", 2015));
        empList.add(new Employee(3, "efg", 30, 115, "M", "HR", "Chennai", 2014));
        empList.add(new Employee(4, "def", 32, 125, "F", "HR", "Chennai", 2013));

        empList.add(new Employee(5, "ijk", 22, 150, "F", "IT", "Noida", 2013));
        empList.add(new Employee(6, "mno", 27, 140, "M", "IT", "Gurugram", 2017));
        empList.add(new Employee(7, "uvw", 26, 130, "F", "IT", "Pune", 2016));
        empList.add(new Employee(8, "pqr", 23, 145, "M", "IT", "Trivandam", 2015));
        empList.add(new Employee(9, "stv", 25, 160, "M", "IT", "Blore", 2010));

//      1. Group the Employees by city.
//        groupEmployeeByCity(empList);

//      2. Group the Employees by age.
//        groupEmployeeByAge(empList);

//      3. Find the count of male and female employees present in the organization.
//        countMaleFemaleEmployees(empList);

//      4. Print the names of all departments in the organization.
//        printAllDepartmentName(empList);

//      5. Print employee details whose age is greater than 28.
//        findEmployeeAgeGreaterThan28(empList);

//      6. Find maximum age of employee.
//        findMaximumAge(empList);

//      7. Print Average age of Male and Female Employees.
//        findAverageAgeOfMaleAndFemaleEmployees(empList);

//      8. Print the number of employees in each department.
//        countEmployeesInEachDepartment(empList);

//      9. Find oldest employee.
//        findOldestEmployee(empList);

//      10. Find youngest female employee.
//        findYoungestFemaleEmployee(empList);

//      11. Find employees whose age is greater than 30 and less than 30.
//        findEmployeesWhoseAgeGreaterThan30AndLessThan30(empList);

//      12. Find the department name which has the highest number of employees.
//        findDepartmentWithHighestEmployees(empList);

//      13. Find if there any employees from HR Department.
//        findEmpployeesFromHR(empList);

//      14. Find the department names that these employees work for, where the number of employees in the department is over 3.
//        findDepartmentWithMoreThan3Employees(empList);

//      15. Find distinct department names that employees work for.
//        findDistinctDepartment(empList);

//      16. Find all employees who lives in ‘Blore’ city, sort them by their name and print the names of employees.
//        findEmployeesFromBangaloreAndSortByName(empList);

//      17. No of employees in the organisation.
//        findTotalEmployees(empList);

//      18. Find employee count in every department
//        findEmployeeCountInEveryDepartment(empList);

//      19. Find the department which has the highest number of employees.
//        findDepartmentWithHighestEmployees(empList);

//      20. Sorting a Stream by age and name fields.
//        sortBasedOnAgeAndName(empList);

//      21. Highest experienced employees in the organization.
//        findHighestExperiencedEmployee(empList);

//      22. Print average and total salary of the organization.
//        findAverageAndTotalSalaryOfOrg(empList);

//      23. Print Average salary of each department.
//        findAverageSalaryForEachDepartment(empList);

//      24. Find Highest salary in the organisation.
//        findHighestSalary(empList);

//      25. Find Second Highest salary in the organisation.
//        findSecondHighestSalary(empList);

//      26. Nth Highest salary.
//        findNthHighestSalary(empList, 5);

//      27. Find highest paid salary in the organisation based on gender.
//        findHighestSalaryInEachGender(empList);

//      28. Find lowest paid salary in the organisation based on gender.
//        findLowestSalaryInEachGender(empList);

//      29. Sort the employees salary in the organisation in ascending order
//        sortEmployeesSalaryInASC(empList);

//      30. Sort the employees salary in the organisation in descending order.
//        sortEmployeesSalaryInDESC(empList);

//      31. Highest salary based on department.
//        findHighestSalaryInEachDepartment(empList);

//      32. Print list of employee’s second highest record based on department
//        find2ndHighestSalaryInEachDepartment(empList);

//      33. Sort the employees salary in each department in ascending order
//        sortEmployeesBasedOnSalaryInEachDepartment(empList);

//      34. Sort the employees salary in each department in descending order
//        sortEmployeesInDescSalaryInEachDepartment(empList);
    }

    private static void sortEmployeesInDescSalaryInEachDepartment(List<Employee> empList) {
        empList.stream()
                .collect(
                        Collectors.groupingBy(
                                Employee::deptName,
                                Collectors.collectingAndThen(Collectors.toList(), list -> list.stream().sorted(Comparator.comparingLong(Employee::salary).reversed()).toList())
                        )
                ).forEach((key, val) -> System.out.println(key + " -> " + val));
    }

    private static void sortEmployeesBasedOnSalaryInEachDepartment(List<Employee> empList) {
        empList.stream()
                .collect(
                        Collectors.groupingBy(
                                Employee::deptName,
                                Collectors.collectingAndThen(Collectors.toList(), list -> list.stream().sorted(Comparator.comparingLong(Employee::salary)).toList())
                        )
                ).forEach((key, val) -> System.out.println(key + " -> " + val));
    }

    private static void find2ndHighestSalaryInEachDepartment(List<Employee> empList) {
        empList.stream()
                .collect(Collectors.groupingBy(Employee::deptName, Collectors.collectingAndThen(Collectors.toList(), list -> list.stream().sorted(Comparator.comparingLong(Employee::salary).reversed()).skip(1).findFirst())))
                .forEach((key, val) -> System.out.println(key + " -> " + val));
    }

    private static void findHighestSalaryInEachDepartment(List<Employee> empList) {
        empList.stream()
                .collect(Collectors.groupingBy(Employee::deptName, Collectors.maxBy(Comparator.comparingLong(Employee::salary))))
                .forEach((key, val) -> System.out.println(key + " -> " + val));
    }

    private static void sortEmployeesSalaryInDESC(List<Employee> empList) {
        empList.stream()
                .sorted(Comparator.comparingLong(Employee::salary).reversed())
                .toList()
                .forEach(System.out::println);
    }

    private static void sortEmployeesSalaryInASC(List<Employee> empList) {
        empList.stream()
                .sorted(Comparator.comparingLong(Employee::salary))
                .toList()
                .forEach(System.out::println);
    }

    private static void findLowestSalaryInEachGender(List<Employee> empList) {
        empList.stream()
                .collect(Collectors.groupingBy(Employee::gender, Collectors.minBy(Comparator.comparingLong(Employee::salary))))
                .forEach((key, val) -> System.out.println(key + " -> " + val));
    }

    private static void findHighestSalaryInEachGender(List<Employee> empList) {
        empList.stream()
                .collect(Collectors.groupingBy(Employee::gender, Collectors.maxBy(Comparator.comparingLong(Employee::salary))))
                .forEach((key, val) -> System.out.println(key + " -> " + val));
    }

    private static void findNthHighestSalary(List<Employee> empList, int n) {
        Employee employee = empList.stream()
                .sorted(Comparator.comparingLong(Employee::salary).reversed())
                .skip(n-1)
                .findFirst()
                .get();
        System.out.println(employee);
    }

    private static void findSecondHighestSalary(List<Employee> empList) {
        Employee employee = empList.stream()
                .sorted(Comparator.comparingLong(Employee::salary).reversed())
                .skip(1)
                .findFirst()
                .get();
        System.out.println(employee);
    }

    private static void findHighestSalary(List<Employee> empList) {
        Employee employee = empList.stream()
                .max(Comparator.comparingLong(Employee::salary))
                .get();
        System.out.println(employee);
    }

    private static void findAverageSalaryForEachDepartment(List<Employee> empList) {
        Map<String, Double> stats = empList.stream()
                .collect(Collectors.groupingBy(Employee::deptName, Collectors.averagingDouble(Employee::salary)));
        System.out.println(stats);
    }

    private static void findAverageAndTotalSalaryOfOrg(List<Employee> empList) {
        DoubleSummaryStatistics summaryStatistics = empList.stream()
                .collect(Collectors.summarizingDouble(Employee::salary));
        System.out.println("Avg salary: " + summaryStatistics.getAverage() + ", Total Salary: " + summaryStatistics.getSum());
    }

    private static void findHighestExperiencedEmployee(List<Employee> empList) {
        Employee employee = empList.stream()
                .sorted(Comparator.comparingInt(Employee::yearOfJoining))
                .findFirst()
                .get();
        System.out.println(employee);
    }

    private static void sortBasedOnAgeAndName(List<Employee> empList) {
//        empList.stream()
//                .sorted(Comparator.comparingInt(Employee::age))
//                .sorted(Comparator.comparing(Employee::name))
//                .toList()
//                .forEach(System.out::println);
//        System.out.println();

        Comparator<Employee> comparator1 = Comparator.comparing(Employee::name);
        Comparator<Employee> comparator2 = Comparator.comparingInt(Employee::age);
        empList.stream()
                .sorted(comparator1.thenComparing(comparator2))
                .toList()
                .forEach(System.out::println);


    }

    private static void findEmployeeCountInEveryDepartment(List<Employee> empList) {
        Map<String, Long> collect = empList.stream()
                .collect(Collectors.groupingBy(Employee::deptName, Collectors.counting()));
        System.out.println(collect);
    }

    private static void findTotalEmployees(List<Employee> empList) {
        System.out.println(empList.stream().count());
    }

    private static void findEmployeesFromBangaloreAndSortByName(List<Employee> empList) {
        empList.stream()
                .filter(emp -> "Blore".equals(emp.city))
                .sorted(Comparator.comparing(Employee::name))
                .forEach(System.out::println);
    }

    private static void findDistinctDepartment(List<Employee> empList) {
        empList.stream()
                .map(Employee::deptName)
                .distinct()
                .forEach(System.out::println);
    }

    private static void findDepartmentWithMoreThan3Employees(List<Employee> empList) {
        empList.stream()
                .collect(Collectors.groupingBy(Employee::deptName, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(department -> department.getValue() > 3)
                .toList()
                .forEach(System.out::println);
    }

    private static void findEmpployeesFromHR(List<Employee> empList) {
        Optional<Employee> any = empList.stream().filter(emp -> "HR".equals(emp.deptName))
                .findAny();
        System.out.println(any.get());
    }

    private static void findDepartmentWithHighestEmployees(List<Employee> empList) {
        Map.Entry<String, Long> stringLongEntry = empList.stream()
                .collect(Collectors.groupingBy(Employee::deptName, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .get();
        System.out.println(stringLongEntry);
    }

    private static void findEmployeesWhoseAgeGreaterThan30AndLessThan30(List<Employee> empList) {
        Map<Boolean, List<Employee>> collect = empList.stream()
                .collect(Collectors.partitioningBy(emp -> emp.age > 30));

        System.out.println(collect.get(false));
        System.out.println(collect.get(true));
    }

    private static void findYoungestFemaleEmployee(List<Employee> empList) {
        Optional<Employee> min = empList.stream()
                .filter(emp -> "F".equals(emp.gender))
                .min(Comparator.comparingInt(Employee::age));
        System.out.println(min);
    }

    private static void findOldestEmployee(List<Employee> empList) {
//        Optional<Employee> first = empList.stream()
//                .sorted((e1, e2) -> e2.age - e1.age)
//                .findFirst();

        Optional<Employee> first = empList.stream()
                .max(Comparator.comparingInt(Employee::age));

        System.out.println(first.get());

    }

    private static void countEmployeesInEachDepartment(List<Employee> empList) {
        empList.stream()
                .collect(Collectors.groupingBy(Employee::deptName, Collectors.counting()))
                .forEach((key, val) -> System.out.println(key + " -> " + val));
    }

    private static void findAverageAgeOfMaleAndFemaleEmployees(List<Employee> empList) {
        empList.stream()
                .collect(Collectors.groupingBy(Employee::gender, Collectors.averagingInt(Employee::age)))
                .forEach((key, val) -> System.out.println(key + " -> " + val));
    }

    private static void findMaximumAge(List<Employee> empList) {
        Integer age = empList.stream()
                .mapToInt(Employee::age)
                .max()
                .getAsInt();
        System.out.println(age);
    }

    private static void findEmployeeAgeGreaterThan28(List<Employee> empList) {
        empList.stream()
                .filter(emp -> emp.age > 28)
                .toList()
                .forEach(System.out::println);
    }

    private static void printAllDepartmentName(List<Employee> empList) {
        List<String> departments = empList.stream()
                .map(Employee::deptName).distinct().toList();
        System.out.println(departments);
    }

    private static void countMaleFemaleEmployees(List<Employee> empList) {
        Map<String, Long> employees = empList.stream()
                .collect(Collectors.groupingBy(Employee::gender, Collectors.counting()));
        System.out.println(employees);

        Map<String, List<Employee>> emp = empList.stream()
                .collect(Collectors.groupingBy(Employee::gender));
        System.out.println(emp.get("M"));
        System.out.println(emp.get("F"));
    }

    public static void groupEmployeeByCity (List<Employee> empList) {
        Map<String, List<Employee>> collect = empList.stream()
                .collect(Collectors.groupingBy(Employee::city));
        System.out.println(collect);
    }

    public static void groupEmployeeByAge (List<Employee> empList) {
        Map<Integer, List<Employee>> collect = empList.stream()
                .collect(Collectors.groupingBy(Employee::age));
        System.out.println(collect);
    }

    public record Employee(int id, String name, int age, long salary, String gender, String deptName, String city, int yearOfJoining) {}
}