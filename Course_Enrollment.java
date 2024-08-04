import java.util.ArrayList;
import java.util.Scanner;

class Course {
    private int courseID;
    private String courseName;
    private int credits;

    public Course(int courseID, String courseName, int credits) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.credits = credits;
    }

    public int getCourseID() {
        return courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCredits() {
        return credits;
    }

    @Override
    public String toString() {
        return "Course ID: " + courseID + ", Course Name: " + courseName + ", Credits: " + credits;
    }
}

class Enrollment {
    private ArrayList<Integer> studentIDs;
    private ArrayList<Integer> courseIDs;

    public Enrollment() {
        studentIDs = new ArrayList<>();
        courseIDs = new ArrayList<>();
    }

    public void enroll(int studentID, int courseID) {
        studentIDs.add(studentID);
        courseIDs.add(courseID);
        System.out.println("Student " + studentID + " enrolled in course " + courseID);
    }

    public void drop(int studentID, int courseID) {
        for (int i = 0; i < studentIDs.size(); i++) {
            if (studentIDs.get(i) == studentID && courseIDs.get(i) == courseID) {
                studentIDs.remove(i);
                courseIDs.remove(i);
                System.out.println("Student " + studentID + " dropped course " + courseID);
                return;
            }
        }
        System.out.println("Enrollment not found.");
    }

    public void getEnrolledCourses(int studentID, ArrayList<Course> courseCatalog) {
        System.out.println("Courses enrolled by student " + studentID + ":");
        for (int i = 0; i < studentIDs.size(); i++) {
            if (studentIDs.get(i) == studentID) {
                int courseID = courseIDs.get(i);
                for (Course course : courseCatalog) {
                    if (course.getCourseID() == courseID) {
                        System.out.println(course);
                    }
                }
            }
        }
    }
}

public class Course_Enrollment {
    public static void main(String[] args) {
        ArrayList<Course> courseCatalog = new ArrayList<>();
        Enrollment enrollment = new Enrollment();
        Scanner scanner = new Scanner(System.in);

        courseCatalog.add(new Course(101, "Introduction to Programming", 3));
        courseCatalog.add(new Course(102, "Data Structures", 4));
        courseCatalog.add(new Course(103, "Database Systems", 3));
        courseCatalog.add(new Course(104, "Operating Systems", 4));

        while (true) {
            System.out.println("1. Enroll in a course");
            System.out.println("2. Drop a course");
            System.out.println("3. View enrolled courses");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter student ID: ");
                    int studentID = scanner.nextInt();
                    System.out.print("Enter course ID: ");
                    int courseID = scanner.nextInt();
                    enrollment.enroll(studentID, courseID);
                    break;
                case 2:
                    System.out.print("Enter student ID: ");
                    int studentIDDrop = scanner.nextInt();
                    System.out.print("Enter course ID: ");
                    int courseIDDrop = scanner.nextInt();
                    enrollment.drop(studentIDDrop, courseIDDrop);
                    break;
                case 3:
                    System.out.print("Enter student ID: ");
                    int studentIDView = scanner.nextInt();
                    enrollment.getEnrolledCourses(studentIDView, courseCatalog);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
