package charis.com.smartremotecontrol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;


import static charis.com.smartremotecontrol.Device.getButton;

public class MainActivity extends AppCompatActivity {

    ImageButton power;
    ImageButton mute;
    ImageButton vol_plus;
    ImageButton vol_minus;
    ImageButton ch_plus;
    ImageButton ch_minus;
    ImageButton ok;
    ImageButton record;
    ImageButton play;
    ImageButton pause;
    ImageButton next;
    ImageButton previous;
    ImageButton fast_forward;
    ImageButton fast_rewind;
    ImageButton zero;
    ImageButton one;
    ImageButton two;
    ImageButton three;
    ImageButton four;
    ImageButton five;
    ImageButton six;
    ImageButton seven;
    ImageButton eight;
    ImageButton nine;

    Map<String, Device> devices = new HashMap<>();
    Device currentDevice;
    Button button;
    private ImageButton zoom;
    private ImageButton sleep;
    private ImageButton exit;
    private ImageButton mode;
    private ImageButton pageup;
    private ImageButton yellow;
    private ImageButton info;
    private ImageButton red;
    private ImageButton green;
    private ImageButton epg;
    private ImageButton blue;
    private ImageButton stop;
    private ImageButton pagedown;
    private ImageButton fav;
 //   private ImageButton band;
    private ImageButton usb;
    private ImageButton sat;
    private ImageButton menu;
    //private ImageButton shift;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//setContentView(R.layout.common_buttons);
        getRaw();
        currentDevice = devices.get("synaps");
        if (currentDevice == null)
            System.out.println("Current device is null");
        else
            System.out.println("Current device set to synaps decoder");

        initiateCommonCommands();
        Device a = devices.get("sonyg88");
        if (a == null) System.out.println("Sony is null");
        else
            a.printDevice();
        a = devices.get("synaps");
        if (a == null) System.out.println("Synaps is null");
        else

            a.printDevice();

        setPanel();

