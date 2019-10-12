/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hierarchical.clustering.average.linkage;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.style.Styler.LegendPosition;

/**
 *
 * @author kmdr7
 */
public class Visualization implements MyChart<XYChart>
{
    private ArrayList<Data> data;
    private int k;
    
    public void setData(ArrayList<Data> data, int k){
        this.data = data;
        this.k = k;
    }
    
    public void show(){
        XYChart chart = this.getChart();
        new SwingWrapper<>(chart).displayChart();
    }
    
    @Override
    public XYChart getChart() {
        // Create Chart
        XYChart chart = new XYChartBuilder().width(1000).height(700).build();

        // Customize Chart
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Scatter);
        chart.getStyler().setChartTitleVisible(false);
        chart.getStyler().setLegendPosition(LegendPosition.InsideSW);
        chart.getStyler().setMarkerSize(10);
//        chart.getStyler().setLegendVisible(false);

        // Series
        List<Double> xData;
        List<Double> yData;
        
        // Distinct cluster label
        ArrayList<Integer> cluster = new ArrayList();
        for ( Data data : this.data )
        {
            if ( !cluster.contains(Integer.parseInt(data.getData().get("label"))) )
            {
                cluster.add(Integer.parseInt(data.getData().get("label")));
            }
        }
        
        Random rand = new Random();
        Color color;
        
        for ( int i=0; i < cluster.size(); i++ )
        {
            xData = new LinkedList();
            yData = new LinkedList();
            color = new Color(rand.nextInt(0xFFFFFF));
            
            for ( Data data : this.data )
            {
                if ( Integer.parseInt(data.getData().get("label")) == cluster.get(i) )
                {
                    xData.add( Double.parseDouble(data.getData().get("x")) );
                    yData.add( Double.parseDouble(data.getData().get("y")) );
                }
            }
            
            XYSeries series = chart.addSeries("Label "+i+1, xData, yData);
            series.setMarkerColor(color);
        }
        
//        for (int i=0; i < this.data.size()-1; i++){
//            xData.add( Double.parseDouble(this.data.get(i).getData().get("x")) );
//            yData.add( Double.parseDouble(this.data.get(i).getData().get("y")) );
//        }
        
//        color = new Color(rand.nextInt(0xFFFFFF));
//        XYSeries series = chart.addSeries("XY", xData, yData);
//        series.setMarkerColor(color);
        
        return chart;
    }
    
}
