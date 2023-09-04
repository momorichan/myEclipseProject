package com.shopping.myservlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.shopping.Utility.MyUtility;
import com.shopping.controller.SuperController;


@WebServlet(
		urlPatterns = { "/Shopping" }, 
		initParams = { 
				@WebInitParam(name = "txtSetting", value = "/WEB-INF/setting.txt"), 
				@WebInitParam(name = "todolist", value = "/WEB-INF/todolist.txt")
		})
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	//초기화 파라미터 관련 변수
	private String txtSetting = null;
	private String todolist = null;

	//map for setting.txt file
	private Map<String,String> settingMap = null; 
	
	//map for todolist.txt file
	private Map<String,SuperController> todolistMap = null; 
	
	//imageUploadWebPath 변수 : 실제 이미지가 업로드 되는 경로
	private String imageUploadWebPath = null;
	
    public FrontController() {}


	public void init(ServletConfig config) throws ServletException {
		this.txtSetting = config.getInitParameter("txtSetting");
		this.todolist = config.getInitParameter("todolist");
		System.out.println("txtSetting is [" + this.txtSetting + "]");
		System.out.println("todolist is [" + this.todolist + "]");
		
		ServletContext application = config.getServletContext();
		
		String txtSettingFile = application.getRealPath(txtSetting);
		String todolistFile = application.getRealPath(todolist);
		System.out.println("txtSettingFile is [" + txtSettingFile + "]");
		System.out.println("todolistFile is [" + todolistFile + "]");
		
		settingMap = MyUtility.getSettingMap(txtSettingFile);
		todolistMap = MyUtility.getTodolistMap(todolistFile);
		System.out.println("setting file element size = [" + settingMap.size() + "]");
		System.out.println("todolist file element size = [" + todolistMap.size() + "]");
		
		application.setAttribute("settingMap", this.settingMap);
		application.setAttribute("todolistMap", this.todolistMap);
		
		//in setting.txt 파일 내의 uploadPath=upload 항목 참조 요망
		//이미지 업로드 경로를 변수에 저장합니다. 
		String tempPath = settingMap.get("UploadPath");
		if(tempPath==null) {tempPath = "image";}
		
		imageUploadWebPath = application.getRealPath(tempPath);
		System.out.println("imageUploadWebPath is [" + imageUploadWebPath + "]");
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");//한글 깨짐 방지
		
		//command Parameter : 컨트롤러 분기를 위한 핵심 파라미터
		String command = request.getParameter("command");

		if(command == null) {
			System.out.println("file upload event invoked");
			
			MultipartRequest mr = MyUtility.getMultipartRequest(request,imageUploadWebPath);
		}
		
		System.out.println("command is [" + command + "]");

		SuperController controller = this.todolistMap.get(command);
		
		if(controller != null) {
			String method = request.getMethod();
			try {
				if(method.equalsIgnoreCase("get")) {
					System.out.println(this.getClass() + " get method called");
					controller.doGet(request, response);
				}else {
					System.out.println(this.getClass() + " post method called");
					controller.doPost(request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("request command is not found");
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

}
