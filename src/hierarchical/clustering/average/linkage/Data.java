/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hierarchical.clustering.average.linkage;

import java.util.HashMap;

/**
 *
 * @author kmdr7
 */
public class Data implements Comparable
{
    private HashMap<String, String> data;

    public Data()
    {
        this.data = new HashMap();
    }
    
    public Data(HashMap data)
    {
        this.data = data;
    }
    
    public HashMap<String, String> getData()
    {
        return this.data;
    }
    
    @Override
    public int compareTo(Object l) {
        int compare = Integer.parseInt(((Data) l).getData().get("label"));
        return Integer.compare(Integer.parseInt(data.get("label")), compare);
    }
}
