package com.dnake.common.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.dnake.service.system.SysPopedomService;
import com.dnake.utils.Constants;
import com.dnake.utils.ResultBuilderUtil;
import com.dnake.utils.SessionBean;
import com.dnake.utils.StringUtils;

/**
 * 登录拦截
 * 
 * @ClassName: LoginInterceptor
 * @Description:
 * @author ts
 * @date 2013-9-30 下午2:26:33
 *
 */
public class LoginInterceptor implements HandlerInterceptor {

	private static final Logger log = Logger.getLogger(LoginInterceptor.class);
	@Resource
	private SysPopedomService sysPopedomService;

	@Autowired
	private HttpSession session;

	/**
	 * 该方法也是需要当前对应的Interceptor的preHandle方法的返回值为true时才会执行。
	 * 该方法将在整个请求完成之后，也就是DispatcherServlet渲染了视图执行， 这个方法的主要作用是用于清理资源的，当然这个方法也只能在当前
	 * 这个Interceptor的preHandle方法的返回值为true时才会执行。
	 */
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
	}

	/**
	 * 这个方法只会在当前这个Interceptor的preHandle方法返回值为true的时候才会执行。
	 * postHandle是进行处理器拦截用的，它的执行时间是在处理器进行处理之
	 * 后，也就是在Controller的方法调用之后执行，但是它会在DispatcherServlet进行视图的渲染之前执行，
	 * 也就是说在这个方法中你可以对ModelAndView进行操
	 * 作。这个方法的链式结构跟正常访问的方向是相反的，也就是说先声明的Interceptor拦截器该方法反而会后调用，
	 * 这跟Struts2里面的拦截器的执行过程有点像，
	 * 只是Struts2里面的intercept方法中要手动的调用ActionInvocation的invoke方法，
	 * Struts2中调用ActionInvocation的invoke方法就是调用下一个Interceptor
	 * 或者是调用action，然后要在Interceptor之前调用的内容都写在调用invoke之前，
	 * 要在Interceptor之后调用的内容都写在调用invoke方法之后。
	 */
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {
	}

	/**
	 * preHandle方法是进行处理器拦截用的，顾名思义，该方法将在Controller处理之前进行调用，
	 * SpringMVC中的Interceptor拦截器是链式的，可以同时存在
	 * 多个Interceptor，然后SpringMVC会根据声明的前后顺序一个接一个的执行，
	 * 而且所有的Interceptor中的preHandle方法都会在
	 * Controller方法调用之前调用。SpringMVC的这种Interceptor链式结构也是可以进行中断的，
	 * 这种中断方式是令preHandle的返回值为false，当preHandle的返回值为false的时候整个请求就结束了。
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {

		HandlerMethod handlerMethod = (HandlerMethod) arg2;
		ClearLogin clearLogin = handlerMethod.getMethod().getAnnotation(ClearLogin.class);
		Login classLogin = handlerMethod.getBeanType().getAnnotation(Login.class);
		Login methodLogin = handlerMethod.getMethod().getAnnotation(Login.class);
		if (null != clearLogin || (null == classLogin && null == methodLogin)) {
			return true;
		}
		String uri = request.getRequestURI();
		SessionBean sessionBean = (SessionBean) session.getAttribute("sessionBean");
		if (null == sessionBean) {
			// 手机提交过来的请求
			if (-1 != uri.indexOf("/appservice/ehome")) {
				try {
					log.info("received mobile request :  session is null ");
					response.setContentType("text/html;charset=" + Constants.CHARSET);
					PrintWriter out = response.getWriter();
					String returnString =ResultBuilderUtil.RESULT_ERROR_SESSION_IS_NULL;
					Map<String, String[]> map=request.getParameterMap();
					if(map.containsKey("callback")){
						returnString=ResultBuilderUtil.jsonTojsonp(map.get("callback")[0], returnString);
					}
					out.println(returnString);
					out.flush();
					out.close();
				} catch (IOException e) {
					log.error(e);
				}
				return false;
			}
			//如果用ajax提交时，session过期，则由此处理 added by ts 2015-9-14
			String requestType = request.getHeader("X-Requested-With");
			if( !StringUtils.isNull(requestType) && requestType.equalsIgnoreCase("XMLHttpRequest") ){
				log.info("received ajax request :  session timeout ");
				response.setHeader("sessionstatus", "timeout");
				try {
					PrintWriter out = response.getWriter();
					out.println("sessiontimeout");
					out.flush();
					out.close();
				} catch (IOException e) {
					log.error(e);
				}
				return false;
			}
			log.info("web not login , jump to login page");
			response.sendRedirect(request.getContextPath() + "/loginPage");
			return false;
		} else {
			return sysPopedomService.isPermission(sessionBean.getRoleId(), uri);
		}
		// String uri = request.getRequestURI();
		// System.out.println(arg2);
		// // 提交登陆信息，手机登录不拦截
		// if (uri.equals(request.getContextPath() + "/login")
		// || uri.equals(request.getContextPath()
		// + "/appservice/mobileLogin")
		// || uri.equals(request.getContextPath()
		// + "/system/SysLogoSettings/queryAll")
		// || uri.indexOf(request.getContextPath()
		// + "/system/ValidateCode") != -1||
		// uri.indexOf(request.getContextPath()+"/r/") != -1
		// || uri.indexOf(request.getContextPath()+"/d/") != -1) {
		// return true;
		// }
		//
		// if (null == request.getSession().getAttribute("sessionBean")) {
		// if (-1 != uri.indexOf("/appservice/ehome")) {
		// try {
		// log.info("received mobile request :  session is null ");
		// response.setContentType("text/html;charset="
		// + Constants.CHARSET);
		// PrintWriter out = response.getWriter();
		// out.println(ResultBuilderUtil.RESULT_ERROR_SESSION_IS_NULL);
		// out.flush();
		// out.close();
		// } catch (IOException e) {
		// log.error(e);
		// }
		// return false;
		// }
		// log.info("web not login , jump to login page");
		// response.sendRedirect(request.getContextPath() + "/");
		// return false;
		// } else {
		// SessionBean sessionBean = (SessionBean) session
		// .getAttribute("sessionBean");
		// // log.info("session 的id="+session.getId());
		// if (sessionBean != null) {
		// return sysPopedomService.isPermission(sessionBean.getRoleId(),
		// uri);
		// } else {
		// return false;
		// }
		// }
	}
}
