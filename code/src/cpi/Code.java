/*
 * Cognitive Profile Inventory Online
 * Copyright (C) 2009 John Pritchard
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation; either version 2 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301 USA.
 */
package cpi;

import java.util.Random;
import java.util.StringTokenizer;

/**
 * CPI statistics basic privacy code.  This scheme permits CPI
 * statistics to be encoded into a URL for the user's benefit, without
 * any other storage, and maintaining the basic privacy that eyeballs
 * won't be able to make sense of it.  However, any actual copy of the
 * URL readily decodes to the statistics.
 * 
 * @author John Pritchard
 */
public abstract class Code {
    /**
     * A simple pad shared by all users.
     */
    private final static byte[] Pad = {
        'L', 'o', 'r', 'e', 'm', ' ', 'i', 'p', 's', 'u', 'm', ' ', 'd', 'o', 'l', 
        'o', 'r', ' ', 's', 'i', 't', ' ', 'a', 'm', 'e', 't', ',', ' ', 'c', 'o', 
        'n', 's', 'e', 'c', 't', 'e', 't', 'u', 'e', 'r', ' ', 'a', 'd', 'i', 'p', 
        'i', 's', 'c', 'i', 'n', 'g', ' ', 'e', 'l', 'i', 't', ',', ' ', 's', 'e', 
        'd', ' ', 'd', 'i', 'a', 'm', ' ', 'n', 'o', 'n', 'u', 'm', 'm', 'y', ' ', 
        'n', 'i', 'b', 'h', ' ', 'e', 'u', 'i', 's', 'm', 'o', 'd', ' ', 't', 'i', 
        'n', 'c', 'i', 'd', 'u', 'n', 't', ' ', 'u', 't', ' ', 'l', 'a', 'o', 'r', 
        'e', 'e', 't', ' ', 'd', 'o', 'l', 'o', 'r', 'e', ' ', 'm', 'a', 'g', 'n', 
        'a', ' ', 'a', 'l', 'i', 'q', 'u', 'a', 'm', ' ', 'e', 'r', 'a', 't', ' ', 
        'v', 'o', 'l', 'u', 't', 'p', 'a', 't', '.', ' ', 'U', 't', ' ', 'w', 'i', 
        's', 'i', ' ', 'e', 'n', 'i', 'm', ' ', 'a', 'd', ' ', 'm', 'i', 'n', 'i', 
        'm', ' ', 'v', 'e', 'n', 'i', 'a', 'm', ',', ' ', 'q', 'u', 'i', 's', ' ', 
        'n', 'o', 's', 't', 'r', 'u', 'd', ' ', 'e', 'x', 'e', 'r', 'c', 'i', ' ', 
        't', 'a', 't', 'i', 'o', 'n', ' ', 'u', 'l', 'l', 'a', 'm', 'c', 'o', 'r', 
        'p', 'e', 'r', ' ', 's', 'u', 's', 'c', 'i', 'p', 'i', 't', ' ', 'l', 'o', 
        'b', 'o', 'r', 't', 'i', 's', ' ', 'n', 'i', 's', 'l', ' ', 'u', 't', ' ', 
        'a', 'l', 'i', 'q', 'u', 'i', 'p', ' ', 'e', 'x', ' ', 'e', 'a', ' ', 'c', 
        'o', 'm', 'm', 'o', 'd', 'o', ' ', 'c', 'o', 'n', 's', 'e', 'q', 'u', 'a', 
        't', '.', ' ', 'D', 'u', 'i', 's', ' ', 'a', 'u', 't', 'e', 'm', ' ', 'v', 
        'e', 'l', ' ', 'e', 'u', 'm', ' ', 'i', 'r', 'i', 'u', 'r', 'e', ' ', 'd', 
        'o', 'l', 'o', 'r', ' ', 'i', 'n', ' ', 'h', 'e', 'n', 'd', 'r', 'e', 'r', 
        'i', 't', ' ', 'i', 'n', ' ', 'v', 'u', 'l', 'p', 'u', 't', 'a', 't', 'e', 
        ' ', 'v', 'e', 'l', 'i', 't', ' ', 'e', 's', 's', 'e', ' ', 'm', 'o', 'l', 
        'e', 's', 't', 'i', 'e', ' ', 'c', 'o', 'n', 's', 'e', 'q', 'u', 'a', 't', 
        ',', ' ', 'v', 'e', 'l', ' ', 'i', 'l', 'l', 'u', 'm', ' ', 'd', 'o', 'l', 
        'o', 'r', 'e', ' ', 'e', 'u', ' ', 'f', 'e', 'u', 'g', 'i', 'a', 't', ' ', 
        'n', 'u', 'l', 'l', 'a', ' ', 'f', 'a', 'c', 'i', 'l', 'i', 's', 'i', 's', 
        ' ', 'a', 't', ' ', 'v', 'e', 'r', 'o', ' ', 'e', 'r', 'o', 's', ' ', 'e', 
        't', ' ', 'a', 'c', 'c', 'u', 'm', 's', 'a', 'n', ' ', 'e', 't', ' ', 'i', 
        'u', 's', 't', 'o', ' ', 'o', 'd', 'i', 'o', ' ', 'd', 'i', 'g', 'n', 'i', 
        's', 's', 'i', 'm', ' ', 'q', 'u', 'i', ' ', 'b', 'l', 'a', 'n', 'd', 'i', 
        't', ' ', 'p', 'r', 'a', 'e', 's', 'e', 'n', 't', ' ', 'l', 'u', 'p', 't', 
        'a', 't', 'u', 'm', ' ', 'z', 'z', 'r', 'i', 'l', ' ', 'd', 'e', 'l', 'e', 
        'n', 'i', 't', ' ', 'a', 'u', 'g', 'u', 'e', ' ', 'd', 'u', 'i', 's', ' ', 
        'd', 'o', 'l', 'o', 'r', 'e', ' ', 't', 'e', ' ', 'f', 'e', 'u', 'g', 'a', 
        'i', 't', ' ', 'n', 'u', 'l', 'l', 'a', ' ', 'f', 'a', 'c', 'i', 'l', 'i', 
        's', 'i', '.', ' ', 'N', 'a', 'm', ' ', 'l', 'i', 'b', 'e', 'r', ' ', 't', 
        'e', 'm', 'p', 'o', 'r', ' ', 'c', 'u', 'm', ' ', 's', 'o', 'l', 'u', 't', 
        'a', ' ', 'n', 'o', 'b', 'i', 's', ' ', 'e', 'l', 'e', 'i', 'f', 'e', 'n', 
        'd', ' ', 'o', 'p', 't', 'i', 'o', 'n', ' ', 'c', 'o', 'n', 'g', 'u', 'e', 
        ' ', 'n', 'i', 'h', 'i', 'l', ' ', 'i', 'm', 'p', 'e', 'r', 'd', 'i', 'e', 
        't', ' ', 'd', 'o', 'm', 'i', 'n', 'g', ' ', 'i', 'd', ' ', 'q', 'u', 'o', 
        'd', ' ', 'm', 'a', 'z', 'i', 'm', ' ', 'p', 'l', 'a', 'c', 'e', 'r', 'a', 
        't', ' ', 'f', 'a', 'c', 'e', 'r', ' ', 'p', 'o', 's', 's', 'i', 'm', ' ', 
        'a', 's', 's', 'u', 'm', '.', ' ', 'T', 'y', 'p', 'i', ' ', 'n', 'o', 'n', 
        ' ', 'h', 'a', 'b', 'e', 'n', 't', ' ', 'c', 'l', 'a', 'r', 'i', 't', 'a', 
        't', 'e', 'm', ' ', 'i', 'n', 's', 'i', 't', 'a', 'm', ';', ' ', 'e', 's', 
        't', ' ', 'u', 's', 'u', 's', ' ', 'l', 'e', 'g', 'e', 'n', 't', 'i', 's', 
        ' ', 'i', 'n', ' ', 'i', 'i', 's', ' ', 'q', 'u', 'i', ' ', 'f', 'a', 'c', 
        'i', 't', ' ', 'e', 'o', 'r', 'u', 'm', ' ', 'c', 'l', 'a', 'r', 'i', 't', 
        'a', 't', 'e', 'm', '.', ' ', 'I', 'n', 'v', 'e', 's', 't', 'i', 'g', 'a', 
        't', 'i', 'o', 'n', 'e', 's', ' ', 'd', 'e', 'm', 'o', 'n', 's', 't', 'r', 
        'a', 'v', 'e', 'r', 'u', 'n', 't', ' ', 'l', 'e', 'c', 't', 'o', 'r', 'e', 
        's', ' ', 'l', 'e', 'g', 'e', 'r', 'e', ' ', 'm', 'e', ' ', 'l', 'i', 'u', 
        's', ' ', 'q', 'u', 'o', 'd', ' ', 'i', 'i', ' ', 'l', 'e', 'g', 'u', 'n', 
        't', ' ', 's', 'a', 'e', 'p', 'i', 'u', 's', '.', ' ', 'C', 'l', 'a', 'r', 
        'i', 't', 'a', 's', ' ', 'e', 's', 't', ' ', 'e', 't', 'i', 'a', 'm', ' ', 
        'p', 'r', 'o', 'c', 'e', 's', 's', 'u', 's', ' ', 'd', 'y', 'n', 'a', 'm', 
        'i', 'c', 'u', 's', ',', ' ', 'q', 'u', 'i', ' ', 's', 'e', 'q', 'u', 'i', 
        't', 'u', 'r', ' ', 'm', 'u', 't', 'a', 't', 'i', 'o', 'n', 'e', 'm', ' ', 
        'c', 'o', 'n', 's', 'u', 'e', 't', 'u', 'd', 'i', 'u', 'm', ' ', 'l', 'e', 
        'c', 't', 'o', 'r', 'u', 'm', '.', ' ', 'M', 'i', 'r', 'u', 'm', ' ', 'e', 
        's', 't', ' ', 'n', 'o', 't', 'a', 'r', 'e', ' ', 'q', 'u', 'a', 'm', ' ', 
        'l', 'i', 't', 't', 'e', 'r', 'a', ' ', 'g', 'o', 't', 'h', 'i', 'c', 'a', 
        ',', ' ', 'q', 'u', 'a', 'm', ' ', 'n', 'u', 'n', 'c', ' ', 'p', 'u', 't', 
        'a', 'm', 'u', 's', ' ', 'p', 'a', 'r', 'u', 'm', ' ', 'c', 'l', 'a', 'r', 
        'a', 'm', ',', ' ', 'a', 'n', 't', 'e', 'p', 'o', 's', 'u', 'e', 'r', 'i', 
        't', ' ', 'l', 'i', 't', 't', 'e', 'r', 'a', 'r', 'u', 'm', ' ', 'f', 'o', 
        'r', 'm', 'a', 's', ' ', 'h', 'u', 'm', 'a', 'n', 'i', 't', 'a', 't', 'i', 
        's', ' ', 'p', 'e', 'r', ' ', 's', 'e', 'a', 'c', 'u', 'l', 'a', ' ', 'q', 
        'u', 'a', 'r', 't', 'a', ' ', 'd', 'e', 'c', 'i', 'm', 'a', ' ', 'e', 't', 
        ' ', 'q', 'u', 'i', 'n', 't', 'a', ' ', 'd', 'e', 'c', 'i', 'm', 'a', '.', 
        ' ', 'E', 'o', 'd', 'e', 'm', ' ', 'm', 'o', 'd', 'o', ' ', 't', 'y', 'p', 
        'i', ',', ' ', 'q', 'u', 'i', ' ', 'n', 'u', 'n', 'c', ' ', 'n', 'o', 'b', 
        'i', 's', ' ', 'v', 'i', 'd', 'e', 'n', 't', 'u', 'r', ' ', 'p', 'a', 'r', 
        'u', 'm', ' ', 'c', 'l', 'a', 'r', 'i', ',', ' ', 'f', 'i', 'a', 'n', 't', 
        ' ', 's', 'o', 'l', 'l', 'e', 'm', 'n', 'e', 's', ' ', 'i', 'n', ' ', 'f', 
        'u', 't', 'u', 'r', 'u', 'm', '.'
    };

