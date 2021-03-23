
//class witch uses to create and keep pool of IP/port values to scan
import org.apache.commons.collections.map.MultiValueMap;

import java.util.*;

public class PoolCreator {

    public MultiValueMap setPool(String listOfIp, String listOfPorts){

//--------------------- creating pool of IP
        Set<String> ip = new HashSet<>();
        String [] tmp = listOfIp.split(",");

        for (String str:tmp){

            if (str.contains("-")){

                int idex = str.indexOf("-");

                String [] points = str.split("\\.");
                String [] range = points[points.length-1].split("-");
                int difference = Integer.parseInt(range[1]) - Integer.parseInt(range[0]);
                String stringIP="";
                for (int j=0;j<=difference;j++) {
                    for (int i = 0; i < points.length - 1; i++) {
                        stringIP += points[i]+".";
                    }
                    stringIP+=(Integer.parseInt(range[0])+j);
                    ip.add(stringIP);
                    stringIP="";
                }

            }else if(str.length()>0){
                ip.add(str);
            }

        }
//--------------------- creating pool of ports
        Set<String> ports = new HashSet<>();

        String [] tmp1 = listOfPorts.split(",");

        for (String str1:tmp1 ){

            if (str1.contains("-")){

                String [] p = str1.split("-");

                int difference1 = Integer.parseInt(p[1])-Integer.parseInt(p[0]);
                String stringPorts = "";
                for (int j=0;j<=difference1;j++){
                    stringPorts+=Integer.parseInt(p[0])+j;
                    ports.add(stringPorts);
                    stringPorts="";
                }

            }else if(str1.length()>0){
                ports.add(str1);
            }

        }

        MultiValueMap readyIpPortPool = new MultiValueMap();
        for (String key : ip) {
            for (String value : ports) {
                readyIpPortPool.put(key, value);
            }
        }
        return readyIpPortPool;
    }
}
