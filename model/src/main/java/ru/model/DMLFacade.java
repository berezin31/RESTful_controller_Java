package ru.model;


import ru.modeldb.Emp;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@Path("/dml")
@Consumes(value = MediaType.APPLICATION_JSON+";charset=utf-8")
@Produces(value = MediaType.APPLICATION_JSON+";charset=utf-8")
public class DMLFacade {
    @EJB
    private SendMessage sendMessage;

    @PersistenceContext(unitName = "ora-scott")
    private EntityManager em;

    @Path("/update")
    @PUT
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Emp update(Emp newEmp){
        System.out.println("======"+newEmp);
        Emp emp= em.find(Emp.class,newEmp.getId());
        emp.setSal(emp.getSal()+1);
        em.merge(emp);
        //////////////////////////////////
        sendMessage.sendToJMS(emp);
        return emp;
    }
}