    final static byte[] Pad(byte[] text){
        if (null == text)
            return null;
        else {
            int len = text.length;
            if (0 == len)
                return null;
            else {
                byte[] re = new byte[len];
                for (int cc = 0; cc < len; cc++){
                    re[cc] = (byte)(text[cc] ^ Pad[cc]);
                }
                return re;
            }
        }
    }

    public final static String Format(float f) {
        String string = String.valueOf(f);
        if (5 < string.length())
            return string.substring(0, 5);
        return string;
    }


    public final static class Encode 
        extends Code
    {
        final static String Encode(String string){
            if (null == string)
                return null;
            else {
                try {
                    byte[] re = Pad(string.getBytes("US-ASCII"));
                    if (null == re)
                        return null;
                    else 
                        return B64.encodeBytes(re);
                }
                catch (java.io.UnsupportedEncodingException exc){
                    return null;
                }
            }
        }

        public final float nt, nf, st, sf;
        public final String string, code;

        public Encode(float nt, float nf, float st, float sf){
            super();
            this.nt = nt;
            this.nf = nf;
            this.st = st;
            this.sf = sf;
            StringBuilder str = new StringBuilder();
            str.append("nt:");
            str.append(nt);
            str.append(";nf:");
            str.append(nf);
            str.append(";st:");
            str.append(st);
            str.append(";sf:");
            str.append(sf);
            this.string = str.toString();
            this.code = Encode(this.string);
        }
    }

