package proyecto_final.backend;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
public class CheckList {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer id;
  private String name;
  private String description;
  @JsonManagedReference
  @OneToMany(mappedBy = "checkList", cascade = CascadeType.REMOVE)
  private Set<Item> items;

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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Set<Item> getItems() {
	return items;
  }

  public void setItems(Set<Item> items) {
	this.items = items;
  }


}