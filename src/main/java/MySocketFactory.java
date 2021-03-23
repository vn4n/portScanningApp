//socket factory witch return Socket instance (difference possibilities)
import javax.net.SocketFactory;
import java.io.IOException;
import java.lang.reflect.Member;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class MySocketFactory extends SocketFactory {
    @Override
    public Socket createSocket() throws IOException {
        return new Socket();
    }

    @Override
    public Socket createSocket(String s, int i) throws IOException, UnknownHostException {
        return new Socket(s,i);
    }

    @Override
    public Socket createSocket(String s, int i, InetAddress inetAddress, int i1) throws IOException, UnknownHostException {
        return new Socket(s,i,inetAddress,i1);
    }

    @Override
    public Socket createSocket(InetAddress inetAddress, int i) throws IOException {
        return new Socket(inetAddress,i);
    }

    @Override
    public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress1, int i1) throws IOException {
        return new Socket(inetAddress,i,inetAddress1,i1);
    }
}
