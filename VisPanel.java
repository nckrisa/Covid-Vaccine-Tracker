import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.IntervalBarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class VisPanel extends JPanel implements MouseListener {
	private GridBagConstraints gbc;
	private JPanel canvas;
	int chartWidth;
	ArrayList <Integer> vTypeCount = new ArrayList<Integer>();
	ArrayList <String> vType = new ArrayList<String>();
	private ArrayList<VaccineInfo> infoList;
	public VisPanel(ArrayList<VaccineInfo> l){
		this.setLayout(new FlowLayout());
		this.setPreferredSize(new Dimension(1400,800));
		infoList = l;
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(10,10,10,10);
		canvas = new JPanel();
		canvas.setPreferredSize(new Dimension(1400,600));
		GridLayout layout = new GridLayout(0,2);
		layout.setHgap(10);
		layout.setVgap(40);
		canvas.setLayout(layout);
		this.addMouseListener(this);
		add(canvas);
		create();
	}
	public void create(){
		if (infoList.isEmpty()){
			return;
		}
		canvas.removeAll();
		CategoryDataset barDataset = createBarDataset();
		JFreeChart barChart = createBarChart(barDataset);
		ChartPanel barChartPanel = new ChartPanel(barChart);
		barChartPanel.setBackground(Color.WHITE);

		if (vType.isEmpty()){
			barChartPanel.setPreferredSize(new Dimension(1200,450));
		}
		else{
			chartWidth = 100 * vType.size();
			barChartPanel.setPreferredSize(new Dimension(chartWidth,450));
		}
		JScrollPane barScrollPane = new JScrollPane(barChartPanel);
		barScrollPane.setPreferredSize(new Dimension(500,500));
		barScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		gbc.gridx = 0; gbc.gridy = 0;
		canvas.add(barScrollPane);


		PieDataset pieDataset = createPieDataset();
		JFreeChart pieChart = createPieChart(pieDataset);
		ChartPanel pieChartPanel = new ChartPanel(pieChart);
		pieChartPanel.setPreferredSize(new Dimension(500,500));
		pieChartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		pieChartPanel.setBackground(Color.WHITE);
		gbc.gridx = 1; gbc.gridy = 0;
		canvas.add(pieChartPanel);
		this.add(canvas);
		revalidate();
	}

	private CategoryDataset createBarDataset() { // creates data set for bar chart
		var barDataset = new DefaultCategoryDataset();
		vType.clear();
		vTypeCount.clear();
		//read through infoList
		for (int count = 0; count < infoList.size(); count++){
			String type = infoList.get(count).getvType();
			if (vType.contains(type)){
				int index = vType.indexOf(type);
				vTypeCount.set(index, vTypeCount.get(index) + 1);
			}
			else {
				vType.add(type);
				vTypeCount.add(1);
			}
		}
		//print to console and add to barDataset
		for (int j = 0; j < vTypeCount.size(); j++) {
			barDataset.setValue(vTypeCount.get(j), "Number of Vaccines", vType.get(j));
		}
		return barDataset;
	}

	private JFreeChart createBarChart(CategoryDataset barDataset) { // returns bar chart
		JFreeChart barChart = ChartFactory.createBarChart (
				"Number of Vaccines Administered per Vaccine Type",
				"Vaccine Type",
				"Number of Vaccines Administered",
				barDataset,
				PlotOrientation.VERTICAL,
				false, true, false);
		return barChart;
	}

	// create Pie Chart Dataset
	private PieDataset createPieDataset() {
		DefaultPieDataset pieDataset = new DefaultPieDataset();
		int vTotCount = 0;
		ArrayList <Integer> vLocCount = new ArrayList<Integer>();
		ArrayList <String> vLoc = new ArrayList<String>();
		//read through infoList
		for (int i = 0; i < infoList.size(); i++) {
				//get nextLoc
				String nextLoc = infoList.get(i).getvLocation();
				if(vLoc.contains(nextLoc)) {
					int index = vLoc.indexOf(nextLoc);
					vLocCount.set(index, vLocCount.get(index) + 1);
					vTotCount++;
				}
					else {
					vLoc.add(nextLoc);
					vLocCount.add(1);

			}
		}
		//print to console and add to barDataset
		for (int j = 0; j < vLocCount.size(); j++) {
			pieDataset.setValue(vLoc.get(j), vLocCount.get(j));
		}

		return pieDataset;
	}

	private JFreeChart createPieChart(PieDataset pieDataset) {
		JFreeChart pieChart = ChartFactory.createPieChart(
				"Vaccine Distribution",   	// chart title
				pieDataset,          			// data
				true,             		// include legend
				true,
				false);

		return pieChart;
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == this){
		if (infoList.size() == 0){
			JOptionPane.showMessageDialog(null, "Nothing to display yet. Please add data first!");
			return;
		}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}