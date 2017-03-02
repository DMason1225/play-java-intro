package controllers;

import models.Customers;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by lrflr on 2/6/2017.
 */
public class CustomersController extends Controller
{
    private final FormFactory formFactory;
    private final JPAApi jpaApi;

    @Inject
    public CustomersController(FormFactory formFactory, JPAApi jpaApi)
    {
        this.formFactory = formFactory;
        this.jpaApi = jpaApi;
    }

    @Transactional(readOnly = true)
    public Result index()
    {
        List<Customers> customers = (List<Customers>) jpaApi.em().createQuery("select c from Customers c order by contactName").getResultList();

        return ok(views.html.customers.render(customers));
    }

}