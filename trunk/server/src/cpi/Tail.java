package cpi;

/**
 * Fast lookup for strings "index.html", "image.png", and "data.json".
 */
public abstract class Tail 
    extends Object
{
    public final static int None = 0;
    public final static int ImagePng = 1;
    public final static int DataJson = 2;
    public final static int IndexHtml = 3;
    public final static int GroupsHtml = 4;
    public final static int ResultHtml = 5;
    public final static int GroupsPng = 6;
    public final static int ExampleHtml = 7;


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
            case 'e':
                if (tail.equals("example.html"))
                    return Tail.ExampleHtml;
                else
                    return Tail.None;
            case 'g':
                if (7 < tail.length()){
                    switch(tail.charAt(7)){
                    case 'h':
                        if (tail.equals("groups.html"))
                            return Tail.GroupsHtml;
                        else
                            return Tail.None;
                    case 'p':
                        if (tail.equals("groups.png"))
                            return Tail.GroupsPng;
                        else
                            return Tail.None;
                    default:
                        return Tail.None;
                    }
                }
                else
                    return Tail.None;
            case 'r':
                if (tail.equals("result.html"))
                    return Tail.ResultHtml;
                else
                    return Tail.None;
            default:
                return Tail.None;
            }
        }
    }
}
