import javax.swing.*;  //this whole program is done using java.swing

public class labCopy
{
    public static void main(String args[]) {
        int option; //to go through the menu
        boolean didS = false;  //were students entered?
        boolean didG = false;  //were grades entered?
        String[] students = new String[5];  //to store students
        int[] grades = new int[5];  // to store grades
        String graNot = "Not yet entered";  //says grades were not entered yet
        String[] comment = new String[5];  //commenting on each student

        for (int i=0; i < comment.length; i++) {
            comment[i] = "Not yet entered";  //every comment becomes "Not yet entered"
            grades[i] = 0;
        }

        JOptionPane.showMessageDialog(null, "Welcome to the Student Manadgement System!"); //greeting the user

        do { //runs menu
            option = Integer.parseInt(JOptionPane.showInputDialog(null,  //menu telling options
                    "   Student Managedment menu:   \n" + 
                    "1. Add students\n" + "2. Add student grades\n" + 
                    "3. View all students\n" + "4. Class grades info\n" + 
                    "5. Edit student information\n" + 
                    "6. Delete student\n" + "7. Exit"));

            switch (option) {
                case 1:
                    if (didS) { //runs only on the first time
                        students(students);  //only stops after all slots are filled
                        didS = true;  //didS is updated to let user use Menu's other options
                    } else {
                        JOptionPane.showMessageDialog(null, "Student System is unfortunately full.\n" + "Please delete or edit a student.", 
                            "Student System full", JOptionPane.ERROR_MESSAGE); //tells all slots are full
                    }
                    break;
                case 2:
                    if (didS) {  //doesn't run unless students are entered
                        if (!didG) {  //runs only on the first run
                            grades(students, grades);
                            didG = true;  //updates didG to true, allowing for a few options
                        } else {  //message after first run is done
                            JOptionPane.showMessageDialog(null, "Student grades were entered.\n" + 
                                "If you'd like to edit something please go to option 5.", 
                                "Student Management System", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {  //message in case students were not entered
                        JOptionPane.showMessageDialog(null, "Students not yet entered.\n" + "Please select option '1' and add students first.", 
                            "Student Management System", JOptionPane.ERROR_MESSAGE);  //tells user students were not entered and to go back and enter students
                    }
                    break;
                case 3:
                    if (didS) {  //doesn't run unless students are entered
                        if (didG) { //checks if grades were enetred
                            viewAll(students, grades, comment);  //shows student details with grades
                        } else {
                            viewAllf(students, graNot, comment);  //shows student details without grades
                        }
                    } else {  //message in case students were not entered
                        JOptionPane.showMessageDialog(null, "Students not yet entered.\n" + "Please select option '1' and add students first.", 
                            "Student Management System", JOptionPane.ERROR_MESSAGE);  //tells user students were not entered and to go back and enter students
                    }
                    break;
                case 4:
                    if (didG) {  //runs only if grades were entered
                        gradesInfo(grades);
                    } else {  //message in case grades were not entered
                        JOptionPane.showMessageDialog(null, "Student Grades not yet entered.\n" + "Please select option '2' and add student grades first.", 
                            "Student Management System", JOptionPane.ERROR_MESSAGE);  //tells user grades were not entered and to go back and enter grades
                    }
                    break;
                case 5:
                    if (didS) {  //doesn't run unless students are entered
                        if (didG) {  //runs only if grades were entered
                            edit(students, grades, comment);
                        } else {
                            editf(students, grades, graNot, comment);
                        }
                    } else {  //message in case students were not entered
                        JOptionPane.showMessageDialog(null, "Students not yet entered.\n" + "Please select option '1' and add students first.", 
                            "Student Management System", JOptionPane.ERROR_MESSAGE);  //tells user students were not entered and to go back and enter students
                    }
                    break;
                case 6:
                    if (didS) {  //doesn't run unless students are entered
                        delete(students, grades, comment);
                    } else {  //message in case students were not entered
                        JOptionPane.showMessageDialog(null, "Students not yet entered.\n" + "Please select option '1' and add students first.", 
                            "Student Management System", JOptionPane.ERROR_MESSAGE);  //tells user students were not entered and to go back and enter students
                    }
                    break;
                case 7:
                    JOptionPane.showMessageDialog(null, "Exiting System. Goodbye!");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option.\n" + "Please try again", "Invalid", JOptionPane.ERROR_MESSAGE);
            }
        } while (option != 7);
    }

    public static void students(String[] students) {  //method to enter students
        for (int i=0; i < students.length; i++) {
            students[i] = JOptionPane.showInputDialog(null, "Please add Student" + (i+1) + " name:");  //asks for student name.

            while (students[i].length() <= 2) {  //checks if name is at least 3 characters long, without this the user could go through the method just clicking enter
                students[i] = JOptionPane.showInputDialog(null, "Name must be at least 3 characters long\n" + 
                    "Please add Student" + (i+1) + " name:");
            }

            students[i] = students[i].toUpperCase();  //makes the full name capitalized

            JOptionPane.showMessageDialog(null, "Student" + (i+1) + " entered: " + students[i]);  //shows the name entered
        }
    }

    public static void grades(String[] student, int[] grades) {  //method to enter grades
        for (int i=0; i < grades.length; i++) {
            grades[i] = Integer.parseInt(JOptionPane.showInputDialog(null, "Please add " + student[i] + "'s grade (0-100):"));

            while (grades[i] < 0 || grades[i] > 100) {  //makes sure the marks stay within 0-100
                grades[i] = Integer.parseInt(JOptionPane.showInputDialog(null, "      Please, enter a valid grade (0-100)      \n" + 
                        "Please add " + student[i] + "'s grade (0-100):"));
            } 

            JOptionPane.showMessageDialog(null, student[i] + "'s grade entered: " + grades[i]);  //shows grade entered
        }
    }

    public static void viewAll(String[] students, int[] grade, String[] comment) {  //method to view students info entered; this runs if grades were entered
        for (int i=0; i < students.length; i++) {
            int a;
            do {  //shows students info and runs until a valid option is entered
                a = JOptionPane.showConfirmDialog(null, students[i] + "\nGrade: " + grade[i] + 
                    "\nComment: " + comment[i] + "\n" + "\nWould you like to enter a comment?");

                if (a == 0) {  //runs if user clicks 'yes'
                    comment[i] = JOptionPane.showInputDialog(null, "Add comment:");  //adds comment
                    comment[i] = comment[i].toUpperCase();  //makes comment uppercase
                    a=1;  //moves to next student
                } else if (a ==2) {  //doesn't allow 'cancel'
                    JOptionPane.showMessageDialog(null, "   ERROR WHEN CANCELLING\n   " + //message in case user selects 'cancel'
                        "   PLEASE ANSWER 'YES' OR 'NO'   ", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            } while (a != 1);  //'no' - goes to next student 
        }
    }

    public static void viewAllf(String[] students, String graNot, String[] comment) {  //method to view students info entered; this runs if grades were NOT entered
        for (int i=0; i < students.length; i++) {
            int a;
            do {
                a = JOptionPane.showConfirmDialog(null, students[i] + "\nGrade: " + graNot + //shows  students info and runs until a valid option is entered
                    "\nComment: " + comment[i] + "\n" + "\nWould you like to enter a comment?");

                if (a == 0) {  //runs if user selects 'yes'
                    comment[i] = JOptionPane.showInputDialog(null, "Add comment:");  //adds comment
                    comment[i] = comment[i].toUpperCase();  //makes it uppercase
                    a=1;  //moves on to next student
                } else if (a == 2) {  //doesn't allow 'cancel'
                    JOptionPane.showMessageDialog(null, "ERROR WHEN CANCELLING\n" + //message in case user selects 'cancel'
                        "PLEASE ANSWER 'YES' OR 'NO'", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            } while (a != 1);  //'No' -  goes to next student
        }
    }

    public static void gradesInfo(int[] grade) {  //general grades info
        int total = 0;
        int a = 0;
        int b = 100;
        for (int i=0; i < grade.length; i++) {  //checks general info
            total += grade[i];  //adds all grades
            if (a < grade[i]) {  //checks which is the highest grade
                a = grade[i];
            }
            if (b > grade[i]) {  //checks which is the lowest grade
                b = grade[i];
            }
        }

        double average = (double) total/grade.length;  //calculates average

        JOptionPane.showMessageDialog(null, "        Class Grades        \n" + "-------------------------\n" + //shows information to screen
            "Student1: " + grade[0] + "\nStudent2: " + grade[1] + "\nStudent3: " + grade[2] + "\nStudent4: " + grade[3] + 
            "\nStudent5: " + grade[4] + "\n-------------------------\n" + "Highest grade: " + a + "\nLowest grade: " + b + 
            "\nAverage grade: " + average, "Student Management System", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void edit(String[] student, int[] grade, String[] comment) {  //method to edit every/any information
        for (int i=0; i < student.length; i++) {
            int a = 0;
            int opt;
            do {  //goes throgh every student
                a = JOptionPane.showConfirmDialog(null, "     Student" + (i+1) + " info     \n" + "Student name: " + student[i] + //shows student info
                    "\nGrade: " + grade[i] + "\nComment: " + comment[i] + "\n" + "\n" + "\nWould you like to edit something?   ", "Edit", JOptionPane.INFORMATION_MESSAGE);

                if (a == 0) {
                    opt = Integer.parseInt(JOptionPane.showInputDialog(null, "What would you like to edit?\n" + //edit menu
                            "1. Edit Student name\n" + "2. Edit Student grade\n" + "3. Edit Comment\n" + "0. Cancel"));

                    switch (opt) {
                        case 1:
                            student[i] = JOptionPane.showInputDialog(null, "Edit name:", student[i]);
                            while (student[i].length() <= 2) {  //checks if name is at least 3 characters long, without this the user could go through the method just clicking enter
                                student[i] = JOptionPane.showInputDialog(null, "Name must be at least 3 characters long\n" + 
                                    "Edit name:");
                            }
                            student[i] = student[i].toUpperCase();
                            break;
                        case 2:
                            grade[i] = Integer.parseInt(JOptionPane.showInputDialog(null, "Edit grade:", grade[i]));
                            while (grade[i] < 0 || grade[i] > 100) {  //makes sure the marks stay within 0-100
                                grade[i] = Integer.parseInt(JOptionPane.showInputDialog(null, "Please, enter a valid grade (0-100)\n" + 
                                        "Edit grade:"));
                            }
                            break;
                        case 3:
                            comment[i] = JOptionPane.showInputDialog(null, "Edit comment:", comment[i]);
                            comment[i] = comment[i].toUpperCase();
                            break;
                        case 0:
                            a = 1;
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Invalid option.\n" + "Please try again", "Invalid", JOptionPane.ERROR_MESSAGE);
                    }
                } else if (a == 2) {  //doesn't allow 'cancel'
                    JOptionPane.showMessageDialog(null, "ERROR WHEN CANCELLING\n" + //message in case user selects 'cancel'
                        "PLEASE ANSWER 'YES' OR 'NO'", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            } while (a != 1);  //'No' -  moves to next student
        }
    }

    public static void editf(String[] student, int[] grade, String graNot, String[] comment) {  //same as edit method but only runs if grades were NOT entered
        for (int i=0; i < student.length; i++) {
            int a = 0;
            int opt;
            do {  //goes through every student
                a = JOptionPane.showConfirmDialog(null, "     Student" + (i+1) + " info     \n" + "Student name: " + student[i] + //shows students info
                    "\nGrade: " + graNot + "\nComment: " + comment[i] + "\n   Would you like to edit something?   ", "Edit", 
                    JOptionPane.INFORMATION_MESSAGE);

                if (a == 0) {  //if user selects 'yes'
                    opt = Integer.parseInt(JOptionPane.showInputDialog(null, "What would you like to edit?\n" + //edit menu
                            "1. Edit Student name\n" + "2. Edit Student grade\n" + "3. Edit Comment\n" + "0. Cancel"));

                    switch (opt) {
                        case 1:
                            student[i] = JOptionPane.showInputDialog(null, "Edit name:", student[i]);
                            while (student[i].length() <= 2) {  //checks if name is at least 3 characters long, without this the user could go through the method just clicking enter
                                student[i] = JOptionPane.showInputDialog(null, "Name must be at least 3 characters long\n" + 
                                    "Edit name:");
                            }
                            student[i] = student[i].toUpperCase();
                            break;
                        case 2:
                            grade[i] = Integer.parseInt(JOptionPane.showInputDialog(null, "Edit grade:"));
                            while (grade[i] < 0 || grade[i] > 100) {  //makes sure the marks stay within 0-100
                                grade[i] = Integer.parseInt(JOptionPane.showInputDialog(null, "Please, enter a valid grade (0-100)\n" + 
                                        "Edit grade:"));
                            }
                            break;
                        case 3:
                            comment[i] = JOptionPane.showInputDialog(null, "Edit comment:", comment[i]);
                            comment[i] = comment[i].toUpperCase();
                            break;
                        case 0:
                            a = 1;
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Invalid option.\n" + "Please try again", "Invalid", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } while (a != 1);  //'No' -  goes the next student
        }
    }

    public static void delete(String[] student, int[] grade, String[] comment) {  //method to delete a student
        int opt;
        do {
            opt = Integer.parseInt(JOptionPane.showInputDialog(null, "         Class Student Info         \n" + //shows every students' info
                    "------------------------------------\n" + "Student1:\n" + "Name: " + student[0] + "  Grade: " + 
                    grade[0] + "\nComment: " + comment[0] + "\n" + "\nStudent2: " + "\nName: " + student[1] + "  Grade: " + 
                    grade[1] + "\nComment: " + comment[1] + "\n" + "\nStudent3: " + "\nName: " + student[2] + "  Grade: " + 
                    grade[2] + "\nComment: " + comment[2] + "\n" + "\nStudent4: " + "\nName: " + student[3] + "  Grade: " + 
                    grade[3] + "\nComment: " + comment[3] + "\n" + "\nStudent5: " + "\nName: " + student[4] + "  Grade: " + 
                    grade[4] + "\nComment: " + comment[4] + "\n" + "\n7. Exit" + "\n" + "\n" + 
                    "\nWhich student would you like to delete from the system? (choose by Student No.)"));

            if (opt > 0 && opt < 6) {  //if they insert a number that symbolises a student
                /*IMPORTANT! I didn't know why this code wasn't working so I asked
                 * Copilot, and with its insight I changed: for (int i = opt-1; i < student.length; i++)
                 * for: for (int i = opt-1; i < (student.length-1); i++)
                 */
                
                for (int i = (opt-1); i < (student.length-1); i++) {
                    student[i] = student[i+1]; //moves the name above to the spot of the deleted one

                    grade[i] = grade[i+1]; //moves the grade above to the spot of the deleted one

                    comment[i] = comment[i+1]; //moves the comment above to the spot of the deleted one
                }
                
                student[student.length-1] = ""; //makes the last item empty
                grade[grade.length-1] = 0; //makes the last grade 0
                comment[comment.length-1] = "Not yet entered"; //makes the last item "Not yet entered"
            } else if (opt == 7) {  //Exits
                opt = 7;  //redundant, just to not run 'else'
            } else { //for invalid options
                JOptionPane.showMessageDialog(null, "Invalid option.\n" + "Please try again", "Invalid", JOptionPane.ERROR_MESSAGE);
            } 
        } while (opt != 7); //exits to main menu
    }
}