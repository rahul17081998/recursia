package com.demo.streamExample;

import java.util.*;
import java.util.stream.Collectors;

public class Run {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Charlie", 22, 3.8));
        students.add(new Student("Alice", 20, 3.9));
        students.add(new Student("Aob", 25, 3.5));
        students.add(new Student("Aob", 21, 3.7));
        students.add(new Student("Sonu", 41, 3.7));
        students.add(new Student("Aush", 35, 3.7));
        students.add(new Student("Bimpi", 15, 2.7));

//        students.sort(new Comparator<Student>() {
//            @Override
//            public int compare(Student s1, Student s2) {
//                int result=s1.getName().compareTo(s2.getName());
//                if(result!=0) return result;
//
//                result=Integer.compare(s2.getAge(), s1.getAge());
//                if(result!=0) return result;
//
//                return Double.compare(s2.getGpa(), s1.getGpa());
//            }
//        });

        for (int i=0; i< students.size(); i++){
            System.out.println(students.get(i).getName() +" - "+students.get(i).getAge()+" - "+students.get(i).getGpa());
        }
        System.out.println("--- stream---");

        List<Student> sortedByStream=students.stream()
                .sorted(new Comparator<Student>() {
                    @Override
                    public int compare(Student o1, Student o2) {
//                        int result=o1.getName().compareTo(o2.getName());
                        int result=Integer.compare(o1.getName().length(), o2.getName().length());
                        if(result !=0) return result;

                        result = Integer.compare(o2.getAge(), o1.getAge());
                        if(result!=0) return result;

                        return Double.compare(o2.getGpa(), o1.getGpa());
                    }
                })
                .collect(Collectors.toList());

        for (int i=0; i< sortedByStream.size(); i++){
            System.out.println(sortedByStream.get(i).getName() +" - "+sortedByStream.get(i).getAge()+" - "+sortedByStream.get(i).getGpa());
        }

        List<String> studentName = students.stream()
                .filter(s -> {
                    if (s.getAge() > 35) {
                        return false;
                    } else if (s.getAge() > 15 && s.getGpa() < 3) {
                        return false;
                    } else {
                        return true;
                    }
                })
                .map(s-> {
                    if(s.getName().startsWith("B")){
                        return s.getName() + "--**--" + s.getAge();
                    }else if(s.getAge()>22){
                        return s.getName() + "--##--" + s.getAge();
                    }else{
                        return s.getName() + "---" + s.getAge();
                    }
                })
                .collect(Collectors.toList());
        System.out.println("student Names are "+studentName);


        Map<String, Student> map1 = students.stream()
                .filter(s -> s.getGpa() > 3 && s.getAge() <= 35)
                .collect(Collectors.toMap(Student::getName, s -> s, (a,b)->a));

        System.out.println(map1);



        Map<String, Student> map2 = students.stream()
                .filter(s -> s.getGpa() > 3 && s.getAge() <= 35)
                .collect(Collectors.toMap(Student::getName, s -> s, (a,b)->a, LinkedHashMap::new));

        System.out.println(map2);

        Map<String, List<Student>> map3 = students.stream()
                .filter(s -> s.getGpa() > 3 && s.getAge() <= 35)
                .collect(Collectors.groupingBy(Student::getName));
        System.out.println(map3);

        Optional<String> first = students.stream()
                .filter(s -> s.getAge() < 20)
                .map(s -> s.getName() + "@")
                .findFirst();
        first.ifPresent(System.out::println);

        var output=students.stream()
                .map(s-> {
                    return s.getGpa();
                })
                .collect(Collectors.toList());

        System.out.println(output);

        List<List<Integer>> listoflist = List.of(List.of(1, 2), List.of(3, 4), List.of(5,4,1,5,3));

        List<Integer> allNumbers = listoflist.stream()
                .flatMap(list -> list.stream())
                .distinct()
                .collect(Collectors.toList());
        System.out.println(allNumbers);



        List<CollegeStudent> collegeStudents = new ArrayList<>();
        collegeStudents.add(new CollegeStudent("Charlie", 22, 3.8, List.of("Math", "Physics")));
        collegeStudents.add(new CollegeStudent("Alice", 20, 3.9, List.of("Math", "Chemistry")));
        collegeStudents.add(new CollegeStudent("Bob", 25, 3.5, List.of("Biology", "Physics")));


        List<String> allSubject = collegeStudents.stream()
                .flatMap(s -> s.getSubjects().stream())
                .distinct()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        System.out.println(allSubject);



        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Bob", 30, 50000, "IT", "M"));
        employees.add(new Employee("Alice", 28, 60000, "HR", "F"));
        employees.add(new Employee("Charlie", 35, 50000, "IT", "M"));
        employees.add(new Employee("Supa", 35, 100000, "IT", "F"));
        employees.add(new Employee("Sonu", 22, 3000, "EC", "M"));
        employees.add(new Employee("Billi", 17, 6000, "CSE", "M"));
        employees.add(new Employee("Soniya", 17, 4323, "CSE", "F"));

