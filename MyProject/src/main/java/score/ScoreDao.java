package score;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import jdbc.DBConn;
import student.Page;
import student.StudentVo;

public class ScoreDao {
   Connection conn;
   PreparedStatement ps;
   ResultSet rs;
   
   public ScoreDao() {
      try {
         conn= new DBConn("myproject").getConn();
               
      }catch(Exception ex) {
         ex.printStackTrace();
      }
   }

   public void close() {
      try {
         if(rs !=null) rs.close();
         if(ps !=null) ps.close();
         if(conn !=null) conn.close();
         
         rs=null;
         ps=null;
         conn=null;
      }catch(Exception ex) {
         ex.printStackTrace();
      }
   }
   
   public List <ScoreVo> load(Page pageVo,String grade, String ban,String test){
      if(conn ==null) conn = new DBConn("myproject").getConn();
      
      List<ScoreVo> list =new ArrayList<ScoreVo>();
      String sql="";
      
      try {
    
    	  sql =   "select count(sc.serial) totSize from score sc"
    	            +   " JOIN student st"
    	            +   " ON sc.serial = st.serial" 
    	            + " WHERE( st.serial like ?"
    	            + " or st.name like ?)";
    	           
    	            if (grade != null && !grade.equals(""))
    					sql += " and grade= " + grade;
    				if (ban != null && !ban.equals(""))
    					sql += " and ban = " + ban;   				
    				
    				if( test != null && !test.equals("")) 			
    					sql += " and test = '" + test +"'" ;
    				//큰따옴표 안에 작은따옴표를 써서 문자로 인식하게끔
    				
    		
         
         ps = conn.prepareStatement(sql);
         PreparedStatement cnt = conn.prepareStatement(sql);
         
         
         ps.setString(1, "%" + pageVo.getFindStr().trim() + "%");
         ps.setString(2, "%" + pageVo.getFindStr().trim() + "%");
         System.out.println(ps);
         
         rs = ps.executeQuery();
         
         rs.next();
         int totSize = rs.getInt("totSize");
         pageVo.setTotSize(totSize);
         pageVo.compute();
         
         
         
         sql =    " select st.grade, st.serial , st.ban ,st.name, "
            +    " sc.kor,sc.math, sc.eng, sc.his, sc.sci,sc.test, "
            +    " (kor+eng+math+his+sci)/5 AS AVG FROM "
            +   " score sc JOIN student st"
            +   " ON sc.serial = st.serial" 
            + " WHERE( st.serial like ?"
            + " or st.name like ?)";
           
            if (grade != null && !grade.equals(""))
				sql += " and grade= " + grade;
			if (ban != null && !ban.equals(""))
				sql += " and ban = " + ban;
			if( test != null && !test.equals("")) 
				sql += " and test = '" +test +"'"  ;			
			 
	
			sql +=  " order by avg desc  " ;
			sql += " limit ? , ?";
            
            
         
			ps= conn.prepareStatement(sql);
              
         
         ps.setString(1, "%" + pageVo.getFindStr().trim() + "%");
         ps.setString(2, "%" + pageVo.getFindStr().trim() + "%");
         
         ps.setInt(3, pageVo.getStartNo());
		ps.setInt(4, pageVo.getListSize());
		 
		System.out.println(ps);
		
         rs=ps.executeQuery();
         
         
         while(rs.next()) {
            ScoreVo vo = new ScoreVo();
      
            
            vo.setGrade(rs.getString("grade"));
            vo.setSerial(rs.getString("serial"));
            vo.setBan(rs.getString("ban"));
            vo.setName(rs.getString("name"));
            vo.setKor(rs.getDouble("kor"));
            vo.setEng(rs.getDouble("eng"));
            vo.setMath(rs.getDouble("math"));
            vo.setHis(rs.getDouble("his"));
            vo.setSci(rs.getDouble("sci"));
            vo.setTest(rs.getString("test"));
            vo.setAvg(vo.getAvg());
            list.add(vo);
         	}
    	
      }catch(Exception ex) {
         ex.printStackTrace();
      }
      
      return list;
   }

 
   
   
   public ScoreVo view(String serial, String test2) {
      if(conn == null) conn = new DBConn("myproject").getConn();
      
      ScoreVo vo = new ScoreVo(); 
      String sql ="SELECT st.grade,st.ban,st.SERIAL,st.NAME,sc.kor,sc.eng,sc.math,sc.his,sc.sci,sc.test FROM score sc "
            + " JOIN student st "
            + " ON sc.serial = st.serial"
            + " where sc.serial=?" 
            + " and sc.test = '" + test2 + "'";
      
      try {
         ps =conn.prepareStatement(sql);
         ps.setString(1,serial);
         rs = ps.executeQuery();
         
         System.out.println(ps);
         if(rs.next()) {
            vo.setGrade(rs.getString("grade"));
            vo.setBan(rs.getString("ban"));
            vo.setSerial(rs.getString("serial"));
            vo.setName(rs.getString("name"));
            vo.setKor(rs.getDouble("kor"));
            vo.setEng(rs.getDouble("eng"));
            vo.setMath(rs.getDouble("math"));
            vo.setHis(rs.getDouble("his"));
            vo.setSci(rs.getDouble("sci"));
            vo.setTest(rs.getString("test"));
            
            
         }
      }catch(Exception ex) {
         ex.printStackTrace();
      }
      
      System.out.println();
      close();
      return vo;
   }
   
   
      public boolean scoremodify (ScoreVo scvo) {
         if(conn ==null) conn = new DBConn("myproject").getConn();

         boolean b = false;
         
         String sql= "";
         sql =  "update score set kor=?, eng=?, math=?, his=?, sci=? where serial=? and test=?";
         
         try {
            conn.setAutoCommit(false);
            ps= conn.prepareStatement(sql);
            
            ps.setDouble(1,scvo.getKor());
            ps.setDouble(2,scvo.getEng());
            ps.setDouble(3,scvo.getMath());
            ps.setDouble(4,scvo.getHis());
            ps.setDouble(5,scvo.getSci());
            ps.setString(6,scvo.getSerial());
            ps.setString(7, scvo.getTest());
            
            int cnt = ps.executeUpdate();
            
            System.out.println(ps);
            System.out.println("modify cnt: " + cnt);
            
            if(cnt>0) {
               b=true;
               conn.commit();
            }else{
               conn.rollback();
            }
            }catch(Exception ex) {
               ex.printStackTrace();
            }
            System.out.println("b는" + b);
            
            return b;
      }
      
      
      public boolean delete (ScoreVo scvo) {
    	  if(conn ==null) conn = new DBConn("myproject").getConn();

          boolean b = false;
          
          String sql= "";
          sql =  " update score set kor=null, eng=null, math=null, his=null, sci=null where serial=? and test=? ";
          
          try {
             conn.setAutoCommit(false);
             ps= conn.prepareStatement(sql);
             
      
             ps.setString(1,scvo.getSerial());
             ps.setString(2, scvo.getTest());
             
             int cnt = ps.executeUpdate();
             
             System.out.println(ps);
             System.out.println("modify cnt: " + cnt);
             
             if(cnt>0) {
                b=true;
                conn.commit();
             }else{
                conn.rollback();
             }
             }catch(Exception ex) {
                ex.printStackTrace();
             }
             System.out.println("b는" + b);
             
             return b;
       }

	public boolean save(ScoreVo sVo) {
		if (conn == null)
			conn = new DBConn("myproject").getConn();

		boolean b = false;
		String sql = " insert into score(kor, eng, serial, name, gender," + " address, address2,phone,email) " 
				+ " values(?,?,?,?,?,?,?,?,?) ";
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			ps.setDouble(1,sVo.getKor());
            ps.setDouble(2,sVo.getEng());
            ps.setDouble(3,sVo.getMath());
            ps.setDouble(4,sVo.getHis());
            ps.setDouble(5,sVo.getSci());
            ps.setString(6,sVo.getSerial());
            ps.setString(7, sVo.getTest());

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
      
      
      



   }

