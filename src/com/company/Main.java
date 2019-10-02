package com.company;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

public class Main {

    public static void main(String[] args) {
	// write your code here
    }

    static public void compressData(byte[] data, OutputStream out) throws IOException {
        Deflater d = new Deflater();
        DeflaterOutputStream dout = new DeflaterOutputStream(out,d);
        dout.write(data);
        dout.close();
    }

    static public byte[] decompressData(InputStream in) throws IOException{
        InflaterInputStream input = new InflaterInputStream(in);
        ByteArrayOutputStream bout = new ByteArrayOutputStream(512);

        int b;
        while((b = in.read()) != -1){
            bout.write(b);
        }
        in.close();
        bout.close();
        return bout.toByteArray();
    }


}
