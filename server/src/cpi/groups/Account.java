
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
 * 
 */
public final class Account
    extends AccountData
{
    public static List.Primitive<Key> List(Group group){
        Query query = Account.CreateQueryFor();
        {
            Filter filter = new Filter(Account.KIND);
            filter.add(Account.Field.Group,Filter.Op.eq,group.getId());
            filter.update(query);
        }
        return Account.QueryNKey(query);
    }
    public static Iterable<Account> ListPage(Group group, Page page){
        Query query = Account.CreateQueryFor();
        {
            Filter filter = new Filter(Account.KIND);
            filter.add(Account.Field.Group,Filter.Op.eq,group.getId());
            filter.update(query);
        }
        return Account.QueryN(query,page);
    }
    public static Iterable<Account> ListPage(Project project, Page page){
        Query query = Account.CreateQueryFor();
        {
            Filter filter = new Filter(Account.KIND);
            filter.add(Account.Field.Project,Filter.Op.eq,project.getId());
            filter.update(query);
        }
        return Account.QueryN(query,page);
    }

    public static class Amount {

        public final static float PerGroup = -3.0f;
        public final static float PerProject = -1.0f;
        public final static float PerMember = -1.0f;

        /**
         * Intended as annual, but subject to cost development
         */
        public final static float PeriodicFee(Group group){

            return Account.Amount.PerGroup;
        }
        public final static float PeriodicFee(Project project){
            if (project.hasCleaned(true) && (0 < project.getCleaned().compareTo(project.getCreated())))
                return Account.Amount.PerProject;
            else {
                final Integer count = project.getCount();
                if (null != count)
                    return Account.Amount.PerProject+(count*Account.Amount.PerMember);
                else
                    throw new IllegalArgumentException("Project missing count");
            }
        }
    }
    public static class Billing {

        public static void NewGroup(Group group, Request request){

            Account account = new Account(Strings.RandomIdentifier());
            account.setGroup(group);
            account.setClosed(Boolean.FALSE);
            account.setAmount(Account.Amount.PeriodicFee(group));
            account.save();

            account.noteNewGroup(group,request,"Created Group");
        }
        public static void NewProject(Group group, Project project, Request request){

            Account account = new Account(Strings.RandomIdentifier());
            account.setGroup(group);
            account.setProject(project);
            account.setClosed(Boolean.FALSE);
            account.setAmount(Account.Amount.PeriodicFee(project));
            account.save();

            account.noteNewProject(group,project,request,"Created Project");
        }
        public static void MaintainProject(Group group, Project project, Request request){

            Account account = new Account(Strings.RandomIdentifier());
            account.setGroup(group);
            account.setProject(project);
            account.setClosed(Boolean.FALSE);
            account.setAmount(Account.Amount.PeriodicFee(project));
            account.save();

            account.note(request,"Project Maintenance");
        }
    }


    public Account() {
        super();
    }
    public Account(String identifier) {
        super( identifier);
        this.setCurrency("USD");
    }
    public Account(Request req){
        this(gap.Strings.RandomIdentifier());

        this.updateFrom(req);
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
    public void note(Request request, String defaultText){

        final Note note = new Note(this,Strings.RandomIdentifier());

        note.setWriter(request.getViewer());

        final Date date = new Date();
        note.setCreated(date);
        note.setUpdated(date);
        {
            String text = request.getParameter("note_text");
            if (null == text || 1 > text.length()){
                text = defaultText;
            }
            note.setText(Strings.TextFromString(text));
        }
        note.save();
    }
    public void noteNewGroup(Group group, Request request, String defaultText){

        Note note = new Note(this,Strings.RandomIdentifier());
        note.setWriter(request.getViewer());
        note.setCreated(group.getCreated());
        note.setUpdated(group.getCreated());
        {
            String text = request.getParameter("note_text");
            if (null == text || 1 > text.length()){
                text = defaultText;
            }
            note.setText(Strings.TextFromString(text));
        }
        note.save();
    }
    public void noteNewProject(Group group, Project project, Request request, String defaultText){

        Note note = new Note(this,Strings.RandomIdentifier());
        note.setWriter(request.getViewer());
        note.setCreated(project.getCreated());
        note.setUpdated(project.getCreated());
        {
            String text = request.getParameter("note_text");
            if (null == text || 1 > text.length()){
                text = defaultText;
            }
            note.setText(Strings.TextFromString(text));
        }
        note.save();
    }
    public boolean hasAccountAccess(Person viewer){

        return this.getGroup().hasGroupAccess(viewer);
    }
}
