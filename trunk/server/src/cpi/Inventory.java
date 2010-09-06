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
import oso.data.Person;

public enum Inventory
{
    L1, L2, L3, L4, R1, R2, R3, R4;
    
    public boolean isLeft(){
        switch (this){
        case L1:
            return true;
        case L2:
            return true;
        case L3:
            return true;
        case L4:
            return true;
        case R1:
            return false;
        case R2:
            return false;
        case R3:
            return false;
        case R4:
            return false;
        default:
            throw new IllegalArgumentException();
        }
    }

    public final static boolean IsComplete(Person viewer){
        if (null != viewer && viewer.isNotEmptyInventory())
            return (Size == viewer.getInventory().size());
        else
            return false;
    }
    public final static void Complete(Person viewer){

        if (null == viewer.getNf() ||
            null == viewer.getSf() ||
            null == viewer.getNt() ||
            null == viewer.getSt())
        {
            Inventory.Product product = new Inventory.Product(viewer.getInventory());
            viewer.setNf( product.normalized_nf);
            viewer.setSf( product.normalized_sf);
            viewer.setNt( product.normalized_nt);
            viewer.setSt( product.normalized_st);
            viewer.store();
        }
    }
    public final static Code.Encode Encode(Person viewer){

        return new Code.Encode(viewer.getNt(),
                               viewer.getNf(),
                               viewer.getSt(),
                               viewer.getSf());

    }
    public final static int ValueOf(Inventory ri){
        switch (ri){
        case L1:
            return 1;
        case L2:
            return 2;
        case L3:
            return 3;
        case L4:
            return 4;
        case R1:
            return 1;
        case R2:
            return 2;
        case R3:
            return 3;
        case R4:
            return 4;
        default:
            throw new IllegalArgumentException();
        }
    }

    public final static int Size = 60;

    public static class Product {

        public final float normalized_sf;
        public final float normalized_st;
        public final float normalized_nf;
        public final float normalized_nt;

        public Product (gap.data.List<Inventory> inventory)
        {
            super();
            if (Inventory.Size == inventory.size()){
                float raw_sf = 0.0F;
                float raw_st = 0.0F;
                float raw_nf = 0.0F;
                float raw_nt = 0.0F;
                float raw_max = 0.0F;

                for (int cc = 0; cc < Inventory.Size; cc++) {
                    Inventory usr_ri = inventory.get(cc);
                    Group usr_grp = Group.For(usr_ri.isLeft(), cc);
                    float usr_val = Inventory.ValueOf(usr_ri);

                    switch(usr_grp){
                    case SF:
                        raw_sf += usr_val;
                        if (raw_sf > raw_max)
                            raw_max = raw_sf;
                        break;
                    case ST:
                        raw_st += usr_val;
                        if (raw_st > raw_max)
                            raw_max = raw_st;
                        break;
                    case NF:
                        raw_nf += usr_val;
                        if (raw_nf > raw_max)
                            raw_max = raw_nf;
                        break;
                    case NT:
                        raw_nt += usr_val;
                        if (raw_nt > raw_max)
                            raw_max = raw_nt;
                        break;
                    default:
                        throw new IllegalStateException("bug");
                    }
                }
                this.normalized_sf = raw_sf / raw_max;
                this.normalized_st = raw_st / raw_max;
                this.normalized_nt = raw_nt / raw_max;
                this.normalized_nf = raw_nf / raw_max;
            }
            else
                throw new IllegalArgumentException("Wrong inventory size");
        }
        public Product (java.util.List<Inventory> inventory)
        {
            super();
            if (Inventory.Size == inventory.size()){
                float raw_sf = 0.0F;
                float raw_st = 0.0F;
                float raw_nf = 0.0F;
                float raw_nt = 0.0F;
                float raw_max = 0.0F;

                for (int cc = 0; cc < Inventory.Size; cc++) {
                    Inventory usr_ri = inventory.get(cc);
                    Group usr_grp = Group.For(usr_ri.isLeft(), cc);
                    float usr_val = Inventory.ValueOf(usr_ri);

                    switch(usr_grp){
                    case SF:
                        raw_sf += usr_val;
                        if (raw_sf > raw_max)
                            raw_max = raw_sf;
                        break;
                    case ST:
                        raw_st += usr_val;
                        if (raw_st > raw_max)
                            raw_max = raw_st;
                        break;
                    case NF:
                        raw_nf += usr_val;
                        if (raw_nf > raw_max)
                            raw_max = raw_nf;
                        break;
                    case NT:
                        raw_nt += usr_val;
                        if (raw_nt > raw_max)
                            raw_max = raw_nt;
                        break;
                    default:
                        throw new IllegalStateException("bug");
                    }
                }
                this.normalized_sf = raw_sf / raw_max;
                this.normalized_st = raw_st / raw_max;
                this.normalized_nt = raw_nt / raw_max;
                this.normalized_nf = raw_nf / raw_max;
            }
            else
                throw new IllegalArgumentException("Wrong inventory size");
        }
    }
}
