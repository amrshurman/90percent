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
 *
 * @author Jacob
 */
public class PasswordDisplay extends JPanel {
    
    CharacterDisplay[] characters = null;
    
    public PasswordDisplay(Password password){
        this(password, true);
    }
    
    public PasswordDisplay(int length){
        this(new Password(new PasswordCharacter[length]), true);
    }
    
    public PasswordDisplay(Password password, boolean changable){
        init(password, changable);
    }
    
    private void init(Password password, boolean changable){
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 1; c.gridheight = 1;
        c.gridy = 0;
        
        characters = new CharacterDisplay[password.getLength()];
        for (int i = 0; i < characters.length; i++){
            characters[i] = new CharacterDisplay(password.getCharacter(i), changable);
            c.gridx = i;
            this.add(characters[i], c);
        }
    }
    
    public Password getPassword(){
        PasswordCharacter[] passChar = new PasswordCharacter[characters.length];
        for (int i = 0; i < passChar.length; i++){
            passChar[i] = characters[i].getCharacter();
        }
        return new Password(passChar);
    }
}
