package com.cg.creditcardpayment.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;
/**
* LoginEntity
* The Login program implements an application such that
* the login data of the  customer/admin is sent to the database 
*/
@Entity
public class Login {
	/**
	 * This a local variable: {@link #loginId} defines the unique Id for user
	 * @HasGetter
	 * @HasSetter
	 */
	@Id
	@Column(name="login_id", nullable=false)
	@Size(min = 4,max = 10,message = "Please enter within range of 4 - 10")
	private String loginId;
	/**
	 * This a local variable: {@link #password} defines the password for user
	 * @HasGetter
	 * @HasSetter
	 */
	@Column(name="password", nullable=false)
	@Size(min = 8,max = 20,message = "Please enter within range of 8 - 20")
	private String password;
	/**
	 * This a local variable: {@link #role} defines the role for user
	 * @HasGetter
	 * @HasSetter
	 */
	@Column(name="role",nullable=false)
	@Size(min = 5,max = 8,message = "Please enter Admin or Customer as a role")
	private String role;
	
	//Default constructor
	public Login() {
		super();
	}

	/**
	 * @param loginId
	 * @param password
	 * @param role
	 */
	public Login(@Size(min = 4, max = 10, message = "Please enter within range of 4 - 10") String loginId,
			@Size(min = 8, max = 20, message = "Please enter within range of 8 - 20") String password,
			@Size(min = 5, max = 8, message = "Please enter Admin or Customer as a role") String role) {
		super();
		this.loginId = loginId;
		this.password = password;
		this.role = role;
	}





	public String getLoginId() {
		return loginId;
	}


	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}


	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

}
