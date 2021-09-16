import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddPanel extends JPanel implements ActionListener {
    private JLabel ID, LastName, FirstName, VaccineType, VaccineDate, VaccineLocation, Instructions;
    private JTextField ID_in, LastName_in, FirstName_in, VaccineType_in, VaccineLocation_in, VaccineDate_in;
    private JButton submit;
    private JPanel canvas;
    private JTable table;
    private JScrollPane scrollPane;
    private GridBagConstraints gbc;
    private ArrayList<VaccineInfo> infoList;
    public AddPanel(ArrayList<VaccineInfo> l){
        // Configurations to addPanel
        this.setPreferredSize( new Dimension(1400,800));
        setLayout(new GridLayout(1,0));
        // ArrayList
        infoList = l;
        // Canvas configurations
        canvas = new JPanel();
        canvas.setSize(1400,800);
        canvas.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        // Labels
        ID = new JLabel("ID: ");
        //ID.setBounds(0,0,150,20);
        LastName = new JLabel("Last Name: ");
        //LastName.setBounds(0,30,150,20);
        FirstName = new JLabel("First Name: ");
        //FirstName.setBounds(0,60,150,20);
        VaccineType = new JLabel("Vaccine Type: ");
       // VaccineType.setBounds(0,90,150,20);
        VaccineDate = new JLabel("Vaccine Date: ");
       // VaccineDate.setBounds(0,120,150,20);
        VaccineLocation = new JLabel("Vaccine Location: ");
       // VaccineLocation.setBounds(0,150,150,20);
        Instructions = new JLabel( "Please enter the following information and click submit.");
        // Text Fields
        ID_in =  new JTextField();
        ID_in.setPreferredSize(new Dimension(200,30));
        LastName_in =  new JTextField();
        LastName_in.setPreferredSize(new Dimension(200,30));
        FirstName_in =  new JTextField();
        FirstName_in.setPreferredSize(new Dimension(200,30));
        VaccineType_in =  new JTextField();
        VaccineType_in.setPreferredSize(new Dimension(200,30));
        VaccineDate_in =  new JTextField();
        VaccineDate_in.setPreferredSize(new Dimension(200,30));
        VaccineLocation_in =  new JTextField();
        VaccineLocation_in.setPreferredSize(new Dimension(200,30));
        // Button
        submit = new JButton("Submit");
        submit.setBounds(0,0,100,100);
        submit.addActionListener(this);
        // Adding components
        gbc.gridx = 0; gbc.gridy = 1;
        canvas.add(ID,gbc);
        gbc.gridx = 0; gbc.gridy = 2;
        canvas.add(LastName,gbc);
        gbc.gridx = 0; gbc.gridy = 3;
        canvas.add(FirstName,gbc);
        gbc.gridx = 0; gbc.gridy = 4;
        canvas.add(VaccineType,gbc);
        gbc.gridx = 0; gbc.gridy = 5;
        canvas.add(VaccineDate,gbc);
        gbc.gridx = 0; gbc.gridy = 6;
        canvas.add(VaccineLocation,gbc);
        gbc.gridx = 1; gbc.gridy = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        canvas.add(ID_in,gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        canvas.add(LastName_in,gbc);
        gbc.gridx = 1; gbc.gridy = 3;
        canvas.add(FirstName_in,gbc);
        gbc.gridx = 1; gbc.gridy = 4;
        canvas.add(VaccineType_in,gbc);
        gbc.gridx = 1; gbc.gridy = 5;
        canvas.add(VaccineDate_in,gbc);
        gbc.gridx = 1; gbc.gridy = 6;
        canvas.add(VaccineLocation_in,gbc);
        gbc.gridx = 3; gbc.gridy = 6;
        canvas.add(submit,gbc);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 3;
        canvas.add(Instructions,gbc);
        // adding canvas to addPanel
        this.add(canvas);
    }
    public void action(){
        if (infoList.size() == 0){
            return;
        }
        if (scrollPane != null){
            canvas.remove(scrollPane);
        }
        String filename;
        String [] info;
        Object[][] data;
        Object[] columnNames = { "ID", "Last Name", "First Name",
                "Vaccine Type", "Vaccine Date", "Vaccine Location"};
        data = new Object[infoList.size()][6];
        for (int count = 0; count < infoList.size(); count++){
            data[count][0] = infoList.get(count).getId();
            data[count][1] = infoList.get(count).getLastName();
            data[count][2] = infoList.get(count).getFirstName();
            data[count][3] = infoList.get(count).getvType();
            data[count][4] = infoList.get(count).getvDate();
            data[count][5] = infoList.get(count).getvLocation();
        }
        table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(700,300));
        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(700,400));
        gbc.gridx = 4; gbc.gridy = 0; gbc.gridheight = 7;
        canvas.add(scrollPane, gbc);
        add(canvas);
        revalidate();
    }
    @Override
    public void actionPerformed(ActionEvent b) {
        int check = 0;
        StringBuilder message = new StringBuilder();
        if (b.getSource() == submit){
            if (ID_in.getText().isBlank()) { message.append("ID, "); check = 1; }
            if (LastName_in.getText().isBlank()){ message.append("Last Name, "); check = 1; }
            if (FirstName_in.getText().isBlank()) { message.append("First Name, "); check = 1; }
            if (VaccineType_in.getText().isBlank()){ message.append("Vaccine Type, "); check = 1; }
            if (VaccineDate_in.getText().isBlank()) { message.append("Vaccine Date, "); check = 1; }
            if (VaccineLocation_in.getText().isBlank()) { message.append("Vaccine location, ");check = 1; }
            if (check == 1){
                message.append(" textfields are empty. Please fill.");
                JOptionPane.showMessageDialog(null, message);
                return;
            }
            else {
                // Loading data into string array
                check = 0;
                for (int cnt = 0; cnt < ID_in.getText().length(); cnt++){
                    if (Character.isDigit(ID_in.getText().charAt(cnt)) == false || ID_in.getText().length() > 5){
                        JOptionPane.showMessageDialog(null, "Please enter a 5 number ID.");
                        return;
                    }
                }
                for (int cnt = 0; cnt < VaccineDate_in.getText().length(); cnt++){
                    if (Character.isLetter(VaccineDate_in.getText().charAt(cnt))){
                        JOptionPane.showMessageDialog(null, "Please enter a valid date.");
                        return;
                    }
                }
                if (ID_in.getText().length() > 5){
                    JOptionPane.showMessageDialog(null, "Please input a valid ID.\nDo not include letters or more than 5 numbers");
                    return;
                }
                String[] newVaccine = {ID_in.getText(), LastName_in.getText(), FirstName_in.getText(),
                        VaccineType_in.getText(), VaccineDate_in.getText(), VaccineLocation_in.getText()};
                newVaccine[0] = ("00000" + newVaccine[0]).substring(newVaccine[0].length());
                for (int count = 0; count < infoList.size(); count++){
                    if (newVaccine[0].compareTo(infoList.get(count).getId()) == 0){ // Used to check if IDs match
                        infoList.get(count).setLastName(newVaccine[1]);
                        infoList.get(count).setFirstName(newVaccine[2]);
                        infoList.get(count).setvType(newVaccine[3]);
                        infoList.get(count).setvDate(newVaccine[4]);
                        infoList.get(count).setvLocation(newVaccine[5]);
                        check = 1;
                    }
                }
                // Passing string array to a new VaccineInfo object and adding into the infoList
                if (check != 1) {
                    infoList.add(new VaccineInfo(newVaccine));
                }
                JOptionPane.showMessageDialog(null, "Successfully added information!");
            }
            ID_in.setText("");
            LastName_in.setText("");
            FirstName_in.setText("");
            VaccineType_in.setText("");
            VaccineDate_in.setText("");
            VaccineLocation_in.setText("");
            action();
        }
    }
}
