import javax.swing.*;
import java.awt.*;

public class AboutPanel extends JPanel{

    private JLabel teamNum;
    private JLabel text1;
    private JLabel name1;
    private JLabel name2;
    private JLabel name3;
    private JLabel name4;
    private JLabel name5;
    private JTextArea info;
    Font font;
    int count = 0;
    public AboutPanel(){
        // About Panel Configurations
        this.setSize(600,600);
        this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        this.setLayout(null);
        this.setBackground(Color.WHITE);

        //
        // Team Information
        teamNum = new JLabel("Team Project Group 48");
        text1 = new JLabel("Team Members:");
        name1 = new JLabel("Carlos");
        name2 = new JLabel("Paul");
        name3 = new JLabel("Natasha");
        name4 = new JLabel("Ryan");
        name5 = new JLabel("Gabriel");
        font = new Font("Times New Roman", Font.PLAIN, 20);
        info = new JTextArea("GUI Application Description:\nAdd Data: Input valid information to the empty fields and click submit. Table displaying input" +
                "will be loaded\n" +
                "Load Data: Enter the path to a csv file and click submit to load in data. Table displaying input will be loaded\n" +
                "Save Data: Only available for use once data has been inputted. Click on the button the choose a location to save a new csv file\n" +
                "Visualize Data: Only available for use once data has been inputted. Charts displaying data information will be automatically loaded.\n");
        teamNum.setBounds(30,0,200,50);
        text1.setBounds(30,80,200,20);
        name1.setBounds(30,110,200,20);
        name2.setBounds(30,140,200,20);
        name3.setBounds(30,170,200,20);
        name4.setBounds(30,200,200,20);
        name5.setBounds(30,230,200,20);
        info.setBounds(400,20,400,400);
        info.setLineWrap(true);
        info.setWrapStyleWord(true);
        info.setEditable(false);
        info.setFont(font); teamNum.setFont(font); text1.setFont(font); name1.setFont(font); name2.setFont(font);
        name3.setFont(font); name4.setFont(font); name5.setFont(font);
        this.add(teamNum);
        this.add(text1);
        this.add(name1);
        this.add(name2);
        this.add(name3);
        this.add(name4);
        this.add(name5);
        this.add(info);
        //
    }
}