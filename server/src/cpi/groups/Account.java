
package cpi.groups;

import oso.data.Merchant;
import oso.data.Person;
import cpi.Redirect;
import cpi.Margins;

import json.Json;

import gap.*;
import gap.data.*;
import gap.hapax.TemplateException;
import gap.hapax.TemplateName;
import gap.hapax.TemplateRenderer;
import gap.service.Templates;
import gap.util.*;

import com.google.checkout.sdk.commands.CartPoster;
import com.google.checkout.sdk.commands.CartPoster.CheckoutShoppingCartBuilder;
import com.google.checkout.sdk.commands.CheckoutException;
import com.google.checkout.sdk.domain.CheckoutRedirect;
import com.google.checkout.sdk.domain.DigitalContent;
import com.google.checkout.sdk.domain.Item;

import com.google.appengine.api.blobstore.*;
import com.google.appengine.api.datastore.*;
import com.google.appengine.api.mail.*;

import java.io.IOException;
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
    /**
     * @return The merchant singleton (for checkout or notification processing)
     */
    public static Merchant GetMerchant(){

        Merchant merchant = Merchant.Instance();
        if (merchant.isTest())
            merchant.setEnvironment(com.google.checkout.sdk.commands.Environment.SANDBOX);
        else
            merchant.setEnvironment(com.google.checkout.sdk.commands.Environment.PRODUCTION);

        return merchant;
    }

    private final static String MessageTitle = "CPI Online for Groups";
    private final static String MessageSender = "John Pritchard <jdp@syntelos.com>";

    final static class Names {
        final static TemplateName MessageTitle = new TemplateName("messageTitle");
        final static TemplateName MessageDescription = new TemplateName("messageDescription");
        final static TemplateName MessageAmount = new TemplateName("messageAmount");
        final static TemplateName MessageCurrency = new TemplateName("messageCurrency");
    }


    public static class Amount {
        /*
         * Account debits
         */
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

            if (group.isNotTest()){
                Account account = new Account(Strings.RandomIdentifier());
                account.setGroup(group);
                account.setProject(project);
                account.setClosed(Boolean.FALSE);
                account.setAmount(Account.Amount.PeriodicFee(project));
                account.save();

                account.noteNewProject(group,project,request,"Created Project");
            }
        }
        public static void MaintainProject(Group group, Project project, Request request){

            if (group.isNotTest()){
                Account account = new Account(Strings.RandomIdentifier());
                account.setGroup(group);
                account.setProject(project);
                account.setClosed(Boolean.FALSE);
                account.setAmount(Account.Amount.PeriodicFee(project));
                account.save();

                account.note(request,"Project Maintenance");
            }
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


    public boolean updateFrom(Request req) throws ValidationError {
        boolean change = false;

        return change;
    }
    public boolean isClosed(){
        final Boolean closed = this.getClosed();
        return (null != closed && closed);
    }
    public boolean isNotClosed(){
        final Boolean closed = this.getClosed();
        return (null == closed || (!closed));
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
            if (null == text)
                return;
            else {
                note.setText(Strings.TextFromString(text));
            }
        }
        note.save();
    }
    public void noteAs(Request request, String text){

        final Note note = new Note(this,Strings.RandomIdentifier());

        note.setWriter(request.getViewer());

        final Date date = new Date();
        note.setCreated(date);
        note.setUpdated(date);
        note.setText(Strings.TextFromString(text));

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
    public boolean fromJsonIdentifier(Json json){
        return false;
    }
    public boolean fromJsonGroup(Json json){
        return false;
    }
    public boolean fromJsonProject(Json json){
        return false;
    }
    public boolean fromJsonClosed(Json json){
        return false;
    }
    public boolean fromJsonAmount(Json json){
        return false;
    }
    public boolean fromJsonCurrency(Json json){
        return false;
    }
    public boolean fromJsonNotes(Json json){
        return false;
    }
    public boolean fromJsonCheckoutSerialNumber(Json json){
        return false;
    }
    public boolean fromJsonCheckoutUrl(Json json){
        return false;
    }
    /**
     * Generate and post the checkout shopping cart if not previously
     * performed, and generate and send the SMTP account mail message.
     * 
     * @return Successful SMTP service send
     */
    public boolean post(Request q, Response p) 
        throws CheckoutException, TemplateException, IOException
    {
        final String description = this.getPostDescription();
        final float amount = this.getPostAmount();
        final String mailto = this.getGroup().getSmtpRecipient();

        final Merchant merchant = Account.GetMerchant();

        if (this.hasNotCheckoutUrl(false)){

            final CartPoster poster = merchant.cartPoster();
            final CheckoutShoppingCartBuilder cart = poster.makeCart();


            final Item item = new Item();
            {
                item.setItemName(Account.MessageTitle);
                item.setItemDescription(description);
                item.setUnitPrice(merchant.makeMoney(amount));
                item.setQuantity(1);
            }
            final DigitalContent content = new DigitalContent();
            {
                content.setEmailDelivery(true);
                content.setDisplayDisposition("OPTIMISTIC");

                item.setDigitalContent(content);
            }
            cart.addItem(item);

            final CheckoutRedirect checkout = cart.buildAndPost();

            this.setCheckoutSerialNumber(checkout.getSerialNumber());
            this.setCheckoutUrl(checkout.getRedirectUrl());
            this.save();

            this.noteAs(q,"Posted");
        }

        final MailService smtp = q.getMailService();
        if (null != smtp){
            final TemplateRenderer template = Templates.GetTemplate("mail.account.html");
            this.setVariable(Account.Names.MessageTitle,Account.MessageTitle);
            this.setVariable(Account.Names.MessageDescription,description);
            this.setVariable(Account.Names.MessageAmount,gap.Strings.FloatToString(amount));
            this.setVariable(Account.Names.MessageCurrency,merchant.getCurrencyCode());
            final String html = template.renderToString(this);

            MailService.Message mail = new MailService.Message();
            mail.setSender(Account.MessageSender);
            mail.setReplyTo(Account.MessageSender);
            mail.setTo(mailto);
            mail.setSubject(Account.MessageTitle+" transaction available");
            mail.setHtmlBody(html);

            smtp.send(mail);
            return true;
        }
        else
            return false;
    }

    private String getPostDescription(){
        final Group group = this.getGroup();
        final Project project = this.getProject();

        StringBuilder description = new StringBuilder();

        if (null != project)
            description.append(String.format("Group '%s', Project '%s' Services",group.getName(),project.getName()));
        else
            description.append(String.format("Group '%s' Services",group.getName()));

        boolean leader = true;

        for (Note note: this.getNotes()){

            String text = gap.Strings.TextToString(note.getText());
            if (null != text){

                if (leader){
                    leader = false;
                    description.append(": ");

                    description.append(text);
                }
                break;
            }
        }

        return description.toString();
    }
    private float getPostAmount(){
        Float amount = this.getAmount();
        if (null == amount)
            throw new IllegalStateException("Missing amount");
        else
            return -(amount.floatValue());
    }
}
