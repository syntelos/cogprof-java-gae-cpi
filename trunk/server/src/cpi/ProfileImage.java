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

import cpi.Code;

import pisces.Color;
import pisces.Graphics;
import pisces.Font;
import pisces.Polygon;

/**
 * Creates a 300x300 PNG image 
 */
public class ProfileImage
    extends pisces.Image
{

    private final static Color COLOR_BG = Color.White;
    private final static Color COLOR_FG = Color.Black;
    private final static Color COLOR_SF = new Color(14680064);
    private final static Color COLOR_ST = new Color(15790080);
    private final static Color COLOR_NF = new Color(3190784);
    private final static Color COLOR_NT = new Color(176);
    private final static Color COLOR_BORDER = new Color(9474192);

    private final static String FONT_NAME = "sun12x22.psfu";

    private final static Font FONT ;
    static {
        try {
            FONT = new Font(FONT_NAME);
        }
        catch(java.io.IOException exc){
            throw new Error(FONT_NAME,exc);
        }
    }

    private final static int PAD = 1;
    private final static int IMG_WH = 300;
    private final static int IMG_WH1 = (IMG_WH-PAD);
    private final static int IMG_WH2 = (IMG_WH>>1);
    private final static float IMG_WH2F = IMG_WH2;
    private final static int IMG_WH10 = (IMG_WH/10);
    private final static int IMG_WH3 = (IMG_WH-IMG_WH10);
    private final static float SQRT12 = (float) Math.sqrt(0.5);
    private final static float IMG_V = IMG_WH2F / SQRT12;


    private Graphics g;


    public ProfileImage(Code.Decode code){
        this(code.st, code.sf, code.nt, code.nf);
    }
    public ProfileImage(float n_st, float n_sf, float n_nt, float n_nf){
        super(IMG_WH,IMG_WH);
        if (Err(n_st))
            throw new IllegalArgumentException();
        else if (Err(n_sf))
            throw new IllegalArgumentException();
        else if (Err(n_nt))
            throw new IllegalArgumentException();
        else if (Err(n_nf))
            throw new IllegalArgumentException();
        else {
            g = this.createGraphics();
            g.setAntialiasing(true);

            g.setColor(COLOR_BG);
            g.fillRect(0, 0, IMG_WH, IMG_WH);

            float v_sf = n_sf * IMG_V;
            float v_st = n_st * IMG_V;
            float v_nf = n_nf * IMG_V;
            float v_nt = n_nt * IMG_V;
            float s_sf = v_sf * SQRT12;
            float s_st = v_st * SQRT12;
            float s_nt = v_nt * SQRT12;
            float s_nf = v_nf * SQRT12;
            int[] p_x = new int[4];
            int[] p_y = new int[4];
            p_x[0] = (int) (IMG_WH2F - s_st);
            p_y[0] = (int) (IMG_WH2F - s_st);
            p_x[1] = (int) (IMG_WH2F + s_sf);
            p_y[1] = (int) (IMG_WH2F - s_sf);
            p_x[2] = (int) (IMG_WH2F + s_nf);
            p_y[2] = (int) (IMG_WH2F + s_nf);
            p_x[3] = (int) (IMG_WH2F - s_nt);
            p_y[3] = (int) (IMG_WH2F + s_nt);
            Polygon polygon = new Polygon(p_x, p_y);
            g.setClip(0, 0, IMG_WH2, IMG_WH2);
            g.setColor(COLOR_ST);
            g.fill(polygon);
            g.setClip(IMG_WH2, 0, IMG_WH2, IMG_WH2);
            g.setColor(COLOR_SF);
            g.fill(polygon);
            g.setClip(IMG_WH2, IMG_WH2, IMG_WH2, IMG_WH2);
            g.setColor(COLOR_NF);
            g.fill(polygon);
            g.setClip(0, IMG_WH2, IMG_WH2, IMG_WH2);
            g.setColor(COLOR_NT);
            g.fill(polygon);

            g.setClip(0, 0, IMG_WH, IMG_WH);

            g.setColor(COLOR_BORDER);
            g.drawRect(       0,        0,  IMG_WH1,  IMG_WH1);
            g.drawLine( IMG_WH2,        0,  IMG_WH2,   IMG_WH);
            g.drawLine(       0,  IMG_WH2,   IMG_WH,  IMG_WH2);
            g.drawLine(IMG_WH10,      PAD, IMG_WH10, IMG_WH10);
            g.drawLine(     PAD, IMG_WH10, IMG_WH10, IMG_WH10);
            g.drawLine(     PAD,  IMG_WH3, IMG_WH10,  IMG_WH3);
            g.drawLine(IMG_WH10,  IMG_WH3, IMG_WH10,  IMG_WH1);
            g.drawLine( IMG_WH3,      PAD,  IMG_WH3, IMG_WH10);
            g.drawLine( IMG_WH3, IMG_WH10,  IMG_WH1, IMG_WH10);
            g.drawLine( IMG_WH3,  IMG_WH3,  IMG_WH1,  IMG_WH3);
            g.drawLine( IMG_WH3,  IMG_WH3,  IMG_WH3,  IMG_WH1);

            g.setFont(FONT);
            g.blit("SF", 274, 6);
            g.blit("ST", 4, 6);
            g.blit("NT", 4, 274);
            g.blit("NF", 274, 274);
        }
    }


    private final static boolean Err(float v){
        return (0f > v || 1.0 < v);
    }
    public static void main(String[] argv){

        if (5 == argv.length){
            try {
                float st = Float.parseFloat(argv[0]);
                float sf = Float.parseFloat(argv[1]);
                float nt = Float.parseFloat(argv[2]);
                float nf = Float.parseFloat(argv[3]);
                java.io.File out = new java.io.File(argv[4]);

                ProfileImage pi = new ProfileImage(st,sf,nt,nf);
                byte[] png = pi.toPNG();
                java.io.OutputStream os = new java.io.FileOutputStream(out);
                try {
                    os.write(png,0,png.length);
                    os.flush();
                }
                finally {
                    os.close();
                }
                System.exit(0);
            }
            catch (Exception any){
                any.printStackTrace();
                System.exit(1);
            }
        }
        else {
            System.err.println("Usage: ProfileImage st sf nt nf out-file.png");
            System.exit(1);
        }
    }
}
