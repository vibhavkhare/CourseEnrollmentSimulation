import javafx.scene.control.Label;
import javafx.scene.control.TextField; 
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.util.ArrayList;
import java.util.Collection;
public class CoursePane extends HBox
{
//GUI components
private ArrayList<Course> courseList;
private VBox checkboxContainer;
//Step 1.1: declare any necessary instance variables here
Label enteredCourses;
Label totalCourses;
Label subject;
Label courseNum;
TextField courseNumBox;
TextField teacherBox;
Label teacher;
Button add;
Button drop;
int totalClasses;
CheckBox check;
ArrayList<Course> dropped;
final ComboBox abbrv;

//constructor
public CoursePane()
{
//step 1.2: initialize instance variables
//----
	enteredCourses = new Label("No course entered");
	totalClasses = 0;
	totalCourses = new Label("Total course enrolled: " + totalClasses);
	
	subject = new Label("Subject");
	
	//This code creates the comboBox, dropbox menu, and adds all the different types of classes as abbreviations. The default is set to CSE so when the code is run the subject will always be filled with CSE as the first option
	abbrv = new ComboBox();
	abbrv.getItems().addAll("ACC","AME","BME","CHE","CSE","DAT","EEE");
	abbrv.setValue("CSE");
	
	courseNum = new Label("Course Num");
	courseNumBox = new TextField();
	teacher = new Label("Instructor");
	teacherBox = new TextField();
	
	//These are written to create 2 buttons, add and drop, and are able to call the ButtonHandler() method when pressed
	add = new Button("Add =>");
	add.setOnAction(new ButtonHandler());
	drop = new Button("Drop <=");
	drop.setOnAction(new ButtonHandler());
	
	//This code makes a VBox and a dropped arrayList along with a course list ArrayList to fill in the Vbox, checkBoxContainer later on
	checkboxContainer = new VBox();
	dropped = new ArrayList();
	courseList = new ArrayList();
	totalClasses = courseList.size();
	
	//these code lines create the following headers for the Left and Right panes and sets their color to blue while also making them bigger
	Label labelLeft = new Label("Add Course(s)");
	labelLeft.setTextFill(Color.BLUE);
	labelLeft.setFont(Font.font(null, 14));
	Label labelRight = new Label("Course(s) Enrolled");
	labelRight.setTextFill(Color.BLUE);
	labelRight.setFont(Font.font(null, 14));
	
//set up the layout. Note: CoursePane is a HBox and contains
//leftPane, centerPane and rightPane. Pick proper layout class
//and use nested sub-pane if necessary, then add each GUI components inside.
//step 1.3: create and set up the layout of the leftPane, leftPane contains a top label, the center sub-pane
//and a label show at the bottom
//----
	
	//These lines of code are used to create and set up the layout of the left pane while also adding the GUI variables. This code uses both BorderPane and GridPane in order to make the layout
	BorderPane leftPane = new BorderPane();
	GridPane leftPane2 = new GridPane();
	leftPane.setTop(labelLeft);
	leftPane2.setVgap(10);
	leftPane2.setHgap(10);
	leftPane2.add(subject, 0, 12);
	leftPane2.add(courseNum, 0, 14);
	leftPane2.add(teacher, 0, 16);
	leftPane2.add(abbrv, 1, 12);
	leftPane2.add(courseNumBox, 1, 14);
	leftPane2.add(teacherBox, 1, 16);
	leftPane.setCenter(leftPane2);
	leftPane.setBottom(enteredCourses);
	this.getChildren().addAll(leftPane);
	leftPane.setStyle("-fx-border-color: black");
	
//step 1.4: create and set up the layout of the centerPane which holds the  two buttons
//----
	
	//This code places both buttons in the center pane in the correct spot in the center. This code uses both BorderPane and GridPane in order to make the layout
	BorderPane centerPane = new BorderPane();
	GridPane centerPane2 = new GridPane();
	centerPane2.setVgap(10);
	centerPane2.setHgap(10);
	centerPane2.add(add,2,2);
	centerPane2.add(drop,2,4);
	centerPane2.setAlignment(Pos.CENTER);
	this.getChildren().addAll(centerPane,centerPane2);
	
//step 1.5: create and set up the layout of the rightPane, rightPane  contains a top label,
//checkboxContainer and a label show at the bottom
//----
	
	//This part of the code creates the right pane and formats the GUI variables to be put in their correct spots. This code uses both BorderPane and GridPane in order to make the layout
	BorderPane rightPane = new BorderPane();
	GridPane rightPane2 = new GridPane();
	rightPane.setTop(labelRight);
	rightPane.setBottom(totalCourses);
	rightPane2.add(checkboxContainer, 0, 0);
	rightPane.setCenter(rightPane2);
	this.getChildren().addAll(rightPane);
	rightPane.setStyle("-fx-border-color: black");
	
//CoursePane is a HBox. Add leftPane, centerPane and rightPane inside
	this.setPadding(new Insets(10, 10, 10, 10));
//----
	
//Step 3: Register the GUI component with its handler class
//----
	
} //end of constructor

//step 2.1: Whenever a new Course is added or one or several courses are  dropped/removed, this method will
//1) clear the original checkboxContainer;
//2) create a CheckBox for each Course object inside the courseList, and also  add it inside the checkboxContainer;
//3) register the CheckBox with the CheckBoxHandler.
public void updateCheckBoxContainer()
{
	//This clears the container and updates it with the new courseList
	checkboxContainer.getChildren().clear();
	
	//This for loop goes through the courseList and creates a check box for each value in the list. The "list" is then put in a setUserData for the CheckBox
	for(Course list : courseList)
	{
		check = new CheckBox(list.toString());
		checkboxContainer.getChildren().addAll(check);
		check.setOnAction(new CheckBoxHandler(list));
		check.setUserData(list);
	}
}
//Step 2.2: Create a ButtonHandler class
private class ButtonHandler implements EventHandler<ActionEvent>
{
	public void handle(ActionEvent e)
	{
		//This class handles all button presses in the runtime.
		
		//This code obtains the current values of all values, subject courseNum and instructor, as strings to be used in if statements later on in the handler method
		String subject = (String) abbrv.getValue();
		String courseNum = courseNumBox.getText();
		String teacher = teacherBox.getText();
		try {
			
			//This if statement checks if the user presses the add button along with the all the text fields not being empty
			//This then checks to see if the user inputted a duplicate class by using the same course abbreviation along with course number disregarding the teacher
			//If all checks out it then adds the course to the rightPane and add 1 to the total amount of classes that the user is enrolled in
			if(e.getSource() == add && (!teacher.trim().isEmpty() && !courseNum.trim().isEmpty() && !subject.trim().isEmpty()))	//(/*everything is entered correctly and the "Add =>" button is pressed*/)
			{
				boolean duplicate = false;
				for(Course x : courseList)
				{
					if(x.getSubject().equals(subject) && x.getCourseNum() == Integer.parseInt(courseNum)/* && x.getInstructor().equals(teacher)*/)
					{
						duplicate = true;
					}
					//break;
				}
         //need to check whether the course already exist inside the courseList or not
				if(!duplicate && !teacher.trim().isEmpty() && !courseNum.trim().isEmpty())	//(/*it's a new course*/)
				{
					//if(!teacher.trim().isEmpty() && !courseNum.trim().isEmpty())
					//{
						Course addNewCourse = new Course(subject,Integer.parseInt(courseNum),teacher);
						courseList.add(addNewCourse);
						enteredCourses.setText("Course successfully added");
						enteredCourses.setTextFill(Color.BLACK);
						updateCheckBoxContainer();
						totalClasses++;
						totalCourses.setText("Total course enrolled: " + totalClasses);
						teacherBox.clear();
						courseNumBox.clear();
					//}	
				}
				//Otherwise if the user put in a duplicate, the interface will show an error message saying that there is a duplicate in the code
				else //a duplicated one
				{
					//show error message
					//----
					if(!teacher.trim().isEmpty())
					{
						enteredCourses.setText("Duplicated course - Not added");
						enteredCourses.setTextFill(Color.RED);
					}
					//This else statement prints out another error message if one or more of the text boxes are empty and the user tries to add the course
					else
					{
						enteredCourses.setText("At least one field is empty. Fill all fields");
						enteredCourses.setTextFill(Color.RED);
					}
				}
			}
		//Clear all the text fields and prepare for the next entry;
			
        //This else if statement prints out another error message if one or more of the text boxes are empty and the user tries to add the course
			else if(e.getSource() == add && (teacher.trim().isEmpty() || courseNum.trim().isEmpty()))	//(/*">=" button is pressed, but one of the text field is empty*/)
			{
				//----
				enteredCourses.setText("At least one field is empty. Fill all fields");
				enteredCourses.setTextFill(Color.RED);
				
			}
			//This else if statement checks if the user presses the drop button, if so the code goes through all check boxes and removes the courses that recieved a check from the courseList. This also updates the total number of courses by setting it equal to the new amount/size of the courseList
			else if(e.getSource() == drop)	//(/*when "Drop Course" button is pushed.*/)
			{
				//----
				//ArrayList dropped = new ArrayList();
				for(Node boxes : checkboxContainer.getChildren())
				{
					if(((CheckBox) boxes).isSelected())
					{
						courseList.remove(boxes.getUserData());
					}
				}
				//courseList.remove(checkboxContainer.getUserData());
				totalClasses = courseList.size();
				totalCourses.setText("Total course enrolled: " + totalClasses);
				updateCheckBoxContainer();
				
			}
			//This else statement prints out another error message if one or more of the text boxes are empty and the user tries to add the course
			else  //  for all other invalid actions, thrown an exception and it will be caught
			{
				if(courseNum.trim().isEmpty())
				{
					enteredCourses.setText("At least one field is empty. Fill all fields");
					enteredCourses.setTextFill(Color.RED);
				}
				
			}
		} //end of try
		//This catches if the user inputs something besides a numerical value for the course num text field
		catch(NumberFormatException ex)
		{
			//----
			enteredCourses.setText("Error! Course number must be an integer");
			enteredCourses.setTextFill(Color.RED);
		}
		catch(Exception exception)
		{
			//----
			enteredCourses.setText("Error! Instructor must be an word");
			enteredCourses.setTextFill(Color.RED);
		}
	} //end of handle() method
	} //end of ButtonHandler class
////Step 2.3: A ComboBoxHandler
private class ComboBoxHandler implements EventHandler<ActionEvent>
{
	public void handle(ActionEvent event)
	{
		// TODO Auto-generated method stub
		
	}
	//----
}//end of ComboBoxHandler

//Step 2.4: A CheckBoxHandler
private class CheckBoxHandler implements EventHandler<ActionEvent>
{
	// Pass in a Course object so that we know which course is associated with which CheckBox
	private Course oneCourse;
	//constructor
	public CheckBoxHandler(Course oneCourse)
	{
		//----
	}
	public void handle(ActionEvent e)
	{
		//----
	}
}//end of CheckBoxHandler

} //end of CoursePane class
