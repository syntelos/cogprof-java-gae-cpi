package cpi;

/**
 * 
 */
public abstract class Tail 
    extends Object
{
    public final static int None = 0;
    public final static int ImagePng = 1;
    public final static int DataJson = 2;
    public final static int IndexHtml = 3;


    public final static int For(String tail){
        if (null == tail || 2 > tail.length())
            return Tail.None;
        else {
            switch (tail.charAt(0)){
            case 'i':
                switch (tail.charAt(1)){
                case 'm':
                    if (tail.equals("image.png"))
                        return Tail.ImagePng;
                    else
                        return Tail.None;
                case 'n':
                    if (tail.equals("index.html"))
                        return Tail.IndexHtml;
                    else
                        return Tail.None;
                default:
                    return Tail.None;
                }
            case 'd':
                if (tail.equals("data.json"))
                    return Tail.DataJson;
                else
                    return Tail.None;
            default:
                return Tail.None;
            }
        }
    }
}
