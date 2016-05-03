import org.apache.commons.io.FileUtils;
import org.json.CDL;
import org.json.JSONArray;
import ucar.ma2.Array;
import ucar.ma2.InvalidRangeException;
import ucar.nc2.NCdumpW;
import ucar.nc2.NetcdfFile;
import ucar.nc2.Variable;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by jorik on 3-5-2016.
 */
public class RwsPrototype {

    public static void main(String[] args) {
        RwsPrototype rwsPrototype = new RwsPrototype();
    }

    public RwsPrototype() {
        //read cdl file
        String filename = "maps1d_rws_prediction_201604151600.nc";
        NetcdfFile ncfile = null;
        try {
            ncfile = NetcdfFile.open(filename);
            loadNodesFromNCFile(ncfile);
        } catch (IOException ioe) {
            //log("trying to open " + filename, ioe);
        } finally {
            if (null != ncfile) try {
                ncfile.close();
            } catch (IOException ioe) {
                //log("trying to close " + filename, ioe);
            }
        }


        //load in nodes with id, lat and lon


    }

    private void loadNodesFromNCFile(NetcdfFile ncfile) {
        Map<Integer,Station> stations = loadStations(ncfile);
        for (Map.Entry<Integer, Station> entry : stations.entrySet()) {
            Integer key = entry.getKey();
            Station value = entry.getValue();
            System.out.println(value);
            // ...
        }
    }
    private Map<Integer,Station> loadStations(NetcdfFile ncfile){
        Map<Integer,Station> stationsMap = new HashMap<>();
        Array names = getVarFromNc("nodenames",ncfile);
        Array lats = getVarFromNc("lat",ncfile);
        Array lons = getVarFromNc("lon",ncfile);
        System.out.println(lats.getSize());
        for (int index = 0; index < lats.getSize(); index++) {
            String name = "test";
            Float lat = lats.getFloat(index);
            Float lon = lons.getFloat(index);
            stationsMap.put(index,new Station(index,name,lat,lon,null));
        }
        return stationsMap;
    }
    private Array getVarFromNc(String varName,NetcdfFile ncfile){
        Variable v = ncfile.findVariable(varName);
        if (null == v) return null;
        try {
            return v.read();
        } catch (IOException ioe) {
            //log("trying to read " + varName, ioe);
        }
        return null;
    }

}
