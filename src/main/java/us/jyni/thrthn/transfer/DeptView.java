/*
 * 
 */
package us.jyni.thrthn.transfer;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import us.jyni.thrthn.common.EntityView;
import us.jyni.thrthn.entity.Dept;

/**
 * @author jynius
 * @Since 2020-08-01
 */
@Data
@EqualsAndHashCode
@ToString
public class DeptView implements EntityView<Dept>, Serializable {

	private static final long serialVersionUID = 3271337051605465204L;

	private Integer id;
	private Integer parentId;
	private Integer order;
	private String name;
	private LocalDateTime time;
	
	private DeptView parent;
	private List<DeptView> children;

	@Override
	public void setEntity(Dept entity) {
		EntityView.super.setEntity(entity);
		setParent(EntityView.of(entity.getParent(), DeptView.class));
		setChildren(EntityView.from(entity.getChildren(), DeptView.class));
	}

	public void setEntity(Dept entity, DeptView parent) {
		EntityView.super.setEntity(entity);
		setParent(parent);
		setChildren(EntityView.from(entity.getChildren(), DeptView.class));
	}
}
