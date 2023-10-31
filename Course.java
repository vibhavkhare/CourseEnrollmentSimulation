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
public class Course
{
private String subject;
private int courseNum;
private String instructor;
public Course()
{
	subject = "?";
	courseNum = 0;
	instructor = "?";
}
public Course(String subject, int courseNum, String instructor)
{
	this.subject = subject;
	this.courseNum = courseNum;
	this.instructor = instructor;
}
public String getSubject()
{
	return subject;
}
public int getCourseNum()
{
	return courseNum;
}
public String getInstructor()
{
	return instructor;
}
public void setSubject(String subject)
{
	this.subject = subject;
}
public void setCourseNum(int courseNum)
{
	this.courseNum = courseNum;
}
public void setInstructor(String instructor)
{
	this.instructor = instructor;
}
public String toString()
{
	return "\nCourse #:\t\t" + subject + " " + courseNum +
			"\nInstructor:\t"+ instructor + "\n";
}
}
