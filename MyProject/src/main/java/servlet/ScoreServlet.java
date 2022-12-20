package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import score.ScoreDao;
import score.ScoreVo;
import student.Page;
import student.StudentDao;
import student.StudentVo;


@WebServlet(urlPatterns ="/score.do")
public class ScoreServlet extends HttpServlet{
   String path = "index.jsp?inc=score/";
    ScoreDao dao;

   public ScoreServlet() {   
         
        dao = new ScoreDao();
        System.out.println("score servlet create");
         
      }
      
      
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      if(dao==null) dao = new ScoreDao();
      Page pageVo = new Page();   
      
      load(pageVo,req,resp);   
   }
   
   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      if(dao==null) dao = new ScoreDao();
      
      String work = req.getParameter("work");
      System.out.println("servlet:" + work);
    String url = " ";
    
    Page pageVo = new Page();
       
   pageVo.setFindStr(req.getParameter("findStr"));
   pageVo.setNowPage(Integer.parseInt(req.getParameter("nowPage")));
   req.setAttribute("pageVo", pageVo);
      
     switch(work) {
      case "load":
         load(pageVo,req,resp);
         break;
          
      case "view":
         view(req,resp);
         break;
         
      case "modify":
         scoremodify(req,resp);
         break;
         
      case "delete":
         delete(req,resp);
         break;
      }
      
      
      
   }
   public void load(Page pageVo, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
      if(dao==null) dao = new ScoreDao();
      String grade = req.getParameter("grade"); 
	  String ban = req.getParameter("ban");
	  String test = req.getParameter("test");
	  
	  HttpSession session = req.getSession();
      session.setAttribute("radiocheck", req.getParameter("test"));
    
	  
	  System.out.println(test);
	  
      List<ScoreVo>list = dao.load(pageVo,grade,ban,test);
      
      String url = path + "score_list.jsp";
      RequestDispatcher rd = req.getRequestDispatcher(url);
      req.setAttribute("list", list);
      req.setAttribute("pageVo", pageVo);
      rd.forward(req, resp);
   }
   
 
        public void view(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
           if(dao == null) dao = new ScoreDao();
           
            String url = path + "score_modify.jsp";
            String serial = req.getParameter("serial");
            String test2 = req.getParameter("test2");
            
            ScoreVo scvo = dao.view(serial,test2);
            
            RequestDispatcher rd = req.getRequestDispatcher(url);
            
            req.setAttribute("scvo", scvo);
            System.out.println(scvo.getTest());
            rd.forward(req, resp);
        }
        
        
        public void scoremodify(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
          if(dao == null) dao = new ScoreDao();
         
          ScoreVo scvo = (ScoreVo)req.getAttribute("scvo");
          Page pageVo = (Page)req.getAttribute("pageVo");
          
          System.out.println(scvo.getSerial()); 
          System.out.println(scvo.getTest());
          
          boolean b = dao.scoremodify(scvo);
          
          if(b) {
             load(pageVo , req , resp);
             
          }else {
             PrintWriter out = resp.getWriter();
             out.print("<script>");
             out.print("   alert('자료수정 중 오류발생');");
             out.print("   history.back();" );
             out.print("</script>");
          }
          
       }
        
       public void delete(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
         if(dao==null) dao = new ScoreDao();
         
         ScoreVo  scvo = (ScoreVo)req.getAttribute("scvo");
         Page pageVo = (Page)req.getAttribute("pageVo");
         System.out.println(scvo.getSerial());
         
         boolean b = dao.delete(scvo);
         if(b) {
            load(pageVo,req,resp);
         }else {
            PrintWriter out = resp.getWriter();
            out.print("<script>");
            out.print("   alert('자료 삭제 중 오류 발생');");
            out.print("   history.back();" );
            out.print("</script>");      }     
      }
   }