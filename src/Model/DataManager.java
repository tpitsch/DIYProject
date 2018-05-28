package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

//To-do: Iterate through list of projects and store them into text file.
//To-do: Think about how to store: text file perhaps?
public class DataManager {
	
	/**
	 * This method intakes the entire project manager and stores it in text file.
	 * Each project's attributes are comma separated and at the end, new line is added.
	 * @author Emmett Kang
	 * @param PM project manager that contains the projects.
	 * @throws IOException If it fails to open, well, it will throw stuff at you.
	 */
	public void storeProjects(ProjectManager PM) throws IOException {
		File file = new File(PM.getUser()+".txt");
		FileWriter filewriter = new FileWriter(file);

		ArrayList<Project> ProjectList = PM.getmyProjects();
		//Get the projects from the list, store them by attributes into text file.
		for (int i = 0; i < ProjectList.size(); i++) {
			Project project = ProjectList.get(i);
			//Only writing name, cost, hours and type for now, until I see how materials is implemented
			filewriter.write(project.getName() + "," + project.getCost() + "," 
							+ project.getHours() + "," + project.getType() + "\n");
			
		}
		filewriter.flush();
		filewriter.close();
	}
	
	
	/**
	 * This method intakes the user info to find the file name and reads through the
	 * file content and generate the projects with that information, and add to project manager
	 * and return the entire project manager.  
	 * @param userinfo User + email via getUser() from project manager class
	 * @return ProjectManager object that contains retrieved projects.
	 * @throws IOException If the file cannot be found(I.E there's no project manager for the user), throw stuff. 
	 */
	public ProjectManager retrieveProjects(String userinfo) throws IOException {
		//ProjectManager to be returned after populating with project.
		ProjectManager retrieved = new ProjectManager();
		String projectString = "";
		FileReader fr;
		BufferedReader br;
		
		File file = new File(userinfo+".txt");
		fr = new FileReader(file);
		br = new BufferedReader(fr);
		
		//Attributes of projects.
		String projectname;
		String projecttype;
		int projecthour;
		int projectcost;
		String[] tokens; //Array of project attributes.
		
		//Read file, create project accordingly and add to the project manager.
		while((projectString = br.readLine()) != null) {
			tokens = projectString.split(",");
			projectname = tokens[0];
			projectcost = Integer.parseInt(tokens[1]);
			projecthour = Integer.parseInt(tokens[2]);
			projecttype = tokens[3];
			
			//Create the project with found attribute, add.
			Project project = new Project(projecttype, projectname, projectcost, projecthour);
			retrieved.addProject(project);
		}
		
		
		fr.close();
		br.close();
		return retrieved;
	}
	
	/*	
	public static void storeProjects(String s1, String s2) throws IOException {
			File file = new File(s1 + ".txt");
			FileWriter filewriter = new FileWriter(file);
	
			filewriter.write(s2 + "'s projects");
			filewriter.flush();
			filewriter.close();
			System.out.println("Yo");	
		}*/
	/*
		public static void main(String[] args) throws IOException {
			storeProjects("emmettkang", "Emmett Kang");
			  
		
		}
		*/
		
		

}
