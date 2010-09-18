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

public enum Quadrant
{
    ST, SF, NT, NF;

    
    public static final Quadrant For(String value) {
        if (null == value)
            return null;
        else if (2 < value.length())
            value = value.trim();
        else if (2 == value.length()) {
            switch (value.charAt(0)) {
            case 'S':
            case 's':
                switch (value.charAt(1)) {
                case 'F':
                case 'f':
                    return Quadrant.SF;
                case 'T':
                case 't':
                    return Quadrant.ST;
                default:
                    break;
                }
                break;
            case 'N':
            case 'n':
                switch (value.charAt(1)) {
                case 'F':
                case 'f':
                    return Quadrant.NF;
                case 'T':
                case 't':
                    return Quadrant.NT;
                default:
                    break;
                }
                break;
            }
        }
        throw new IllegalArgumentException(value);
    }
    public static final Quadrant For(boolean left, int id) {
        switch (id) {
        case 0:
            if (left)
                return Quadrant.ST;
            else
                return Quadrant.SF;
        case 1:
            if (left)
                return Quadrant.ST;
            else
                return Quadrant.SF;
        case 2:
            if (left)
                return Quadrant.ST;
            else
                return Quadrant.SF;
        case 3:
            if (left)
                return Quadrant.ST;
            else
                return Quadrant.SF;
        case 4:
            if (left)
                return Quadrant.ST;
            else
                return Quadrant.SF;
        case 5:
            if (left)
                return Quadrant.ST;
            else
                return Quadrant.SF;
        case 6:
            if (left)
                return Quadrant.ST;
            else
                return Quadrant.SF;
        case 7:
            if (left)
                return Quadrant.ST;
            else
                return Quadrant.SF;
        case 8:
            if (left)
                return Quadrant.ST;
            else
                return Quadrant.SF;
        case 9:
            if (left)
                return Quadrant.ST;
            else
                return Quadrant.SF;
        case 10:
            if (left)
                return Quadrant.ST;
            else
                return Quadrant.NT;
        case 11:
            if (left)
                return Quadrant.ST;
            else
                return Quadrant.NT;
        case 12:
            if (left)
                return Quadrant.ST;
            else
                return Quadrant.NT;
        case 13:
            if (left)
                return Quadrant.ST;
            else
                return Quadrant.NT;
        case 14:
            if (left)
                return Quadrant.ST;
            else
                return Quadrant.NT;
        case 15:
            if (left)
                return Quadrant.ST;
            else
                return Quadrant.NT;
        case 16:
            if (left)
                return Quadrant.ST;
            else
                return Quadrant.NT;
        case 17:
            if (left)
                return Quadrant.ST;
            else
                return Quadrant.NT;
        case 18:
            if (left)
                return Quadrant.ST;
            else
                return Quadrant.NT;
        case 19:
            if (left)
                return Quadrant.ST;
            else
                return Quadrant.NT;
        case 20:
            if (left)
                return Quadrant.ST;
            else
                return Quadrant.NF;
        case 21:
            if (left)
                return Quadrant.ST;
            else
                return Quadrant.NF;
        case 22:
            if (left)
                return Quadrant.ST;
            else
                return Quadrant.NF;
        case 23:
            if (left)
                return Quadrant.ST;
            else
                return Quadrant.NF;
        case 24:
            if (left)
                return Quadrant.ST;
            else
                return Quadrant.NF;
        case 25:
            if (left)
                return Quadrant.ST;
            else
                return Quadrant.NF;
        case 26:
            if (left)
                return Quadrant.ST;
            else
                return Quadrant.NF;
        case 27:
            if (left)
                return Quadrant.ST;
            else
                return Quadrant.NF;
        case 28:
            if (left)
                return Quadrant.ST;
            else
                return Quadrant.NF;
        case 29:
            if (left)
                return Quadrant.ST;
            else
                return Quadrant.NF;
        case 30:
            if (left)
                return Quadrant.NF;
            else
                return Quadrant.SF;
        case 31:
            if (left)
                return Quadrant.NF;
            else
                return Quadrant.SF;
        case 32:
            if (left)
                return Quadrant.NF;
            else
                return Quadrant.SF;
        case 33:
            if (left)
                return Quadrant.NF;
            else
                return Quadrant.SF;
        case 34:
            if (left)
                return Quadrant.NF;
            else
                return Quadrant.SF;
        case 35:
            if (left)
                return Quadrant.NF;
            else
                return Quadrant.SF;
        case 36:
            if (left)
                return Quadrant.NF;
            else
                return Quadrant.SF;
        case 37:
            if (left)
                return Quadrant.NF;
            else
                return Quadrant.SF;
        case 38:
            if (left)
                return Quadrant.NF;
            else
                return Quadrant.SF;
        case 39:
            if (left)
                return Quadrant.NF;
            else
                return Quadrant.SF;
        case 40:
            if (left)
                return Quadrant.SF;
            else
                return Quadrant.NT;
        case 41:
            if (left)
                return Quadrant.SF;
            else
                return Quadrant.NT;
        case 42:
            if (left)
                return Quadrant.SF;
            else
                return Quadrant.NT;
        case 43:
            if (left)
                return Quadrant.SF;
            else
                return Quadrant.NT;
        case 44:
            if (left)
                return Quadrant.SF;
            else
                return Quadrant.NT;
        case 45:
            if (left)
                return Quadrant.SF;
            else
                return Quadrant.NT;
        case 46:
            if (left)
                return Quadrant.SF;
            else
                return Quadrant.NT;
        case 47:
            if (left)
                return Quadrant.SF;
            else
                return Quadrant.NT;
        case 48:
            if (left)
                return Quadrant.SF;
            else
                return Quadrant.NT;
        case 49:
            if (left)
                return Quadrant.SF;
            else
                return Quadrant.NT;
        case 50:
            if (left)
                return Quadrant.NT;
            else
                return Quadrant.NF;
        case 51:
            if (left)
                return Quadrant.NT;
            else
                return Quadrant.NF;
        case 52:
            if (left)
                return Quadrant.NT;
            else
                return Quadrant.NF;
        case 53:
            if (left)
                return Quadrant.NT;
            else
                return Quadrant.NF;
        case 54:
            if (left)
                return Quadrant.NT;
            else
                return Quadrant.NF;
        case 55:
            if (left)
                return Quadrant.NT;
            else
                return Quadrant.NF;
        case 56:
            if (left)
                return Quadrant.NT;
            else
                return Quadrant.NF;
        case 57:
            if (left)
                return Quadrant.NT;
            else
                return Quadrant.NF;
        case 58:
            if (left)
                return Quadrant.NT;
            else
                return Quadrant.NF;
        case 59:
            if (left)
                return Quadrant.NT;
            else
                return Quadrant.NF;
        default:
            throw new IllegalArgumentException(String.valueOf(id));
        }
    }
}
