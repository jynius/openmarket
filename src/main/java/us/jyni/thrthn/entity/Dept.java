/*
 * 
 */
package us.jyni.thrthn.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author jynius
 * @Since 2020-08-01
 */
@Entity
@Data
@EqualsAndHashCode
@ToString
public class Dept implements Serializable {

  private static final long serialVersionUID = -1414466401626871778L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "parentId", insertable = false, updatable = false) 
  private Dept parent;
  private Integer parentId;

  @OneToMany(mappedBy = "parentId", cascade = CascadeType.ALL)
  @OrderColumn(name = "`order`")
  private List<Dept> children;
  
  private String code;
  private Integer level;
  private String fullCode;
  private String fullId;
  private String name;
  private LocalDateTime time;
}
