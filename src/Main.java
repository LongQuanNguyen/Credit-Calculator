import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame openMessage = new JFrame();
        JOptionPane.showMessageDialog(openMessage, "USASK DEGREE WORK CALCULATOR\n" +
                "\nby:   Nguyen Long Quan" +
                "" +
                "\n         iqm026@usask.ca");
        new MainMenuFrame();
    }
}