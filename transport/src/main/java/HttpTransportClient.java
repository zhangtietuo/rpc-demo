import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Auther: zhangtietuo
 * @Description:
 * @Date: 2020/4/23 14:00
 */
public class HttpTransportClient implements TransportClient {

    private String url;

    public void connect(Peer peer) {
        this.url = "http://" + peer.getHost() + ":" + peer.getPort();
    }

    public InputStream write(InputStream data) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDefaultUseCaches(false);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.connect();
            IOUtils.copy(data, httpURLConnection.getOutputStream());
            int code = httpURLConnection.getResponseCode();
            if(HttpURLConnection.HTTP_OK ==  code) {
                return httpURLConnection.getInputStream();
            } else {
                return httpURLConnection.getErrorStream();
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

    }

    public void close() {

    }
}
