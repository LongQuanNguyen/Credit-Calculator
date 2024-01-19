import javax.swing.*;

public class MainMenuFrame extends JFrame {
    public static final int DEFAULT_WIDTH = 500;
    public static final int DEFAULT_HEIGHT = 400;

    public MainMenuFrame(){
        setTitle("Main Menu");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainMenuPanel panel = new MainMenuPanel();
        add(panel);
        setVisible(true);
    }

    public static void main(String[]args){
        MainMenuFrame frame = new MainMenuFrame();
   }
}