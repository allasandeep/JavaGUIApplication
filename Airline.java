/*******************************************************************************************************
  *                                                                                                  *
  *  CSCI 470/502          Assignment 8           Fall 2018                                          *
  *                                                                                                  *
  * Programmer : Bodipudi Pragna (Z1851660) ,Gayathri Sanikommu (Z1822939), Sandeep Alla (Z1821331)  *
  *                                                                                                  *
  * Due Date : 11/28/2018 11:59PM                                                                    *
  *                                                                                                  *
  * Purpose : This program contains a "Airline" class which has a                                    *
  * panels,frames,button,jscroll and events       					                                 *
  *                                                                                                  *
  *******************************************************************************************************/

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.io.File;
import java.awt.event.*;
public class Airline {
    private static void createAndShowGUI(){
        JPanel final1;
        JPanel xpanel;
        JPanel ypanel;
        JPanel cpanel;
        JPanel c1panel;
        JPanel c1pane2;
        JPanel c1pane3;
        JPanel c1pane4;
        JPanel c1pane5;
        JTextField x1field;
        JTextField x2field;
        JTextField x3field;
        JTextField x4field;

        MileRedeemer mil;
        try {
            mil = new MileRedeemer();
            JFileChooser fileChooser  = new JFileChooser();
    		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
    		int returnValue = fileChooser.showOpenDialog(null);
    		if (returnValue == JFileChooser.APPROVE_OPTION) {
    		      File selectedFile = fileChooser.getSelectedFile();
    		      Scanner sc = new Scanner(selectedFile);
                  mil.readDestinations(sc);
                  }

            final JPanel upanel;
            final JPanel bpanel;
            final JButton redButton;

            //left panel label and text fields
            JFrame mainFrame = new JFrame("Mile Redemption App");
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setLayout(new BorderLayout()); //creating the mainframe with BorderLayout
           
            cpanel = new JPanel(new GridLayout(4, 2)); //creating cpanel with gridLayout

            JLabel label1 = new JLabel("Normal Miles", SwingConstants.LEFT);
            cpanel.add(label1);
            x1field = new JTextField(5);                                    //Jtext filed
            x1field.setFont(new Font("century", Font.PLAIN, 14));
            x1field.setForeground(Color.BLACK);
            x1field.setEditable(false);
            cpanel.add(x1field);

            JLabel label2 = new JLabel("Supersaver Miles", SwingConstants.LEFT);
            cpanel.add(label2);
            x2field = new JTextField(5);                                    //Jtext filed
            x2field.setFont(new Font("century", Font.PLAIN, 14));
            x2field.setForeground(Color.BLACK);
            x2field.setEditable(false);
            cpanel.add(x2field);

            JLabel label3 = new JLabel("Upgrade Cost", SwingConstants.LEFT);
            cpanel.add(label3);
            x3field = new JTextField(5);                                    //Jtext filed
            x3field.setFont(new Font("century", Font.PLAIN, 14));
            x3field.setForeground(Color.BLACK);
            x3field.setEditable(false);
            cpanel.add(x3field);

            JLabel label4 = new JLabel("SuperSaver Dates", SwingConstants.LEFT);
            cpanel.add(label4);
            x4field = new JTextField(5);                                    //Jtext filed
            x4field.setFont(new Font("century", Font.PLAIN, 14));
            x4field.setForeground(Color.BLACK);
            x4field.setEditable(false);
            cpanel.add(x4field);

            upanel = new JPanel(new BorderLayout());
            upanel.setPreferredSize(new Dimension(350, 300));
            upanel.setBorder(BorderFactory.createTitledBorder("Destination"));

            DefaultListModel<String> l1 = new DefaultListModel<>();
            for (int i = 0; i < mil.getCityNames().length; i++) {
                l1.addElement(mil.getCityNames()[i]);
            }
            JList<String> list = new JList<>(l1);
            JScrollPane scrollPane = new JScrollPane(list);
            upanel.add(scrollPane, BorderLayout.PAGE_START);
            ListSelectionListener listSelectionListener = new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent e) {
                    JList lsm = (JList)e.getSource();
                    if (lsm.isSelectionEmpty()) 
                    {
                        x1field.setText("ERROR");
                    }
                    else
                    {
                        // Find out which indexes are selected.
                        int minIndex = lsm.getMinSelectionIndex();
                        int maxIndex = lsm.getMaxSelectionIndex();
                        for (int i = minIndex; i <= maxIndex; i++) {
                            for (int j = 0; j < mil.getCityNames().length; j++) {
                                if(i==j){
                                    Destination thisDestination= mil.findDestination(mil.getCityNames()[i]);
                                    x1field.setText(Integer.toString(thisDestination.getNormal_miles()));
                                    x2field.setText(Integer.toString(thisDestination.getFF_miles()));
                                    x3field.setText(Integer.toString(thisDestination.getAdd_miles()));
                                    String[] monthstrings = new Airline().getMonthStrings();
                                    int a=0;
                                    String start="",end="";
                                    for(String s:monthstrings){
                                        if(thisDestination.getFF_startMonth()-1==a){
                                            start=s;
                                        }
                                        if(thisDestination.getFF_endMonth()-1==a){
                                            end=s;
                                        }
                                        a++;
                                    }
                                    x4field.setText(start+" to "+end);
                                }
                            }
                        }
                    }
                }

            };
            list.addListSelectionListener(listSelectionListener);

