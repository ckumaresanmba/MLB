package com.mylimobook.pojos;

public class AcceptedUsers {
	

    	private String Name,UserKey;

		public AcceptedUsers(String string, String string2) {
			// TODO Auto-generated constructor stub
			Name=string;
			UserKey=string2;
		}

		public String getName() {
			return Name;
		}

		public void setName(String name) {
			Name = name;
		}

		public String getUserKey() {
			return UserKey;
		}

		public void setUserKey(String userKey) {
			UserKey = userKey;
		}

}
