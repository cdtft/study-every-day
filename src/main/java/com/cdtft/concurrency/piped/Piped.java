package com.cdtft.concurrency.piped;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * @author : wangcheng
 * @Date: 2021年02月05 15:54
 */
public class Piped {

    public static void main(String[] args) {
        PipedReader in = new PipedReader();
        PipedWriter out = new PipedWriter();

        new Thread(new Print(in)).start();

        int receive = 0;
        try {
            out.connect(in);
            while (true) {
                if ((receive = System.in.read()) != -1) {
                    out.write(receive);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    static class Print implements Runnable {

        private PipedReader in;

        public Print(PipedReader in) {
            this.in = in;
        }

        @Override
        public void run() {
            int receive = 0;
            try {
                while ((receive = in.read()) != -1) {
                    System.out.print((char) receive);
                }
            } catch (IOException ex) {

            } finally {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
