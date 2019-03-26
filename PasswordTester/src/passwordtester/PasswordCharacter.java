/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordtester;

/**
 *
 * @author Jacob
 */
public class PasswordCharacter {
    
    public static final String WHITE = "White";
    public static final String GREY = "Grey";
    public static final String BLACK = "Black";
    public static final String RED = "Red";
    public static final String BLUE = "Blue";
    public static final String YELLOW = "Yellow";
    public static final String ORANGE = "Orange";
    public static final String GREEN = "Green";
    public static final String PURPLE = "Purple";
    public static final String BROWN = "Brown";
    
    public static final String BEAR = "bear";
    public static final String CAT = "cat";
    public static final String CHICKEN = "chicken";
    public static final String COW = "cow";
    public static final String DOG = "dog";
    public static final String ELEPHANT = "elephant";
    public static final String GIRAFFE = "giraffe";
    public static final String GORILLA = "gorilla";
    public static final String KANGAROO = "kangaroo";
    public static final String LION = "lion";
    public static final String PANDA = "panda";
    public static final String RABBIT = "rabbit";
    public static final String SNAKE = "snake";
    
    
    public static final String[] COLORS = new String[]{WHITE, GREY, BLACK, RED, BLUE, YELLOW,
                                            GREEN, PURPLE, ORANGE, BROWN};
    public static final String[] ANIMALS = new String[]{BEAR, CAT, CHICKEN, COW, DOG, ELEPHANT,
                                            GIRAFFE, GORILLA, KANGAROO, LION, PANDA, RABBIT, SNAKE};
    
    private String color = null;
    private String animal = null;
    
    
    public PasswordCharacter(String color, String animal){
        this.color = color;
        this.animal = animal;
    }
    
    public String getColor() { return color; }
    public String getAnimal() { return animal; }
    
    public boolean equals(PasswordCharacter other){
        return color.equals(other.color) && animal.equals(other.animal);
    }
    
    public static PasswordCharacter createRandomCharacter(){
        return new PasswordCharacter(COLORS[(int)(Math.random() * COLORS.length)], ANIMALS[(int)(Math.random() * ANIMALS.length)]);
    }
}