    public final static class Decode
        extends Code
    {
        final static String Clean(String string){
            if (null == string || 1 > string.length())
                return null;
            else {
                int idx = string.indexOf('/');
                while (-1 != idx){

                    string = string.substring(idx+1);

                    if (1 > string.length())
                        return null;
                    else
                        idx = string.indexOf('/');
                }
                return string;
            }
        }
        final static String Decode(String string){
            if (null == string)
                return null;
            else {
                try {
                    byte[] re = B64.decode(string);
                    if (null == re)
                        return null;
                    else 
                        return new String(Pad(re),"US-ASCII");
                }
                catch (java.io.UnsupportedEncodingException exc){
                    return null;
                }
            }
        }

        public final float nt, nf, st, sf;
        public final String string, code;

        public Decode(String code){
            super();
            this.code = Clean(code);
            this.string = Decode(this.code);
            StringTokenizer strtok = new StringTokenizer(this.string,";");
            String tok;
            int check = 0;
            float nt = 0, nf = 0, st = 0, sf = 0;

            while (strtok.hasMoreTokens()){
                tok = strtok.nextToken();
                switch (tok.charAt(0)){
                case 'n':
                    switch (tok.charAt(1)){
                    case 'f':
                        nf = Float.parseFloat(tok.substring(3));
                        check += 1;
                        break;
                    case 't':
                        nt = Float.parseFloat(tok.substring(3));
                        check += 1;
                        break;
                    default:
                        throw new IllegalArgumentException();
                    }
                    break;
                case 's':
                    switch (tok.charAt(1)){
                    case 'f':
                        sf = Float.parseFloat(tok.substring(3));
                        check += 1;
                        break;
                    case 't':
                        st = Float.parseFloat(tok.substring(3));
                        check += 1;
                        break;
                    default:
                        throw new IllegalArgumentException();
                    }
                    break;
                default:
                    throw new IllegalArgumentException();
                }
            }
            this.nf = nf;
            this.nt = nt;
            this.st = st;
            this.sf = sf;
            if (4 == check)
                return;
            else
                throw new IllegalArgumentException();
        }

        public String toStringNt(){
            return Format(this.nt);
        }
        public String toStringNf(){
            return Format(this.nf);
        }
        public String toStringSt(){
            return Format(this.st);
        }
        public String toStringSf(){
            return Format(this.sf);
        }
    }


    /**
     * Test
     */
    public static void main(String[] argv){
        int errors = 0;
        Random prng = new Random();
        int N = 99;
        for (int T = 0; T < N; T++){
            float in_nt = prng.nextFloat();
            float in_nf = prng.nextFloat();
            float in_st = prng.nextFloat();
            float in_sf = prng.nextFloat();
            Encode enc = new Encode(in_nt,in_nf,in_st,in_sf);
            Decode dec = new Decode(enc.code);

            if (dec.nt != in_nt ||
                dec.nf != in_nf ||
                dec.st != in_st ||
                dec.sf != in_sf)
            {
                errors += 1;
            }
            else {
                System.out.println(enc.string);
                System.out.println(enc.code);
            }
        }
        System.err.println("Errors: "+errors);
        if (0 != errors)
            System.exit(1);
        else
            System.exit(0);
    }
}