        List<Employee> employees1 = employees.stream()
                .sorted(new Comparator<Employee>() {
                    @Override
                    public int compare(Employee e1, Employee e2) {
                        int result = Double.compare(e2.getSalary(), e1.getSalary());
                        if (result != 0) return result;
                        return e1.getName().compareTo(e2.getName());
                    }
                })
                .collect(Collectors.toList());
        System.out.println(employees1);

        Map<String, List<String>> collect = employees.stream()
                .filter(e-> {
                    if(e.getDepartment().startsWith("IT")) {
                        return false;

                    }else if(e.getSalary() == 60000){
                        return true;
                    }else{
                        return true;
                    }
                })
                .collect(Collectors.groupingBy(
                        e -> e.getDepartment(),
                        Collectors.mapping(Employee::getName, Collectors.toList())

                ));


        System.out.println(collect);

        Long count=employees.stream()
                .filter(e->!e.getDepartment().startsWith("IT"))
                .map(employee -> employee.getName())
                .collect(Collectors.counting());

        System.out.println(count);

      Optional<Double> maxSalary= employees.stream()
                .map(e -> e.getSalary())
                .reduce(Double::max);

        System.out.println(maxSalary);

        Optional<Double> min = employees.stream()
                .map(Employee::getSalary)
                .min(Comparator.naturalOrder());
        System.out.println(min.get());

        Optional<Employee> richEmployee = employees.stream()
                .max(new Comparator<Employee>() {
                    @Override
                    public int compare(Employee o1, Employee o2) {
                        return Double.compare(o1.getSalary(), o2.getSalary());
                    }
                });

        System.out.println(richEmployee);
        String na = employees.stream()
                .max(new Comparator<Employee>() {
                    @Override
                    public int compare(Employee o1, Employee o2) {
                        return Double.compare(o1.getSalary(), o2.getSalary());
                    }
                })
                .map(e -> e.getName())
                .orElse("NA");

        System.out.println(na);

        List<Integer> nums = Arrays.asList(5, 1, 8, 3, 2, 9, 4,2,5,1,8,1,5);


        // find the smallest number
        Optional<Integer> minNum = nums.stream()
                .sorted(new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return Integer.compare(o1, o2);
                    }
                })
                .findFirst();

        System.out.println(minNum.get());
        System.out.println("-----");

        // second max number
        Optional<Integer> seclarge = nums.stream()
                .sorted(new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return Integer.compare(o2, o1);
                    }
                })
//                .peek(System.out::println)
                .skip(1)
                .findFirst();
        System.out.println(seclarge.get());

        // duplicate

        Set<Integer> set = new HashSet<>();
        List<Integer> duplicate=nums.stream()
                .filter(n-> {
                    if(set.contains(n)) return true;
                    else {
                        set.add(n);
                        return false;
                    }
                })
                .distinct()
                .collect(Collectors.toList());

        System.out.println("duplicate: + "+duplicate);

        List<String> itEmployee=employees.stream()
                .filter(e->"IT".equals(e.getDepartment()))
                .map(e->e.getName())
                .collect(Collectors.toList());
        System.out.println(itEmployee);


        Optional<Employee> RichEmployee = employees.stream()
                .max(new Comparator<Employee>() {
                    @Override
                    public int compare(Employee o1, Employee o2) {
                        return Double.compare(o1.getSalary(), o2.getSalary());
                    }
                });
        System.out.println(richEmployee.get());

        Map<String, Long> employeeByGender = employees.stream()
                .collect(Collectors.groupingBy(
                        e -> e.getGender(),
                        Collectors.counting()
                ));
        System.out.println(employeeByGender);

        // salary by deperatmant

        Map<String, Double> salaryByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.summingDouble(Employee::getSalary)
                ));

        System.out.println(salaryByDept);

        Map<String, List<Employee>> empByGender = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getGender
                ));

        System.out.println(empByGender);


        List<Employee> allEmp = empByGender.values().stream()
                .flatMap(e -> e.stream())
                .collect(Collectors.toList());
        System.out.println(allEmp);


        List<Integer> number = Arrays.asList(5, 1, 8, 3, 2, 9, 4,2,5,1,8,1,5);
        number.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        });
        System.out.println(number);

        employees.sort(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return Double.compare(o2.getSalary(),o1.getSalary()); // descending order
            }
        });

        System.out.println(employees);

        PriorityQueue<Employee> employeePriorityQueue = new PriorityQueue<>(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                int result=Double.compare(o1.getSalary(),o2.getSalary());
                if(result!=0) return result;
                result=Integer.compare(o2.getAge(), o1.getAge());
                if(result!=0) return result;
                return o1.getName().compareTo(o2.getName());
            }
        });

        employeePriorityQueue.add(new Employee("Ram", 30, 50000, "IT", "Male"));
        employeePriorityQueue.add(new Employee("Sita", 28, 45000, "HR", "Female"));
        employeePriorityQueue.add(new Employee("Tom", 35, 50000, "IT", "Male"));
        employeePriorityQueue.add(new Employee("Amy", 25, 45000, "Finance", "Female"));
        employeePriorityQueue.add(new Employee("Zoe", 40, 50000, "IT", "Female"));


        while(!employeePriorityQueue.isEmpty()){
            System.out.println(employeePriorityQueue.poll());
        }


    }
}
