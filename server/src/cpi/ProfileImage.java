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

    private final static String DefaultFontName = "sun12x22.psfu";

    private final static Font DefaultFont ;
    static {
        try {
            DefaultFont = new Font(DefaultFontName);
        }
        catch(java.io.IOException exc){
            throw new Error(DefaultFontName,exc);
        }
    }

    private final static int PAD = 1;
    private final static int IMG_WH = 300;
    private final static int IMG_WH1 = (IMG_WH-PAD);
    private final static int IMG_WH2 = (IMG_WH>>1);

    private final static float IMG_WH2F = IMG_WH2;

    private final static int IMG_WH10 = (IMG_WH/10);



    public ProfileImage(Code.Decode code){
        this(code.st, code.sf, code.nt, code.nf, ProfileLabels.Default);
    }
    public ProfileImage(Code.Encode code, ProfileLabels labels){
        this(code.st, code.sf, code.nt, code.nf, labels);
    }
    public ProfileImage(float n_st, float n_sf, float n_nt, float n_nf, ProfileLabels labels){
        this(n_st,n_sf,n_nt,n_nf,labels,DefaultFont);
    }
    public ProfileImage(float n_st, float n_sf, float n_nt, float n_nf, ProfileLabels labels, String font)
        throws java.io.IOException
    {
        this(n_st,n_sf,n_nt,n_nf,labels,new Font(font));
    }
    public ProfileImage(float n_st, float n_sf, float n_nt, float n_nf, ProfileLabels labels, Font font){
        super(IMG_WH,IMG_WH);
        if (Err(n_st))
            throw new IllegalArgumentException();
        else if (Err(n_sf))
            throw new IllegalArgumentException();
        else if (Err(n_nt))
            throw new IllegalArgumentException();
        else if (Err(n_nf))
            throw new IllegalArgumentException();
        else if (null == labels)
            throw new IllegalArgumentException();
        else if (null == font)
            throw new IllegalArgumentException();
        else {

            Graphics g = this.createGraphics();
            try {
                g.setAntialiasing(true);

                g.setColor(COLOR_BG);
                g.fillRect(0, 0, IMG_WH, IMG_WH);

                float s_sf = n_sf * IMG_WH2F;
                float s_st = n_st * IMG_WH2F;
                float s_nf = n_nf * IMG_WH2F;
                float s_nt = n_nt * IMG_WH2F;
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

                g.drawRect(       0,        0,  IMG_WH1,  IMG_WH1); // outline border 
                g.drawLine( IMG_WH2,        0,  IMG_WH2,   IMG_WH); // vert middle separator
                g.drawLine(       0,  IMG_WH2,   IMG_WH,  IMG_WH2); // horz middle separator

                g.setFont(font);
                /*
                 * Label Borders
                 */
                final int wSt = labels.wSt(IMG_WH10);
                final int wSf = labels.wSf(IMG_WH10);
                final int wNt = labels.wNt(IMG_WH10);
                final int wNf = labels.wNf(IMG_WH10);

                int x1, y1, x2, y2;

                /* ST -- TL vert
                 */
                x1 = wSt;           y1 = PAD;                x2 = wSt;            y2 = IMG_WH10;
                g.drawLine( x1, y1, x2, y2); 

                /* ST -- TL horz
                 */
                x1 = PAD;           y1 = IMG_WH10;           x2 = wSt;            y2 = IMG_WH10;
                g.drawLine( x1, y1, x2, y2); 

                g.blit(labels.getSt(), (x1+3), 6);

                /* NT -- BL vert
                 */
                x1 = wNt;           y1 = (IMG_WH-IMG_WH10);  x2 = wNt;            y2 = IMG_WH1;
                g.drawLine( x1, y1, x2, y2); 

                /* NT -- BL horz
                 */
                x1 = PAD;           y1 = (IMG_WH-IMG_WH10);  x2 = wNt;            y2 = (IMG_WH-IMG_WH10);
                g.drawLine( x1, y1, x2, y2); 

                g.blit(labels.getNt(), (x1+3), 274);

                /* SF -- TR vert
                 */
                x1 = (IMG_WH-wSf);  y1 = PAD;                x2 = (IMG_WH-wSf);   y2 = IMG_WH10;
                g.drawLine( x1, y1, x2, y2); 

                /* SF -- TR horz
                 */
                x1 = (IMG_WH-wSf);  y1 = IMG_WH10;           x2 = IMG_WH1;        y2 = IMG_WH10;
                g.drawLine( x1, y1, x2, y2); 

                g.blit(labels.getSf(), (x1+4), 6);

                /* NF -- BR vert
                 */
                x1 = (IMG_WH-wNf);  y1 = (IMG_WH-IMG_WH10);  x2 = (IMG_WH-wNf);   y2 = IMG_WH1;
                g.drawLine( x1, y1, x2, y2); 

                /* NF -- BR horz
                 */
                x1 = (IMG_WH-wNf);  y1 = (IMG_WH-IMG_WH10);  x2 = IMG_WH1;        y2 = (IMG_WH-IMG_WH10);
                g.drawLine( x1, y1, x2, y2); 

                g.blit(labels.getNf(), (x1+4), 274);
            }
            finally {
                g.dispose();
            }
        }
    }


    private final static boolean Err(float v){
        return (0f > v || 1.0 < v);
    }
    public static void main(String[] argv){

        if (5 <= argv.length){
            try {
                float st = Float.parseFloat(argv[0]);
                float sf = Float.parseFloat(argv[1]);
                float nt = Float.parseFloat(argv[2]);
                float nf = Float.parseFloat(argv[3]);
                java.io.File out = new java.io.File(argv[4]);

                ProfileImage pi;
                if (6 == argv.length)
                    pi = new ProfileImage(st,sf,nt,nf,new ProfileLabels(argv[5]));
                else if (9 == argv.length)
                    pi = new ProfileImage(st,sf,nt,nf,new ProfileLabels(argv[5],argv[6],argv[7],argv[8]));
                else
                    pi = new ProfileImage(st,sf,nt,nf,ProfileLabels.Default);

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
            System.err.println("Usage: ProfileImage st sf nt nf out-file.png [labels]");
            System.exit(1);
        }
    }
}
