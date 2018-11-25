package charis.com.smartremotecontrol;


/**
 * Created by Charis on 04-Jun-16.
 */
public interface ArduinoCommunicator extends Command{

    enum TRANSPORT{TCP,UCP}

    String defaultUrl="http://192.168.10.100";

   /// String udp;

}
