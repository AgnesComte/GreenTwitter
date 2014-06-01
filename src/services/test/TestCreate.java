package services.test;

import org.json.JSONObject;

import services.comment.AddCommentService;
import services.comment.SearchCommentsService;
import services.friend.AddFriendService;
import services.friend.RemoveFriendService;
import services.user.CreateUserService;
import services.user.LoginService;
import services.user.LogoutService;



public class TestCreate {
	
public static void main(String[] args){
		
//	 JSONObject obj = CreateUserService.createUser("admin", "admin", "admin", "admin");
//	 System.out.println(obj);
	 
//	 JSONObject obj2 = LoginService.loginService("TEST", "TEST00");
//	 System.out.println(obj2);
	 
//	 JSONObject obj3 = LogoutService.logout("uvth9m18aois6o77klbv2as7s");
//	 System.out.println(obj3);
	
//	 JSONObject obj4 = AddFriendService.addFriendService("hfepaq2k45heo0kk52akl73gqa", "5");
//	 System.out.println(obj4);

//	 JSONObject obj5 = RemoveFriendService.removeFriendService("hfepaq2k45heo0kk52akl73gqa", "1");
//	 System.out.println(obj5);
	
//	 JSONObject obj6 = AddCommentService.addCommentService("hfepaq2k45heo0kk52akl73gqa", "kdbjvkdsbgkj");
//	 System.out.println(obj6);
//	
//	 JSONObject obj7 = SearchCommentsService.searchCommentsService(null, null, "0");
//	 System.out.println(obj7);
//	
//	 JSONObject obj8 = SearchCommentsService.searchCommentsService("h3c770thfegh68bams2188stod", null, "0");
//	 System.out.println(obj8);
//	 
//	 JSONObject obj9 = SearchCommentsService.searchCommentsService("j8v2i3gfdl6qsnc1blsnchcuij", null, "0");
//	 System.out.println(obj9);
//	}

//	JSONObject obj10 = SearchCommentsService.searchCommentsService("oqq5r9sbflpbmqrfe38017nfa6", null, "0");
//	System.out.println(obj10);
	
	JSONObject obj11 = SearchCommentsService.searchCommentsService(null, "bonjour", "0");
	System.out.println(obj11);
}

}
