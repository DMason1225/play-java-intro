package controllers;

import models.Employees;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;

import static play.libs.Json.toJson;

public class EmployeesController extends Controller
{
        private final FormFactory formFactory;
        private final JPAApi jpaApi;

        @Inject
        public EmployeesController(FormFactory formFactory, JPAApi jpaApi)
        {
            this.formFactory = formFactory;
            this.jpaApi = jpaApi;
        }

        @Transactional(readOnly = true)
        public Result getEmployees()
        {
            List<Employees> employees = (List<Employees>) jpaApi.em().
                    createQuery("select employeeId, LastName from Employees").getResultList();

            return ok(toJson(employees));
        }

        @Transactional(readOnly = true)
        public Result index()
        {
            List<Employees> employees = (List<Employees>) jpaApi.em().
                    createQuery("select e from Employees e").getResultList();
            return ok(views.html.employees.render(employees));
        }

    @Transactional(readOnly = true)
    public Result newEmployee()
    {
        return ok(views.html.newEmployee.render());
    }

    @Transactional
    public Result addEmployee()
    {
        //Map the data from the html form into an instance of the Employees model
        //Categories.class lets the form factory create an instance of Employees
        //Values from the form are pushed into the model based on name of form field match name of variable in model

        Employees employee = formFactory.form(Employees.class).bindFromRequest().get();

        //Save the new employee to the database
        jpaApi.em().persist(employee);

        //Send the browser to the list of employees page
        return redirect(routes.EmployeesController.index());
    }

    @Transactional(readOnly = true)
    public Result editEmployee(Long employeeId)
    {
        //Get the model that we need to edit based on the passed in categoryId
        Employees employee = (Employees) jpaApi.em()
                .createQuery("select e from Employees e where employeeId = :id")
                .setParameter("id", employeeId).getSingleResult();

        //Send the model we got to the update view
        return ok(views.html.updateEmployee.render(employee));
    }

    @Transactional
    public Result updateEmployee()
    {
        //Gets the data from the form the user submitted
        DynamicForm postedForm = formFactory.form().bindFromRequest();

        //Copy the form values out into local variables
        Long employeeId = new Long(postedForm.get("employeeId"));
        String firstName = postedForm.get("firstName");
        String lastName = postedForm.get("lastName");
        String title = postedForm.get("title");
        String titleOfCourtesy = postedForm.get("titleOfCourtesy");
        String address = postedForm.get("address");
        String city = postedForm.get("city");
        String notes = postedForm.get("notes");



        //Get the model of the category we want to update
        Employees employee = (Employees) jpaApi.em()
                .createQuery("select e from Employees e where employeeId = :id")
                .setParameter("id", employeeId).getSingleResult();

        //Copy new values into model
        employee.firstName = firstName;
        employee.lastName = lastName;
        employee.title = title;
        employee.titleOfCourtesy = titleOfCourtesy;
        employee.address = address;
        employee.city = city;
        employee.notes = notes;



        //Save the model to the database
        jpaApi.em().persist(employee);

        //Send the user to the list of categories view
        return redirect(routes.EmployeesController.index());
    }

    @Transactional(readOnly = true)
    public Result getPicture(Long id)
    {
        Employees employee = (Employees)jpaApi.em().
                createQuery("select e from Employees e where employeeid = :id").
                setParameter("id", id).getSingleResult();

        if (employee.photo == null)
        {
            return null;
        }
        else
        {
            return ok(employee.photo).as("image/bmp");
        }
    }
}
