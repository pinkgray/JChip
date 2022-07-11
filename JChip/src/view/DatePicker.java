package view;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JPanel;
import org.jdesktop.swingx.JXDatePicker;
//import javafx.scene.control.*;

public class DatePicker {

	private JXDatePicker datePicker;
	
	public DatePicker() {
		
		datePicker = new JXDatePicker();
		datePicker.setDate(Calendar.getInstance().getTime());
		datePicker.setFormats(new SimpleDateFormat("yyyy-MM-dd"));
		
	}
	
	public JXDatePicker getDatePicker() {
		return datePicker;
	}
	
	
}
