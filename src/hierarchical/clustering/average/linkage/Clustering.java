/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hierarchical.clustering.average.linkage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author kmdr7
 */
public class Clustering
{
    private BufferedReader reader;
    private String currentLine;
    private String[] values;
    
    private int k = 4;
    private int clusterSize;
    private Scanner scan;
    private ArrayList<Integer> cluster;
    private ArrayList<Data> data;
    
    public void setK()
    {    
        this.scan = new Scanner(System.in);
        System.out.print("Masukkan jumlah cluster yang diinginkan : ");
        this.k = scan.nextInt();
    }
    
    public int getK(){
        return this.k;
    }
    
    public void readFile(String file)
    {
        int itr = 0;
        this.data = new ArrayList<>();
        ArrayList<String> header = new ArrayList();
        HashMap<String, String> map;
        
        try
        {
            reader = new BufferedReader(new FileReader(file));
            while ( (currentLine = reader.readLine()) != null )
            {
                values = currentLine.split(",", 0);
                map = new HashMap<>();
                
                // tambahkan header untuk HashMap
                if ( itr == 0 )
                {
                    header.addAll(Arrays.asList(values));
                }
                else
                {
                    for (int i = 0; i<header.size(); i++)
                    {
                        map.put(header.get(i), values[i]);
                    }
                    map.put("label", String.valueOf(itr));
                    this.data.add(new Data(map));
                }
                
                itr++;
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally 
        {
            try {
                if ( reader != null ) reader.close();
                clusterSize = this.data.size();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    private double calculateDistance(Data dari, Data ke)
    {
        Double dari_x = Double.parseDouble(dari.getData().get("x"));
        Double ke_x = Double.parseDouble(ke.getData().get("x"));
        Double dari_y = Double.parseDouble(dari.getData().get("y"));
        Double ke_y = Double.parseDouble(ke.getData().get("y"));
        return Math.sqrt(Math.pow((dari_x - ke_x), 2) + Math.pow((dari_y - ke_y), 2));  
    }
    
    private double calculateClusterDistance(ArrayList<Data> cluster1, ArrayList<Data> cluster2)
    {
        int itr = 0;
        double minDistance = this.calculateDistance(cluster1.get(0), cluster2.get(0));
        double distance = 0, totalDistance = 0;
        
        for ( int i=0; i<cluster1.size(); i++ )
        {
            for ( int j=0; j<cluster2.size(); j++ )
            {
                distance = calculateDistance(cluster1.get(i), cluster2.get(j));
                totalDistance += distance;
                itr++;
                if ( minDistance > distance )
                {
                    minDistance = distance;
                }
            }
        }       
        return totalDistance/itr;
    }
    
    // Cek label cluster yang dimiliki semua data
    private void getCluster(ArrayList<Data> data)
    {
        cluster = new ArrayList();
        for ( Data item : data )
        {
            if ( !cluster.contains( Integer.parseInt(item.getData().get("label")) ) )
            {
                cluster.add(
                    Integer.parseInt( item.getData().get("label") )
                );
            }
        }
        clusterSize = cluster.size();
    }
    
    private ArrayList<Data> cekDataCluster(ArrayList<Data> data, int cluster)
    {
        ArrayList<Data> result = new ArrayList();
        for (Data item : data)
        {
            if ( Integer.parseInt(item.getData().get("label")) == cluster )
            {
                result.add(item);
            }
        }
        return result;
    }
    
    public void go()
    {
        while ( clusterSize-1 > k )
        {
            this.getCluster(data);
            double minimal = 0;
            int label1=0, label2=0;
            ArrayList<Data> cluster1, cluster2;
            
            for ( int i=0; i<cluster.size(); i++ )
            {
                for ( int j=cluster.size()-1; j>i; j-- )
                {
                    cluster1 = cekDataCluster(data, cluster.get(i));
                    cluster2 = cekDataCluster(data, cluster.get(j));
                    if ( minimal == 0 || minimal > calculateClusterDistance(cluster1, cluster2) )
                    {
                        minimal = calculateClusterDistance(cluster1, cluster2);
                        label1 = cluster.get(i);
                        label2 = cluster.get(j);
                    }
                }
            }
            
            for (Data item : data)
            {
                if ( Integer.parseInt(item.getData().get("label")) == label2 )
                {
                    item.getData().put("label", String.valueOf(label1));
                }
            }
        }
    }
    
    public void print()
    {
        Collections.sort(this.data);
        for ( Data data : this.data ){
            System.out.print("X = " + data.getData().get("x"));
            System.out.print(" | Y = " + data.getData().get("y"));
            System.out.println(" | Label = " + data.getData().get("label"));
        }
    }
    
    public ArrayList<Data> getData(){
        return this.data;
    }
}
