package augusto.devgridteste.wekatest.client;

import augusto.devgridteste.wekatest.client.utils.Tools;

public class ClientMain {

    public static void main(String[] args) {

        Tools tools = new Tools();
        tools.readFile("events.txt");

    }
}
