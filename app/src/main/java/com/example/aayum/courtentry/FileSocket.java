package com.example.aayum.courtentry;

import android.os.AsyncTask;
import android.os.Environment;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class FileSocket implements Serializable{

    public boolean filetrans(String ip,int port){

        boolean success = false;

        File csv = new File(Environment.getExternalStorageDirectory().getPath()+"/Android/data/com.example.aayum.courtentry/files/transfer.csv");
        if(csv.exists()){
            InetAddress server = null;
            try {
               server = InetAddress.getByName(ip);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            if(server!=null) {
                try {

                    Socket sck = new Socket(server, port);
                    sck.setSoTimeout(30);

                    byte[] databytes = new byte[(int)csv.length()];
                    BufferedInputStream bis = null;
                    try{

                        bis = new BufferedInputStream(new FileInputStream(csv));
                        bis.read(databytes,0,databytes.length);
                       // ObjectOutputStream oos = new ObjectOutputStream(sck.getOutputStream());
                        OutputStream os = sck.getOutputStream();
                        os.write(databytes,0,databytes.length);
                        os.flush();

                        sck.close();
                       System.out.print("file sent to : "+ip);
                       success = true;

                    }catch(Exception e){
                        e.printStackTrace();
                    }


                }catch(Exception e){
                    e.printStackTrace();
                }
            }

        }
        return success;

    }



}
