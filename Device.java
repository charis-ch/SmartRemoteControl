package charis.com.smartremotecontrol;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Charis on 10-Jun-16.
 */
public class Device implements Serializable {
    private static final long serialVersionUID = 454897646;
    String name;

    Map<BUTTON, String> commands;


    public Device(String name) {

        this.name = name;

        commands = new HashMap<BUTTON, String>();

    }

    public void insertCommand(BUTTON key, String value) {


        this.commands.put(key, value);

    }

    public String getCommand(BUTTON key) {


        return this.commands.get(key);
    }

    public void printDevice(){



        System.out.println("\n\nName of device:\t"+this.name+"\n\n");



        for ( Map.Entry<BUTTON, String> entry : commands.entrySet()) {
            System.out.print(entry.getKey()+":\t");
            System.out.println(entry.getValue());

        }
/*
        Collection c = this.commands.values();
        Iterator itr = c.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
*/


    }

    public static void main(String args[]) {

        String current_path = System.getProperty("user.dir");

        File folder = new File(current_path);
        File[] listOfFiles = folder.listFiles();
        int length = listOfFiles.length;
        BUTTON curCommnand;
        String filename = "";
        Device current = null;

        File f = new File(current_path+"/ser");
        if (!(f.exists()) || !(f.isDirectory())) {
            f.mkdir();
            System.out.println("Folder ser has been created!!!");
        }

        for (int i = 0; i < length; i++) {
            if (listOfFiles[i].isFile())if((filename = listOfFiles[i].getName()).endsWith(".txt")) {
                System.out.println("Serialize file " + (filename = listOfFiles[i].getName()));

                try {
                    BufferedReader reader = new BufferedReader(new FileReader(filename));
                    String line;

                    if ((line = reader.readLine()) != null)
                        current = new Device(line);
                    while ((line = reader.readLine()) != null) {
                        curCommnand = getButton(Integer.parseInt(line));
                        if ((line = reader.readLine()) != null)

                            current.insertCommand(curCommnand, line);

                    }
                    reader.close();


                    try {
                        filename=filename.replace(".txt","");
                        FileOutputStream fileOut =
                                new FileOutputStream(current_path + "/ser/" +filename.toLowerCase()+ ".ser");
                        ObjectOutputStream out = new ObjectOutputStream(fileOut);
                        out.writeObject(current);
                        out.close();
                        fileOut.close();
                        System.out.printf("Serialized data "+filename+".ser is saved in current directory\n");
                    } catch (IOException error) {
                        error.printStackTrace();
                    }

                } catch (Exception e) {
                    System.err.format("Exception occurred trying to read '%s'.", filename);
                    e.printStackTrace();

                }


            } else if (listOfFiles[i].isDirectory()) {
                System.out.println("Directory " + listOfFiles[i].getName());
            }

        }



        Device e = null;
        try
        {
            FileInputStream fileIn = new FileInputStream("C:/Users/Charis/Desktop/Encode ir file/ser/SYNAPS.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            e = (Device) in.readObject();
            in.close();
            fileIn.close();
        }catch(IOException i)
        {
            i.printStackTrace();
            return;
        }catch(ClassNotFoundException c)
        {
            System.out.println("Employee class not found");
            c.printStackTrace();
            return;
        }

        e.printDevice();

    }

    public static BUTTON getButton(int number) {


        return BUTTON.values()[number];

    }





}
