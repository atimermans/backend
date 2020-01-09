package proyecto_final.backend;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.JoinColumn;

@Entity // This tells Hibernate to make a table out of this class
public class Item {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer id;
  private String name;
  private Boolean done;
  @JsonBackReference
  @ManyToOne(fetch=FetchType.LAZY)
  @JoinColumn(name = "checkList")
  private CheckList checkList;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Boolean getDone() {
    return done;
  }

  public void setDone(Boolean done) {
    this.done = done;
  }
  
  public void check() {
	  done = !done;
  }

  public CheckList getCheckList() {
	return checkList;
  }

  public void setCheckList(CheckList checkList) {
	this.checkList = checkList;
  }

}