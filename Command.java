package charis.com.smartremotecontrol;


/**
 * Created by Charis on 04-Jun-16.
 */
public interface Command {

    String executeUDP(String code);
    String executeTCP(String code);

}
