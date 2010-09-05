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

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.net.URL;

/**
 * Creates a 300x360 image at 0,0 
 */
public class Applet
    extends java.applet.Applet
{
    private final static Font FONT = new Font(Font.SANS_SERIF,Font.PLAIN,18);
    private static final Font FONT_TITLE = new Font(Font.SANS_SERIF,Font.PLAIN, 14);

    private final static Color BG = Color.white;
    private final static Color FG = Color.black;
    private final static Color COLOR_SF = new Color(14680064);
    private final static Color COLOR_ST = new Color(15790080);
    private final static Color COLOR_NF = new Color(3190784);
    private final static Color COLOR_NT = new Color(176);
    private final static Color COLOR_BORDER = new Color(9474192);

    private final static int IMG_WH = 300;
    private final static int IMG_WH2 = 150;
    private final static float IMG_WH2F = 150.0F;
    private final static float SQRT12 = (float) Math.sqrt(0.5);
    private final static float IMG_V = IMG_WH2F / SQRT12;

    private static final String Format(float f) {
        String string = String.valueOf(f);
        if (5 < string.length())
            return string.substring(0, 5);
        return string;
    }


    private volatile FontMetrics fm;
    private volatile BufferedImage backing;


    private Float normalized_sf;
    private Float normalized_st;
    private Float normalized_nf;
    private Float normalized_nt;


    public Applet(){
        super();
        this.setForeground(FG);
        this.setBackground(FG);
    }

    public void init(){

        URL doc = this.getDocumentBase();

        String string = doc.getFile();

        Code.Decode code = new Code.Decode(string);

        this.normalized_sf = code.sf;
        this.normalized_st = code.st;
        this.normalized_nf = code.nf;
        this.normalized_nt = code.nt;
    }

    public void paint(Graphics g) {
        this.update(g);
    }
    public void update(Graphics g){
        Graphics2D g2 = this.createGraphics();
        try {
            this.update(g2);
            this.blit(g);
        }
        finally {
            g2.dispose();
        }
    }
    protected void update(Graphics2D g){

        float n_sf = normalized_sf.floatValue();
        float n_st = normalized_st.floatValue();
        float n_nf = normalized_nf.floatValue();
        float n_nt = normalized_nt.floatValue();
        g.setColor(Color.WHITE);
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
        Polygon polygon = new Polygon(p_x, p_y, 4);
        g.setClip(0, 0, IMG_WH2, IMG_WH2);
        g.setColor(COLOR_ST);
        g.fillPolygon(polygon);
        g.setClip(IMG_WH2, 0, IMG_WH2, IMG_WH2);
        g.setColor(COLOR_SF);
        g.fillPolygon(polygon);
        g.setClip(IMG_WH2, IMG_WH2, IMG_WH2, IMG_WH2);
        g.setColor(COLOR_NF);
        g.fillPolygon(polygon);
        g.setClip(0, IMG_WH2, IMG_WH2, IMG_WH2);
        g.setColor(COLOR_NT);
        g.fillPolygon(polygon);

        g.setClip(0, 0, IMG_WH, IMG_WH + 60);

        g.setColor(COLOR_BORDER);
        g.drawRect(0, 0, 299, 299);
        g.drawLine(IMG_WH2, 0, IMG_WH2, IMG_WH);
        g.drawLine(0, IMG_WH2, IMG_WH, IMG_WH2);
        g.drawLine(30, 1, 30, 30);
        g.drawLine(1, 30, 30, 30);
        g.drawLine(1, 270, 30, 270);
        g.drawLine(30, 270, 30, 299);
        g.drawLine(270, 1, 270, 30);
        g.drawLine(270, 30, 299, 30);
        g.drawLine(270, 270, 299, 270);
        g.drawLine(270, 270, 270, 299);
        g.setFont(FONT);
        g.drawString("SF", 276, 22);
        g.drawString("ST", 4, 22);
        g.drawString("NT", 2, 292);
        g.drawString("NF", 274, 292);
        FontMetrics fontmetrics = g.getFontMetrics(FONT);
        int fm_h = fontmetrics.getHeight();
        int fm_x = (IMG_WH2 - fontmetrics.stringWidth("SF ")
                     + (fontmetrics.charWidth(' ') >> 1));
        int fm_y = 310 + fm_h;
        g.drawString("SF " + Format(n_sf), fm_x, fm_y);
        fm_y += fm_h;
        g.drawString("ST " + Format(n_st), fm_x, fm_y);
        fm_y += fm_h;
        g.drawString("NT " + Format(n_nt), fm_x, fm_y);
        fm_y += fm_h;
        g.drawString("NF " + Format(n_nf), fm_x, fm_y);

        g.setFont(FONT_TITLE);
        fontmetrics = g.getFontMetrics(FONT_TITLE);
        String string = "Cognitive Profile Inventory Online";
        int stringw = fontmetrics.stringWidth(string);
        fm_x = IMG_WH2 - stringw / 2;
        fm_y += 2 * fm_h;
        g.drawString(string, fm_x, fm_y);
    }

    public final boolean hasComponentResized(){

        return (null == this.backing || (this.backing.getWidth() != this.getWidth() ||
                                         this.backing.getHeight() != this.getHeight()));
    }
    protected final BufferedImage getBacking(){
        BufferedImage backing = this.backing;

        if (this.hasComponentResized()){

            if (null != backing)
                backing.flush();

            int width = this.getWidth();
            int height = this.getHeight();
            if (0 < width && 0 < height){
                backing = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);//_PRE
                this.backing = backing;
            }
        }
        return backing;
    }
    protected final Graphics2D createGraphics(){
        return this.getBacking().createGraphics();
    }
    protected final void blit(Graphics g){
        BufferedImage backing = this.backing;
        if (null != backing)
            g.drawImage(backing,0,0,this);
    }
}
