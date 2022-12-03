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

             JLabel AddClassTexts = new JLabel("      Name                Level                Credit               Grade");
             AddClassTexts.setBounds(20, 85, 330, 15);
             add(AddClassTexts);

             JTextField nameInput = new JTextField();
             nameInput.setBounds(20, 105, 70, 20);
             add(nameInput);

             JTextField levelInput = new JTextField();
             levelInput.setBounds(100, 105, 70, 20);
             add(levelInput);

             JTextField creditInput = new JTextField();
             creditInput.setBounds(180, 105, 70, 20);
             add(creditInput);

             JTextField gradeInput = new JTextField();
             gradeInput.setBounds(260, 105, 70, 20);
             add(gradeInput);

             JButton AddClassButton = new JButton("Add Class");
             AddClassButton.setBounds(360, 60, 100, 65);
             add(AddClassButton);
             AddClassButton.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent event) {
                     try {
                         String name = nameInput.getText();
                         int level = Integer.parseInt(levelInput.getText());
                         int credit = Integer.parseInt(creditInput.getText());
                         int grade = Integer.parseInt(gradeInput.getText());
                         Class a_class = new Class(name, level, credit, grade);
                         P.addClass(a_class);
                         nameInput.setText(null);
                         levelInput.setText(null);
                         creditInput.setText(null);
                         gradeInput.setText(null);
                     } catch (NumberFormatException e) {
                         nameInput.setText("   ERROR ");
                         levelInput.setText("Integer only");
                         creditInput.setText("Integer only");
                         gradeInput.setText("Integer only");
                     }
                 }
             });
         }

         // DROP CLASS
         {
             JLabel RemoveClassText = new JLabel("Enter the class level number to remove:");
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
                     try {
                         int classLevel = Integer.parseInt(RemoveClassInput.getText());
                         if (!P.hasClassLevel(classLevel)) {
                             RemoveClassInput.setText("There is no class level " + classLevel + " in the system!");
                         } else {
                             P.removeClass(classLevel);
                             RemoveClassInput.setText(null);
                         }
                     } catch (NumberFormatException e) {
                         RemoveClassInput.setText("A numeric input is required!");
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
                        NameFile.setText("File name can not be empty!");
                    } else if (!NameFile.getText().equals("File name can not be empty!") && !NameFile.getText().equals("")) {
                        PrintFileCreate outputFile = new PrintFileCreate();
                        outputFile.fileCreate(NameFile.getText());
                        outputFile.fileWrite(P.toString());

                        JFrame messageDialog = new JFrame();
                        JOptionPane.showMessageDialog(messageDialog, outputFile.getFileStatus());

                    }
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
                         JOptionPane.showMessageDialog(messageDialog, P.progressPercentage() + " of your degree");
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