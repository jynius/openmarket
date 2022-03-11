/*
 * 
 */
package us.jyni.thrthn.transfer;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import us.jyni.thrthn.common.FormEntity;
import us.jyni.thrthn.entity.Dept;

/**
 * @author jynius
 * @Since 2020-08-01
 */
@Data
@EqualsAndHashCode
@ToString
public class DeptForm implements FormEntity<Dept>, Serializable {

	private static final long serialVersionUID = 3271337051605465204L;

	private Integer id;
	private Integer parentId;
	private Integer order;
	private String name;
	private LocalDateTime time;
	
	private DeptForm parent;
	private List<DeptForm> children;

	public Dept getEntity() {
		Dept entity = new Dept();
		BeanUtils.copyProperties(this, entity);
		return entity;
	}
}
