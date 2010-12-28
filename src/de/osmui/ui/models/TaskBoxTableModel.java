package de.osmui.ui.models;

import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class TaskBoxTableModel extends AbstractTableModel{

	List<String[]> data;
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return (data == null) ? 0 : data.get(0).length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return (data == null) ? 0 : data.size() - 1;
	}

	@Override
	public Object getValueAt(int row, int column) {
		// TODO Auto-generated method stub
		String[] values = data.get(row + 1);
		return (column < values.length) ? values[column] : "";
	}
	
	public void addTasks(String Task) throws Exception {
		data = new Vector<String>(); 
		String line;
		data.add(Task);
//		BufferedReader in = new BufferedReader(new FileReader(fn));
//		while ((line = in.readLine()) != null) {
//		String[] values = line.split(",");
//		data.add(values);
//		}
		fireTableStructureChanged();
		}
}
