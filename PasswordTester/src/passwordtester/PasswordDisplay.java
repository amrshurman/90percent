/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordtester;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

/**
 * A Panel class that displays and can allow the user to enter a password
 * @author Jacob
 */
public class PasswordDisplay extends JPanel {
    
    CharacterDisplay[] characters = null;
    
    /**
     * Creates a panel that displays the given password and is editable
     * @param password The Password to display
     */
    public PasswordDisplay(Password password){
        this(password, true);
    }
    
    /**
     * Creates a panel that displays a blank password of length 3 and is editable
     * @param length The length of the password to display
     */
    public PasswordDisplay(int length){
        this(new Password(new PasswordCharacter[length]), true);
    }
    
    /**
     * Creates a panel that displays the given password. Can be either editable or not
     * @param password The Password to display
     * @param changable True if the password should be editable, False otherwise
     */
    public PasswordDisplay(Password password, boolean changable){
        init(password, changable);
    }
    
    /**
     * Initializes the panel
     * @param password The Password to display
     * @param changable True if the password should be editable, false otherwise
     */
    private void init(Password password, boolean changable){
        //Sets up the layout of the panel
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 1; c.gridheight = 1;
        c.gridy = 0;
        
        //Creates the character display panels for all the classes and adds them to the panel
        characters = new CharacterDisplay[password.getLength()];
        for (int i = 0; i < characters.length; i++){
            characters[i] = new CharacterDisplay(password.getCharacter(i), changable);
            c.gridx = i;
            this.add(characters[i], c);
        }
    }
    
    /**
     * Gets the password currently displayed by this panel
     * @return The password currently display by this panel
     */
    public Password getPassword(){
        //Gets the character pairs from all the character pair displays and puts those into a password structure
        PasswordCharacter[] passChar = new PasswordCharacter[characters.length];
        for (int i = 0; i < passChar.length; i++){
            passChar[i] = characters[i].getCharacter();
        }
        return new Password(passChar);
    }
}
