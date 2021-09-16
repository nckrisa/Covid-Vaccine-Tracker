import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SavePanel extends JPanel implements ActionListener {

    private ArrayList<VaccineInfo> infoList;
    private JPanel canvas;
    private GridBagConstraints gbc;
    private JLabel askFileName;
    private JButton save;
    final JFrame parent = new JFrame();

    public SavePanel(ArrayList<VaccineInfo> l) {

        //GUI Interface SetUp
        infoList = l;
        setPreferredSize(new Dimension(1400,800));
        setLayout(new BorderLayout());
        canvas = new JPanel();
        canvas.setSize(1400, 1000);
        canvas.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        askFileName = new JLabel("Click Button to save current Data. MAKE SURE TO ADD .csv TO FILENAME");
        save = new JButton("Save File");
        save.setSize(100, 100);
        save.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 0;
        canvas.add(askFileName, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridx = 2;
        gbc.gridy = 0;
        canvas.add(save,gbc);
        this.add(canvas, BorderLayout.CENTER);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        final JFileChooser fc = new JFileChooser();
        //Name of ArrayLists<VaccineInfo> data-variables
        Object[] columnNames = { "ID", "Last Name", "First Name", "Vaccine Type", "Vaccine Date", "Vaccine Location" };
        //Opens SaveDialog
        if (infoList.isEmpty()){
            JOptionPane.showMessageDialog(null, "Error: Nothing to be saved.");
            return;
        }
        int returnVal = fc.showSaveDialog(parent);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            //File to be written on
            File file = fc.getSelectedFile();
            for(int cnt = 0; cnt < infoList.size(); cnt++){
                infoList.get(cnt).print();
            }
            try {
                //writes ArrayLists<VaccineInfo> data-variables  names to file (csv)
                ArrayList<VaccineInfo> model = infoList;
                FileWriter csv = new FileWriter(file);
                for (int i = 0; i < columnNames.length; i++) {

                    csv.write(columnNames[i] + "");
                    if (i + 1 != columnNames.length) {
                        csv.write(",");
                    }
                }

                csv.write("\n");
                //Writes Each dataVariable of VaccineInfo to file
                for (int i = 0; i < model.size(); i++) {

                    try {
                        csv.write(model.get(i).getId() + "");
                        csv.write(",");
                        csv.write(model.get(i).getLastName());
                        csv.write(",");
                        csv.write(model.get(i).getFirstName());
                        csv.write(",");
                        csv.write(model.get(i).getvType());
                        csv.write(",");
                        csv.write(model.get(i).getvDate());
                        csv.write(",");
                        csv.write(model.get(i).getvLocation());
                    } catch (NullPointerException exception) {

                    }

                    csv.write("\n");
                }
                //closes file
                csv.close();
                JOptionPane.showMessageDialog(null, "Successfully saved data!");
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            //User Cancels or Closes SaveDialog
        } else if (returnVal == JFileChooser.CANCEL_OPTION) {
            JOptionPane.showMessageDialog(null, "User cancelled operation. No file was saved.");
            //The save process did not complete successfully
        } else if (returnVal == JFileChooser.ERROR_OPTION) {

            JOptionPane.showMessageDialog(null, "An error occurred. No file was saved.");
            //UnKnown error
        } else {
            JOptionPane.showMessageDialog(null, "Unknown operation occurred.");
        }
    }
}