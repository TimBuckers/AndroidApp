package apps.myapplication;

/**
 * Created by Boning on 21-8-2015.
 */
public class ThreeRow {

    private static int Block1;
    private static int Block2;

    public ThreeRow(int B1, int B2)
    {
        this.Block1 = B1;
        this.Block2 = B2;
    }

    public int[] calcOptions()
    {
        int[] res = new int[2];
        int option1;
        int option2;

        // Block1 ligt rechts van block2
        if((Block1 - Block2) == 1)
        {
            option1 = Block1 + 1;
            option2 = Block2 - 1;
            res[0] = option1;
            res[1] = option2;
            return res;
        }
        // Block1 ligt boven Block2
        else if((Block1 - Block2) == 10)
        {
            option1 = Block1 + 10;
            option2 = Block2 - 10;
            res[0] = option1;
            res[1] = option2;
            return res;
        }
        // Block1 ligt rechtsonder Block2
        else if((Block1 - Block2) == 11)
        {
            option1 = Block1 + 11;
            option2 = Block2 - 11;
            res[0] = option1;
            res[1] = option2;
            return res;
        }
        // Block1 ligt linksonder Block2
        else if((Block1 - Block2) == 9)
        {
            option1 = Block1 + 9;
            option2 = Block2 - 9;
            res[0] = option1;
            res[1] = option2;
            return res;
        }
        // Block1 en Block2 liggen 1 verwijderd naast elkaar met 1 block ertussen in
        else if((Block1 - Block2) == 2)
        {
            option1 = Block1 - 1;
            option2 = Block2 + 1;
            res[0] = option1;
            res[1] = option2;
            return res;
        }
        // Block1 en Block2 liggen 1 verwijderd boven elkaar met 1 block ertussen in
        else if((Block1 - Block2 == 20))
        {
            option1 = Block1 - 10;
            option2 = Block2 + 10;
            res[0] = option1;
            res[1] = option2;
            return res;
        }
        // Block1 en Block2 liggen 1 verwijderd rechts schuinonder elkaar met 1 block ertussen in
        else if((Block1 - Block2 == 22))
        {
            option1 = Block1 - 11;
            option2 = Block2 + 11;
            res[0] = option1;
            res[1] = option2;
            return res;
        }
        // Block1 en Block2 liggen 1 verwijderd links schuinonder elkaar met 1 block ertussen in
        else if((Block1 - Block2 == 18))
        {
            option1 = Block1 - 9;
            option2 = Block2 + 9;
            res[0] = option1;
            res[1] = option2;
            return res;
        }

        return null;
    }

    public int getBlock1()
    {
        return Block1;
    }

    public int getBlock2()
    {
        return Block2;
    }

    public void setBlock1(int B1)
    {
        Block1 = B1;
    }

    public void setBlock2(int B2)
    {
        Block2 = B2;
    }

}
