import org.apache.commons.io.FileUtils;
import org.json.CDL;
import org.json.JSONArray;
import ucar.ma2.Array;
import ucar.ma2.InvalidRangeException;
import ucar.nc2.NCdumpW;
import ucar.nc2.NetcdfFile;
import ucar.nc2.Variable;

import java.io.*;

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
        String varName = "waterlevel";
        Variable v = ncfile.findVariable(varName);
        if (null == v) return;
        try {
            Array data = v.read("0:2:1, 0:19:1");
            NCdumpW.printArray(data, varName,
                    System.out, null);
        } catch (IOException ioe) {
            //log("trying to read " + varName, ioe);

        } catch (InvalidRangeException e) {
            //log("invalid Range for " + varName, e);
        }
    }

}
