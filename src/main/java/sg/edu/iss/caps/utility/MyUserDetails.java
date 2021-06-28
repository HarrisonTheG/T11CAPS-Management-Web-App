//package sg.edu.iss.caps.utility;
//
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import sg.edu.iss.caps.model.RoleType;
//import sg.edu.iss.caps.model.User;
//
////THis class will get instantiated after user log in for authentication
//public class MyUserDetails implements UserDetails{
//	
//	private String userName;
//	private String password;
//	private RoleType roletype;
//	private List<GrantedAuthority> authorities;
//	
//	
//	public MyUserDetails(User user) {
//		this.userName=user.getEmail();
//		this.password=user.getPassword();
//		this.roletype=user.getRole();
//		this.authorities=Arrays.stream(user.getRole().toString().split(","))
//				.map(SimpleGrantedAuthority::new)
//				.collect(Collectors.toList());
//	}
//
//
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		// TODO Auto-generated method stub
//		return authorities;
//	}
//
//
//	@Override
//	public String getPassword() {
//		// TODO Auto-generated method stub
//		return password;
//	}
//
//
//
//	@Override
//	public boolean isAccountNonExpired() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//
//	@Override
//	public boolean isAccountNonLocked() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//
//	@Override
//	public boolean isEnabled() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//
//	@Override
//	public String getUsername() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//
//}
