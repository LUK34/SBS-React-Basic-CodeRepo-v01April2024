package kw.kng.service;

import kw.kng.binders.UserModel;
import kw.kng.entity.UserSetup;

public interface UserSetupService 
{
	UserSetup createUser(UserModel uModel);
	UserSetup readUserDetails();
	UserSetup updateUserDetails(UserModel uModel);
    void deleteUserDetailsById();
    UserSetup getLoggedInUser();
}
