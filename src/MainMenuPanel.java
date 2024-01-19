import javax.swing.*;
import java.awt.event.*;

public class MainMenuPanel extends JPanel {
     public MainMenuPanel(){
         Progress P = new Progress();
         setLayout(null);

         // DEGREE TYPE
         {
             JLabel DegreeTypeText = new JLabel("Select degree type:");
             DegreeTypeText.setBounds(20, 15, 120, 20);
             add(DegreeTypeText);

             String options[] = {"", "THREE YEARS  90 credits", "FOUR YEARS  120 credits"};
             JComboBox option = new JComboBox(options);
             option.setBounds(150, 10, 180, 25);
             add(option);

             option.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     String select = (String) option.getSelectedItem();
                     if (select == "THREE YEARS  90 credits") {
                         P.setTotalRequireCredit(3);
                     } else if (select == "FOUR YEARS  120 credits") {
                         P.setTotalRequireCredit(4);
                     } else {
                         P.setTotalRequireCredit(0);
                     }
                 }
             });
         }

         // ADD CLASS
         {
             JLabel AddClassText = new JLabel("Enter the class to be added:      (Must fill all 4 below)");
             AddClassText.setBounds(20, 60, 330, 15);
             add(AddClassText);

             JLabel AddClassTexts = new JLabel("    Subject               Level                Grade                Credit");
             AddClassTexts.setBounds(20, 85, 330, 15);
             add(AddClassTexts);

             JTextField nameInput = new JTextField();
             nameInput.setBounds(20, 105, 70, 20);
             add(nameInput);

             JTextField levelInput = new JTextField();
             levelInput.setBounds(100, 105, 70, 20);
             add(levelInput);

             JTextField gradeInput = new JTextField();
             gradeInput.setBounds(180, 105, 70, 20);
             add(gradeInput);

             String creditOptions[] = {"3", "1.5", "1", ""};
             JComboBox credChoice = new JComboBox(creditOptions);
             credChoice.setBounds(260, 105, 70, 20);
             add(credChoice);

             JButton AddClassButton = new JButton("Add Class");
             AddClassButton.setBounds(360, 60, 100, 65);
             add(AddClassButton);
             AddClassButton.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent event) {
                     try {
                         String name = nameInput.getText().toUpperCase();
                         int level = Integer.parseInt(levelInput.getText());
                         int grade = Integer.parseInt(gradeInput.getText());
                         double credit;
                         String select = (String)credChoice.getSelectedItem();
                         if (select == "3") {
                             credit = 3;
                         } else if (select == "1.5") {
                             credit = 1.5;
                         } else if (select == "1") {
                             credit = 1;
                         } else {
                             credit = 0;
                         }
                         if (P.hasClass(nameInput.getText().toUpperCase() + " " + levelInput.getText())){
                             JFrame warningDialog = new JFrame();
                             JOptionPane.showMessageDialog(warningDialog, nameInput.getText().toUpperCase() + " " + levelInput.getText() + " is already in the system!", "Alert", JOptionPane.WARNING_MESSAGE);
                         }else {
                             Class a_class = new Class(name, level, grade, credit);
                             P.addClass(a_class);
                         }
                         nameInput.setText(null);
                         levelInput.setText(null);
                         gradeInput.setText(null);
                     } catch (NumberFormatException e) {
                         JFrame messageDialog = new JFrame();
                         JOptionPane.showMessageDialog(messageDialog, "Input error occurred!\n" +
                                 "\nINPUT USAGE: "+
                                 "\n<Subject> : name code for the class subject"+
                                 "\n<Level> : must be three-digits numeric values" +
                                 "\n<Grade> : must be numeric values","Alert", JOptionPane.WARNING_MESSAGE);
                         nameInput.setText(null);
                         levelInput.setText(null);
                         gradeInput.setText(null);
                     }
                 }
             });
         }

         // DROP CLASS
         {
             JLabel RemoveClassText = new JLabel("Enter class name to remove:      (Subject + level)");
             RemoveClassText.setBounds(20, 153, 310, 15);
             add(RemoveClassText);

             JTextField RemoveClassInput = new JTextField();
             RemoveClassInput.setBounds(20, 175, 310, 20);
             add(RemoveClassInput);

             JButton RemoveClassButton = new JButton("Remove Class");
             RemoveClassButton.setBounds(345, 155, 115, 40);
             add(RemoveClassButton);

             RemoveClassButton.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent event) {
                     String className = RemoveClassInput.getText().toUpperCase();
                     if (!P.hasClass(className)) {
                         JFrame messageDialog = new JFrame();
                         JOptionPane.showMessageDialog(messageDialog, "There is no " + className + " class in the system!\n" +
                                 "\nINPUT USAGE: <subject name> + space + <class level>"+
                                 "\n<subject name> : name code for the class subject"+
                                 "\n<class level> : must be three-digits numeric values", "Alert", JOptionPane.WARNING_MESSAGE);
                         RemoveClassInput.setText(null);
                     } else {
                         P.removeClass(className);
                         RemoveClassInput.setText(null);
                     }
                 }
             });
         }

         // PRINT OUT
         {
             JLabel PrintInstructionText = new JLabel("Name the output file:      (Do not include '.txt')");
             PrintInstructionText.setBounds(20, 227, 310, 15);
             add(PrintInstructionText);

             JTextField NameFile = new JTextField();
             NameFile.setBounds(20, 250, 310, 20);
             add(NameFile);

             JButton PrintOutButton = new JButton("Print to Text!");
             PrintOutButton.setBounds(345, 230, 115, 40);
             add(PrintOutButton);

             PrintOutButton.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                    if(NameFile.getText().equals("")){
                        JFrame warningDialog = new JFrame();
                        JOptionPane.showMessageDialog(warningDialog, "File name can not be empty!", "Alert", JOptionPane.WARNING_MESSAGE);
                    } else if (!NameFile.getText().equals("")) {
                        PrintFileCreate outputFile = new PrintFileCreate();
                        outputFile.fileCreate(NameFile.getText());
                        outputFile.fileWrite(P.toString());

                        JFrame messageDialog = new JFrame();
                        JOptionPane.showMessageDialog(messageDialog, outputFile.getFileStatus());
                    }
                     NameFile.setText(null);
                 }
             });
         }

         // CLASS LIST
         {
             JButton ClassListButton = new JButton("Class List");
             ClassListButton.setBounds(20, 300, 100, 40);
             add(ClassListButton);

             ClassListButton.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     JFrame messageDialog = new JFrame();
                     if (P.getTotalClass() == 0){
                         JOptionPane.showMessageDialog(messageDialog, "There is no class in the system!", "Alert", JOptionPane.WARNING_MESSAGE);
                     }else {
                         JOptionPane.showMessageDialog(messageDialog, P.getClassList(), "Class list", JOptionPane.INFORMATION_MESSAGE);
                     }
                 }
             });
         }

         // COMPLETED
         {
             JButton CompletedButton = new JButton("Completed");
             CompletedButton.setBounds(140, 300, 100, 40);
             add(CompletedButton);

             CompletedButton.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     JFrame messageDialog = new JFrame();
                     if (P.getProgram().equals("unspecified program")){
                         JOptionPane.showMessageDialog(messageDialog, "Degree type is not specified!", "Alert",JOptionPane.WARNING_MESSAGE);
                     } else {
                         String message = P.progressPercentage() + "\n";
                         message += "\nYou have taken  <" + P.getTotalClass() + ">  classes.";
                         message += "\nYou have total of  <" + P.getTotalCredit() + "/" + P.getTotalRequireCredit() + ">  required credit.";
                         message += "\nYou have  <" + P.getTotalSeniorCredit() + "/" + P.getSeniorRequiredCredit() + ">  required senior credit.";
                         JOptionPane.showMessageDialog(messageDialog, message);
                     }
                 }
             });
         }

         // AVERAGE
         {
             JButton AverageButton = new JButton("My Average");
             AverageButton.setBounds(260, 300, 110, 40);
             add(AverageButton);

             AverageButton.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     JFrame messageDialog = new JFrame();
                     if (P.getTotalClass() == 0) {
                         JOptionPane.showMessageDialog(messageDialog, "There is no class in the system!", "Alert", JOptionPane.WARNING_MESSAGE);
                     } else {
                         JOptionPane.showMessageDialog(messageDialog, "Your average is " + P.getAverage());
                     }
                 }
             });
         }

         // EXIT
         {
             JButton ExitButton = new JButton("Exit");
             ExitButton.setBounds(390, 300, 70, 40);
             add(ExitButton);

             ExitButton.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     System.exit(0);
                 }
             });
         }
     }

}