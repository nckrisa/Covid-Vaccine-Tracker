import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.TableView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LoadPanel extends JPanel implements ActionListener {
    private ArrayList<VaccineInfo> infoList;
    private JPanel canvas;
    private GridBagConstraints gbc;
    private JTextField fileLocation;
    private JLabel askFile;
    private JButton submit;
    private JTable table;
    private JScrollPane scrollPane;
    private File file;

    public LoadPanel(ArrayList<VaccineInfo> l){
        infoList = l;
        // Configurations to LoadPanel
        setSize(1400,800);
        setLayout(new BorderLayout());
        // Canvas configurations
        canvas = new JPanel();
        canvas.setSize(600,600);
        canvas.setLayout(new GridBagLayout());
        // gbc constraints
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        // GUI setup
        fileLocation = new JTextField();
        fileLocation.setPreferredSize(new Dimension(200,30));
        askFile = new JLabel("Please enter file");
        submit = new JButton("Submit");
        submit.setSize(100,100);
        submit.addActionListener(this);

        gbc.gridx = 0; gbc.gridy = 0;
        canvas.add(askFile, gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        canvas.add(fileLocation, gbc);
        gbc.gridx = 2; gbc.gridy = 0;
        canvas.add(submit, gbc);
        this.add(canvas, BorderLayout.CENTER);
    }
   public void action(){
        if (infoList.size() == 0){
            return;
        }
        if (table != null){
            canvas.remove(scrollPane);
            this.remove(canvas);
        }
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
        gbc.gridy = 1;
        gbc.gridwidth = 5;
        gbc.gridheight = 5;
        canvas.add(scrollPane);
        add(canvas);
        revalidate();
    }
    @Override
    public void actionPerformed(ActionEvent b) {
        if (b.getSource() == submit){
            int check = 0;
            String [] info;
            if (table != null){
                canvas.remove(scrollPane);
            }
            if (fileLocation.getText().isBlank()) {
                JOptionPane.showMessageDialog(null, "Please enter a file name.");
                return;
            }
            try{
                file = new File(fileLocation.getText());
                Scanner read = new Scanner(file);
                if(read.hasNextLine())
                    read.nextLine();
                while(read.hasNextLine()){
                    info = read.nextLine().split(",");
                    info[0] = ("00000" + info[0]).substring(info[0].length());
                    for (int count = 0; count < infoList.size();count++){
                        if (info[0].compareTo(infoList.get(count).getId()) == 0){ // used to rewrite info if IDs match
                            infoList.get(count).setLastName(info[1]);
                            infoList.get(count).setFirstName(info[2]);
                            infoList.get(count).setvType(info[3]);
                            infoList.get(count).setvDate(info[4]);
                            infoList.get(count).setvLocation(info[5]);
                            check = 1;
                            break;
                        }
                    }
                    if (check != 1) {
                        infoList.add(new VaccineInfo(info));
                    }
                    check = 0;
                }
                action();
                JOptionPane.showMessageDialog(null, "Successfully appended data!");
            }
            catch (FileNotFoundException e) {
            	System.out.println(e);
                JOptionPane.showMessageDialog(null, "An error occurred opening the file.");
                return;
            }

        }
    }
}
