package controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Grading {
	
	/**
	 * Returns a HashMap of the students grades per assignment number, given
	 * the student's id.
	 * @param id Student number
	 * @return HashMap<Integer, String>
	 */
        public static HashMap<Integer, String> getStudentGrades(String id){
        	HashMap<Integer, String> assignNumToGrade = new HashMap<>();
                String submissionFileName = "";
                int i = 1;
                
                // Get all assignment submission files
                while((new File(submissionFileName = ("Assignment" + Integer.toString(i) + "Submission.csv")).exists())){
                        try {
                                FileReader fr = new FileReader(submissionFileName);
                                BufferedReader br = new BufferedReader(fr);
                                // Skip first cell
                                String line = br.readLine();
                                // Read all studentIDs
                                while ((line = br.readLine()) != null) {
                                        // Get line
                                        String[] assignmentLine = line.split(",");
                                        // Check if studentID is present
                                        if (assignmentLine[0].equals(id)){
                                                // Last index == length - 1
                                        		assignNumToGrade.put(i, assignmentLine[assignmentLine.length - 1] + "%");
                                                break;
                                        }
                                }
                                br.close();
                                fr.close();
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                        i++;
                }
                return assignNumToGrade;
        }
}
