/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hierarchical.clustering.average.linkage;

import org.knowm.xchart.internal.chartpart.Chart;

/**
 *
 * @author kmdr7
 */
public interface MyChart <C extends Chart<?, ?>> {
   C getChart();
}
