/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hierarchical.clustering.average.linkage;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author kmdr7
 */
public class ClusterAnalysis
{   
    public void analyzeCPI(ArrayList<Data> awal, ArrayList<Data> akhir)
    {
        ArrayList<Integer> clusterAwal = new ArrayList();
        ArrayList<Integer> clusterAkhir = new ArrayList();
        ArrayList<HashMap<String, Double>> centroidAwal = new ArrayList();
        ArrayList<HashMap<String, Double>> centroidAkhir = new ArrayList();
        ArrayList<HashMap<String, Integer>> cluster = new ArrayList();
        
        // distinct label
        for ( Data data : awal )
        {
            if ( !clusterAwal.contains(Integer.parseInt(data.getData().get("label"))) )
            {
                clusterAwal.add(Integer.parseInt(data.getData().get("label")));
            }
        }
        
        // tentukan centroid cluster awal
        for ( int i=0; i < clusterAwal.size(); i++ )
        {
            cluster.clear();
            for ( Data data : awal )
            {
                if ( Integer.parseInt(data.getData().get("label")) == clusterAwal.get(i) )
                {
                    HashMap<String, Integer> map = new HashMap();
                    map.put("x", Integer.parseInt(data.getData().get("x")));
                    map.put("y", Integer.parseInt(data.getData().get("y")));
                    cluster.add(map);
                }
            }
            
            // hitung
            double x = 0, y = 0, avgX = 0, avgY = 0;
            for (HashMap<String, Integer> data : cluster)
            {
                x += (double) data.get("x");
                y += (double) data.get("y");
            }
            
            avgX = x/cluster.size();
            avgY = y/cluster.size();
            
            HashMap<String, Double> ctr = new HashMap();
            ctr.put("x", avgX);
            ctr.put("y", avgY);
            centroidAwal.add(ctr);
        }
        
        //===========================================================================================
        
        // distinct label
        for ( Data data : akhir )
        {
            if ( !clusterAkhir.contains(Integer.parseInt(data.getData().get("label"))) )
            {
                clusterAkhir.add(Integer.parseInt(data.getData().get("label")));
            }
        }
        
        // tentukan centroid cluster akhir
        for ( int i=0; i < clusterAkhir.size(); i++ )
        {
            cluster.clear();
            for ( Data data : akhir )
            {
                if ( Integer.parseInt(data.getData().get("label")) == clusterAkhir.get(i) )
                {
                    HashMap<String, Integer> map = new HashMap();
                    map.put("x", Integer.parseInt(data.getData().get("x")));
                    map.put("y", Integer.parseInt(data.getData().get("y")));
                    cluster.add(map);
                }
            }
            
            // hitung
            double x = 0, y = 0, avgX = 0, avgY = 0;
            for (HashMap<String, Integer> data : cluster)
            {
                x += (double) data.get("x");
                y += (double) data.get("y");
            }
            
            avgX = x/cluster.size();
            avgY = y/cluster.size();
            
            HashMap<String, Double> ctr = new HashMap();
            ctr.put("x", avgX);
            ctr.put("y", avgY);
            centroidAkhir.add(ctr);
        }
        
        // bandingkan masing" selisih centroid
        
        double selisih = 0, tempSelisih1 = 0, tempSelisih2 = 0;
        for (int i=0; i<clusterAwal.size(); i++)
        {
            tempSelisih1 = 0;
            tempSelisih2 = 0;
            
            tempSelisih1 += Math.abs( centroidAwal.get(i).get("x") - centroidAkhir.get(i).get("x") );
            tempSelisih1 += Math.abs( centroidAwal.get(i).get("y") - centroidAkhir.get(i).get("y") );
            System.out.println("Selisih : " + tempSelisih1);
            
            tempSelisih2 += Math.abs( centroidAwal.get(i).get("x") - centroidAkhir.get(i).get("y") );
            tempSelisih2 += Math.abs( centroidAwal.get(i).get("y") - centroidAkhir.get(i).get("x") );
            System.out.println("Selisih : " + tempSelisih2);
            
            if ( tempSelisih1 < tempSelisih2 ){
                selisih += tempSelisih1;
            } else {
                selisih += tempSelisih2;
            }
        }
        
        selisih /= clusterAwal.size();
        
        // jumlahkan error
        System.out.println("ERRORRRRR : " + selisih);
    }
}
