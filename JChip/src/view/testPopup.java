package view;

import java.awt.Dialog;
import java.awt.TextArea;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import model.vo.MOM;

public class testPopup extends JPanel {

	private Dialog d;
	String MOMTitle;
	Date MOMDay;

	public testPopup() {

		JFrame mf = new JFrame("tset");
		mf.setBounds(150, 150, 515, 620);
		JTextArea ta = new JTextArea(10, 30);

		try (ObjectInputStream objIn = new ObjectInputStream(new FileInputStream("MOMList/MOM_회의명.txt"))) {
			 System.out.println(objIn.readObject());
			MOM str = (MOM) (objIn.readObject());
			// ta.append(str); @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 수정해야함 

		} catch (Exception e) {
			e.printStackTrace();
		}
		mf.add(ta, "Center");

		mf.setVisible(true);

	}
	// JFrame mf = new JFrame("tset");
	// mf.setBounds(150, 150, 515, 620);
	//
	// JTextArea ta = new JTextArea(10, 30);
	//
	// try (BufferedReader br = new BufferedReader(new FileReader("test.txt"))) {
	//
	// String temp;
	// while ((temp = br.readLine()) != null) {
	// ta.append(temp + "\n");
	// }
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	//
	// mf.add(ta, "Center");
	//
	// mf.setVisible(true);
	// }
}