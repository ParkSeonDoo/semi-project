package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import student.Page;
import student.StudentDao;
import student.StudentVo;


@WebServlet(urlPatterns ="/student.do")
public class StudentServlet extends HttpServlet{
   String path = "index.jsp?inc=student/";
   StudentDao dao;

   public StudentServlet() {   
      
      dao = new StudentDao();
      System.out.println("servlet create");
      
   }
   
   @Override
   protected void doGet( HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      if(dao==null) dao = new StudentDao();	
      String main = req.getParameter("main");
      if("1".equals(main)) {
    	  String chart = dao.chart();
          String[] strings_parse = chart.replace("[", "").replace("]", "").split(", ");
          System.out.println(strings_parse);
          req.setAttribute("chart", strings_parse);
          String url= "index.jsp";
          RequestDispatcher rd = req.getRequestDispatcher(url);
          rd.forward(req, resp);
      }else {    	  
    	  Page pageVo = new Page();	
    	  load(pageVo,req,resp);
      }
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      if(dao==null) dao = new StudentDao();
      
         String work = req.getParameter("work");
         System.out.println("work:" +work);
         String url = " ";

 		Page pageVo = new Page();
 		pageVo.setFindStr(req.getParameter("findStr"));
 		pageVo.setNowPage(Integer.parseInt(req.getParameter("nowPage")));
 		req.setAttribute("pageVo", pageVo);
 		
 		
         switch(work) {
         case "login" :
            System.out.println("case : login");
            String id = req.getParameter("id");
            String pwd =req.getParameter("pwd");
            System.out.println(id);
            System.out.println(pwd);
            
            boolean b = dao.login(id,pwd);
            
            if(b) {
            String chart = dao.chart();
            String[] strings_parse = chart.replace("[", "").replace("]", "").split(", ");
            System.out.println(strings_parse);
            req.setAttribute("chart", strings_parse);
            req.setAttribute("id", id);
            
            url= "index.jsp";
            RequestDispatcher rd = req.getRequestDispatcher(url);
            HttpSession session = req.getSession();
            session.setAttribute("sessionId", req.getParameter("id"));
            
            rd.forward(req, resp);
               
               
            }else {
            	resp.setContentType("text/html; charset=utf-8");
                PrintWriter out = resp.getWriter();
                out.println("<script>");
                out.print("   alert('아이디랑 비밀번호를 확인해주세요.');" );
                out.print("history.back();");
                req.setAttribute("pwd", "");
                out.print("</script>");
            }
            break;
         case "load":
        	 load(pageVo,req,resp);
        	 break;
         case "save":
        	url = path + "student_input.jsp";
        	RequestDispatcher rd = req.getRequestDispatcher(url);		
 			rd.forward(req, resp);
  
 			break;
       	  
         case "saveR":
        	 StudentVo sVo =(StudentVo)req.getAttribute("sVo");
 			if(saveR(sVo,resp) ) {
 				load(pageVo,req,resp);
 			}else {
 				PrintWriter out = resp.getWriter();
 				out.print("<script>");
 				out.print(" alert('저장이 안됬습니다.!!');");
 				out.print(" history.back();");
 				out.print("</script>");
 			}
 			break;
         case "view":
        	 view(req,resp);
        	 break;
         case "modify":
        	 modify(pageVo,req,resp);
        	 break;
         case "delete":
        	 delete(pageVo,req,resp);
        	 break;
         }
      
      
   }
      public void load(Page pageVo ,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
    	  if(dao==null) dao = new StudentDao();
    	  String search_grade=req.getParameter("search_grade");
    	  String search_ban=req.getParameter("search_ban");
    	  String search = req.getParameter("search_status");
    	  List<StudentVo> list;
    	  if("search".equals(search)) {
    		  list = dao.load(pageVo,search_grade,search_ban,search);
    		  pageVo = dao.pageLode(pageVo,search_ban,search_grade);
    		  req.setAttribute("grade", search_grade);
    		  req.setAttribute("ban", search_ban);
    	  }else {
    		  search = "def";
    		  list = dao.load(pageVo,search_grade,search_ban,search);
    	  }
    	  
    	  String url = path + "student_list.jsp";
    	  RequestDispatcher rd = req.getRequestDispatcher(url);
    	  req.setAttribute("list",list);
    	  req.setAttribute("pageVo", pageVo);
    	  rd.forward(req, resp);
    	  
      }
     public boolean saveR(StudentVo sVo, HttpServletResponse resp) throws ServletException, IOException{
	    if(dao==null) dao = new StudentDao();
	    boolean b= dao.save(sVo);
	    System.out.println(b);
	   	return b;   	  
     }
     public void view(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
    	 if(dao == null) dao = new StudentDao();
    	  String url = path + "student_modify.jsp";    	  
    	  String serial = req.getParameter("serial");
    	  StudentVo vo = dao.view(serial);
    	 
    	  System.out.println(serial);
    	 
      	 RequestDispatcher rd = req.getRequestDispatcher(url); 
    	 req.setAttribute("vo", vo);
    	 
    	 rd.forward(req, resp);
     }
     public void modify(Page pageVo, HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
 		if(dao == null) dao = new StudentDao();
 		StudentVo vo = (StudentVo)req.getAttribute("sVo");
 		
 		boolean b = dao.modify(vo);
 		if(b) {
 			load(pageVo ,req, resp);
 		}else {
 			PrintWriter out = resp.getWriter();
 			out.print("<script>");
 			out.print("   alert('자료 수정이 안됨');");
 			out.print("   history.back();" );
 			out.print("</script>");

 		}
 		
     }
 	public void delete(Page pageVo, HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
		if(dao==null) dao = new StudentDao();
		StudentVo  sVo = (StudentVo)req.getAttribute("sVo");
		boolean b = dao.delete(sVo);
		if(b) {
			load(pageVo,req , resp);
		}else {
			PrintWriter out = resp.getWriter();
			out.print("<script>");
			out.print("   alert('자료 삭제 오류!!!');");
			out.print("   history.back();" );
			out.print("</script>");		}
				
	
	}


}
     
