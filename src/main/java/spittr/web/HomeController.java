package spittr.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.*; //GET、POST等方法的枚举类

@Controller //声明为一个控制器
public class HomeController {
	@RequestMapping(value="/",method=GET) //处理对/的GET请求
	public String home(){
		return "home";	//视图名为home
	}
}