        ///   button = (Button) findViewById(R.id.button);
        vol_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((currentDevice.getCommand(BUTTON.VOLUME_UP)) != null) {
                    Thread a = new Thread(new UdpThread(currentDevice.getCommand(BUTTON.VOLUME_UP)));
                    a.start();
                }
            }
        });

    }

    private void setPanel() {


//
//        TabHost host = (TabHost)findViewById(R.id.tabHost);
//        host.setup();
//
//        //Tab 1
//        TabHost.TabSpec spec = host.newTabSpec("Tab One");
//        spec.setContent(R.id.remoteinterface);
//        spec.setIndicator("Tab One");
//        host.addTab(spec);
//
//        //Tab 2
//        spec = host.newTabSpec("Tab Two");
//        spec.setContent(R.id.remoteinterface);
//        spec.setIndicator("Tab Two");
//        host.addTab(spec);
    }

    private void getRaw() {

        BUTTON curCommnand;
        Field[] fields = R.raw.class.getFields();
        // loop for every file in raw folder

        for (Field field : fields) {

            // Use that if you just need the file name
            String filename = field.getName();

            // Use this to load the file
            try {

                System.out.println(filename);
                InputStream ins = getResources().openRawResource(
                        getResources().getIdentifier(filename,
                                "raw", getPackageName()));

                Device current = new Device(filename);

                BufferedReader reader = new BufferedReader(new InputStreamReader(ins));
                String line;
                reader.readLine();

                while ((line = reader.readLine()) != null) {
                    curCommnand = getButton(Integer.parseInt(line));
                    if ((line = reader.readLine()) != null)

                        current.insertCommand(curCommnand, line);

                }
                reader.close();
                devices.put(filename, current);
                System.out.println("Size=" + devices.size());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void initiateCommonCommands() {

        power = (ImageButton) findViewById(R.id.power);

        vol_plus = (ImageButton) findViewById(R.id.vol_plus);
        vol_minus = (ImageButton) findViewById(R.id.vol_minus);
        ch_plus = (ImageButton) findViewById(R.id.ch_plus);
        ch_minus = (ImageButton) findViewById(R.id.ch_minus);
        mute = (ImageButton) findViewById(R.id.mute);
        zero = (ImageButton) findViewById(R.id.zero);
        one = (ImageButton) findViewById(R.id.one);
        two = (ImageButton) findViewById(R.id.two);
        three = (ImageButton) findViewById(R.id.three);
        four = (ImageButton) findViewById(R.id.four);
        five = (ImageButton) findViewById(R.id.five);
        six = (ImageButton) findViewById(R.id.six);
        seven = (ImageButton) findViewById(R.id.seven);
        eight = (ImageButton) findViewById(R.id.eight);
        nine = (ImageButton) findViewById(R.id.nine);
        zoom = (ImageButton) findViewById(R.id.zoom);
        sleep = (ImageButton) findViewById(R.id.sleep);
        menu = (ImageButton) findViewById(R.id.menu);
        info = (ImageButton) findViewById(R.id.info);
        red = (ImageButton) findViewById(R.id.red);
        green = (ImageButton) findViewById(R.id.green);
        yellow = (ImageButton) findViewById(R.id.yellow);
        blue = (ImageButton) findViewById(R.id.blue);
        mode = (ImageButton) findViewById(R.id.mode);
        exit = (ImageButton) findViewById(R.id.exit);
        epg = (ImageButton) findViewById(R.id.epg);
        stop = (ImageButton) findViewById(R.id.stop);
//        shift = (ImageButton) findViewById(R.id.shift);
  //      sat = (ImageButton) findViewById(R.id.sat);
        usb = (ImageButton) findViewById(R.id.usb);
        //band = (ImageButton) findViewById(R.id.mode);
        fav = (ImageButton) findViewById(R.id.fav);
        pagedown = (ImageButton) findViewById(R.id.pagedown);
        pageup = (ImageButton) findViewById(R.id.pageup);

        ok = (ImageButton) findViewById(R.id.ok);

        record = (ImageButton) findViewById(R.id.record);
        play = (ImageButton) findViewById(R.id.play);
        pause = (ImageButton) findViewById(R.id.pause);
        next = (ImageButton) findViewById(R.id.next);
        previous = (ImageButton) findViewById(R.id.previous);
        fast_forward = (ImageButton) findViewById(R.id.fast_forward);
        fast_rewind = (ImageButton) findViewById(R.id.fast_rewind);

        power.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((currentDevice.getCommand(BUTTON.POWER)) != null) {
                    Thread a = new Thread(new UdpThread(currentDevice.getCommand(BUTTON.POWER)));
                    a.start();
                }

            }
        });

        mute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((currentDevice.getCommand(BUTTON.MUTE)) != null) {
                    Thread a = new Thread(new UdpThread(currentDevice.getCommand(BUTTON.MUTE)));
                    a.start();
                }
            }
        });//mute  pressed

        vol_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((currentDevice.getCommand(BUTTON.VOLUME_UP)) != null) {
                    Thread a = new Thread(new UdpThread(currentDevice.getCommand(BUTTON.VOLUME_UP)));
                    a.start();
                }
            }
        });//vol_plus  pressed

        vol_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((currentDevice.getCommand(BUTTON.VOLUME_DOWN)) != null) {
                    Thread a = new Thread(new UdpThread(currentDevice.getCommand(BUTTON.VOLUME_DOWN)));
                    a.start();
                }
            }
        });//vol_minus  pressed

        ch_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((currentDevice.getCommand(BUTTON.CHANNEL_UP)) != null) {
                    Thread a = new Thread(new UdpThread(currentDevice.getCommand(BUTTON.CHANNEL_UP)));
                    a.start();
                }
            }
        });//ch_plus  pressed

        ch_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((currentDevice.getCommand(BUTTON.CHANNEL_DOWN)) != null) {
                    Thread a = new Thread(new UdpThread(currentDevice.getCommand(BUTTON.CHANNEL_DOWN)));
                    a.start();
                }
            }
        });//ch_minus  pressed

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((currentDevice.getCommand(BUTTON.OK)) != null) {
                    Thread a = new Thread(new UdpThread(currentDevice.getCommand(BUTTON.OK)));
                    a.start();
                }
            }
        });//ok  pressed

        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((currentDevice.getCommand(BUTTON.RECORD)) != null) {
                    Thread a = new Thread(new UdpThread(currentDevice.getCommand(BUTTON.RECORD)));
                    a.start();
                }
            }
        });//record  pressed

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((currentDevice.getCommand(BUTTON.PLAY)) != null) {
                    Thread a = new Thread(new UdpThread(currentDevice.getCommand(BUTTON.PLAY)));
                    a.start();
                }
            }
        });//play  pressed

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((currentDevice.getCommand(BUTTON.PAUSE)) != null) {
                    Thread a = new Thread(new UdpThread(currentDevice.getCommand(BUTTON.PAUSE)));
                    a.start();
                }
            }
        });//pause  pressed

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((currentDevice.getCommand(BUTTON.NEXT)) != null) {
                    Thread a = new Thread(new UdpThread(currentDevice.getCommand(BUTTON.NEXT)));
                    a.start();
                }
            }
        });//next  pressed

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((currentDevice.getCommand(BUTTON.PREVIOUS)) != null) {
                    Thread a = new Thread(new UdpThread(currentDevice.getCommand(BUTTON.PREVIOUS)));
                    a.start();
                }
            }
        });//previous  pressed

        fast_forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((currentDevice.getCommand(BUTTON.FAST_FORWARD)) != null) {
                    Thread a = new Thread(new UdpThread(currentDevice.getCommand(BUTTON.FAST_FORWARD)));
                    a.start();
                }
            }
        });//fast_forward  pressed

        fast_rewind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((currentDevice.getCommand(BUTTON.FAST_REWIND)) != null) {
                    Thread a = new Thread(new UdpThread(currentDevice.getCommand(BUTTON.FAST_REWIND)));
                    a.start();
                }
            }
        });//fast_rewind  pressed

        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((currentDevice.getCommand(BUTTON.ZERO)) != null) {
                    Thread a = new Thread(new UdpThread(currentDevice.getCommand(BUTTON.ZERO)));
                    a.start();
                }
            }
        });//zero  pressed

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((currentDevice.getCommand(BUTTON.ONE)) != null) {
                    Thread a = new Thread(new UdpThread(currentDevice.getCommand(BUTTON.ONE)));
                    a.start();
                }
            }
        });//one  pressed

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((currentDevice.getCommand(BUTTON.TWO)) != null) {
                    Thread a = new Thread(new UdpThread(currentDevice.getCommand(BUTTON.TWO)));
                    a.start();
                }
            }
        });//two  pressed
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((currentDevice.getCommand(BUTTON.THREE)) != null) {
                    Thread a = new Thread(new UdpThread(currentDevice.getCommand(BUTTON.THREE)));
                    a.start();
                }
            }
        });//three  pressed
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((currentDevice.getCommand(BUTTON.FOUR)) != null) {
                    Thread a = new Thread(new UdpThread(currentDevice.getCommand(BUTTON.FOUR)));
                    a.start();
                }
            }
        });//four  pressed

        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((currentDevice.getCommand(BUTTON.FIVE)) != null) {
                    Thread a = new Thread(new UdpThread(currentDevice.getCommand(BUTTON.FIVE)));
                    a.start();
                }
            }
        });//five  pressed

        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((currentDevice.getCommand(BUTTON.SIX)) != null) {
                    Thread a = new Thread(new UdpThread(currentDevice.getCommand(BUTTON.SIX)));
                    a.start();
                }
            }
        });//six  pressed

        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((currentDevice.getCommand(BUTTON.SEVEN)) != null) {
                    Thread a = new Thread(new UdpThread(currentDevice.getCommand(BUTTON.SEVEN)));
                    a.start();
                }
            }
        });//seven  pressed

        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((currentDevice.getCommand(BUTTON.EIGHT)) != null) {
                    Thread a = new Thread(new UdpThread(currentDevice.getCommand(BUTTON.EIGHT)));
                    a.start();
                }
            }
        });//eight  pressed

        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((currentDevice.getCommand(BUTTON.NINE)) != null) {
                    Thread a = new Thread(new UdpThread(currentDevice.getCommand(BUTTON.NINE)));
                    a.start();
                }
            }
        });//nine  pressed


        zoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((currentDevice.getCommand(BUTTON.ZOOM)) != null) {
                    Thread a = new Thread(new UdpThread(currentDevice.getCommand(BUTTON.ZOOM)));
                    a.start();
                }
            }
        });
        sleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((currentDevice.getCommand(BUTTON.SLEEP)) != null) {
                    Thread a = new Thread(new UdpThread(currentDevice.getCommand(BUTTON.SLEEP)));
                    a.start();
                }
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((currentDevice.getCommand(BUTTON.EXIT)) != null) {
                    Thread a = new Thread(new UdpThread(currentDevice.getCommand(BUTTON.EXIT)));
                    a.start();
                }
            }
        });
        mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((currentDevice.getCommand(BUTTON.MODE)) != null) {
                    Thread a = new Thread(new UdpThread(currentDevice.getCommand(BUTTON.MODE)));
                    a.start();
                }
            }
        });
        pageup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((currentDevice.getCommand(BUTTON.PAGE_UP)) != null) {
                    Thread a = new Thread(new UdpThread(currentDevice.getCommand(BUTTON.PAGE_UP)));
                    a.start();
                }
            }
        });
        yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((currentDevice.getCommand(BUTTON.YELLOW)) != null) {
                    Thread a = new Thread(new UdpThread(currentDevice.getCommand(BUTTON.YELLOW)));
                    a.start();
                }
            }
        });
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((currentDevice.getCommand(BUTTON.YELLOW)) != null) {
                    Thread a = new Thread(new UdpThread(currentDevice.getCommand(BUTTON.YELLOW)));
                    a.start();
                }
            }
        });
        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((currentDevice.getCommand(BUTTON.RED)) != null) {
                    Thread a = new Thread(new UdpThread(currentDevice.getCommand(BUTTON.RED)));
                    a.start();
                }
            }
        });
        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((currentDevice.getCommand(BUTTON.GREEN)) != null) {
                    Thread a = new Thread(new UdpThread(currentDevice.getCommand(BUTTON.GREEN)));
                    a.start();
                }
            }
        });
        epg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((currentDevice.getCommand(BUTTON.EPG)) != null) {
                    Thread a = new Thread(new UdpThread(currentDevice.getCommand(BUTTON.EPG)));
                    a.start();
                }
            }
        });
        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((currentDevice.getCommand(BUTTON.BLUE)) != null) {
                    Thread a = new Thread(new UdpThread(currentDevice.getCommand(BUTTON.BLUE)));
                    a.start();
                }
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((currentDevice.getCommand(BUTTON.STOP)) != null) {
                    Thread a = new Thread(new UdpThread(currentDevice.getCommand(BUTTON.STOP)));
                    a.start();
                }
            }
        });
        pagedown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((currentDevice.getCommand(BUTTON.PAGE_DOWN)) != null) {
                    Thread a = new Thread(new UdpThread(currentDevice.getCommand(BUTTON.PAGE_DOWN)));
                    a.start();
                }
            }
        });
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((currentDevice.getCommand(BUTTON.FAV)) != null) {
                    Thread a = new Thread(new UdpThread(currentDevice.getCommand(BUTTON.FAV)));
                    a.start();
                }
            }
        });
//        band.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if ((currentDevice.getCommand(BUTTON.BAND)) != null) {
//                    Thread a = new Thread(new UdpThread(currentDevice.getCommand(BUTTON.BAND)));
//                    a.start();
//                }
//            }
//        });
        usb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((currentDevice.getCommand(BUTTON.USB)) != null) {
                    Thread a = new Thread(new UdpThread(currentDevice.getCommand(BUTTON.USB)));
                    a.start();
                }
            }
        });
//        sat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if ((currentDevice.getCommand(BUTTON.SAT)) != null) {
//                    Thread a = new Thread(new UdpThread(currentDevice.getCommand(BUTTON.SAT)));
//                    a.start();
//                }
//            }
//        });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((currentDevice.getCommand(BUTTON.MENU)) != null) {
                    Thread a = new Thread(new UdpThread(currentDevice.getCommand(BUTTON.MENU)));
                    a.start();
                }
            }
        });
//        shift.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if ((currentDevice.getCommand(BUTTON.SHIFT)) != null) {
//                    Thread a = new Thread(new UdpThread(currentDevice.getCommand(BUTTON.SHIFT)));
//                    a.start();
//                }
//            }
//        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
