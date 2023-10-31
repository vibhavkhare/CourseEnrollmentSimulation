package FX205;
//Assignment #: Arizona State University Spring 2023 CSE205 #6
//Name: Vibhav Khare
//StudentID: 1224826473
//Lecture: TTh 1:30-2:45 
//Description: Use JavaFX GUI to create an application that simulates the Arizona State Universities course enrollment screen.
//The screen will have a leftPane, the pane where the user will be able to add courses, a rightPane, where the user can see what courses
//they have added and be able to view the total amount of classes enrolled in, and finally a centerPane that contains two buttons that allow
//the user to either add or drop courses. The program also had methods that update the course check boxes. The assignment also 
//requires the use of handler classes such as ButtonHandler that handle inputs from buttons, check boxes, etc.
//Note: when you submit on gradescope, you need to comment out the package line
//package yourPackageName;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
public class Assignment6 extends Application
{
    public static final int WIDTH = 600, HEIGHT = 400;
    public void start(Stage stage)
    {
        StackPane root = new StackPane();
        CoursePane coursePane = new CoursePane();
        root.getChildren().add(coursePane);
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        stage.setTitle("ASU Course Enrollment System");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args)
    {
        launch(args);
    }
}