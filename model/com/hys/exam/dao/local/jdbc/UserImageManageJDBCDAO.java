package com.hys.exam.dao.local.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.common.util.StringUtil;
import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.UserImageManageDAO;

import com.hys.exam.model.ExamProp;
import com.hys.exam.model.GeneralUserImage;
import com.hys.exam.model.ModelType;
import com.hys.exam.model.PropUnit;
import com.hys.exam.model.SpecialUserImage;
import com.hys.exam.model.UserImage;
import com.hys.exam.utils.StringUtils;
import com.ibm.wsdl.Constants;


public class UserImageManageJDBCDAO extends BaseDao implements
		UserImageManageDAO {

	@Override
	public List<UserImage> getUserImageList(UserImage userImage) {
		
		List<UserImage> userImageList = new ArrayList<UserImage>();
		StringBuffer sql = new StringBuffer();
		
		if (userImage.getId() != null) {
			sql.append("select * from QM_USER_IMAGE where ID=").append(userImage.getId());
			userImageList = getJdbcTemplate().query(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(UserImage.class));
			
			for (UserImage image:userImageList) {
				
				String tmp_sql = "select TYPEID from QM_USER_IMAGE where ID=" + image.getId();
				Long type_id = getJdbcTemplate().queryForLong(tmp_sql);
				tmp_sql = "select id,name from exam_prop_val where ID=" + type_id+" and type=24";
				
				List<ModelType> modelTypeList = getJdbcTemplate().query(tmp_sql, ParameterizedBeanPropertyRowMapper.newInstance(ModelType.class));

				image.setType(modelTypeList.get(0));

				String prop_sql = "select t1.prop_id as id, t3.name from QM_USER_IMAGE_PROP t1, EXAM_PROP_VAL t3 where t1.prop_id=t3.id and t1.userimage_id=" + image.getId();
				List<PropUnit> departmentPropList = getJdbcTemplate().query(prop_sql, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
				
				image.setDepartmentPropList(departmentPropList);				
			}
		} else if (userImage.getType() == null) {
		
			
			sql.append("select id,name from exam_prop_val where id>0 and type=24");
			List<ModelType> modelTypeList = getJdbcTemplate().query(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(ModelType.class));
			for (ModelType modelType:modelTypeList) {
				UserImage model = new UserImage();
				model.setType(modelType);
				userImageList.add(model);
			}
		} else {
			if (userImage.getType().getId() == null) {
				
				sql.append("select id,name  from exam_prop_val where id>0 ");
				
				if(!StringUtil.checkNull(userImage.getType().getName())){
					sql.append(" and name like '%").append(userImage.getType().getName()).append("%'");
				}
				List<ModelType> modelTypeList = getJdbcTemplate().query(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(ModelType.class));
				
				for (ModelType modelType:modelTypeList) {
					UserImage model = new UserImage();
					model.setType(modelType);
					userImageList.add(model);
				}
			} else {
				
				sql.append("select DISTINCT t.* from QM_USER_IMAGE t, QM_USER_IMAGE_PROP t1, QM_USER_IMAGE_GENERAL_PROP t2, QM_USER_IMAGE_SPECIAL_PROP t3 where t.id>0 ");
				
				sql.append(" and t.TYPEID=").append(userImage.getType().getId());
			//Searching By UserImageName area
				if(!StringUtil.checkNull(userImage.getName())){
					sql.append(" and t.name like '%").append(userImage.getName()).append("%'");
				}
			//Searching By Department
				if(userImage.getDepartmentPropList()!=null && userImage.getDepartmentPropList().size()>0)
				{
					sql.append(" and t.ID=t1.USERIMAGE_ID and t1.PROP_ID in (");
					for (int i=0; i<userImage.getDepartmentPropList().size(); i++) {
						if (i > 0) sql.append(",");
						sql.append(userImage.getDepartmentPropList().get(i).getId());
					}
					sql.append(")");
				}
				if (userImage.getGeneralUserImage() != null && userImage.getGeneralUserImage().getDutyPropList()!=null && userImage.getGeneralUserImage().getDutyPropList().size()>0)
				{
					sql.append(" and t.ID=t2.IMAGE_ID and t2.PROP_ID in (");
					for (int i=0; i<userImage.getGeneralUserImage().getDutyPropList().size(); i++) {
						if (i > 0) sql.append(",");
						sql.append(userImage.getGeneralUserImage().getDutyPropList().get(i).getId());
					}
					sql.append(")");					
				}
				if (userImage.getSpecialUserImage() != null && userImage.getSpecialUserImage().getDutyPropList()!=null && userImage.getSpecialUserImage().getDutyPropList().size()>0) {
					sql.append(" and t.ID=t3.IMAGE_ID and t3.PROP_ID in(");
					for (int i=0; i<userImage.getSpecialUserImage().getDutyPropList().size(); i++) {
						if (i > 0) sql.append(",");
						sql.append(userImage.getSpecialUserImage().getDutyPropList().get(i).getId());
					}
					sql.append(")");	
				}
				
				userImageList = getJdbcTemplate().query(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(UserImage.class));
				
				for (UserImage image:userImageList) {
					
					String type_sql = "select id,name from exam_prop_val where ID=" + userImage.getType().getId();
					List<ModelType> mTypeList = getJdbcTemplate().query(type_sql, ParameterizedBeanPropertyRowMapper.newInstance(ModelType.class));
					
					image.setType(mTypeList.get(0));
					
					String prop_sql1 = "select t1.prop_id as id, t3.name from QM_USER_IMAGE_PROP t1, EXAM_PROP_VAL t3 where t1.prop_id=t3.id and t1.userimage_id=" + image.getId();
					//Display General Userimage sql statement
					String prop_sql2 = "select t1.prop_id as id, t3.name from QM_USER_IMAGE_GENERAL_PROP t1, EXAM_PROP_VAL t3 where t1.prop_id=t3.id and t1.image_id=" + image.getId();
					//Display Sepcial Userimage sql statement
					String prop_sql3 = "select t1.prop_id as id, t3.major as name from QM_USER_IMAGE_SPECIAL_PROP t1, EXAM_MAJOR_ORDER t3 where t1.prop_id=t3.id and t1.image_id=" + image.getId();
				
					//Start area of Display GeneralUserImage
				
					List<PropUnit> departmentPropList = getJdbcTemplate().query(prop_sql1, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
					image.setDepartmentPropList(departmentPropList);
					GeneralUserImage generalUserImage = new GeneralUserImage();
					
					//Display HospitalPropList
					String prop_sql_hospital =prop_sql2+ " and t1.TYPE=" +com.hys.exam.constants.Constants.PROP_HOSPITAL_TYPE;
					List<PropUnit> hospitalPropList = getJdbcTemplate().query(prop_sql_hospital,  ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
					generalUserImage.setHospitalPropList(hospitalPropList);
				
					
					//Display AreaPropList
					String prop_sql_area = prop_sql2+ " and t1.TYPE=" +com.hys.exam.constants.Constants.PROP_LOCALAREA_TYPE;
					
					List<PropUnit> areaPropList = getJdbcTemplate().query(prop_sql_area,ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class) );
					generalUserImage.setAreaPropList(areaPropList);
					
					
					//Display general DutyPropList
					String prop_sql_duty_general = prop_sql2 +" and t1.TYPE=" + com.hys.exam.constants.Constants.PROP_DUTY_TYPE;
					
					List<PropUnit> dutyPropList_general = getJdbcTemplate().query(prop_sql_duty_general,ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class) );
					generalUserImage.setDutyPropList(dutyPropList_general);
					image.setGeneralUserImage(generalUserImage);
					
					
					
					
				// End of Display GeneralUserImage
					
				//Start of Display SpecialUserImage
					
					SpecialUserImage specialUserImage = new SpecialUserImage();
					
					
					//Display majorPropList
					
					String prop_sql_major = prop_sql3 + " and t1.TYPE=" + com.hys.exam.constants.Constants.PROP_MAJOR_TYPE;
					
					List<PropUnit> majorPropList = getJdbcTemplate().query(prop_sql_major,  ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
					specialUserImage.setMajorPropList(majorPropList);
				
					
					//Display dutyPropList
					String prop_sql_duty_special= prop_sql2+ " and t1.TYPE=" +com.hys.exam.constants.Constants.PROP_DUTY_TYPE;
					List<PropUnit> dutyPropList_special = getJdbcTemplate().query(prop_sql_duty_special,  ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
					specialUserImage.setDutyPropList(dutyPropList_special);
					image.setSpecialUserImage(specialUserImage);
					
					
				}
			}
		}
			
		return userImageList;
	}

	@Override
	public boolean addUserImage(UserImage userImage) {
		
		Long id = userImage.getId();
		if (id == null) id = this.getNextId("QM_USER_IMAGE");
		userImage.setId(id);
		String add_name_userImage = userImage.getName();
		ModelType modelType = userImage.getType();
		GeneralUserImage generalUserImage = userImage.getGeneralUserImage();
		SpecialUserImage specialUserImage = userImage.getSpecialUserImage();
		List<PropUnit> departmentList = userImage.getDepartmentPropList();
				
		String sql = "";
		if (modelType != null) {
			sql = "INSERT INTO QM_USER_IMAGE (ID,TYPEID,NAME) VALUES (?, ?, ?)";
			getSimpleJdbcTemplate().update(sql, id, modelType.getId(), add_name_userImage);
		} else {
			sql = "INSERT INTO QM_USER_IMAGE (ID,NAME) VALUES (?, ?)";
			getSimpleJdbcTemplate().update(sql, id, add_name_userImage);
		}
		
		if(departmentList != null && departmentList.size() > 0)
			for(int i=0;i<departmentList.size();i++) {
				sql = "insert into QM_USER_IMAGE_PROP (USERIMAGE_ID, PROP_ID) values (?, ?)";
				getSimpleJdbcTemplate().update(sql, id, departmentList.get(i).getId());
			}
		
		if (generalUserImage != null) {
			
			List<PropUnit> areaPropList = generalUserImage.getAreaPropList();
			List<PropUnit> hospitalPropList = generalUserImage.getHospitalPropList();
			List<PropUnit> dutyPropList =  generalUserImage.getDutyPropList();
			
			if(areaPropList!=null && areaPropList.size()>0)
			{
				for(int i=0;i<areaPropList.size();i++) {
					sql = "insert into QM_USER_IMAGE_GENERAL_PROP (IMAGE_ID, PROP_ID, TYPE) values (?, ?, ?)";
					getSimpleJdbcTemplate().update(sql, id, areaPropList.get(i).getId(), com.hys.exam.constants.Constants.PROP_LOCALAREA_TYPE);
				}
			}
			
			if(hospitalPropList!=null && hospitalPropList.size()>0)
			{
				for(int i=0;i<hospitalPropList.size();i++) {
					sql = "insert into QM_USER_IMAGE_GENERAL_PROP (IMAGE_ID, PROP_ID, TYPE) values (?, ?, ?)";
					getSimpleJdbcTemplate().update(sql, id, hospitalPropList.get(i).getId(), com.hys.exam.constants.Constants.PROP_HOSPITAL_TYPE);
				}
			}
			
			if(dutyPropList!=null && dutyPropList.size()>0)
			{
				for(int i=0;i<dutyPropList.size();i++) {
					sql = "insert into QM_USER_IMAGE_GENERAL_PROP (IMAGE_ID, PROP_ID, TYPE) values (?, ?, ?)";
					getSimpleJdbcTemplate().update(sql, id, dutyPropList.get(i).getId(), com.hys.exam.constants.Constants.PROP_DUTY_TYPE);
				}
			}
		}
		
		if (specialUserImage != null) {
			
			List<PropUnit> majorPropList = specialUserImage.getMajorPropList();			
			List<PropUnit> dutyPropList =  specialUserImage.getDutyPropList();
			
			if(majorPropList!=null && majorPropList.size()>0)
			{
				for(int i=0;i<majorPropList.size();i++) {
					sql = "insert into QM_USER_IMAGE_SPECIAL_PROP (IMAGE_ID, PROP_ID, TYPE) values (?, ?, ?)";
					getSimpleJdbcTemplate().update(sql, id, majorPropList.get(i).getId(), com.hys.exam.constants.Constants.PROP_MAJOR_TYPE);
				}
			}
			
			if(dutyPropList!=null && dutyPropList.size()>0)
			{
				for(int i=0;i<dutyPropList.size();i++) {
					sql = "insert into QM_USER_IMAGE_SPECIAL_PROP (IMAGE_ID, PROP_ID, TYPE) values (?, ?, ?)";
					getSimpleJdbcTemplate().update(sql, id, dutyPropList.get(i).getId(), com.hys.exam.constants.Constants.PROP_DUTY_TYPE);
				}
			}
		}

		
		return true;
	}

	@Override
	public boolean updateUserImage(UserImage userImage) {

		
		
		List<PropUnit> departmentList = userImage.getDepartmentPropList();
		String sql_update_userImageprop = null;
		if(departmentList != null && departmentList.size() >0){
			for(int i=0;i<departmentList.size();i++){
				sql_update_userImageprop = "UPDATE QM_USER_IMAGE_PROP SET PROP_ID="+departmentList.get(i).getId()+" WHERE USERIMAGE_ID="+userImage.getId();
			}
			getSimpleJdbcTemplate().update(sql_update_userImageprop);
		}
		String sql_general_update_hospital = "";
		String sql_general_update_duty="";
		String sql_general_update_area="";
		if(userImage.getGeneralUserImage() != null )
		{
			List<PropUnit> hospital = userImage.getGeneralUserImage().getHospitalPropList();
			List<PropUnit> compare_hospital = new ArrayList<PropUnit>();
			String sql_Compare_hospital = "select PROP_ID as id from qm_user_image_general_prop where image_id="+userImage.getId()+" and type="+com.hys.exam.constants.Constants.PROP_HOSPITAL_TYPE;
			compare_hospital = getJdbcTemplate().query(sql_Compare_hospital, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
			if(hospital != null && hospital.size()>0){
				if(compare_hospital.size() < hospital.size()){
					for(int i=0;i<compare_hospital.size();i++){
						sql_general_update_hospital = "UPDATE QM_USER_IMAGE_GENERAL_PROP SET PROP_ID="+ hospital.get(i).getId()+" WHERE IMAGE_ID="+userImage.getId()+" AND TYPE="+com.hys.exam.constants.Constants.PROP_HOSPITAL_TYPE;
						getSimpleJdbcTemplate().update(sql_general_update_hospital);
					}
					for(int i=compare_hospital.size();i<hospital.size();i++){
						String sql_add = "INSERT INTO QM_USER_IMAGE_GENERAL_PROP (IMAGE_ID,PROP_ID,TYPE) VALUES(?,?,?)";
						getSimpleJdbcTemplate().update(sql_add,userImage.getId(),hospital.get(i).getId(),com.hys.exam.constants.Constants.PROP_HOSPITAL_TYPE );
					}
				}else{
					for(int i=0;i<hospital.size();i++){
						sql_general_update_hospital = "UPDATE QM_USER_IMAGE_GENERAL_PROP SET PROP_ID="+ hospital.get(i).getId()+" WHERE IMAGE_ID="+userImage.getId()+" AND TYPE="+com.hys.exam.constants.Constants.PROP_HOSPITAL_TYPE;
						getSimpleJdbcTemplate().update(sql_general_update_hospital);
					}
				}
			}
			
			List<PropUnit> duty = userImage.getGeneralUserImage().getDutyPropList();
			List<PropUnit> compare_duty = new ArrayList<PropUnit>();
			String sql_compare_duty = "select prop_id as id from qm_user_image_general_prop where image_id="+userImage.getId()+" and type="+com.hys.exam.constants.Constants.PROP_DUTY_TYPE;
			compare_duty = getJdbcTemplate().query(sql_compare_duty, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
			
			if(duty != null && duty.size()>0)
			{
				if(compare_duty.size()<duty.size()){
					for(int i=0;i<compare_duty.size();i++){
						sql_general_update_duty = "UPDATE QM_USER_IMAGE_GENERAL_PROP SET PROP_ID="+duty.get(i).getId() +" WHERE IMAGE_ID="+userImage.getId()+"  AND TYPE="+com.hys.exam.constants.Constants.PROP_DUTY_TYPE;
						getSimpleJdbcTemplate().update(sql_general_update_duty);
					}
					for(int i=compare_duty.size();i<duty.size();i++){
						String sql_add = "insert into qm_user_image_general_prop (image_id,prop_id,type) values (?,?,?)";
						getSimpleJdbcTemplate().update(sql_add,userImage.getId(),duty.get(i).getId(),com.hys.exam.constants.Constants.PROP_DUTY_TYPE);
					}
				}else {
					for(int i=0;i<duty.size();i++){
						sql_general_update_duty = "UPDATE QM_USER_IMAGE_GENERAL_PROP SET PROP_ID="+duty.get(i).getId() +" WHERE IMAGE_ID="+userImage.getId()+"  AND TYPE="+com.hys.exam.constants.Constants.PROP_DUTY_TYPE;
						getSimpleJdbcTemplate().update(sql_general_update_duty);
					}
				}
			}
			
			List<PropUnit> area = userImage.getGeneralUserImage().getAreaPropList();
			List<PropUnit> compare_area = new ArrayList<PropUnit>();
			String sql_compare_area = "select prop_id as id from qm_user_image_general_prop where image_id="+userImage.getId()+" and type="+com.hys.exam.constants.Constants.PROP_LOCALAREA_TYPE;
			compare_area = getJdbcTemplate().query(sql_compare_area, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
			if(area != null && area.size()>0){
				if(compare_area.size()<area.size()){
					for(int i=0;i<compare_area.size();i++){
						sql_general_update_area = "UPDATE QM_USER_IMAGE_GENERAL_PROP SET PROP_ID="+area.get(i).getId() +" WHERE IMAGE_ID="+userImage.getId()+" AND TYPE="+com.hys.exam.constants.Constants.PROP_LOCALAREA_TYPE;
						getSimpleJdbcTemplate().update(sql_general_update_area);
					}
					for(int i=compare_area.size();i<duty.size();i++){
						String sql_add = "insert into qm_user_image_general_prop (image_id,prop_id,type) values (?,?,?)";
						getSimpleJdbcTemplate().update(sql_add,userImage.getId(),duty.get(i).getId(),com.hys.exam.constants.Constants.PROP_LOCALAREA_TYPE);
					}
				}else{
					for(int i=0;i<area.size();i++){
						sql_general_update_area = "UPDATE QM_USER_IMAGE_GENERAL_PROP SET PROP_ID="+area.get(i).getId() +" WHERE IMAGE_ID="+userImage.getId()+" AND TYPE="+com.hys.exam.constants.Constants.PROP_LOCALAREA_TYPE;
						getSimpleJdbcTemplate().update(sql_general_update_area);
					}
				}
			}
		}
		
		if(userImage.getSpecialUserImage() != null)
		{
			String sql_update_sepcial_major = "";
			String sql_update_sepcial_duty = "";
			List<PropUnit> compare_major = new ArrayList<PropUnit>();
			String sql_compare_major = "select prop_id as id from qm_user_image_sepcial_prop where image_id="+userImage.getId()+" and type="+com.hys.exam.constants.Constants.PROP_MAJOR_TYPE;
			compare_major = getJdbcTemplate().query(sql_compare_major, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
			List<PropUnit> major = userImage.getSpecialUserImage().getMajorPropList();
			
			if(major != null && major.size()>0){
				if(compare_major.size()<major.size()){
					for(int i=0;i<compare_major.size();i++){
						sql_update_sepcial_major = "UPDATE QM_USER_IMAGE_SPECIAL_PROP SET PROP_ID="+major.get(i).getId()+" WHERE IMAGE_ID="+userImage.getId()+" AND TYPE="+com.hys.exam.constants.Constants.PROP_MAJOR_TYPE;
						getSimpleJdbcTemplate().update(sql_update_sepcial_major);
					}
					for(int i=compare_major.size();i<major.size();i++){
						String sql_add = "insert into qm_user_image_sepcial_prop (image_id,prop_id,type) values (?,?,?)";
						getSimpleJdbcTemplate().update(sql_add, userImage.getId(),major.get(i).getId(),com.hys.exam.constants.Constants.PROP_MAJOR_TYPE);
					}
				}else{
					for(int i=0;i<major.size();i++){
						sql_update_sepcial_major = "UPDATE QM_USER_IMAGE_SPECIAL_PROP SET PROP_ID="+major.get(i).getId()+" WHERE IMAGE_ID="+userImage.getId()+" AND TYPE="+com.hys.exam.constants.Constants.PROP_MAJOR_TYPE;
						getSimpleJdbcTemplate().update(sql_update_sepcial_major);
					}
				}
			}
			List<PropUnit> compare_spe_duty = new ArrayList<PropUnit>();
			String sql_compare_duty =  "select prop_id as id from qm_user_image_special_prop where image_id="+userImage.getId()+" and type="+com.hys.exam.constants.Constants.PROP_DUTY_TYPE;
			compare_spe_duty = getJdbcTemplate().query(sql_compare_duty, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
			List<PropUnit> spe_duty = userImage.getSpecialUserImage().getDutyPropList();
			
			if(spe_duty != null && spe_duty.size()>0){
				if(compare_spe_duty.size()<spe_duty.size()){
					for(int i=0;i<compare_spe_duty.size();i++){
						sql_update_sepcial_duty = "UPDATE QM_USER_IMAGE_SPECIAL_PROP SET PROP_ID="+spe_duty.get(i).getId()+" WHERE IMAGE_ID="+userImage.getId()+" AND TYPE="+com.hys.exam.constants.Constants.PROP_DUTY_TYPE;
						getSimpleJdbcTemplate().update(sql_update_sepcial_duty);
					}
					for(int i=compare_spe_duty.size();i<spe_duty.size();i++){
						String sql_add = "insert into qm_user_image_special_prop (image_id,prop_id,type) values(?,?,?)";
						getSimpleJdbcTemplate().update(sql_add, userImage.getId(),spe_duty.get(i).getId(),com.hys.exam.constants.Constants.PROP_DUTY_TYPE);
					}
			}else {
				for(int i=0;i<spe_duty.size();i++){
					String sql_updateSpecDuty = "update qm_user_image_special_prop set prop_id="+spe_duty.get(i).getId()+" where image_id="+userImage.getId()+" and type="+com.hys.exam.constants.Constants.PROP_DUTY_TYPE;
					getSimpleJdbcTemplate().update(sql_updateSpecDuty);
				}
			}
		}
	}
		return true;

	}

	@Override
	public boolean deleteUserImage(UserImage userImage) {
		
		String sql = "delete from QM_USER_IMAGE_PROP where USERIMAGE_ID=" + +userImage.getId();
		getJdbcTemplate().update(sql);
		
		sql = "delete from QM_USER_IMAGE_GENERAL_PROP where IMAGE_ID=" + +userImage.getId();
		getJdbcTemplate().update(sql);
		
		sql = "delete from QM_USER_IMAGE_SPECIAL_PROP where IMAGE_ID=" + +userImage.getId();
		getJdbcTemplate().update(sql);
		
		sql = "delete from QM_USER_IMAGE where id="+userImage.getId();
		getJdbcTemplate().update(sql);
		
		return true;
	}

	@Override
	public List<PropUnit> getHospitalList() {
		String sql= "select id, name from EXAM_PROP_VAL WHERE TYPE = 23";
		List<PropUnit> hospitalList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
		return hospitalList;
	}

	@Override
	public List<PropUnit> getAreaList() {
		String sql = "select id,name from EXAM_PROP_VAL WHERE TYPE = 21";
		List<PropUnit> areaList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
		return areaList;
	}

	@Override
	public List<PropUnit> getDutyList() {
		String sql = "select id,name from EXAM_PROP_VAL WHERE TYPE =9";
		List<PropUnit> dutyList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
		return dutyList;
	}

	@Override
	public List<PropUnit> getMajorList() {
		
		String sql = "select id,major as name from EXAM_MAJOR_ORDER";
		List<PropUnit> majorList = getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(PropUnit.class));
		return majorList;
	}
}
