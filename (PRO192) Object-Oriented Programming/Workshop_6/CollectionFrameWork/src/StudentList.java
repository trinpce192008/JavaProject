/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author TRI
 */
import java.util.ArrayList;

public class StudentList extends ArrayList<Student>{
	public StudentList() {
		super();
	}
	
	public Student search(String codeString) {
		codeString = codeString.trim().toUpperCase();
		for (int i = 0; i < this.size(); i++) {
			if(this.get(i).getCodeString().equals(codeString))
				return this.get(i);
		}
		return null;
	}
	private boolean isCodeDupplicated(String codeString) {
		codeString = codeString.trim().toUpperCase();
		return search(codeString)!= null;
	}
	public void addStudent() {
		String newCode, newName;
		int newMark;
		boolean codeDuplicated = false;
		do {
			newCode = Inputter.inputPattern("St. code S000: ","[sS][\\d]{3}");
			newCode = newCode.trim().toUpperCase();
			codeDuplicated = isCodeDupplicated(newCode);
		} while (codeDuplicated == true);
		
		newName = Inputter.inputNonBlankStr("Name of new Student : ");
		newName = newName.toUpperCase();
		newMark = Inputter.inputInt("Mark: ",0,10);
		Student student = new Student(newCode,newName,newMark);
		
		this.add(student);
		System.out.println("Student " +newCode + " has been added.");
		
	}
	public void searchStudent() {
		if (this.isEmpty()) {
			System.out.println("Emty list. No search can be performed!");
		}else {
			String scode = Inputter.intputStr("Input student code for search: ");
			Student student = this.search(scode);
			if (student == null) {
				System.out.println("Student "+scode + " doesn't existed!");
			}else {
				System.out.println("Found: " +student);
			}
		}
	}
	
	
	public void updateStudent() {
		if (this.isEmpty()) {
			System.out.println("Emty list. No search can be performed!");
		}else {
			String ucode = Inputter.intputStr("Input code of updated student: ");
			Student student = this.search(ucode);
			if (student == null) {
				System.out.println("Student " + ucode + " doesn't existed!");
			}else {
				String oldName = student.getNameString();
				String msg = "Old name: " +oldName + ", new name: ";
				String newName = Inputter.inputNonBlankStr(msg);
				student.setNameString(newName);
				int oldMark = student.getMark();
				msg = "Old mark: " + oldMark +", new mark 0..10";
				int newMark = Inputter.inputInt(msg, 0, 10);
				student .setMark(newMark);
				System.out.println("Student " +ucode + " has been updated");
			}
		}
	}
	
	
	public void removeStudent() {
		if (this.isEmpty()) {
			System.out.println("Emty list. No search can be performed!");
		}else {
			String rCode = Inputter.intputStr("Input code of removed student: ");
			Student student = this.search(rCode);
			if (student == null) {
				System.out.println("Student " + rCode + " doesn't existed");
			}else {
				this.remove(student);
				System.out.println("Student " + rCode + " has been removed");
			}
		}
	}
	
	public void printAll() {
		if (this.isEmpty()) {
			System.out.println("Empty List!");
		}else {
			System.out.println("Student list:");
			for (Student student  : this ) {
				System.out.println(student);
			}
			System.out.println("Total: " +this.size() + " student(s).");
		}
	}
}
