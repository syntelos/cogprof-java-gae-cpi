
import gap.data.List;
import gap.data.Store;

import oso.data.Person;

import com.google.appengine.api.datastore.Key;


/**
 * Test inventory storage consistency
 */
public class Inventory
    extends AbstractTest
{
    private final static cpi.Inventory[] Case_RL = {
        cpi.Inventory.R1,
        cpi.Inventory.R2,
        cpi.Inventory.R3,
        cpi.Inventory.R4,
        cpi.Inventory.L1,
        cpi.Inventory.L2,
        cpi.Inventory.L3,
        cpi.Inventory.L4
    };
    private final static int Case_RL_length = Case_RL.length;

    private final static cpi.Inventory[] Case_LRRL = {
        cpi.Inventory.L3,
        cpi.Inventory.R1,
        cpi.Inventory.L4,
        cpi.Inventory.R2,
        cpi.Inventory.R3,
        cpi.Inventory.L1,
        cpi.Inventory.R4,
        cpi.Inventory.L2
    };
    private final static int Case_LRRL_length = Case_LRRL.length;





    public Inventory(){
        super();
    }


    public void testInventoryRL(){
        Store.Test();
        try {
            final Person write = Person.GetCreateLong("test@example.com");
            {
                final List.Primitive<cpi.Inventory> inventory = write.getInventory();

                for (cpi.Inventory cc: Case_RL){

                    inventory.add(cc);
                }
                write.save();
            }

            int correct = 0;

            final Person read = Person.ForLongLogonId("test@example.com");
            {
                final List.Primitive<cpi.Inventory> inventory = read.getInventory();

                final StringBuilder found = new StringBuilder();

                for (int ix = 0; ix < Case_RL_length; ix++){

                    final cpi.Inventory cc = Case_RL[ix];

                    if (ix < inventory.size()){

                        final cpi.Inventory chk = inventory.get(ix);

                        if (null != chk){

                            if (0 < found.length())
                                found.append(' ');

                            found.append(chk.name());

                            if (chk == cc)
                                correct += 1;
                        }
                    }
                }
                /*
                 */
                System.err.printf("Found %s%n",found);

                assertTrue (Case_RL_length == correct);
            }

        }
        finally {
            Store.Exit();
        }
    }
    public void testInventoryLRRL(){
        Store.Test();
        try {
            final Person write = Person.GetCreateLong("test@example.com");
            {
                final List.Primitive<cpi.Inventory> inventory = write.getInventory();

                for (cpi.Inventory cc: Case_LRRL){

                    inventory.add(cc);
                }
                write.save();
            }

            int correct = 0;

            final Person read = Person.ForLongLogonId("test@example.com");
            {
                final List.Primitive<cpi.Inventory> inventory = read.getInventory();

                final StringBuilder found = new StringBuilder();

                for (int ix = 0; ix < Case_LRRL_length; ix++){

                    final cpi.Inventory cc = Case_LRRL[ix];

                    if (ix < inventory.size()){

                        final cpi.Inventory chk = inventory.get(ix);

                        if (null != chk){

                            if (0 < found.length())
                                found.append(' ');

                            found.append(chk.name());

                            if (chk == cc)
                                correct += 1;
                        }
                    }
                }
                /*
                 */
                System.err.printf("Found %s%n",found);

                assertTrue (Case_LRRL_length == correct);
            }

        }
        finally {
            Store.Exit();
        }
    }
}
