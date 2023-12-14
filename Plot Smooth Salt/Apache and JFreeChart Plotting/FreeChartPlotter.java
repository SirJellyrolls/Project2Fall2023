package libGraph;
import org.jfree.chart.*;
import org.jfree.data.xy.*;
import org.jfree.chart.plot.PlotOrientation;
import java.util.ArrayList;
import org.apache.commons.math3.distribution.*;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;


public class FreeChartPlotter {
ArrayList<Double> xSet;
ArrayList<Double> ySet;

public FreeChartPlotter(){
    xSet=new ArrayList();
    ySet = new ArrayList();
}
    public  void plotNorm() {
        XYSeries series = new XYSeries("y = x^2");
        
        for (double x = 0; x <= 10; x=x+.5) {
            series.add(x,( x * x));
        }
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        JFreeChart chart = ChartFactory.createXYLineChart("y = x^2","x", "y",dataset);//creating a chart
        ChartFrame frame = new ChartFrame("Good Ol Og", chart);//set window title
        frame.pack();
        frame.setVisible(true);
    }

    public  void plotSalt() {
        XYSeries series = new XYSeries("y = x^2 (w/ salt)");

        UniformRealDistribution uniform = new UniformRealDistribution(0, 10);
        for (double x = 0; x <= 10; x=x+.5) {
            double newX=x+uniform.sample();
            double newY=(x *x)+uniform.sample();
            series.add(newX,newY);
            xSet.add(newX);
            ySet.add(newY);
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        JFreeChart chart = ChartFactory.createXYLineChart("y = x^2 Salted","x", "y",dataset);

        ChartFrame frame = new ChartFrame("Salty :P", chart);
        frame.pack();
        frame.setVisible(true);
    }

    public  void plotSmooth() {
        XYSeries series = new XYSeries("y = x^2 (smoothed salt)");
        DescriptiveStatistics stat=new DescriptiveStatistics();
        stat.setWindowSize(3);
        ArrayList<Double >smoothX=new ArrayList();
        ArrayList<Double >smoothY=new ArrayList();

        for (double xval:xSet) {
            stat.addValue(xval);
            smoothX.add(stat.getMean());
        }
        for (double yval:ySet) {
            stat.addValue(yval);
            smoothY.add(stat.getMean());
        }

        for(int i =0;i<smoothX.size();i++){
            series.add(smoothX.get(i),smoothY.get(i));
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        JFreeChart chart = ChartFactory.createXYLineChart("y = x^2 salty but now smooth","x", "y",dataset);

        ChartFrame frame = new ChartFrame("Smoothie", chart);
        frame.pack();
        frame.setVisible(true);
    }

    public void runTest(){
        
        plotNorm();
        plotSalt();
        plotSmooth();
    }

}
