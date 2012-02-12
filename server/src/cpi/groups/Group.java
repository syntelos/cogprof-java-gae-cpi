
package cpi.groups;

import cpi.Redirect;
import cpi.Margins;
import cpi.ProfileLabels;

import oso.data.Person;

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
 * @see GroupData
 */
public final class Group
    extends GroupData
{
    public final static Group ForOwner(Person owner){
        Query query = Group.CreateQueryFor();
        {
            Filter filter = new Filter(Group.KIND).add(Field.Owner,Filter.Op.eq,owner.getId());
            filter.update(query);
        }
        return Group.Query1(query);
    }
    public final static Group ForAdmin(Person admin){
        Query query = Group.CreateQueryFor();
        {
            Filter filter = new Filter(Group.KIND).add(Field.Admin,Filter.Op.eq,admin.getId());
            filter.update(query);
        }
        return Group.Query1(query);
    }
    public final static Group For(Person user){
        Group group = Group.ForAdmin(user);
        if (null == group){
            group = Group.ForOwner(user);
        }
        return group;
    }


    public Group() {
        super();
    }
    public Group(String identifier) {
        super( identifier);
    }
    public Group(Request req){
        super(gap.Strings.RandomIdentifier());

        this.updateFrom(req);

        this.setCreated(new Date());
    }


    public boolean isTest(){
        Boolean test = this.getTest();
        return (null != test && test);
    }
    public boolean isNotTest(){
        Boolean test = this.getTest();
        return (null == test || (!test));
    }
    @Override
    public boolean updateFrom(Request req) throws ValidationError {
        boolean modified = false;

        final String name = req.getParameter("name");
        if (null != name && 0 < name.length()){
            modified = this.setName(name);
        }
        modified = (this.setRedirect(new Redirect(req)) || modified);
        modified = (this.setMargins(new Margins(req)) || modified);
        modified = (this.setLabels(new ProfileLabels(req)) || modified);

        final String owner_logonId = req.getParameter("owner_logonId");
        if (null != owner_logonId){
            modified = (this.setOwner(Person.GetCreateLongLogonId(owner_logonId)) || modified);
        }

        final String admin_logonId = req.getParameter("admin_logonId");
        if (null != admin_logonId){
            modified = (this.setAdmin(Person.GetCreateLongLogonId(admin_logonId)) || modified);
        }

        modified = this.setTest(Test(req.getParameter("test"),this.getTest())) || modified;

        return modified;
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
    /**
     * @param person Viewer
     * @return Person is admin or owner
     */
    public boolean hasGroupAccess(Person person){
        if (null == person)
            return false;
        else
            return (person.equals(this.getAdmin()) || person.equals(this.getOwner()));
    }
    public Redirect getCreateRedirect(){
        Redirect redirect = this.getRedirect();
        if (null == redirect){
            redirect = new Redirect();
            this.setRedirect(redirect);
        }
        return redirect;
    }
    public Margins getCreateMargins(){
        Margins margins = this.getMargins();
        if (null == margins){
            margins = new Margins();
            this.setMargins(margins);
        }
        return margins;
    }
    public ProfileLabels getCreateProfileLabels(){
        ProfileLabels profileLabels = this.getLabels();
        if (null == profileLabels){
            profileLabels = new ProfileLabels();
            this.setLabels(profileLabels);
        }
        return profileLabels;
    }
    public boolean fromJsonIdentifier(json.Json json){
        return false;
    }
    // public boolean fromJsonName(json.Json json){
    // }
    public boolean fromJsonOwner(json.Json json){
        try {
            String logonId = (String)json.getValue("logonId");
            if (null != logonId){

                Person person = Person.ForLongLogonId(logonId);
                if (null != person){
                    return this.setOwner(person);
                }
                else {
                    person = this.getOwner(MayNotInherit);
                    if (null != person){

                        if (person.setLogonId(logonId))
                            person.save();
                    }
                    else {

                        person = new Person(gap.Strings.RandomIdentifier());
                        person.setLogonId(logonId);
                        person.save();

                        return this.setOwner(person);
                    }
                }
            }
        }
        catch (RuntimeException exc){
        }
        return false;
    }
    public boolean fromJsonAdmin(json.Json json){
        try {
            String logonId = (String)json.getValue("logonId");
            if (null != logonId){

                Person person = Person.ForLongLogonId(logonId);
                if (null != person){
                    return this.setAdmin(person);
                }
                else {
                    person = this.getAdmin(MayNotInherit);
                    if (null != person){

                        if (person.setLogonId(logonId))
                            person.save();
                    }
                    else {

                        person = new Person(gap.Strings.RandomIdentifier());
                        person.setLogonId(logonId);
                        person.save();

                        return this.setAdmin(person);
                    }
                }
            }
        }
        catch (RuntimeException exc){
        }
        return false;
    }
    public boolean fromJsonCreated(json.Json json){
        return false;
    }
    public boolean fromJsonBilled(json.Json json){
        return false;
    }
    public boolean fromJsonPaid(json.Json json){
        return false;
    }
    // public boolean fromJsonRedirect(json.Json json){
    // }
    // public boolean fromJsonMargins(json.Json json){
    // }
    // public boolean fromJsonProfileLabels(json.Json json){
    // }
    public boolean fromJsonTest(json.Json json){
        return false;
    }
    public boolean fromJsonLabels(json.Json json){
        return false;
    }
    public List.Primitive<Key> listProjects(){
        return Project.List(this);
    }
    public List.Primitive<Key> listAccounts(){
        return Account.List(this);
    }
    public void dropProjects(){
        List.Primitive<Key> keys = this.listProjects();
        if (null != keys && keys.isNotEmpty()){

            List<Project> classes = Store.GetClass(keys);
            for (Project project: classes){

                project.drop();
            }
        }
    }
    private final static lxl.Map<String,Boolean> TestLook = new lxl.Map();
    static {
        TestLook.put("true",Boolean.TRUE);
        TestLook.put("TEST",Boolean.TRUE);

        TestLook.put("false",Boolean.FALSE);
        TestLook.put("LIVE",Boolean.FALSE);
    }
    private final static Boolean Test(String value, Boolean current){
        if (null == value || 1 > value.length()){

            if (null == current)
                return Boolean.TRUE;
            else
                return current;
        }
        else {
            Boolean newValue = TestLook.get(value);
            if (null != newValue)
                return newValue;
            else {

                if (null == current)
                    return Boolean.TRUE;
                else
                    return current;
            }
        }
    }
}
