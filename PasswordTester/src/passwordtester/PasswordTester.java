/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordtester;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Main class of the system. Creates all the nessecary visual elements and data member
 * for the system to run
 * @author Jacob
 */
public class PasswordTester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PasswordTester tester = new PasswordTester();
    }
    
    private final static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    
    JFrame frame = null;
    JTextArea txtLogs = null;
    
    //Hashmap to contain all the passwords in use in the system, uses the name of the site as the key
    private HashMap<String, Password> passwords = new HashMap<>();
    private String user = null;
    private final String scheme = "ColorAnimalPair", condition = "PairPoll";
    
    private CSVWriter writer = null;
    
    public PasswordTester(){
        init();
    }
    
    /**
     * Initializes all the elements in the system
     */
    private void init(){
        frame = new JFrame("Test Password");
        frame.setResizable(true);
        
        user = "User"+(int)(Math.random()*1000000);
        
        //Sets up the layout manager for the frame
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 1; c.gridheight = 1;
        c.gridy = 0; c.gridx = 0;
        c.weightx = 0; c.weighty = 0;
        
        //Creating elements to display the system data
        JLabel lblTitle = new JLabel("Animal Color Pair Tester");
        panel.add(lblTitle, c);
        
        JLabel lblUser = new JLabel("User: " + user);
        c.gridy = 1; c.gridx = 0;
        panel.add(lblUser, c);
        
        JLabel lblScheme = new JLabel("Scheme: " + scheme);
        c.gridy = 2; c.gridx = 0;
        panel.add(lblScheme, c);
        
        JLabel lblCondition = new JLabel("Condition: " + condition);
        c.gridy = 2; c.gridx = 1;
        panel.add(lblCondition, c);
        
        c.gridwidth = 2;
        
        //Createing the visual elements for all the buttons to create and check passwords
        CheckPasswordPanel pnlCheckEmail = CheckPasswordPanel("Email", null);
        CheckPasswordPanel pnlCheckShopping = CheckPasswordPanel("Shopping", pnlCheckEmail.btnCheck);
        CheckPasswordPanel pnlCheckBank = CheckPasswordPanel("Bank", pnlCheckShopping.btnCheck);
        CreatePasswordPanel pnlCreateShopping = createPasswordPanel("Shopping", pnlCheckBank.btnCheck, false);
        CreatePasswordPanel pnlCreateBank = createPasswordPanel("Bank", pnlCreateShopping.btnCreate, false);
        CreatePasswordPanel pnlCreateEmail = createPasswordPanel("Email", pnlCreateBank.btnCreate, true);
        
        //Adding all the panels with the buttons to create and check the passwords
        c.gridy = 3; c.gridx = 0;
        panel.add(pnlCreateEmail, c);
        c.gridy = 4; c.gridx = 0;
        panel.add(pnlCreateBank, c);
        c.gridy = 5; c.gridx = 0;
        panel.add(pnlCreateShopping, c);
        c.gridy = 6; c.gridx = 0;
        panel.add(pnlCheckBank, c);
        c.gridy = 7; c.gridx = 0;
        panel.add(pnlCheckShopping, c);
        c.gridy = 8; c.gridx = 0;
        panel.add(pnlCheckEmail, c);
        
        //Creating the text area for the system logs
        txtLogs = new JTextArea();
        JScrollPane pnlLogs = new JScrollPane(txtLogs);
        c.gridwidth = 3; c.gridheight = 1;
        c.gridy = 9; c.gridx = 0;
        c.weightx = 1; c.weighty = 1;
        panel.add(pnlLogs, c);
        
        //Password p = Password.createRandomPassword(3);
        //PasswordDisplay dis = new PasswordDisplay(3);
        //frame.add(dis);
        
        panel.setPreferredSize(new Dimension(800, 800));
        
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        
        //Opening a csv writer for writing the log file
        try {
            String fileName = System.getProperty("user.home") + "/Desktop/KeyPairPassword"+user+".csv";
            writer = new CSVWriter(fileName);
            txtLogs.append("Writing Log to: "+fileName+"\n");
        } catch (IOException ex) {
            Logger.getLogger(PasswordTester.class.getName()).log(Level.SEVERE, null, ex);
            txtLogs.append("Error: Unable to create log file\n");
        }
        
        //Creating a shutdown hock to close the log csv writer when the system closes
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    writer.close();
                } catch (IOException ex) {
                    Logger.getLogger(PasswordTester.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }));
    }
    
    /**
     * Creates a panel with the buttons and labels to create a password
     * @param field The name of the site that this panel is creating the password for
     * @param nextBtn The next button to enable after this password is created
     * @param start True if the create button for this password should be enabled from the start, False otherwise
     * @return A panel containing all the required buttons and labels to create a password
     */
    private CreatePasswordPanel createPasswordPanel(String field, JButton nextBtn, boolean start) {
        return new CreatePasswordPanel(field, nextBtn, start);
    }
    
    /**
     * Creates a panel with the buttons and labels to check a password
     * @param field The name of the site that this panel is checking the password for
     * @param nextBtn The next button to enable after this password is checked
     * @return A panel containing all the required buttons and labels to check a password
     */
    private CheckPasswordPanel CheckPasswordPanel(String field, JButton nextBtn){
        return new CheckPasswordPanel(field, nextBtn);
    }
    
    /**
     * Logs data to the log file and log text area
     * @param site The name of the site for this log
     * @param event The event that is being logged
     * @param data Any data that should be logged
     */
    private void log(String site, String event, String data){
        //Putting all the required log data into a string array
        String[] logData = new String[8];
        logData[0] = format.format(new Date());
        logData[1] = user;
        logData[2] = site;
        logData[3] = scheme;
        logData[4] = "log";
        logData[5] = event;
        logData[6] = data;
        logData[7] = "";
        
        //Putting all the log data into a string to write to the lgo text area
        String output = "";
        for (int i = 0; i < logData.length; i++){
            output += logData[i];
            if (i < logData.length-1)
                output += ",";
            else output += "\n";
        }
        txtLogs.append(output);
        
        //Attempting to write the log data to the log file
        try {
            writer.writeNext(logData);
        } catch (IOException ex) {
            Logger.getLogger(PasswordTester.class.getName()).log(Level.SEVERE, null, ex);
            txtLogs.append("Error: Unable to write to log file\n");
        }
    }
    
    /**
     * A panel that contains all the required buttons and labels for creating a password
     */
    private class CreatePasswordPanel extends JPanel {
        
        private JLabel lbl = null;
        private JButton btnCreate = null;
        private JButton btnNext = null;
        
        /**
         * 
         * @param field The name of the site that this panel will create the password for
         * @param nextBtn The button to enable after this password is created
         * @param start True if the create button for this password should start enabled, false otherwise
         */
        public CreatePasswordPanel(String field, JButton nextBtn, boolean start){
            this.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.BOTH;
            c.gridwidth = 1; c.gridheight = 1;
            c.gridy = 0; c.gridx = 0;
            c.weightx = 1; c.weighty = 1;

            //Creating the label for creating this password
            lbl = new JLabel("Create Password for: " + field);
            c.gridwidth = 3; c.gridheight = 1;
            c.gridy = 0; c.gridx = 0;
            this.add(lbl, c);

            //Creating the next button for after the password was created
            btnNext = new JButton("Next");
            btnNext.setEnabled(false);
            //Creating an event listener for when this button is pressed to enable the next button if there is one
            btnNext.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (nextBtn != null)
                        nextBtn.setEnabled(true);
                }
            });
            c.gridwidth = 1; c.gridheight = 1;
            c.gridy = 1; c.gridx = 1;
            this.add(btnNext, c);

            //Creating the create button to create the password for this site
            btnCreate = new JButton("Create");
            btnCreate.setEnabled(start);
            //Creating an event listener for when this button is pressed to create the password for this site
            btnCreate.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    //Initializing the layout for the panel that will show the user their password
                    JPanel panel = new JPanel();
                    panel.setLayout(new GridBagLayout());
                    GridBagConstraints c = new GridBagConstraints();
                    c.fill = GridBagConstraints.BOTH;
                    c.gridwidth = 1; c.gridheight = 1;
                    c.gridy = 0; c.gridx = 0;
                    c.weightx = 0; c.weighty = 0;
                    
                    JLabel lblmsg = new JLabel("Your Password for "+field+" is:");
                    panel.add(lblmsg, c);
                    
                    //Creating a random password for the user and adding it to the password display
                    Password password = Password.createRandomPassword(3);
                    passwords.put(field, password);
                    PasswordDisplay display = new PasswordDisplay(password, false);
                    c.gridy = 1; c.gridx = 0;
                    c.weightx = 1; c.weighty = 1;
                    panel.add(display, c);
                    
                    //Logging the password creation and showing the popup panel to show the user the password
                    log(field, "create", "start");
                    JOptionPane.showMessageDialog(frame, panel, "Create a Password", JOptionPane.PLAIN_MESSAGE);
                    log(field, "create", "passwordSubmitted");
                    
                    //Enabling the next button
                    btnNext.setEnabled(true);
                }
            });
            c.gridwidth = 1; c.gridheight = 1;
            c.gridy = 1; c.gridx = 0;
            this.add(btnCreate, c);
        }
    }
    
    /**
     * A panel that contains all the required buttons and labels for checking a password
     */
    private class CheckPasswordPanel extends JPanel {
        
        private JLabel lbl = null;
        private JButton btnCheck = null;
        private JButton btnNext = null;
        
        /**
        * Creates a panel with the buttons and labels to check a password
        * @param field The name of the site that this panel is checking the password for
        * @param nextBtn The next button to enable after this password is checked
        * @return A panel containing all the required buttons and labels to check a password
        */
        public CheckPasswordPanel(String field, JButton nextBtn){
            this.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.BOTH;
            c.gridwidth = 1; c.gridheight = 1;
            c.gridy = 0; c.gridx = 0;
            c.weightx = 1; c.weighty = 1;

            //Creating the label for checking this password
            lbl = new JLabel("Check Password for: " + field);
            c.gridwidth = 3; c.gridheight = 1;
            c.gridy = 0; c.gridx = 0;
            this.add(lbl, c);

            //Creating the next button for after the password was checked
            btnNext = new JButton("Next");
            btnNext.setEnabled(false);
            //Creating an event listener for when this button is pressed to enable the next button if there is one
            btnNext.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (nextBtn != null)
                        nextBtn.setEnabled(true);
                }
            });
            c.gridwidth = 1; c.gridheight = 1;
            c.gridy = 1; c.gridx = 1;
            this.add(btnNext, c);

            //Creating the create button to check the password for this site
            btnCheck = new JButton("Check");
            btnCheck.setEnabled(false);
            //Creating an event listener for when this button is pressed to create the password for this site
            btnCheck.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    //Initializing the layout for the panel that will show the user their password
                    JPanel panel = new JPanel();
                    panel.setLayout(new GridBagLayout());
                    GridBagConstraints c = new GridBagConstraints();
                    c.fill = GridBagConstraints.BOTH;
                    c.gridwidth = 1; c.gridheight = 1;
                    c.gridy = 0; c.gridx = 0;
                    c.weightx = 0; c.weighty = 0;
                    
                    JLabel lblmsg = new JLabel("Password:");
                    panel.add(lblmsg, c);
                    
                    //Creating a display for the user to enter the password on
                    PasswordDisplay display = new PasswordDisplay(3);
                    c.gridy = 1; c.gridx = 0;
                    c.weightx = 1; c.weighty = 1;
                    panel.add(display, c);
                    
                    //Logging that the user is entering the password and showing the popup panel to get the password from the user
                    log(field, "enter", "start");
                    int retVal = JOptionPane.showConfirmDialog(frame, panel, "Enter " + field + " Password", JOptionPane.OK_CANCEL_OPTION);
                    
                    //If the user clicked ok on the popup, checking the password
                    if (retVal == JOptionPane.OK_OPTION){
                        log(field, "enter", "passwordSubmitted");
                        Password givenPass = display.getPassword();
                        Password correctPass = passwords.get(field);
                        
                        //Checking if the password is correct and logging the output
                        if (givenPass.equals(correctPass)){
                            log(field, "login", "success");
                            System.out.println("Correct");
                        } else {
                            log(field, "login", "failure");
                            System.out.println("Fail");
                        }
                    }
                    
                    btnNext.setEnabled(true);
                }
            });
            c.gridwidth = 1; c.gridheight = 1;
            c.gridy = 1; c.gridx = 0;
            this.add(btnCheck, c);
        }
    }
}
