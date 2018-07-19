package dao;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.xml.bind.DatatypeConverter;

import DBconnector.DBConnector;
import beans.AdminBean;
import login.ProfileForm;

public class LoginDao {
	static final int COUNT = 5;
	AdminBean admin = new AdminBean();

	// Oneレコード
	public int OneList(String email,String passWord) {
		PreparedStatement st = null; // SQLを送るとき必要 /クラス型のst
		DBConnector db = new DBConnector();// *DBを使用するための準備
		ResultSet rs = null;
		final int COUNT = 5;
		try {
			st = db.connect().prepareStatement("select * from ADMIN where EMAIL = ?");
			st.setString(1, email);
			rs = st.executeQuery();// 実行と結果の戻り//
			if(!rs.next()) {
				db.close(st, rs);
			return -1;
			 }
			if(COUNT <= rs.getInt("LOGIN_MISS_COUNT")) {
				db.close(st, rs);
				return -3;
			}
			byte[] bytes = MessageDigest.getInstance("MD5").digest(passWord.getBytes(StandardCharsets.UTF_8));
			String hash = DatatypeConverter.printHexBinary(bytes);
			if(!hash.equals(rs.getString("PASSWORD"))) {
				doCount(email,rs.getInt("LOGIN_MISS_COUNT"));
				return -2;
			}
			doCountR(email);
			doLoginTime(email);
			admin.setAdminId(rs.getInt("ADMIN_ID"));
			admin.setFirstName(rs.getString("FIRST_NAME"));
			admin.setLastName(rs.getString("LAST_NAME"));
			admin.setUpdateDate(rs.getDate("UPDATE_DATE"));
			admin.setEmail(rs.getString("EMAIL"));
			admin.setPassWord(rs.getString("PASSWORD"));
			admin.setLoginMissCount(rs.getInt("LOGIN_MISS_COUNT"));
			admin.setLoginDate(rs.getTimestamp("LOGIN_DATE"));
		} catch (SQLException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		} finally {
			db.close(st, rs);
		}
		return 1;
	}

	//misscoutリセット,loginDate更新
	public void doCountR(String email) {
		PreparedStatement st = null; // SQLを送るとき必要 /クラス型のst
		DBConnector db = new DBConnector();// *DBを使用するための準備
		ResultSet rs = null;
		try {
			st = db.connect().prepareStatement("update ADMIN set LOGIN_MISS_COUNT = ?,LOGIN_DATE = SYSDATE where EMAIL = ?");
			st.setInt(1, 0);
			st.setString(2, email);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.close(st, rs);
		}
	}

	//misscout　インクリメント処理
	public void doCount(String email , int loginMissCount) {
		PreparedStatement st = null; // SQLを送るとき必要 /クラス型のst
		DBConnector db = new DBConnector();// *DBを使用するための準備
		ResultSet rs = null;
		try {
			st = db.connect().prepareStatement("update ADMIN set LOGIN_MISS_COUNT = ? where EMAIL = ?");
			int i = loginMissCount;
			st.setInt(1, ++i);
			st.setString(2, email);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.close(st, rs);
		}
	}

	public AdminBean getAdmin() {
		return this.admin;
	}


	//新規ユーザー
	public void doInsert(ProfileForm form) {
		PreparedStatement st = null; // SQLを送るとき必要 /クラス型のst
		DBConnector db = new DBConnector();// *DBを使用するための準備
		ResultSet rs = null;
		try {
			st = db.connect().prepareStatement("insert into ADMIN values(?,?,?,SYSDATE,SYSDATE,?,?,0,SYSDATE)");
			byte[] bytes = MessageDigest.getInstance("MD5").digest(form.getPassWord().getBytes(StandardCharsets.UTF_8));
			String hash = DatatypeConverter.printHexBinary(bytes);
			st.setInt(1, doAdminIdMax());
			st.setString(2, form.getFirstName());
			st.setString(3, form.getLastName());
			st.setString(4, form.getEmail());
			st.setString(5, hash);
			st.executeUpdate();
		} catch (SQLException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		} finally {
			db.close(st, rs);
		}
	}
	//Admin_IDの最大値
		public int doAdminIdMax() {
			DBConnector db = new DBConnector();
			PreparedStatement st = null;
			ResultSet rs = null;
			int maxId = 0;
			try {
				st = db.connect().prepareStatement("SELECT MAX(ADMIN_ID) FROM ADMIN");
				rs = st.executeQuery();
				while (rs.next()) {// 次のレコードに下がれればの条件式//
					maxId = rs.getInt("MAX(ADMIN_ID)");
				}
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} finally {
				db.close(st);
			}
			return ++maxId;

		}
	//ユーザーDelete
	public void doDelete(String email) {
		PreparedStatement st = null; // SQLを送るとき必要 /クラス型のst
		DBConnector db = new DBConnector();// *DBを使用するための準備
		ResultSet rs = null;
		try {
			st = db.connect().prepareStatement("delete from ADMIN where EMAIL = ?");
			st.setString(1, email);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.close(st, rs);
		}
	}
	//ユーザーUpdate
	public void doUpdate(ProfileForm form) {
		PreparedStatement st = null; // SQLを送るとき必要 /クラス型のst
		DBConnector db = new DBConnector();// *DBを使用するための準備
		ResultSet rs = null;
		try {
			st = db.connect().prepareStatement("update ADMIN set UPDATE_DATE = SYSDATE,FIRST_NAME = ?, LAST_NAME = ?, EMAIL = ?, PASSWORD = ? where ADMIN_ID = ?");
			byte[] bytes = MessageDigest.getInstance("MD5").digest(form.getPassWord().getBytes(StandardCharsets.UTF_8));
			String hash = DatatypeConverter.printHexBinary(bytes);
			st.setString(1, form.getFirstName());
			st.setString(2, form.getLastName());
			st.setString(3, form.getEmail());
			st.setString(4, hash);
			st.setInt(5, form.getAdminId());
			st.executeUpdate();
		} catch (SQLException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		} finally {
			db.close(st, rs);
		}
	}

	//ログイン日付登録
	public void doLoginTime(String email) {
		PreparedStatement st = null; // SQLを送るとき必要 /クラス型のst
		DBConnector db = new DBConnector();// *DBを使用するための準備
		ResultSet rs = null;
		try {
			st = db.connect().prepareStatement("update ADMIN set LOGIN_DATE = SYSDATE where EMAIL = ?");
			st.setString(1, email);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.close(st, rs);
		}
	}
}