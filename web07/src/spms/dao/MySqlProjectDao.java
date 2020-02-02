package spms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import spms.annotation.Component;
import spms.vo.Project;

@Component("projectDao")
public class MySqlProjectDao implements ProjectDao {
	DataSource ds;
	
	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}
	
	@Override
	public List<Project> selectList() throws Exception {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			connection = ds.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(
					"select PNO,PNAME,STA_DATE,END_DATE,STATE " +
							"from projects " +
					"order by CRE_DATE asc");
			ArrayList<Project> project = new ArrayList<Project>();
			while(rs.next()) {
				project.add(new Project().setNo(rs.getInt("PNO"))
						.setTitle(rs.getString("PNAME"))
						.setStartDate(rs.getDate("STA_DATE"))
						.setEndDate(rs.getDate("END_DATE"))
						.setState(rs.getInt("STATE"))
						);
			}
			return project;
			
		} catch (Exception e) {
			throw e;
		} finally {
			try {if(rs != null) {rs.close();}} catch (Exception e) {}
			try {if(stmt != null) {stmt.close();}} catch (Exception e) {}
			try {if(connection != null) {connection.close();}} catch (Exception e) {}
		}
	}

	@Override
	public int insert(Project project) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = ds.getConnection();
			stmt = connection.prepareStatement("insert into "
					+ "projects(PNAME,CONTENT,STA_DATE,END_DATE,STATE,CRE_DATE,TAGS)"
					+ "values (?,?,?,?,?,now(),?);");
			stmt.setString(1, project.getTitle());
			stmt.setString(2, project.getContent());
			stmt.setDate(3, project.getStartDate());
			stmt.setDate(4, project.getEndDate());
			stmt.setInt(5, project.getState());
			stmt.setString(6, project.getTags());
			
			return stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			try {if(stmt != null) {stmt.close();}} catch (Exception e) {}
			try {if(connection != null) {connection.close();}} catch (Exception e) {}
			
		}
		
	}

	@Override
	public Project selectOne(int no) throws Exception {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			connection = ds.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(
					"select PNO,PNAME,CONTENT,STA_DATE,END_DATE,STATE,TAGS from projects where PNO = "+no+";");
			if(rs.next()) {
				return new Project().setNo(rs.getInt("PNO"))
						.setTitle(rs.getString("PNAME"))
						.setContent(rs.getString("CONTENT"))
						.setStartDate(rs.getDate("STA_DATE"))
						.setEndDate(rs.getDate("END_DATE"))
						.setState(rs.getInt("STATE"))
						.setTags(rs.getString("TAGS")
								);
			}else {
				return null;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {if(rs != null) {rs.close();}} catch (Exception e) {}
			try {if(stmt != null) {stmt.close();}} catch (Exception e) {}
			try {if(connection != null) {connection.close();}} catch (Exception e) {}
		}
	}

	@Override
	public int update(Project project) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = ds.getConnection();
			stmt = connection.prepareStatement(
					"update projects set PNO=?, PNAME=?, CONTENT=?, STA_DATE=?, END_DATE=?, STATE=?, TAGS=? where PNO=?");
			stmt.setInt(1, project.getNo());
			stmt.setString(2, project.getTitle());
			stmt.setString(3, project.getContent());
			stmt.setDate(4, project.getStartDate());
			stmt.setDate(5, project.getEndDate());
			stmt.setInt(6, project.getState());
			stmt.setString(7, project.getTags());
			stmt.setInt(8, project.getNo());
			
			return stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
				try {if(stmt != null) {stmt.close();}} catch (Exception e) {}
				try {if(connection != null) {connection.close();}} catch (Exception e) {}
		}
	}

	@Override
	public int delete(int no) throws Exception {
		Connection connection = null;
		Statement stmt = null;
		try {
			connection = ds.getConnection();
			stmt = connection.createStatement();
			return stmt.executeUpdate("DELETE FROM projects WHERE PNO="+no);
		} catch (Exception e) {
			throw e;
		} finally {
			try {if(stmt != null) {stmt.close();}} catch (Exception e) {}
			try {if(connection != null) {connection.close();}} catch (Exception e) {}
		}
	}
}
