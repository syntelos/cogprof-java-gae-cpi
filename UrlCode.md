# Overview #

> The CPI Online result data is encoded into a URL.  The software that performs this function is [cpi.Code](http://code.google.com/p/cpi/source/browse/trunk/code/src/cpi/Code.java).

# Code #

> The coding functions are the following.
```
    String Encode(float nt, float nf, float st, float sf){
        return Hex(Pad(Bits(nt,nf,st,sf)));
    }
    float[] Decode(String string){
        return Bits(Pad(Hex(string)));
    }
```

> The [Pad](http://www.google.com/search?q=One+Time+Pad) function is a well known cipher, used with a known key to scramble the binary results data.
```
    byte[] Pad = {
        'L', 'o', 'r', 'e', 'm', ' ', 'i', 'p', 's', 'u', 'm', ' ', 'd', 'o', 'l', 
        'o', 'r', ' ', 's', 'i', 't', ' ', 'a', 'm', 'e', 't', ',', ' ', 'c', 'o', 
    };

    byte[] Pad(byte[] text){
        int len = text.length;
        byte[] re = new byte[len];
        for (int cc = 0; cc < len; cc++){

           re[cc] = (byte)(text[cc] ^ Pad[cc]);
        }
        return re;
    }
```

> The Bits function performs a 16 bit encoding and decoding of the quadrant values.  It does this using a fixed point representation for the values, known to exist between zero and one and needing only a modest (1e-4) margin of error.
```
    float FX = 0xffff;

    byte[] Bits(float nt, float nf, float st, float sf){

        int b_nt = (int)(nt*FX);
        int b_nf = (int)(nf*FX);
        int b_st = (int)(st*FX);
        int b_sf = (int)(sf*FX);

        byte[] re = new byte[8];
        re[0] = (byte)((b_nt>>8) & 0xff);
        re[1] = (byte)(b_nt & 0xff);
        re[2] = (byte)((b_nf>>8) & 0xff);
        re[3] = (byte)(b_nf & 0xff);
        re[4] = (byte)((b_st>>8) & 0xff);
        re[5] = (byte)(b_st & 0xff);
        re[6] = (byte)((b_sf>>8) & 0xff);
        re[7] = (byte)(b_sf & 0xff);
        return re;
    }
    float[] Bits(byte[] bits){

        float b_nt = (((bits[0] & 0xff)<<8)|(bits[1] & 0xff));
        float b_nf = (((bits[2] & 0xff)<<8)|(bits[3] & 0xff));
        float b_st = (((bits[4] & 0xff)<<8)|(bits[5] & 0xff));
        float b_sf = (((bits[6] & 0xff)<<8)|(bits[7] & 0xff));

        return new float[]{
            (b_nt/FX),
            (b_nf/FX),
            (b_st/FX),
            (b_sf/FX)
        };
    }
```

# Plain text #

> The plain text embedded in this code is a binary 16 bit fixed point representation of the four CPI Quadrant values: NT, NF, ST, and SF.

# Eyeballs Privacy #

> We hope that most people will want to share their CPI Online results with others.  Sharing can be a powerful aid.

> But first we recognize our responsibility to provide a basic degree of privacy for any form of personal data, including the anonymous CPI Online result.

> As the result URL is intended to be saved, the URL itself does not reveal any personal data to a casual glance at it.

> This is eyeballs privacy.

> One must have a copy of the URL, and decode the URL, in order to have the CPI result data.