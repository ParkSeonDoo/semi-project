package student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jdbc.DBConn;

public class StudentDao {
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;

	public StudentDao() {
		try {
			conn = new DBConn("myproject").getConn();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void close() {
	      try {
	          if(rs !=null)  rs.close();
	          if(ps !=null) ps.close();
	          if(conn !=null) conn.close();

			rs = null;
			ps = null;
			conn = null;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public boolean login(String id, String pwd) {
		if (conn == null)
			conn = new DBConn("myproject").getConn();
		boolean b = false;

		String sql = "select * from login where id= ? and pwd= ?";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pwd);

			rs = ps.executeQuery();

			if (rs.next()) {
				b = true;
			} else {

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("dao : " + b);
		return b;

	}

	public boolean save(StudentVo vo) {
		if (conn == null)
			conn = new DBConn("myproject").getConn();

		boolean b = false;
		String sql = " insert into student(grade, ban, serial, name, gender," + " address, address2,phone,email) "
				+ " values(?,?,?,?,?,?,?,?,?) ";
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getGrade());
			ps.setString(2, vo.getBan());
			ps.setString(3, vo.getSerial());
			ps.setString(4, vo.getName());
			ps.setString(5, vo.getGender());
			ps.setString(6, vo.getAddress());
			ps.setString(7, vo.getAddress2());
			ps.setString(8, vo.getPhone());
			ps.setString(9, vo.getEmail());

			int cnt = ps.executeUpdate();
			if (cnt > 0) {
				conn.commit();
				b = true;
			} else {
				conn.rollback();
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return b;
	}

	public List<StudentVo> load(Page pageVo, String grade, String ban, String search) {
		if (conn == null)
			conn = new DBConn("myproject").getConn();
		List<StudentVo> list = new ArrayList<StudentVo>();
		String sql = "";
		String common = "";
		String like;
		if(!"".equals(pageVo.getFindStr())) {				
			like = "or";
		}else {
			like = "and";
		}
		if(!"".equals(grade) && "".equals(ban)) {					
			common = "and grade = ?";
		}
		if("".equals(grade) && !"".equals(ban)){
			common = "and ban = ?";
		}
		if(!"".equals(grade) && !"".equals(ban)){
			common = "and grade = ? and ban = ?";
		}
		if("".equals(grade) && "".equals(ban)){
			common = "";
		}
		
		try {
			if ("def".equals(search)) {
				sql = "select count(serial) totSize from student" 
						+ " where serial like ?" 
						+ " and grade like ?"
						+ " and ban like ?" 
						+ " and name like ?" 
						+ " and gender like ?" 
						+ " and phone like ?"
						+ " and address like ?" 
						+ " and email like ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, "%" + pageVo.getFindStr() + "%");
				ps.setString(2, "%" + pageVo.getFindStr() + "%");
				ps.setString(3, "%" + pageVo.getFindStr() + "%");
				ps.setString(4, "%" + pageVo.getFindStr() + "%");
				ps.setString(5, "%" + pageVo.getFindStr() + "%");
				ps.setString(6, "%" + pageVo.getFindStr() + "%");
				ps.setString(7, "%" + pageVo.getFindStr() + "%");
				ps.setString(8, "%" + pageVo.getFindStr() + "%");
			} else {
				sql = "select count(serial) totSize from student" 
						+ " where "
						+ " (serial like ?"
						+ like + " name like ?"
						+ like + " gender like ?" 
						+ like + " phone like ?" 
						+ like + " address like ?" 
						+ like + " email like ?)"
						+ common;
				ps = conn.prepareStatement(sql);
				
				ps.setString(1, "%" + pageVo.getFindStr() + "%");
				ps.setString(2, "%" + pageVo.getFindStr() + "%");
				ps.setString(3, "%" + pageVo.getFindStr() + "%");
				ps.setString(4, "%" + pageVo.getFindStr() + "%");
				ps.setString(5, "%" + pageVo.getFindStr() + "%");
				ps.setString(6, "%" + pageVo.getFindStr() + "%");
				if(!"".equals(grade) && "".equals(ban)) {					
					ps.setString(7, grade);
				}
				if("".equals(grade) && !"".equals(ban)){
					ps.setString(7, ban);
				}
				if(!"".equals(grade) && !"".equals(ban)){
					ps.setString(7, grade);
					ps.setString(8, ban);
				}
			}

			rs = ps.executeQuery();

			rs.next();
			int totSize = rs.getInt("totSize");
			pageVo.setTotSize(totSize);
			pageVo.compute();

			if ("def".equals(search)) {
				sql = "select * from student" 
						+ " where serial like ?" 
						+ " and grade like?" 
						+ " and ban like ?"
						+ " and name like ?" 
						+ " and gender like ?" 
						+ " and phone like ?" 
						+ " and address like ?"
						+ " and email like ?" 
						+ " limit ? , ?";

				ps = conn.prepareStatement(sql);
				ps.setString(1, "%" + pageVo.getFindStr().trim() + "%");
				ps.setString(2, "%" + pageVo.getFindStr().trim() + "%");
				ps.setString(3, "%" + pageVo.getFindStr().trim() + "%");
				ps.setString(4, "%" + pageVo.getFindStr().trim() + "%");
				ps.setString(5, "%" + pageVo.getFindStr().trim() + "%");
				ps.setString(6, "%" + pageVo.getFindStr().trim() + "%");
				ps.setString(7, "%" + pageVo.getFindStr().trim() + "%");
				ps.setString(8, "%" + pageVo.getFindStr().trim() + "%");
				ps.setInt(9, pageVo.getStartNo());
				ps.setInt(10, pageVo.getListSize());
			} else {
				
				sql = "select * from student" 
						+ " where "
						+ " (serial like ?"
						+ like + " name like ?"
						+ like + " gender like ?" 
						+ like + " phone like ?" 
						+ like + " address like ?" 
						+ like + " email like ?)"
						+ common 
						+ " limit ? , ?";
				
				ps = conn.prepareStatement(sql);
				
				ps.setString(1, "%" + pageVo.getFindStr() + "%");
				ps.setString(2, "%" + pageVo.getFindStr() + "%");
				ps.setString(3, "%" + pageVo.getFindStr() + "%");
				ps.setString(4, "%" + pageVo.getFindStr() + "%");
				ps.setString(5, "%" + pageVo.getFindStr() + "%");
				ps.setString(6, "%" + pageVo.getFindStr() + "%");
				if(!"".equals(grade) && "".equals(ban)) {					
					ps.setString(7, grade);
					ps.setInt(8, pageVo.getStartNo());
					ps.setInt(9, pageVo.getListSize());
				}else if("".equals(grade) && !"".equals(ban)){
					ps.setString(7, ban);
					ps.setInt(8, pageVo.getStartNo());
					ps.setInt(9, pageVo.getListSize());
				}else if(!"".equals(grade) && !"".equals(ban)){
					ps.setString(7, grade);
					ps.setString(8, ban);
					ps.setInt(9, pageVo.getStartNo());
					ps.setInt(10, pageVo.getListSize());
				}else {
					ps.setInt(7, pageVo.getStartNo());
					ps.setInt(8, pageVo.getListSize());
				}
			}

			rs = ps.executeQuery();

			while (rs.next()) {
				StudentVo vo = new StudentVo();

				vo.setGrade(rs.getString("grade"));
				vo.setBan(rs.getString("ban"));
				vo.setSerial(rs.getString("serial"));
				vo.setName(rs.getString("name"));
				vo.setGender(rs.getString("gender"));
				vo.setAddress(rs.getString("address"));
				vo.setAddress2(rs.getString("address2"));
				vo.setPhone(rs.getString("phone"));
				vo.setEmail(rs.getString("email"));

				list.add(vo);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	public Page pageLode(Page pageVo, String ban, String grade) {
		if (conn == null)
			conn = new DBConn("myproject").getConn();
		String sql = "";
		String common = "";
		String like;
		if(!"".equals(pageVo.getFindStr())) {				
			like = "or";
		}else {
			like = "and";
		}
		if(!"".equals(grade) && "".equals(ban)) {					
			common = "and grade = ?";
		}
		if("".equals(grade) && !"".equals(ban)){
			common = "and ban = ?";
		}
		if(!"".equals(grade) && !"".equals(ban)){
			common = "and grade = ? and ban = ?";
		}
		if("".equals(grade) && "".equals(ban)){
			common = "";
		}
		try {
			sql = "select count(serial) totSize from student" 
					+ " where "
					+ " (serial like ?"
					+ like + " name like ?"
					+ like + " gender like ?" 
					+ like + " phone like ?" 
					+ like + " address like ?" 
					+ like + " email like ?)"
					+ common;

			ps = conn.prepareStatement(sql);
			
			ps.setString(1, "%" + pageVo.getFindStr() + "%");
			ps.setString(2, "%" + pageVo.getFindStr() + "%");
			ps.setString(3, "%" + pageVo.getFindStr() + "%");
			ps.setString(4, "%" + pageVo.getFindStr() + "%");
			ps.setString(5, "%" + pageVo.getFindStr() + "%");
			ps.setString(6, "%" + pageVo.getFindStr() + "%");
			if(!"".equals(grade) && "".equals(ban)) {					
				ps.setString(7, grade);
			}
			if("".equals(grade) && !"".equals(ban)){
				ps.setString(7, ban);
			}
			if(!"".equals(grade) && !"".equals(ban)){
				ps.setString(7, grade);
				ps.setString(8, ban);
			}
			
			rs = ps.executeQuery();

			rs.next();
			int totSize = rs.getInt("totSize");
			pageVo.setTotSize(totSize);
			pageVo.compute();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return pageVo;
	}

	public StudentVo view(String Serial) {
		if (conn == null)
			conn = new DBConn("myproject").getConn();

		StudentVo vo = new StudentVo();
		String sql = " select * from student where serial=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, Serial);
			rs = ps.executeQuery();
			if (rs.next()) {
				vo.setGrade(rs.getString("grade"));
				vo.setBan(rs.getString("ban"));
				vo.setSerial(rs.getString("serial"));
				vo.setName(rs.getString("name"));
				vo.setGender(rs.getString("gender"));
				vo.setAddress(rs.getString("address"));
				vo.setAddress2(rs.getString("address2"));
				vo.setPhone(rs.getString("phone"));
				vo.setEmail(rs.getString("email"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		close();
		return vo;
	}

	public boolean modify(StudentVo vo) {
		if (conn == null)
			conn = new DBConn("myproject").getConn();

		boolean b = false;
		String sql = " update student set grade=?, ban=?, name=?, gender=?, "
				+ " address=?, address2=?, phone=? , email=? " + " where serial=? ";

		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getGrade());
			ps.setString(2, vo.getBan());
			ps.setString(3, vo.getName());
			ps.setString(4, vo.getGender());
			ps.setString(5, vo.getAddress());
			ps.setString(6, vo.getAddress2());
			ps.setString(7, vo.getPhone());
			ps.setString(8, vo.getEmail());
			ps.setString(9, vo.getSerial());

			int cnt = ps.executeUpdate();
			if (cnt > 0) {
				b = true;
				conn.commit();
			} else {
				conn.rollback();
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return b;
	}

	public boolean delete(StudentVo vo) {
		if (conn == null)
			conn = new DBConn("myproject").getConn();

		boolean b = false;

		String sql = "delete from student where serial=?";

		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getSerial());

			int cnt = ps.executeUpdate();
			if (cnt > 0) {
				b = true;
				conn.commit();
			} else {
				conn.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return b;
	}
	
	public String chart(){
        if(conn ==null) conn = new DBConn("myproject").getConn();
        
        String sql1,sql2,sql3,sql4,sql5,sql6="";
        String chart_list="";
        ArrayList<String> chart = new ArrayList<>();
        try {
			sql1 = "select AVG((kor+eng+math+his+sci)/5) AS AVG from score sc JOIN student st ON sc.serial = st.serial where grade = ? and test = ?";
			ps= conn.prepareStatement(sql1);

			ps.setString(1, "1");
			ps.setString(2, "a");
			
			rs=ps.executeQuery();
			 
			while(rs.next()) {
				String avg = rs.getString("AVG");
				chart.add(avg);
			}
			
			sql2 = "select AVG((kor+eng+math+his+sci)/5) AS AVG from score sc JOIN student st ON sc.serial = st.serial where grade = ? and test = ?";
			ps= conn.prepareStatement(sql2);

			ps.setString(1, "1");
			ps.setString(2, "b");
			
			rs=ps.executeQuery();
			 
			while(rs.next()) {
				String avg = rs.getString("AVG");
				chart.add(avg);
			}
			sql3 = "select AVG((kor+eng+math+his+sci)/5) AS AVG from score sc JOIN student st ON sc.serial = st.serial where grade = ? and test = ?";
			ps= conn.prepareStatement(sql3);

			ps.setString(1, "2");
			ps.setString(2, "a");
			
			rs=ps.executeQuery();
			 
			while(rs.next()) {
				String avg = rs.getString("AVG");
				chart.add(avg);
			}
			sql4 = "select AVG((kor+eng+math+his+sci)/5) AS AVG from score sc JOIN student st ON sc.serial = st.serial where grade = ? and test = ?";
			ps= conn.prepareStatement(sql4);

			ps.setString(1, "2");
			ps.setString(2, "b");
			
			rs=ps.executeQuery();
			 
			while(rs.next()) {
				String avg = rs.getString("AVG");
				chart.add(avg);
			}
			sql5 = "select AVG((kor+eng+math+his+sci)/5) AS AVG from score sc JOIN student st ON sc.serial = st.serial where grade = ? and test = ?";
			ps= conn.prepareStatement(sql5);

			ps.setString(1, "3");
			ps.setString(2, "a");
			
			rs=ps.executeQuery();
			 
			while(rs.next()) {
				String avg = rs.getString("AVG");
				chart.add(avg);
			}
			sql6 = "select AVG((kor+eng+math+his+sci)/5) AS AVG from score sc JOIN student st ON sc.serial = st.serial where grade = ? and test = ?";
			ps= conn.prepareStatement(sql6);

			ps.setString(1, "3");
			ps.setString(2, "b");
			
			rs=ps.executeQuery();
			 
			while(rs.next()) {
				String avg = rs.getString("AVG");
				chart.add(avg);
			}
           
			chart_list = chart.toString();
        }catch(Exception ex) {
           ex.printStackTrace();
        }
        
        return chart_list;
     }

}
