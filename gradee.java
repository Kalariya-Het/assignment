import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private int studentID;
    private String name;

    public Student(int studentID, String name) {
        this.studentID = studentID;
        this.name = name;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Student ID: " + studentID + ", Name: " + name;
    }
}

class Grade {
    private int studentID;
    private int courseID;
    private char grade;

    public Grade(int studentID, int courseID, char grade) {
        this.studentID = studentID;
        this.courseID = courseID;
        this.grade = grade;
    }

    public int getStudentID() {
        return studentID;
    }

    public int getCourseID() {
        return courseID;
    }

    public char getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "Student ID: " + studentID + ", Course ID: " + courseID + ", Grade: " + grade;
    }
}

class GradingSystem {
    private ArrayList<Student> students;
    private ArrayList<Grade> grades;
    private int[] courseCredits;

    public GradingSystem(int numberOfCourses) {
        students = new ArrayList<>();
        grades = new ArrayList<>();
        courseCredits = new int[numberOfCourses];
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void addGrade(Grade grade) {
        grades.add(grade);
    }

    public void addCourseCredits(int courseID, int credits) {
        courseCredits[courseID] = credits;
    }

    public double calculateGPA(int studentID) {
        int totalPoints = 0;
        int totalCredits = 0;

        for (Grade grade : grades) {
            if (grade.getStudentID() == studentID) {
                int courseID = grade.getCourseID();
                int credits = courseCredits[courseID];
                totalPoints += gradeToPoints(grade.getGrade()) * credits;
                totalCredits += credits;
            }
        }

        return totalCredits == 0 ? 0 : (double) totalPoints / totalCredits;
    }

    public void generateGradeReport(int studentID) {
        System.out.println("Grade Report for Student ID: " + studentID);
        for (Grade grade : grades) {
            if (grade.getStudentID() == studentID) {
                System.out.println("Course ID: " + grade.getCourseID() + ", Grade: " + grade.getGrade());
            }
        }
        System.out.printf("GPA: %.2f%n", calculateGPA(studentID));
    }

    private int gradeToPoints(char grade) {
        switch (grade) {
            case 'A': return 4;
            case 'B': return 3;
            case 'C': return 2;
            case 'D': return 1;
            case 'F': return 0;
            default: return 0;
        }
    }
}

public class gradee {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GradingSystem system = new GradingSystem(10);  

        while (true) {
            System.out.println("1. Add Student");
            System.out.println("2. Add Grade");
            System.out.println("3. Add Course Credits");
            System.out.println("4. Calculate GPA");
            System.out.println("5. Generate Grade Report");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter student ID: ");
                    int studentID = scanner.nextInt();
                    scanner.nextLine();  
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    system.addStudent(new Student(studentID, name));
                    break;
                case 2:
                    System.out.print("Enter student ID: ");
                    int sid = scanner.nextInt();
                    System.out.print("Enter course ID: ");
                    int cid = scanner.nextInt();
                    System.out.print("Enter grade (A, B, C, D, F): ");
                    char grade = scanner.next().charAt(0);
                    system.addGrade(new Grade(sid, cid, grade));
                    break;
                case 3:
                    System.out.print("Enter course ID: ");
                    int courseID = scanner.nextInt();
                    System.out.print("Enter credits: ");
                    int credits = scanner.nextInt();
                    system.addCourseCredits(courseID, credits);
                    break;
                case 4:
                    System.out.print("Enter student ID: ");
                    int studentIDForGPA = scanner.nextInt();
                    double gpa = system.calculateGPA(studentIDForGPA);
                    System.out.printf("GPA: %.2f%n", gpa);
                    break;
                case 5:
                    System.out.print("Enter student ID: ");
                    int studentIDForReport = scanner.nextInt();
                    system.generateGradeReport(studentIDForReport);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
