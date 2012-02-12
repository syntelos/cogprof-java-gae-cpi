
package cpi.groups;

import oso.data.Person;
import cpi.Redirect;
import cpi.Margins;

import gap.*;
import gap.data.*;
import gap.util.*;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.blobstore.*;

import java.util.Date;

/**
 * Generated once (user) bean.
 * This source file will not be overwritten unless deleted,
 * so it can be edited for extensions.
 *
 * @see NoteData
 */
public final class Note
    extends NoteData
{
    public final static BigTableIterator<Note> ListPage(Account account, Page page){
        return Note.ListPage(account.getKey(),page);
    }


    public Note() {
        super();
    }
    public Note(Key ancestor, String identifier) {
        super(ancestor,  identifier);
    }
    public Note(Account ancestor, String identifier) {
        this(ancestor.getKey(),  identifier);
    }


    public String getTextShort(){
        Text text = this.getText();
        if (null != text){

            String textString = Strings.TextToString(text);
            if (null != textString){

                textString = textString.replace('\n',' ');

                if (25 < textString.length())
                    return textString.substring(0,20)+"...";
            }
        }
        return "";
    }
    public boolean mayUpdate(Request req) {

        return this.mayUpdate(req.getViewer());
    }
    public boolean mayUpdate(Person viewer) {

        Key writer = this.getWriterKey();
        if (null != writer)
            return Equals(writer,viewer.getKey());
        else
            return false;
    }
    public boolean updateFrom(Request req) throws ValidationError {
        boolean change = false;

        String textRequest = req.getParameter("text");
        if (null != textRequest && 0 < textRequest.length()){
            try {
                change = this.setText(gap.Strings.TextFromString(textRequest));
            }
            catch (RuntimeException exc){
                throw new ValidationError(ClassName,"text",textRequest,exc);
            }
        }
        return change;
    }
    public void onread(){
    }
    public void onwrite(){
    }
    public java.io.Serializable valueOf(gap.data.Field field, boolean mayInherit){
        return (java.io.Serializable)Field.Get((Field)field,this,mayInherit);
    }
    public void define(gap.data.Field field, java.io.Serializable value){
        Field.Set((Field)field,this,value);
    }
    public void drop(){
        Delete(this);
    }
    public void clean(){
        Clean(this);
    }
    public void save(){
        Save(this);
    }
    public void store(){
        Store(this);
    }
}
