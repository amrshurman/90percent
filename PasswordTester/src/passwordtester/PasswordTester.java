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
 *
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
    
    private HashMap<String, Password> passwords = new HashMap<>();
    private String user = null;
    private final String scheme = "ColorAnimalPair", condition = "PairPoll";
    
    private CSVWriter writer = null;
    
    public PasswordTester(){
        init();
    }
    
    private void init(){
        frame = new JFrame("Test Password");
        frame.setResizable(true);
        
        user = "User"+(int)(Math.random()*1000000);
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 1; c.gridheight = 1;
        c.gridy = 0; c.gridx = 0;
        c.weightx = 0; c.weighty = 0;
        
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
        
        CheckPasswordPanel pnlCheckEmail = CheckPasswordPanel("Email", null);
        CheckPasswordPanel pnlCheckShopping = CheckPasswordPanel("Shopping", pnlCheckEmail.btnCheck);
        CheckPasswordPanel pnlCheckBank = CheckPasswordPanel("Bank", pnlCheckShopping.btnCheck);
        CreatePasswordPanel pnlCreateShopping = createPasswordPanel("Shopping", pnlCheckBank.btnCheck, false);
        CreatePasswordPanel pnlCreateBank = createPasswordPanel("Bank", pnlCreateShopping.btnCreate, false);
        CreatePasswordPanel pnlCreateEmail = createPasswordPanel("Email", pnlCreateBank.btnCreate, true);
        
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
        
        try {
            writer = new CSVWriter(System.getProperty("user.home") + "/Desktop/KeyPairPassword.csv");
        } catch (IOException ex) {
            Logger.getLogger(PasswordTester.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
    
    private CreatePasswordPanel createPasswordPanel(String field, JButton nextBtn, boolean start) {
        return new CreatePasswordPanel(field, nextBtn, start);
    }
    
    private CheckPasswordPanel CheckPasswordPanel(String field, JButton nextBtn){
        return new CheckPasswordPanel(field, nextBtn);
    }
    
    
    private void log(String site, String mode, String event){
        String[] logData = new String[7];
        logData[0] = format.format(new Date());
        logData[1] = user;
        logData[2] = site;
        logData[3] = scheme;
        logData[4] = mode;
        logData[5] = event;
        logData[6] = "";
        
        String output = "";
        for (int i = 0; i < logData.length; i++){
            output += logData[i];
            if (i < logData.length-1)
                output += ",";
            else output += "\n";
        }
        
        txtLogs.append(output);
        try {
            writer.writeNext(logData);
        } catch (IOException ex) {
            Logger.getLogger(PasswordTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private class CreatePasswordPanel extends JPanel {
        
        private JLabel lbl = null;
        private JButton btnCreate = null;
        private JButton btnNext = null;
        
        public CreatePasswordPanel(String field, JButton nextBtn, boolean start){
            this.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.BOTH;
            c.gridwidth = 1; c.gridheight = 1;
            c.gridy = 0; c.gridx = 0;
            c.weightx = 1; c.weighty = 1;

            lbl = new JLabel("Create Password for: " + field);
            c.gridwidth = 3; c.gridheight = 1;
            c.gridy = 0; c.gridx = 0;
            this.add(lbl, c);

            btnNext = new JButton("Next");
            btnNext.setEnabled(false);
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

            btnCreate = new JButton("Create");
            btnCreate.setEnabled(start);
            btnCreate.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    JPanel panel = new JPanel();
                    panel.setLayout(new GridBagLayout());
                    GridBagConstraints c = new GridBagConstraints();
                    c.fill = GridBagConstraints.BOTH;
                    c.gridwidth = 1; c.gridheight = 1;
                    c.gridy = 0; c.gridx = 0;
                    c.weightx = 0; c.weighty = 0;
                    
                    JLabel lblmsg = new JLabel("Your Password is:");
                    panel.add(lblmsg, c);
                    
                    Password password = Password.createRandomPassword(3);
                    PasswordDisplay display = new PasswordDisplay(password, false);
                    c.gridy = 1; c.gridx = 0;
                    c.weightx = 1; c.weighty = 1;
                    panel.add(display, c);
                    
                    log(field, "create", "start");
                    JOptionPane.showMessageDialog(frame, panel, "Create a Password", JOptionPane.PLAIN_MESSAGE);
                    log(field, "create", "passwordSubmitted");
                    
                    passwords.put(field, password);
                    
                    btnNext.setEnabled(true);
                }
            });
            c.gridwidth = 1; c.gridheight = 1;
            c.gridy = 1; c.gridx = 0;
            this.add(btnCreate, c);
        }
    }
    
    private class CheckPasswordPanel extends JPanel {
        
        private JLabel lbl = null;
        private JButton btnCheck = null;
        private JButton btnNext = null;
        
        public CheckPasswordPanel(String field, JButton nextBtn){
            this.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.BOTH;
            c.gridwidth = 1; c.gridheight = 1;
            c.gridy = 0; c.gridx = 0;
            c.weightx = 1; c.weighty = 1;

            lbl = new JLabel("Check Password for: " + field);
            c.gridwidth = 3; c.gridheight = 1;
            c.gridy = 0; c.gridx = 0;
            this.add(lbl, c);

            btnNext = new JButton("Next");
            btnNext.setEnabled(false);
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

            btnCheck = new JButton("Check");
            btnCheck.setEnabled(false);
            btnCheck.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    JPanel panel = new JPanel();
                    panel.setLayout(new GridBagLayout());
                    GridBagConstraints c = new GridBagConstraints();
                    c.fill = GridBagConstraints.BOTH;
                    c.gridwidth = 1; c.gridheight = 1;
                    c.gridy = 0; c.gridx = 0;
                    c.weightx = 0; c.weighty = 0;
                    
                    JLabel lblmsg = new JLabel("Password:");
                    panel.add(lblmsg, c);
                    
                    PasswordDisplay display = new PasswordDisplay(3);
                    c.gridy = 1; c.gridx = 0;
                    c.weightx = 1; c.weighty = 1;
                    panel.add(display, c);
                    
                    log(field, "enter", "start");
                    int retVal = JOptionPane.showConfirmDialog(frame, panel, "Enter " + field + " Password", JOptionPane.OK_CANCEL_OPTION);
                    
                    if (retVal == JOptionPane.OK_OPTION){
                        log(field, "enter", "passwordSubmitted");
                        Password givenPass = display.getPassword();
                        Password correctPass = passwords.get(field);
                        
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
