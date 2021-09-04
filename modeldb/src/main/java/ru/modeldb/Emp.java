package ru.modeldb;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "EMP", schema = "SCOTT")
@NamedQueries(value = {
        @NamedQuery(name = "GetEmpsById", query = "select o from Emp o where o.deptno = :p order by o.ename")
})
public class Emp implements Serializable {
    @Id
    @Column(name = "EMPNO")
    private int id;
    private int deptno;
    private String ename;
    private String job;
    @Temporal(TemporalType.DATE)
    private Date hiredate;
    private Float sal;

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", deptno=" + deptno +
                ", ename='" + ename + '\'' +
                ", job='" + job + '\'' +
                ", hiredate=" + hiredate +
                ", sal=" + sal +
                '}';
    }

    public Emp(int id, int deptno, String ename, String job, Date hiredate, Float sal) {
        this.id = id;
        this.deptno = deptno;
        this.ename = ename;
        this.job = job;
        this.hiredate = hiredate;
        this.sal = sal;
    }

    public Emp() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDeptno() {
        return deptno;
    }

    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public Float getSal() {
        return sal;
    }

    public void setSal(Float sal) {
        this.sal = sal;
    }
}
