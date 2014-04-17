package challengeGUI;
// Christopher Kovaleski
// Challenge part 2
// COP 3330
// Due December 6, 2013

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Align extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Align frame = new Align();
					frame.setTitle("Align");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Align() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 360, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JCheckBox chckbxSnapToGrid = new JCheckBox("Snap to Grid");
		chckbxSnapToGrid.setBounds(20, 30, 97, 23);
		contentPane.add(chckbxSnapToGrid);
		
		JCheckBox chckbxShowGrid = new JCheckBox("Show Grid");
		chckbxShowGrid.setBounds(20, 60, 97, 23);
		contentPane.add(chckbxShowGrid);
		
		textField = new JTextField();
		textField.setBounds(150, 31, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblX = new JLabel("X:");
		lblX.setBounds(123, 34, 46, 14);
		contentPane.add(lblX);
		
		textField_1 = new JTextField();
		textField_1.setBounds(150, 61, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblY = new JLabel("Y:");
		lblY.setBounds(123, 64, 46, 14);
		contentPane.add(lblY);
		
		JButton btnNewButton = new JButton("Ok");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(246, 11, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(246, 44, 89, 23);
		contentPane.add(btnCancel);
		
		JButton btnHelp = new JButton("Help");
		btnHelp.setBounds(246, 78, 89, 23);
		contentPane.add(btnHelp);
	}
}
