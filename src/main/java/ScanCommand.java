//command to scan tcp ports (without multithreading)

import org.apache.commons.collections.map.MultiValueMap;
import org.apache.commons.net.SocketClient;

import java.io.*;
import java.net.SocketException;

public class ScanCommand implements Command{

    @Override
    public void execute(MultiValueMap pool, String thread) {
        System.out.println("=========\nScanning...\n=========");
        AbstractLogger logger = new Logger();
        SocketClient socketClient = new MySocketClient();
        socketClient.setSocketFactory(new MySocketFactory());
        socketClient.setConnectTimeout(500);

        logger.openLog();

        for (Object ip : pool.keySet()) {
            for (Object port : pool.getCollection(ip)) {
                try {
                    socketClient.connect(((String) ip), Integer.valueOf((String) port));
                    socketClient.disconnect();
                    logger.log((String)ip+" "+(String)port+" "+true);

                } catch (SocketException ee) {
                    logger.log((String)ip+" "+(String)port+" "+false);

                } catch (IOException e) {
                    logger.log((String)ip+" "+(String)port+" "+false);
                }
            }
        }

        logger.closeLog();
    }
}
