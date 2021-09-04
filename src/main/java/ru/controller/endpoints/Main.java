package ru.controller.endpoints;

import ru.modeldb.Emp;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.awt.*;
import java.util.Date;
import java.util.List;

@Path("/main")
@Consumes(value = MediaType.APPLICATION_JSON+";charset=utf-8")
@Produces(value = MediaType.APPLICATION_JSON+";charset=utf-8")
public class Main {
    @PersistenceContext(unitName = "ora-scott")
    private EntityManager em;

    @Path("/info")
    @GET
    @Consumes(value = MediaType.TEXT_PLAIN+";charset=utf-8")
    @Produces(value = MediaType.TEXT_PLAIN+";charset=utf-8")
    public String info(){
        return "Hello world "+em;
    }

    @Path("/depts")
    @GET
    public List<Object[]> getDepts(){
        return em.createNativeQuery("select deptno, dname from dept order by dname")
                .getResultList();
    }

    @Path("/emps/{id}")
    @GET
    public List<Emp> getEmpsById(@PathParam(value = "id")int id){
//        System.out.println("-----"+id);
        return em.createNamedQuery("GetEmpsById", Emp.class)
                .setParameter("p", id)
                .getResultList();
    }

    @Path("/emp")
    @GET
    public Emp getEmpById(@QueryParam(value = "id")int id){
//        System.out.println("+++++"+id);
        return em.find(Emp.class,id);
    }
    @Path("/badupdate")
    @GET
    public Emp badUpdate(){
        Emp emp= em.find(Emp.class,7788);
        emp.setSal(emp.getSal()+1);
        em.merge(emp);
        return emp;
    }
}
