package augusto.devgridteste.wekatest.client.utils;

import augusto.devgridteste.wekatest.client.model.ClientRequest;
import com.google.gson.Gson;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Tools {

    /**
     *
     * @param src (path to file)
     */
    public void readFile(String src) {

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(src));
            String row;

            while ((row = br.readLine()) != null) {

                String activePower = getValueRow("Active=", row, ";");
                String reactivePower = getValueRow("Reactive=", row, ";");
                String apparentPower = getValueRow("Appearent=", row, ";");
                String current = getValueRow("Current=", row, ";");
                String voltage = getValueRow("Voltage=", row, ";");

                String transients = getValueRow("Peaks:", row, "FFT Re");
                String[] transientList = transients.split(";");

                Double transient1 = Double.valueOf(transientList[0]);
                Double transient2 = Double.valueOf(transientList[1]);
                Double transient3 = Double.valueOf(transientList[2]);

                ClientRequest clientRequest = new ClientRequest(activePower, reactivePower, apparentPower, current, voltage, transient1, transient2, transient3);

                String urlRequest = row.substring(row.indexOf("http:"));

                Gson gson = new Gson();

                sendPost(urlRequest, gson.toJson(clientRequest));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     *
     * @param key (beginning of the word to be separated)
     * @param row (text content the values)
     * @param separetor (end of the word to be separated)
     * @return
     */
    public static String getValueRow(String key, String row, String separetor) {
        int keySize = key.length();
        String value;
        value = row.substring(row.indexOf(key) + keySize);
        return value.substring(0, value.indexOf(separetor));
    }

    /**
     *
     * @param url (url to connection)
     * @param json (content to be sended)
     * @throws Exception
     */
    public static void sendPost(String url, String json) throws Exception {

        HttpURLConnection request = (HttpURLConnection) new URL(url).openConnection();

        request.setDoOutput(true);
        request.setDoInput(true);

        request.setRequestProperty("Content-Type", "application/json");

        request.setRequestMethod("POST");

        try (OutputStream outputStream = request.getOutputStream()) {
            outputStream.write(json.getBytes("UTF-8"));
        }

        request.connect();

        int response = request.getResponseCode();

        if (response == 200) {
            String jsonResponse = new Scanner(request.getInputStream()).next();
            if(!jsonResponse.equals("added")){
                System.out.println(jsonResponse);
            }
        }
    }
}