            cpanel.setBackground(new Color(214,214,194));
            upanel.add(cpanel, BorderLayout.CENTER);
            upanel.setBackground(new Color(214,214,194));

            bpanel = new JPanel(new BorderLayout()); // creating the right side panel with borderLayout
            bpanel.setPreferredSize(new Dimension(550, 300));


            //right panel labels and textfields
            bpanel.setBorder(BorderFactory.createTitledBorder("Redeem Miles"));
            c1panel = new JPanel(new GridLayout(3, 1));
            c1pane3 = new JPanel(new FlowLayout());
            JLabel lblFName = new JLabel("Enter your miles ");
            JTextField tfFName = new JTextField(20);
            lblFName.setLabelFor(tfFName);
            c1pane3.add(lblFName);
            c1pane3.add(tfFName);
            c1pane3.setBackground(new Color(255, 51, 51));
            c1panel.add(c1pane3);

            c1pane4 = new JPanel(new FlowLayout());
            JLabel lblFName1 = new JLabel("Select the month of departure");
            JTextField tfFName1 = new JTextField(20);

            String[] monthstrings = new Airline().getMonthStrings();
            SpinnerListModel months = new SpinnerListModel(monthstrings);
            JSpinner spinner = new JSpinner(months); // creating object for JSpinner class
            Dimension d = spinner.getPreferredSize(); //set spinner size
            d.width = 100;
            spinner.setPreferredSize(d);
            lblFName1.setLabelFor(tfFName1);
            c1pane4.add(lblFName1);
            c1pane4.add(spinner);
            c1pane4.setBackground(new Color(255, 77, 77));
            c1panel.add(c1pane4);

            c1pane5 = new JPanel(new FlowLayout());
            redButton = new JButton("Redeem Miles");               //Initializing the Jbutton
            c1pane5.add(redButton);                                     //Updatingelements to Panel
            JTextArea area = new JTextArea(" ", 8, 40);
            JScrollPane scrollPanel = new JScrollPane(area);
            bpanel.add(scrollPanel, BorderLayout.CENTER);
            c1pane5.setBackground(new Color(255,102,102));
            c1panel.add(c1pane5);
            bpanel.add(c1panel, BorderLayout.PAGE_START);

            c1pane2 = new JPanel(new FlowLayout());
            JLabel lblFName2 = new JLabel("Your Remaining Miles");
            JTextField tfFName2 = new JTextField(20);
            lblFName2.setLabelFor(tfFName2);
            c1pane2.add(lblFName2);
            c1pane2.add(tfFName2);
            
            // ActionListener to generate response when a button is clicked
            redButton.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    area.setText(null);
                    String[] redmiles;
                    redmiles=mil.redeemMiles(Integer.parseInt(tfFName.getText()),4);
                    if(!(redmiles.length==0)) {
                    	area.append("\nYour accumulated miles can be used to redeem the following tickets:\n\n");
                    for(int i=0;i<redmiles.length;i++){
                        area.append("* "+redmiles[i]+"\n");
                    }
                    tfFName2.setText(Integer.toString(mil.getRemainingMiles()));}
                    else
                    {
                    	area.append("\n Your client has not accumulated enough Frequent Flyer Miles \n\n");
                    	tfFName2.setText(tfFName.getText());
                    }
                }
            });

            c1pane2.setBackground(new Color(128, 255, 128));
            bpanel.add(c1pane2, BorderLayout.PAGE_END);
            bpanel.setBackground(new Color(214,214,194));
            xpanel = new JPanel();                              //new Jpanel to hold
            xpanel.add(upanel, BorderLayout.LINE_START);         //Add the Panel and Border Layout

            //final main panels
            ypanel = new JPanel();
            ypanel.add(bpanel, BorderLayout.CENTER);
            final1 = new JPanel();
            final1.add(xpanel, BorderLayout.WEST);
            final1.add(ypanel, BorderLayout.CENTER);
            xpanel.setBackground(new Color(214,214,194));
            ypanel.setBackground(new Color(214,214,194));
            mainFrame.add(final1);
            mainFrame.pack();
            mainFrame.setSize(950, 350);
            mainFrame.setMinimumSize(new Dimension(950,350));
            mainFrame.setMaximumSize(new Dimension(950,350));
            mainFrame.setLocationRelativeTo(null);
            mainFrame.setVisible(true);
            final1.setBackground(new Color(214,214,194));

        }
        catch (Exception e){
            System.out.println("Error-:"+e);
        }
    }

  
    private String[] getMonthStrings() {
        String[] months = new java.text.DateFormatSymbols().getMonths();
        int lastIndex = months.length - 1;
        if (months[lastIndex] == null || months[lastIndex].length() <= 0) {
            //last item empty
            String[] monthStrings = new String[lastIndex];
            System.arraycopy(months, 0, monthStrings, 0, lastIndex);
            return monthStrings;
        } else {
            //last item not empty
            return months;
        }
    }

    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable() {
                                   public void run() {
                                       createAndShowGUI();
                                   }
                               }
        );

    }
}


