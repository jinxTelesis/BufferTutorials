package com.company;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;

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

    
}
