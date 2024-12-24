package com.app.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBSTATE {
	DBConfig config = DBConfig.getInstance();
	
//	protected DBConfig config = DBConfig.getInstance();
	protected Connection conn = config.getCon();
	protected PreparedStatement stmt = config.getStatement();
	protected ResultSet rs = config.getResult();

}
