//command to scan tcp ports (with multithreading)
import org.apache.commons.collections.map.MultiValueMap;
import org.apache.commons.net.SocketClient;
import org.json.JSONObject;

import java.io.*;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ScanCommandWithThreads implements Command {
    AbstractLogger logger = new ThreadLogger();
    @Override
    public void execute(MultiValueMap pool, String threads) {
        ExecutorService executorService = Executors.newFixedThreadPool(Integer.parseInt(threads));
        List<Future<String>> futures = new ArrayList<>();
        JSONObject hosts = new JSONObject();
        System.out.println("=========\nScanning...\n=========");


        for (Object key : pool.keySet()) {
            for (Object value : pool.getCollection(key)) {
                Callable<String > callable = new Callable<>() {
                    @Override
                    public String call() {
                        SocketClient socketClient = new MySocketClient();
                        socketClient.setSocketFactory(new MySocketFactory());
                        socketClient.setConnectTimeout(500);

                        try {
                            socketClient.connect(((String) key), Integer.valueOf((String) value));
                            socketClient.disconnect();
                            return key+" "+value+" "+true;
                        } catch (SocketException ee) {
                            return key+" "+value+" "+false;
                        } catch (IOException e) {
                            return key+" "+value+" "+false;
                        }
                    }
                };

                Future<String> f = executorService.submit(callable);
                futures.add(f);
            }
        }
        String log="";
        JSONObject all = new JSONObject();
        for (Object ip : pool.keySet()){
            for (Future fut : futures) {
                try {
                    String [] s = fut.get().toString().split(" ");
                    if (s[0].equals(ip)) {
                        all.put("port"+s[1], s[2]);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            hosts.put((String)ip,all);
            log+=hosts;
            hosts.remove((String)ip);
    }

        logger.openLog();
        logger.log(log);
        logger.closeLog();

        executorService.shutdown();


    }
}
