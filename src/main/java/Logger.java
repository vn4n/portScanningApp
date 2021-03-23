import org.json.JSONObject;
//concrete logger witch uses in single thread processing

import java.io.*;

public class Logger implements AbstractLogger{
    private Writer writer;
    private JSONObject jsHost;
    private JSONObject jsPort;
    private String IP="";

    public void log(String ipPortAvailable){
        String [] pool = ipPortAvailable.split(" ");
        String tmp=null;
        String toWrite="";
        String ip=pool[0];
        String port=pool[1];
        boolean isAvailable=Boolean.parseBoolean(pool[2]);
        try {
            JSONObject jsIsAsvailable = new JSONObject();
            if(!ip.equals(IP)){
                if (jsHost!=null&&jsPort!=null) {
                    jsHost.put(IP, jsPort);
                    writer.write(jsHost.toString());
                }
                jsPort = new JSONObject();
                jsHost = new JSONObject();
                IP=ip;
            }
            jsIsAsvailable.put("isAvailable",isAvailable);
            jsPort.put("port"+port, jsIsAsvailable);

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void openLog(){
        try {
            File file = new File(System.getProperty("user.home")+"/log.json");
            if(!file.exists()){
              if(!file.createNewFile())
                  throw new CreatingFileFailed("Creating file error");
            }
             writer = new BufferedWriter(new FileWriter(file));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public void closeLog(){
        try{
            jsHost.put(IP, jsPort);
            writer.write(jsHost.toString());
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

}
