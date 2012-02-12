
package cpi.groups;

import cpi.Redirect;
import cpi.Margins;
import cpi.Tail;

import gap.Request;
import gap.Response;
import gap.Strings;
import gap.data.List;
import gap.util.Page;

import json.Json;

import oso.data.Person;

import com.google.appengine.api.datastore.Key;

import javax.servlet.ServletException;

import java.io.IOException;
import java.util.Date;


/**
 * Bound to "/members/"
 *
 * @see Member
 */
public final class MemberServlet
    extends gap.service.Servlet
{

    public MemberServlet() {
        super();
    }

}
