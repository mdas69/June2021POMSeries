package com.qa.opencart.utils;

import java.util.Arrays;
import java.util.List;

public class ConstantsUtil {
	public static final int DEFAULT_TIME_OUT=5;
	public static final String  LOGIN_PAGE_TITLE="Account Login";
	public static final String  PAGE_Header="Your Store";
	
	public static final String  ACCOUNT_PAGE_Title="My Account";
	public static final String  ACCOUNT_PAGE_URL_FRATCION="route=account/account";
	public static final List<String> EXPECTED_ACC_SEC_LIST=Arrays.asList("My Account", "My Orders","My Affiliate Account", "Newsletter");
	public static final String  REGISTER_SUCCESS_MESSAGE="Your Account Has Been Created";

}
