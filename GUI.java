import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class GUI extends JFrame implements ChangeListener{
    JTabbedPane tp;
    AboutPanel abtPage;
    AddPanel addPage;
    LoadPanel loadPage;
    SavePanel savePage;
    VisPanel visPage;
    ImageIcon image;
    private ArrayList<VaccineInfo> infoList;

    public GUI() {
        // set GUI configurations
        this.setTitle("COVID Vaccinations");
        this.setVisible(true);
        this.setSize(1400, 750);
        image = new ImageIcon("src/Logo.png");
        this.setIconImage(image.getImage());
        //ArrayList of VaccineInfo objects
        infoList = new ArrayList<VaccineInfo>();
        // Adding to GUI
        tp = new JTabbedPane();
        tp.addChangeListener(this);
        abtPage = new AboutPanel();
        addPage = new AddPanel(infoList);
        loadPage = new LoadPanel(infoList);
        savePage = new SavePanel(infoList);
        visPage = new VisPanel(infoList);
        tp.addTab("About", abtPage);
        tp.addTab("Add Data", addPage);
        tp.addTab("Load Data", loadPage);
        tp.addTab("Save Data", savePage);
        tp.addTab("Visualize Data", visPage);
        tp.setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().add(tp);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        loadPage.action();
        addPage.action();
        visPage.create();
    }
}