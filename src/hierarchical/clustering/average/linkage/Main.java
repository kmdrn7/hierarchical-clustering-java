/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hierarchical.clustering.average.linkage;

import java.util.ArrayList;

/**
 *
 * @author kmdr7
 */
public class Main
{
    public static void main(String[] args)
    {
        String file = "/home/kmdr7/Documents/Kuliah/SEMESTER 5/Machine Learning/Pertemuan 5/Hierarchical Clustering Average Linkage/src/ruspini.csv";
        
        ArrayList<Data> awal;
        ArrayList<Data> akhir;
        
        Clustering clustering = new Clustering();
//        clustering.setK();
        clustering.readFile(file);
        
        awal = clustering.getData();
        clustering.go();
        akhir = clustering.getData();
        
//        clustering.print();
        
        Visualization vis = new Visualization();
        vis.setData(clustering.getData(), clustering.getK());
        vis.show();
       
        ClusterAnalysis clus = new ClusterAnalysis();
        clus.analyzeCPI(awal, akhir);
    }    
}
