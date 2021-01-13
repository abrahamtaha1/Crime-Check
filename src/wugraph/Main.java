package wugraph;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Main 
{
	
	public static void main(String[] args) throws Exception 
	{
		
        //Creating the Frame
        JFrame frame = new JFrame("CrimeCheck");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(550, 500);
        
        //Initializing Drop-down lists 
        JComboBox attractionsList1 = new JComboBox();
        JComboBox attractionsList2 = new JComboBox();
        
        //Creating the panel at bottom and adding components
        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.white);
        JPanel panel2 = new JPanel(); 
        panel2.setBackground(Color.white);
        JTextArea outputArea = new JTextArea();
  
        JLabel CrimeCheckLabel = new JLabel();
        CrimeCheckLabel.setIcon(new ImageIcon("Logo.PNG"));
        JLabel Location1label = new JLabel("Starting Location");
        JLabel Location2label = new JLabel("Destination Location");
        JButton enter = new JButton("Find Path");
        
		// Read data set files
		Place areas [] = Connected.getAreas();
		
		// Sort areas by place names
		PlaceSort.Sort(areas);
		
		// Print sorted areas in drop-down lists		
		for (int i = 0; i < areas.length; i++) {
			attractionsList1.addItem(areas[i].getPlace());
			attractionsList2.addItem(areas[i].getPlace());
		}
		
		// Create undirected graph
		Graph graph = Connected.AddConnEdge();
		
		//Obtaining start and end locations
        enter.addActionListener(new ActionListener(){  
        	public void actionPerformed(ActionEvent e){  
        		String start = (String) attractionsList1.getItemAt(attractionsList1.getSelectedIndex());
        		String end = (String) attractionsList2.getItemAt(attractionsList2.getSelectedIndex());
        		
        		// Binary search for start and end places in areas array
        		int Beg = BinarySearch.BinarySearch(areas, start);
        		int End = BinarySearch.BinarySearch(areas, end);
                
        		// Call Dijkstra to find path from start to end place
        		ArrayList<Place> path = graph.dijPath(areas[Beg], areas[End]);
        		
        		outputArea.setText("");
                outputArea.append("Safest Path:\n");
        		
                // Print safest path
        		if (path == null) {
        			outputArea.append("No path found!");
        		} else {
        			for (Place p : path)
        				outputArea.append(p.getPlace() + "\n");
        		}
        		
        	}  
        }); 
        
        //Adding Components to the frame.
		panel1.add(CrimeCheckLabel);
        panel2.add(Location1label); 
        panel2.add(attractionsList1);         
        panel2.add(Location2label); 
        panel2.add(attractionsList2);   
        panel2.add(enter);
        
        //Adding Other Components 
        outputArea.setEditable(false);
		frame.getContentPane().add(panel1, BorderLayout.NORTH);
		frame.getContentPane().add(panel2, BorderLayout.CENTER);
		frame.getContentPane().add(outputArea, BorderLayout.SOUTH);
		frame.setResizable(false);
        frame.setVisible(true);   	
	}  
}
